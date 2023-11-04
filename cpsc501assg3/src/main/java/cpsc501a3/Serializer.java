package cpsc501a3;
import org.jdom2.*;
import java.lang.reflect.*; 
import java.util.IdentityHashMap;
public class Serializer {

    public Document serialize(Object objectToSerialize)
    {
        IdentityHashMap<Object, Integer> map = new IdentityHashMap<Object, Integer>();
        //Element root = new Element("serialized");
        Element root = createNewElement(9);
        Document docToHoldSerializedObjs = new Document(root);
        return serializeProvidedObject(objectToSerialize, docToHoldSerializedObjs, map);
    }

    private Document serializeProvidedObject(Object objectToSerialize, Document documentToSerializeTo, IdentityHashMap<Object, Integer> serializeHashMap)
    {
        int objectMapIndex = serializeHashMap.size();
        serializeHashMap.put(objectToSerialize, objectMapIndex);
        //Element objectElement = new Element("object");
        Element objectElement = createNewElement(4);
        objectElement.setAttribute("id", Integer.toString(objectMapIndex));
        documentToSerializeTo.getRootElement().addContent(objectElement);

        if(objectToSerialize == null)
        {
            //Element nullElement = new Element("null");
            Element nullElement = createNewElement(8);
            objectElement.addContent(nullElement);
        }
        else //object is not null so we should handle all the cases 
        {
            int fieldLength = objectToSerialize.getClass().getDeclaredFields().length;
            objectElement.setAttribute("class", objectToSerialize.getClass().getName());

            if(objectToSerialize.getClass().isArray())
            {
                objectElement.setAttribute("length", Integer.toString(Array.getLength(objectToSerialize)));
                //objectElement.addContent(serializeArrays(objectToSerialize, documentToSerializeTo, serializeHashMap));  
                for (Element arrElementToAddToObjectElement : serializeArrays(objectToSerialize, documentToSerializeTo, serializeHashMap))
                {
                    objectElement.addContent(arrElementToAddToObjectElement);
                }
            }
            else if(objectToSerialize.getClass().isPrimitive())
            {
                objectElement.addContent(serializeObject(objectToSerialize.getClass(), objectToSerialize, documentToSerializeTo, serializeHashMap));
            }
            else//object is a field 
            {
                for(Field fieldToAdd: objectToSerialize.getClass().getDeclaredFields())
                {
                    fieldToAdd.setAccessible(true);
                    Element fieldElement = createNewElement(6);
                    fieldElement.setAttribute("name", fieldToAdd.getName());
                    fieldElement.setAttribute("declaringclass", fieldToAdd.getDeclaringClass().getName());
                    Object objectForFields = null; 

                    try{
                        objectForFields = fieldToAdd.get(objectToSerialize);
                    }catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }

                    Element singleFieldInformation = serializeObject(fieldToAdd.getType(),objectForFields, documentToSerializeTo, serializeHashMap);
                    fieldElement.addContent(singleFieldInformation);
                    objectElement.addContent(fieldElement);
                    //fieldElement.addContent(serializeObject(fieldToAdd.getType(), objectForFields, documentToSerializeTo, serializeHashMap));
                    //objectElement.addContent(fieldElement);
                }
            }

        }

