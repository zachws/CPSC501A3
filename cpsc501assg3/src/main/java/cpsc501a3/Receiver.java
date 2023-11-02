package cpsc501a3;
import java.net.ServerSocket; 
import java.net.Socket;
import java.io.InputStream;

public class Receiver {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("RECEIVER: Waiting for connection...");
            Socket socket = serverSocket.accept();
            System.out.println("RECEIVER: Client connected to: " + socket.getInetAddress());
            InputStream inputStream = socket.getInputStream();
            while(true)
            {
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                if(bytesRead == -1)
                {
                    break;
                }
                System.out.println("Received " + bytesRead + " bytes");
                System.out.println("RECEIVER: Message: " + new String(buffer));

            }
        inputStream.close();
        serverSocket.close();
            //System.out.println("Connected to " + socket.getInetAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
