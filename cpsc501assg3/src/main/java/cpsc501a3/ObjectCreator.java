package cpsc501a3;

import java.util.Scanner;

public class ObjectCreator {

    public Object createObjectFromChoice()
    {
        printObjectCreationChoices();
        return createObject(getObjectTypeChoice());
    }


    private static void printObjectCreationChoices(){
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

    private static void printPrimitiveObjectDetails(PrimitiveObject primObject)
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
        if(userChoice == 1)
        {
            System.out.println("Creating a simple object with only primitives for instance variables");
            objectToReturn = createPrimitiveObject();
            printPrimitiveObjectDetails((PrimitiveObject)objectToReturn);
        }
        else if(userChoice == 2)
        {
            System.out.println("Creating an object with references to other objects");
        }
        else if(userChoice == 3)
        {
            System.out.println("Creating an object with an array of primitives");
        }
        else if(userChoice == 4)
        {
            System.out.println("Creating an object with an array of object references");
        }
        else if(userChoice == 5)
        {
            System.out.println("Creating an object that uses an instance of one of Java's collection classes to refer to several other objects");
        }
        return objectToReturn; 
    }

    private static Object createPrimitiveObject()
    {
        System.out.println("\nCreating a simple object with only primitives for instance variables\n");
        System.out.println("Enter a value for each of the prompted primitive types; pressing enter will default the primitive values");

        PrimitiveObject primitiveObject = new PrimitiveObject();

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
                primitiveObject.intVar = i;
                break;
            }
            else
            {
                try{
                    i = Integer.parseInt(userIntInput);
                    primitiveObject.intVar = i;
                    break;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input. Please enter an integer value.");
                }
            }
        }
        userInput.close();
        userInput = new Scanner(System.in); // Create a Scanner object

        //get double value
        double doubleVar; 
        while(true)
        {
            System.out.println("Enter a double value for Primitive Object double: ");
            String userDoubleInput = userInput.nextLine(); // Read user input
            if(userDoubleInput.equals(""))
            {
                doubleVar = 0.0; 
                primitiveObject.doubleVar = doubleVar;
                break;
            }
            else
            {
                try{
                    doubleVar = Double.parseDouble(userDoubleInput);
                    primitiveObject.doubleVar = doubleVar;
                    break;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input. Please enter a double value.");
                }
            }
        }
        userInput.close();
        userInput = new Scanner(System.in); // Create a Scanner object

        //Get float value
        float floatVar;
        while(true)
        {
            System.out.println("Enter a float value for Primitive Object float: ");
            String userFloatInput = userInput.nextLine(); // Read user input
            if(userFloatInput.equals(""))
            {
                floatVar = 0; 
                primitiveObject.floatVar = floatVar;
                break;
            }
            else
            {
                try{
                    floatVar = Float.parseFloat(userFloatInput);
                    primitiveObject.floatVar = floatVar;
                    break;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input. Please enter a float value.");
                }
            }

        }
        userInput.close();
        userInput = new Scanner(System.in); // Create a Scanner object

        //Get char value
        char charVar; 
        while(true)
        {
            System.out.println("Enter a char value for Primitive Object char: ");
            String userCharInput = userInput.nextLine(); // Read user input

            if(userCharInput.equals(""))
            {
                charVar = 'a'; 
                primitiveObject.charVar = charVar;
                break;
            }
            else if(userCharInput.length() > 1)
            {
                System.out.println("Invalid input. Please enter a single character.");
            }
            else
            {
                charVar = userCharInput.charAt(0);
                primitiveObject.charVar = charVar;
                break;
            }
        }
        userInput.close();
        userInput = new Scanner(System.in); // Create a Scanner object

        //Get boolean value
        boolean boolVar;
        while(true)
        {
            System.out.println("Enter a boolean value for Primitive Object boolean: [true/false] ");
            String userBoolInput = userInput.nextLine(); // Read user input
            if(userBoolInput.equals(""))
            {
                boolVar = false; 
                primitiveObject.boolVar = boolVar;
                break;
            }
            else if(userBoolInput.equals("true"))
            {
                boolVar = true; 
                primitiveObject.boolVar = boolVar;
                break;
            }
            else if(userBoolInput.equals("false"))
            {
                boolVar = false; 
                primitiveObject.boolVar = boolVar;
                break;
            }
            else
            {
                System.out.println("Invalid input. Please enter true or false.");
            }
        }
        userInput.close();


        return primitiveObject; 

    }

    // private static double scanForDouble()
    // {
        
    // }
}
