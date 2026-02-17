package Punto3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FunServer {

    private static final int PORT = 35000;

    public static void main(String[] args) {

        System.out.println("Servidor iniciado en el puerto " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) { 
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado");
                handleClient(clientSocket);
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

private static void handleClient(Socket clientSocket) {

    try (
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()))
    ) {

        String inputLine;
        String currentFunction = "cos";

        while ((inputLine = in.readLine()) != null) {

            System.out.println("Mensaje recibido: " + inputLine);

            String response = processInput(inputLine, currentFunction);

            if (response.startsWith("FUNC_CHANGED:")) {
                currentFunction = response.substring(13);
                out.println("Función cambiada a: " + currentFunction);
            } else {
                out.println(response);
            }
        }

        clientSocket.close();
        System.out.println("Cliente desconectado");

    } catch (IOException e) {
        System.err.println("Error manejando cliente: " + e.getMessage());
    }
}

private static String processInput(String inputLine, String currentFunction) {

    if (inputLine.startsWith("fun:")) {
        return handleFunctionChange(inputLine);
    } else {
        return calculateFunction(inputLine, currentFunction);
    }
}

private static String handleFunctionChange(String inputLine) {

    String newFunction = inputLine.substring(4).toLowerCase();

    if (newFunction.equals("sin") ||
        newFunction.equals("cos") ||
        newFunction.equals("tan")) {

        return "FUNC_CHANGED:" + newFunction;

    } else {
        return "Función no válida";
    }
}

private static String calculateFunction(String inputLine, String currentFunction) {

    try {
        double number = Double.parseDouble(inputLine);
        double result = 0;

        switch (currentFunction) {
            case "sin":
                result = Math.sin(number);
                break;
            case "cos":
                result = Math.cos(number);
                break;
            case "tan":
                result = Math.tan(number);
                break;
        }

        return String.valueOf(result);

    } catch (NumberFormatException e) {
        return "Entrada inválida";
    }
}
   
}
