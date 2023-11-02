package cpsc501a3;

import java.util.Scanner;

public class ObjectCreator {

    public static Object createObjectFromChoice()
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

    private static char getObjectTypeChoice(){
        Scanner userInput = new Scanner(System.in); // Create a Scanner object

        while(true)
        {
            char userChoice = userInput.nextLine().charAt(0); // Read user input
            if(userChoice == '1' || userChoice == '2' || userChoice == '3' || userChoice == '4' || userChoice == '5')
            {
                return userChoice;
            }
            else
            {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                printObjectCreationChoices();
            }
        }

    }

    private static Object createObject(char userChoice)
    {
        Object objectToReturn = null; 
        if(userChoice == 1)
        {
            System.out.println("Creating a simple object with only primitives for instance variables");
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
    
}
