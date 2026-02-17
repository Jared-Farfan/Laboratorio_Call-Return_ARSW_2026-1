package punto5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatagramTimeClient {
public static void main(String[] args) { 
    byte[] sendBuf = new byte[256];

try {
    while(true){
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(6000);
        
        String received = new String("hora no recibida aun") ;

        try{
        byte[] buf = new byte[256];
        InetAddress address = InetAddress.getByName("127.0.0.1");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        received = new String(packet.getData(), 0, packet.getLength());
        }
        catch(SocketTimeoutException ex){
            System.out.println("Servidor no disponible");
        }   

        System.out.println("Date now: " + received);
        try{
            Thread.sleep(5000);
        }
        catch (Exception ex){
            System.out.println("error al esperar");
        }
    }
} 

    catch (SocketException ex) { 
        Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);

    }
    catch (UnknownHostException ex) {
        Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) { 
        Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
