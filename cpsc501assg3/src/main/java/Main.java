import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import cpsc501a3.ObjectCreator;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");

        Object objectCreated = objectCreationHandler();
        

        //char choice2 = '2';
        //testCreator.createObject(choice2);
    }

    private static Object objectCreationHandler()
    {
        ObjectCreator testCreator = new ObjectCreator();
        Object objectToCreate = null; 

        //char choice = '5'; 
        //testCreator.createObject(choice);
        while(true)
        {
            System.out.println("1. Create object from scratch, 2.Exit Program");
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                int selection = Integer.parseInt(in.readLine());
                if(selection == 2){
                    System.out.println("Exiting program...");
                    System.exit(0);

                } 
                else if(selection == 1){
                    objectToCreate = testCreator.createObjectFromChoice(); 
                    break; 
                }
                else{
                    System.out.println("Invalid input, please try again");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
		//int selectionInt = Integer.parseInt(selection);
        return objectToCreate; 
    }

    public static File createXMLFile(Document docToWriteTo){
        File xmlFile = new File("test.xml");
    }
}