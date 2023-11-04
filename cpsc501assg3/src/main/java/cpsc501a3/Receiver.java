package cpsc501a3;
import java.net.ServerSocket; 
import java.net.Socket;
import java.io.InputStream;
import java.io.ObjectInputStream;
import org.jdom2.*;

public class Receiver {

    public void main(String[] args) {


        if(args.length == 0)
        {
            int newPort = 6969;
            System.out.println("No specific port requested by user, using default port: " + newPort);
            handleReceiving(newPort);
            //System.out.println("If you wish to use a different port, please restart the program and enter the port number as an argument");
        }
        else if (args.length == 1)
        {
            int newPort = Integer.parseInt(args[0]);
            System.out.println("Using port: " + newPort);
            handleReceiving(newPort);
        }
        else
        {
            System.out.println("Invalid number of arguments, please try again");
            System.exit(0);
        }


        // try {
        //     ServerSocket serverSocket = new ServerSocket(5000);
        //     System.out.println("RECEIVER: Waiting for connection...");
        //     Socket socket = serverSocket.accept();
        //     System.out.println("RECEIVER: Client connected to: " + socket.getInetAddress());
        //     InputStream inputStream = socket.getInputStream();
        //     while(true)
        //     {
        //         byte[] buffer = new byte[1024];
        //         int bytesRead = inputStream.read(buffer);
        //         if(bytesRead == -1)
        //         {
        //             break;
        //         }
        //         System.out.println("Received " + bytesRead + " bytes");
        //         System.out.println("RECEIVER: Message: " + new String(buffer));

        //     }
        // inputStream.close();
        // serverSocket.close();
        //     //System.out.println("Connected to " + socket.getInetAddress());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }


    }

    private void handleReceiving(int portNumber)
    {
        try {
            ServerSocket receiveSocket = new ServerSocket(portNumber);
            while(true)
            {
                Socket acceptedSocket = receiveSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(acceptedSocket.getInputStream());
                Document docToDeserialize = (Document) objectInputStream.readObject();
                //Object objectDeserialized = new Deserializer.deserialize(docToDeserialize)
                //runInspection("deserialized.txt", objectDeserialized, true);
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
