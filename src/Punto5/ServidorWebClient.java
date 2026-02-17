package Punto5;

import java.io.*;
import java.net.*;

public class ServidorWebClient {
    
    public static void main(String[] args) throws IOException {
        Socket echoSocket = null;
        PrintWriter out = null;
        InputStream in = null;
        try {
            echoSocket = new Socket("127.0.0.1", 35000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = echoSocket.getInputStream();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host!.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: localhost.");
            System.exit(1);
        }
        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.print("Ingrese la ruta del archivo (o 'Bye.' para salir): ");
        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            if (userInput.equals("Bye.")) {
                out.println(userInput);
                break;
            }
            out.println(userInput);
            
            DataInputStream dis = new DataInputStream(in);
            long fileSize = dis.readLong();
            String fileName = dis.readUTF();
            
            if (fileSize == -1) {
                System.out.println("Error del servidor: " + fileName);
            } else {
                File downloadDir = new File("downloads");
                if (!downloadDir.exists()) {
                    downloadDir.mkdir();
                }
                
                FileOutputStream fos = new FileOutputStream(new File(downloadDir, fileName));
                byte[] buffer = new byte[4096];
                long totalRead = 0;
                
                while (totalRead < fileSize) {
                    int bytesToRead = (int) Math.min(buffer.length, fileSize - totalRead);
                    int bytesRead = in.read(buffer, 0, bytesToRead);
                    if (bytesRead == -1) break;
                    fos.write(buffer, 0, bytesRead);
                    totalRead += bytesRead;
                }
                fos.close();
                System.out.println("Archivo recibido y guardado: downloads/" + fileName + " (" + fileSize + " bytes)");
            }
            
            System.out.print("Ingrese la ruta del archivo (o 'Bye.' para salir): ");
        }
        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}
