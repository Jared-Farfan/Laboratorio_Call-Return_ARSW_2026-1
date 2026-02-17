package Punto3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FunClient {

    private static final String HOST = "localhost";
    private static final int PORT = 35000;

    public static void main(String[] args) {

        try (
            Socket socket = new Socket(HOST, PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            BufferedReader console = new BufferedReader(
                    new InputStreamReader(System.in))
        ) {

            System.out.println("Conectado al servidor.");
            System.out.println("Función actual es coseno");
            System.out.println("Escribe números o 'fun:sin', 'fun:cos', 'fun:tan'");
        

            String userInput;

            while ((userInput = console.readLine()) != null) {

                out.println(userInput);

                String response = in.readLine();
                System.out.println("Respuesta del servidor: " + response);
            }

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}

