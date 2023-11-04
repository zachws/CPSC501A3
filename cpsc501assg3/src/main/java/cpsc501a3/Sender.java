package cpsc501a3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.InetAddress; 
import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Sender {

    public class ServerInformation
    {
        String xmlFileName, serverHostName;
        int portNumber; 
    }

    public void main(String[] args) {


        String xmlFileName, serverHostName;
        int defaultPort = 6969; 
        xmlFileName = "serialized.xml";
        serverHostName = "localhost";
        ServerInformation serverInfo = new ServerInformation();

        if(args.length == 0)
        {
            System.out.println("If you meant to run the program with default network settings continue with defaults, otherwise ctrl-c to restart program and run with the following arguments: <serverHostName> <portNumber>");
            serverInfo.xmlFileName = xmlFileName;
            serverInfo.serverHostName = serverHostName;
            serverInfo.portNumber = defaultPort;

        }
        else if(args.length == 2)
        {
            String newServerHostName = args[0];
            int newPort = Integer.parseInt(args[1]);

            serverInfo.xmlFileName = xmlFileName;
            serverInfo.serverHostName = newServerHostName;
            serverInfo.portNumber = newPort;
        }
        else 
        {
            System.out.println("Invalid number of arguments, please try again");
            System.exit(0);
        }

        handleCreationAndSerialization(serverInfo);
        // try{
        //     Socket socket = new Socket(InetAddress.getByName("localhost"), 5000);
        //     OutputStream outputStream = socket.getOutputStream();
        //     String message = "Hello World!";
        //     byte[] buffer = message.getBytes();
        //     outputStream.write(buffer);
        //     System.out.println("Sent " + buffer.length + " bytes");
        //     outputStream.close();
        //     socket.close();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

    }

    private Object handleObjectCreation()
    {
        ObjectCreator testCreator = new ObjectCreator();
        Object objectToCreate = null; 

        //char choice = '5'; 
        //testCreator.createObject(choice);
        while(true)
        {
            System.out.println("1. Create object from scratch");
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                int selection = Integer.parseInt(in.readLine());
                // if(selection == 2){
                //     System.out.println("Exiting program...");
                //     System.exit(0);

                // } 
                if(selection == 1){
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
        return objectToCreate; 
    }

    private void sendObjectToReceiver(Object objectToSend, Document docToWriteTo, ServerInformation serverInfo)
    {
        try{
            Socket socket = new Socket(InetAddress.getByName(serverInfo.serverHostName), serverInfo.portNumber);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(outputStream);
            outputStream.flush();
            outputStream.close();
            socket.close();
            //String message = "Hello World!";
            //byte[] buffer = message.getBytes();
            // XMLOutputter xmlOutput = new XMLOutputter();
            // xmlOutput.setFormat(Format.getPrettyFormat());
            // xmlOutput.output(docToWriteTo, outputStream);
            // //outputStream.write(buffer);
            // //System.out.println("Sent " + buffer.length + " bytes");
            // outputStream.close();
            // socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleCreationAndSerialization(ServerInformation serverInfo)
    {
        ObjectCreator testCreator = new ObjectCreator();
        while(true)
        {
            System.out.println("1. Create object from scratch and Serialize it, 2.Exit Program");
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                int selection = Integer.parseInt(in.readLine());
                if(selection == 2){
                    System.out.println("Exiting program...");
                    System.exit(0);
                } 
                else if(selection == 1){
                    Object objectToCreate = testCreator.createObjectFromChoice(); 
                    Serializer serializer = new Serializer();
                    Document docToWriteTo = serializer.serialize(objectToCreate);
                    //break; 

                    try{
                        XMLOutputter xmlOutput = new XMLOutputter();
                        xmlOutput.setFormat(Format.getPrettyFormat());
                        xmlOutput.output(docToWriteTo, new java.io.FileWriter("serialized.xml"));

                    }catch(IOException i)
                    {
                        i.printStackTrace();
                    }

                    sendObjectToReceiver(objectToCreate, docToWriteTo, serverInfo);
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


        // Document docToWriteTo = Serializer.serialize(objectToCreate);
        // File xmlFile = createXMLFile(docToWriteTo);
        // sendXMLFile(xmlFile);
    }
    
}
