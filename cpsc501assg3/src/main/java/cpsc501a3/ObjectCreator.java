package cpsc501a3;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class ObjectCreator {

    public class PrimitiveObject{

        public int intVar; 
        public float floatVar; 
        public double doubleVar; 
        public char charVar = 'a';
        public boolean boolVar;
    
    }

    public class ReferenceObject{
        public PrimitiveObject refObject; 
        //public ReferenceObject refObject; 
    }

    public class ArrayOfIntegerPrimitives{
        public int[] primitiveIntArray; 
    }

    public class ArrayOfReferencedObjects{
        public PrimitiveObject[] arrayOfReferences; 
    }

    public class ArrayListOfReferencedObjects{
        public ArrayList<PrimitiveObject> arrayListOfReferences;
    }

    public ObjectCreator()
    {
        System.out.println("Object Creator created");
    }

    public Object createObjectFromChoice()
    {
        printObjectCreationChoices();
        return createObject(getObjectTypeChoice());
    }


    public void printObjectCreationChoices(){
        System.out.println("Select an object type to create:");
        System.out.println("1. Simple Object with only primitives for instance variables");
        System.out.println("2. Object with references to other objects");
        System.out.println("3. Object with an array of primitives");
        System.out.println("4. Object with an array of object references");
        System.out.println("5. Object that uses an instance of one of Java's collection classes to refer to several other objects");
    }

    public char getObjectTypeChoice(){
        Scanner userInput = new Scanner(System.in); // Create a Scanner object

        while(true)
        {
            char userChoice = userInput.nextLine().charAt(0); // Read user input
            if(userChoice == '1' || userChoice == '2' || userChoice == '3' || userChoice == '4' || userChoice == '5')
            {
                userInput.close();
                return userChoice;
            }
            else
            {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                printObjectCreationChoices();
            }
        }

    }

    public void printPrimitiveObjectDetails(PrimitiveObject primObject)
    {
        System.out.println("Primitive Object int: " + primObject.intVar);
        System.out.println("Primitive Object double: " + primObject.doubleVar);
        System.out.println("Primitive Object float: " + primObject.floatVar);
        System.out.println("Primitive Object char: " + primObject.charVar);
        System.out.println("Primitive Object boolean: " + primObject.boolVar);
    }

    public Object createObject(char userChoice)
    {
        Object objectToReturn = null; 
        if(userChoice == '1')
        {
            System.out.println("Creating a simple object with only primitives for instance variables");
            objectToReturn = createPrimitiveObject();
            printPrimitiveObjectDetails((PrimitiveObject)objectToReturn);
        }
        else if(userChoice == '2')
        {
            System.out.println("Creating an object with references to other objects");
            objectToReturn = createReferenceObject();
            printPrimitiveObjectDetails((PrimitiveObject)((ReferenceObject)objectToReturn).refObject);
        }
        else if(userChoice == '3')
        {
            System.out.println("Creating an object with an array of primitives");
            Object toPrint = createArrayOfPrimitiveObjects();
            System.out.println("Printing array of primitive objects");
            for(int i = 0; i < ((ArrayOfIntegerPrimitives)toPrint).primitiveIntArray.length; i++)
            {
                System.out.println("Primitive Object int: " + ((ArrayOfIntegerPrimitives)toPrint).primitiveIntArray[i]);
            }

        }
        else if(userChoice == '4')
        {
            System.out.println("Creating an object with an array of object references");
            objectToReturn = createArrayOfReferencedObjects();
            for(int i = 0; i< ((ArrayOfReferencedObjects)objectToReturn).arrayOfReferences.length; i++)
            {
                if(((ArrayOfReferencedObjects)objectToReturn).arrayOfReferences[i] == null)
                {
                    System.out.println("Array of Reference Objects index[" + i + "]: null");
                }
                else
                {
                    System.out.println("Array of Reference Objects index[" + i + "]: ");
                    printPrimitiveObjectDetails((PrimitiveObject)((ArrayOfReferencedObjects)objectToReturn).arrayOfReferences[i]);
                }
            }
        }
        else if(userChoice == '5')
        {
            System.out.println("Creating an object that uses an instance of one of Java's collection classes to refer to several other objects");
            objectToReturn = createArrayListOfReferencedObjects();
            for(int i = 0; i< ((ArrayListOfReferencedObjects)objectToReturn).arrayListOfReferences.size(); i++)
            {
                if(((ArrayListOfReferencedObjects)objectToReturn).arrayListOfReferences.get(i) == null)
                {
                    System.out.println("Array List of Reference Objects index[" + i + "]: null");
                }
                else
                {
                    System.out.println("Array List of Reference Objects index[" + i + "]: ");
                    printPrimitiveObjectDetails((PrimitiveObject)((ArrayListOfReferencedObjects)objectToReturn).arrayListOfReferences.get(i));
                }
            }
        }
        return objectToReturn; 
    }

    public double getUserDoubleInput()
    {
        Scanner userDbInput = new Scanner(System.in); // Create a Scanner object

        //get double value
        double doubleVar; 
        while(true)
        {
            System.out.println("Enter a double value for Primitive Object double: ");
            String userDoubleInput = userDbInput.nextLine(); // Read user input
            if(userDoubleInput.equals(""))
            {
                doubleVar = 0.0; 
              
                break;
            }
            else
            {
                try{
                    doubleVar = Double.parseDouble(userDoubleInput);
                    break;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input. Please enter a double value.");
                }
            }
        }
        //userDbInput.close();
        return doubleVar;
    }

    public Object createReferenceObject()
    {
        ReferenceObject refObject = new ReferenceObject();
        refObject.refObject = (PrimitiveObject)createPrimitiveObject();
        return refObject; 
    }

    public Object createPrimitiveObject()
    {
        System.out.println("\nCreating a simple object with only primitives for instance variables\n");
        System.out.println("Enter a value for each of the prompted primitive types; pressing enter will default the primitive values");

        PrimitiveObject primObject = new PrimitiveObject();

        Scanner userInput;
        userInput = new Scanner(System.in); // Create a Scanner object

        //Get int value
        int i; 
        while(true)
        {
            System.out.println("Enter an integer value for Primitive Object int: ");
            String userIntInput = userInput.nextLine(); // Read user input
            if(userIntInput.equals(""))
            {
                i = 0; 
                primObject.intVar = i;
                break;
            }
            else
            {
                try{
                    i = Integer.parseInt(userIntInput);
                    primObject.intVar = i;
                    break;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input. Please enter an integer value.");
                }
            }
        }
        //userInput.close();

        double userDoubleInput = getUserDoubleInput();
        primObject.doubleVar = userDoubleInput;
        // Scanner userDbInput = new Scanner(System.in); // Create a Scanner object

        // //get double value
        // double doubleVar; 
        // while(true)
        // {
        //     System.out.println("Enter a double value for Primitive Object double: ");
        //     String userDoubleInput = userDbInput.nextLine(); // Read user input
        //     if(userDoubleInput.equals(""))
        //     {
        //         doubleVar = 0.0; 
        //         primObject.doubleVar = doubleVar;
        //         break;
        //     }
        //     else
        //     {
        //         try{
        //             doubleVar = Double.parseDouble(userDoubleInput);
        //             primObject.doubleVar = doubleVar;
        //             break;
        //         }
        //         catch(Exception e)
        //         {
        //             System.out.println("Invalid input. Please enter a double value.");
        //         }
        //     }
        // }
        // userDbInput.close();
        Scanner userFlInput = new Scanner(System.in); // Create a Scanner object

        //Get float value
        float floatVar;
        while(true)
        {
            System.out.println("Enter a float value for Primitive Object float: ");
            String userFloatInput = userFlInput.nextLine(); // Read user input
            if(userFloatInput.equals(""))
            {
                floatVar = 0; 
                primObject.floatVar = floatVar;
                break;
            }
            else
            {
                try{
                    floatVar = Float.parseFloat(userFloatInput);
                    primObject.floatVar = floatVar;
                    break;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input. Please enter a float value.");
                }
            }

        }
        //userFlInput.close();
        Scanner userChInput = new Scanner(System.in); // Create a Scanner object

        //Get char value
        char charVar; 
        while(true)
        {
            System.out.println("Enter a char value for Primitive Object char: ");
            String userCharInput = userChInput.nextLine(); // Read user input

            if(userCharInput.equals(""))
            {
                charVar = 'a'; 
                primObject.charVar = charVar;
                break;
            }
            else if(userCharInput.length() > 1)
            {
                System.out.println("Invalid input. Please enter a single character.");
            }
            else
            {
                charVar = userCharInput.charAt(0);
                primObject.charVar = charVar;
                break;
            }
        }
        //userChInput.close();
        Scanner userBInput = new Scanner(System.in); // Create a Scanner object

        //Get boolean value
        boolean boolVar;
        while(true)
        {
            System.out.println("Enter a boolean value for Primitive Object boolean: [true/false] ");
            String userBoolInput = userBInput.nextLine(); // Read user input
            if(userBoolInput.equals(""))
            {
                boolVar = false; 
                primObject.boolVar = boolVar;
                break;
            }
            else if(userBoolInput.equals("true"))
            {
                boolVar = true; 
                primObject.boolVar = boolVar;
                break;
            }
            else if(userBoolInput.equals("false"))
            {
                boolVar = false; 
                primObject.boolVar = boolVar;
                break;
            }
            else
            {
                System.out.println("Invalid input. Please enter true or false.");
            }
        }
        //userBInput.close();


        return primObject; 

    }

    public Object createArrayOfPrimitiveObjects()
    {

        System.out.println("Creating an Array of Primitive Objects: type int; other primitive types in future releases");
        System.out.println("Please enter size of Array to be created: ");
        Scanner arraySizeScan = new Scanner(System.in);
        int arrayLength = 0; 
        while(true){
            if(arraySizeScan.hasNextInt())
            {
                arrayLength = arraySizeScan.nextInt();
                if(arrayLength >=1)
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid length (must be >= 1) please try again");
                    arraySizeScan.nextLine();//purge scanner buffer
                }
            }
        }
        Object arrayOfPrimitiveObjects = new ArrayOfIntegerPrimitives();
        ((ArrayOfIntegerPrimitives)arrayOfPrimitiveObjects).primitiveIntArray = new int[arrayLength];
        Scanner primitiveObjIntegerVals = new Scanner(System.in);


        for(int i = 0; i < arrayLength; i++){
        int intToAdd;
        while(true)
        {
            System.out.println("Enter an integer value for Primitive Object int: ");
            String userIntInput = primitiveObjIntegerVals.nextLine(); // Read user input
            if(userIntInput.equals(""))
            {
                intToAdd = 0; 
                //primObject.intVar = i;
                ((ArrayOfIntegerPrimitives) arrayOfPrimitiveObjects ).primitiveIntArray[i] = intToAdd;
                break;
            }
            else
            {
                try{
                    intToAdd = Integer.parseInt(userIntInput);
                    //primObject.intVar = i;
                    ((ArrayOfIntegerPrimitives) arrayOfPrimitiveObjects ).primitiveIntArray[i] = intToAdd;
                    break;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input. Please enter an integer value.");
                }
            }
        }
    }


        return arrayOfPrimitiveObjects;
    }

    public Object createArrayOfReferencedObjects()
    {
        System.out.println("Creating an Array of Reference Objects: ");
        System.out.println("Please enter size of Array to be created: ");
        Scanner arraySizeScan = new Scanner(System.in);
        int arrayLength = 0; 
        while(true){
            if(arraySizeScan.hasNextInt())
            {
                arrayLength = arraySizeScan.nextInt();
                if(arrayLength >=1)
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid length (must be >= 1) please try again");
                    arraySizeScan.nextLine();//purge scanner buffer
                }
            }
        }
        Object arrayOfReferenceObjects = new ArrayOfReferencedObjects();
        ((ArrayOfReferencedObjects)arrayOfReferenceObjects).arrayOfReferences = new PrimitiveObject[arrayLength];
        Scanner referenceObjValsScanner = new Scanner(System.in);

        for(int i = 0; i<arrayLength; i++){
            System.out.println("Please make a choice\n1.Create a new Primitive Object\n2.Create null object reference");
            String userInput = referenceObjValsScanner.nextLine();
            if(userInput.equals("1"))
            {
                ((ArrayOfReferencedObjects)arrayOfReferenceObjects).arrayOfReferences[i] = (PrimitiveObject)createPrimitiveObject();
            }
            else if(userInput.equals("2"))
            {
                ((ArrayOfReferencedObjects)arrayOfReferenceObjects).arrayOfReferences[i] = null;
            }
            else
            {
                System.out.println("Invalid choice. Please enter 1 or 2");
                i--;
            }
            //((ArrayOfReferencedObjects)arrayOfReferenceObjects).arrayOfReferences[i] = (PrimitiveObject)createPrimitiveObject();
        }
        return arrayOfReferenceObjects;
    }

    public Object createArrayListOfReferencedObjects(){
        ArrayListOfReferencedObjects arrayListOfReferences = new ArrayListOfReferencedObjects();
        arrayListOfReferences.arrayListOfReferences = new ArrayList<PrimitiveObject>();
        Scanner userInputScanner = new Scanner(System.in);
        boolean controlArrayCreation = true;
        while(controlArrayCreation)
        {
            System.out.println("Please make a choice\n1.Create a new Primitive Object\n2.Create null object reference\n3.Exit");
            String userInput = userInputScanner.nextLine();
            if(userInput.equals("1"))
            {
                arrayListOfReferences.arrayListOfReferences.add((PrimitiveObject)createPrimitiveObject());
                //((ArrayOfReferencedObjects)arrayOfReferenceObjects).arrayOfReferences[i] = (PrimitiveObject)createPrimitiveObject();
            }
            else if(userInput.equals("2"))
            {
                arrayListOfReferences.arrayListOfReferences.add(null);
                //((ArrayOfReferencedObjects)arrayOfReferenceObjects).arrayOfReferences[i] = null;
            }
            else if(userInput.equals("3"))
            {
                controlArrayCreation = false; 
                break; //exit 
            }
            else
            {
                System.out.println("Invalid choice. Please enter 1, 2, or 3");
            }
        }
        return arrayListOfReferences; 
    }
    // private static double scanForDouble()
    // {
        
    // }
}
