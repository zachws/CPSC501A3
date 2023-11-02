package cpsc501a3;
import java.io.OutputStream;
import java.net.Socket;
import java.net.InetAddress; 

public class Sender {

    public static void main(String[] args) {

        try{
            Socket socket = new Socket(InetAddress.getByName("localhost"), 5000);
            OutputStream outputStream = socket.getOutputStream();
            String message = "Hello World!";
            byte[] buffer = message.getBytes();
            outputStream.write(buffer);
            System.out.println("Sent " + buffer.length + " bytes");
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
