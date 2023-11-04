//import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.lang.model.type.PrimitiveType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
//import static org.junit.Assert.*;

import cpsc501a3.ObjectCreator;
import cpsc501a3.ObjectCreator.ReferenceObject;
import cpsc501a3.ObjectCreator.PrimitiveObject;

public class ObjectCreatorTest {
    
    // @Test
    // public void testCreatePrimitiveObject() {
    //     ObjectCreator creator = new ObjectCreator();
    //     Object obj = creator.createObject('1');
    //     assertTrue(obj instanceof PrimitiveObject);
    // }

    @Test
    public void testCreatePrimitiveObjectValues() {
        ObjectCreator creator = new ObjectCreator();
        ObjectCreator.PrimitiveObject primObjTest = creator.new PrimitiveObject();
       // Object obj = creator.createPrimitiveObject();
       primObjTest.intVar = 45; 
       primObjTest.floatVar = 45.0f;
       primObjTest.doubleVar = 45.0;
       primObjTest.charVar = 'b';
       primObjTest.boolVar = true;
        assertEquals(((ObjectCreator.PrimitiveObject)primObjTest).intVar, 45);
        assertEquals(((ObjectCreator.PrimitiveObject)primObjTest).floatVar, 45.0f, 45.0f);
        assertEquals(((ObjectCreator.PrimitiveObject)primObjTest).doubleVar, 45.0, 45.0);
        assertEquals(((ObjectCreator.PrimitiveObject)primObjTest).charVar, 'b');
        assertEquals(((ObjectCreator.PrimitiveObject)primObjTest).boolVar, true);
    }
    
    @Test
    public void testCreateReferenceObject() {

        ObjectCreator creator = new ObjectCreator();
        //Object obj = creator.createObject('2');
        ObjectCreator.PrimitiveObject primObjTest = creator.new PrimitiveObject();
        // Object obj = creator.createPrimitiveObject();
        ObjectCreator.ReferenceObject refObjTest = creator.new ReferenceObject();

        primObjTest.intVar = 45; 
        primObjTest.floatVar = 45.0f;
        primObjTest.doubleVar = 45.0;
        primObjTest.charVar = 'b';
        primObjTest.boolVar = true;

        refObjTest.refObject = (PrimitiveObject)primObjTest;

        assertTrue(refObjTest instanceof ReferenceObject);
        assertTrue(((ReferenceObject)refObjTest).refObject instanceof PrimitiveObject);
    }
    
    // @Test
    // public void testCreateObjectWithArrayOfPrimitives() {
    //     ObjectCreator creator = new ObjectCreator();
    //     Object obj = creator.createObject('3');
    //     // Add your assertions here
    // }
    
    // @Test
    // public void testCreateObjectWithArrayOfObjectReferences() {
    //     ObjectCreator creator = new ObjectCreator();
    //     Object obj = creator.createObject('4');
    //     // Add your assertions here
    // }
    
    // @Test
    // public void testCreateObjectWithCollection() {
    //     ObjectCreator creator = new ObjectCreator();
    //     Object obj = creator.createObject('5');
    //     // Add your assertions here
    // }
}