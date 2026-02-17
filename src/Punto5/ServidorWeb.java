package punto5;

import java.io.*;
import java.net.*;

public class ServidorWeb {
    
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
            System.out.println("Listo para recibir ...");
            while (true) {
                clientSocket = serverSocket.accept();
                fileView(clientSocket);
            }
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        clientSocket.close();
        serverSocket.close();
    }

    public static void fileView(Socket clientSocket) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String requestLine = in.readLine();
        System.out.println(requestLine);

        String[] parts = requestLine.split(" ");
        String fileName = parts[1];

        fileName = fileName.substring(1);

        File file = new File("src/Punto5/files/"+fileName);

        if (file.exists()) {

            FileInputStream fis = new FileInputStream(file);
            byte[] content = fis.readAllBytes();

            String contentType = "application/octet-stream";
            if (fileName.endsWith(".html")) {
                contentType = "text/html; charset=UTF-8";
            } else if (fileName.endsWith(".png")) {
                contentType = "image/png";
            } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                contentType = "image/jpeg";
            } else if (fileName.endsWith(".css")) {
                contentType = "text/css";
            } else if (fileName.endsWith(".js")) {
                contentType = "application/javascript";
            }

            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: " + contentType);
            out.println("Content-Length: " + content.length);
            out.println();
            out.flush();

            clientSocket.getOutputStream().write(content);
            clientSocket.getOutputStream().flush();

            fis.close();

        } else {

            String notFound = "<h1>404 Not Found</h1>";
            out.println("HTTP/1.1 404 Not Found");
            out.println("Content-Type: text/html");
            out.println("Content-Length: " + notFound.length());
            out.println();
            out.println(notFound);
        }
    }
}
