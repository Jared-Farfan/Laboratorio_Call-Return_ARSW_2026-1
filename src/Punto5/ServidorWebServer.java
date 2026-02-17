package Punto5;

import java.io.*;
import java.net.*;

public class ServidorWebServer {
    
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        OutputStream out = clientSocket.getOutputStream();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.equals("Bye."))
                break;
            System.out.println("file: " + inputLine);
            try {
                File file = new File(inputLine);
                if (file.exists() && file.isFile()) {
                    
                    FileInputStream fis = new FileInputStream(file);
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    
                    long fileSize = file.length();
                    DataOutputStream dos = new DataOutputStream(out);
                    dos.writeLong(fileSize);
                    dos.writeUTF(file.getName());
                    
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                    fis.close();
                    out.flush();
                    System.out.println("Archivo enviado: " + file.getName() + " (" + fileSize + " bytes)");
                } else {
                    DataOutputStream dos = new DataOutputStream(out);
                    dos.writeLong(-1);
                    dos.writeUTF("El archivo no existe o no es un archivo válido");
                }
            } catch (IOException e) {
                DataOutputStream dos = new DataOutputStream(out);
                dos.writeLong(-1);
                dos.writeUTF("No se pudo leer el archivo: " + e.getMessage());
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }

}
