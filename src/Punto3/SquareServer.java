package Punto3;

import java.io.*;
import java.net.*;

public class SquareServer {
    
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
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.equals("Bye."))
                
                break;
            System.out.println("Mensaje: " + inputLine);
            try {
                double number = Double.parseDouble(inputLine);
                double square = number * number;
                outputLine = "Respuesta: " + square;
            } catch (NumberFormatException e) {
                outputLine = "Respuesta: Error - ingrese un número válido";
            }
            out.println(outputLine);
            
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