        return documentToSerializeTo;

    }

    // private Element[] serializeFields(Object objectToSerialize, Document docToSerializeTo, IdentityHashMap<Object, Integer> serializeHashMap)
    // {
    //     Element[] fieldElementContainer = new Element[objectToSerialize.getClass().getDeclaredFields().length];
    //     Field[] fieldArray = objectToSerialize.getClass().getDeclaredFields();
    //     for(int i = 0; i < fieldElementContainer.length; i++)
    //     {
    //        //fieldElementContainer[i] = serializeField(fieldArray[i], objectToSerialize, docToSerializeTo, serializeHashMap);
    //        Field currentField = fieldArray[i];
    //        currentField.setAccessible(true);
    //        Element fieldElement = createNewElement(6);
    //        fieldElement.setAttribute("name", currentField.getName());
    //        fieldElement.setAttribute("declaringclass", currentField.getDeclaringClass().getName());

    //        Object objectForFields = null; 
           

    //        try{
    //         objectForFields = currentField.get(objectToSerialize);
    //        }catch(Exception exception)
    //        {
    //            exception.printStackTrace();
    //        }

    //        Element fieldElementToAdd = serializeObject(currentField.getType(), objectToSerialize, docToSerializeTo, serializeHashMap);
    //     }
    //     return fieldElementContainer; 
    // }

    // //TODO: Actually implement this, copilot starter code is not that great here I think 
    // private Element serializePrimitives(Object primitiveObject, Document docToSerializeTo, IdentityHashMap<Object, Integer> serializeHashMap)
    // {
    //     Element primitiveElement = createNewElement(5);
    //     primitiveElement.setAttribute("id", Integer.toString(serializeHashMap.size()));
    //     primitiveElement.setAttribute("class", primitiveObject.getClass().getName());
    //     primitiveElement.setAttribute("value", primitiveObject.toString());
    //     return primitiveElement; 
    // }


    // private Element serializeReferences(Object referenceObject, Document docToSerializeTo, IdentityHashMap<Object, Integer> serializeHashMap)
    // {
    //     // Element referenceElement = new Element("reference");
    //     // referenceElement.setAttribute("id", Integer.toString(serializeHashMap.size()));
    //     // referenceElement.setAttribute("class", referenceObject.getClass().getName());
    //     // referenceElement.setAttribute("value", referenceObject.toString());
    //     // return referenceElement; 
    //     Class arrayClass = referenceObject.getClass(); 
    //     Class arrayComponentType = getComponentType(arrayClass);

    //     Element referenceElement = createNewElement(2); 


    //     return null; 
    // }

    private Element[] serializeArrays(Object arrayObject, Document docToSerializeTo, IdentityHashMap<Object, Integer> serializeHashMap)
    {
        //Create container to hold array Elements to return 
        Element[] arrayElementContainer = new Element[Array.getLength(arrayObject)]; 
        Class arrayClass = arrayObject.getClass(); 
        Class arrayComponentType = getComponentType(arrayClass);
        Element singleArrayElement = null; 

        for(int i = 0; i < arrayElementContainer.length; i++)
        {
            singleArrayElement = null; 
            Object arrayElementObject = Array.get(arrayObject, i);

            if(arrayComponentType.isPrimitive())
            {
               // arrayElementContainer[i] = new Element("null");
               arrayElementContainer[i] = serializeObject(arrayComponentType, arrayElementObject, docToSerializeTo, serializeHashMap);
            }
            else 
            {
                arrayElementContainer[i] = serializeObject(arrayComponentType,arrayElementObject, docToSerializeTo, serializeHashMap);
            }

        }
        return arrayElementContainer; 
    }

    private Element serializeObject(Class<?> componentType, Object objToSerialize, Document docToSerializeTo, IdentityHashMap<Object, Integer> serializeHashMap)
    {
        if(isNull(objToSerialize))
        {
            return createNewElement(8);
        }
        else if(componentType.isPrimitive())
        {
            Element valElement = createNewElement(7);
            return valElement.setText(objToSerialize.toString());
        }
        else
        {
            Element refElement = createNewElement(2);
            //if the child key isn't in the map we should probably handle it and add it and traverse it recursively  
            if(!serializeHashMap.containsKey(objToSerialize))
            {
                int hashmapSize = serializeHashMap.size();
                String mapSizeString = Integer.toString(hashmapSize);
                refElement.setText(mapSizeString);
                serializeProvidedObject(objToSerialize, docToSerializeTo, serializeHashMap);
            }
            else
            {
                refElement.setText(serializeHashMap.get(objToSerialize).toString());
                //return serializeProvidedObject(objToSerialize, docToSerializeTo, serializeHashMap);
            }
            return refElement; 
        }
        //return refElement; 
    }


    private Class getComponentType(Object arrayObject)
    {
        return arrayObject.getClass().getComponentType();

    }

    private Element createNewElement(int elementChoice)
    {
        Element newElement = null; 
        switch(elementChoice)
        {
            case 1: 
                newElement = new Element("class");
                break; 
            case 2: 
                newElement = new Element("reference");
                break; 
            case 3: 
                newElement = new Element("array");
                break; 
            case 4: 
                newElement = new Element("object");
                break; 
            case 5: 
                newElement = new Element("primitive");
                break; 
            case 6: 
                newElement = new Element("field");
                break;
            case 7:
                newElement = new Element("value");
                break;
            case 8: 
                newElement = new Element("null");
                break;
            case 9: 
                newElement = new Element("serialized");
                break;
            default: 
                newElement = new Element("null");
                break; 
        }
        return newElement; 

    }



    // private Element[] serializeArrayOfPrimitives()
    // {
    //     return null; 
    // }

    // private Element serializeBoolean()
    // {
    //     return null; 
    // }
    
    private boolean isNull(Object objectToCheck)
    {
        return objectToCheck == null;
    }
}
