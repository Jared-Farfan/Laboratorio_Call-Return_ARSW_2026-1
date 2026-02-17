import Punto1.URLReader;
import Punto2.Browser;

import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Main {


    public static void main(String[] args) {
        // puunto 3.1
        punto1();
        // punto 3.2
        punto2();
        // punto 4.3.1
        punto3();
        

    }

    public static void punto1(){
        URLReader urlReader = new URLReader();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a URL:");
        String url = scanner.nextLine();
        while (url != null && !url.isEmpty()) {
            try {
            urlReader.urlReader(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
            System.out.print("Enter a URL:");
            url = scanner.nextLine();
        }
        scanner.close();
    }

    public static void punto2(){
        Browser browser = new Browser();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a URL:");
        String url = scanner.nextLine();
        try {
            browser.browserHtml(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.close();
    }

    public static void punto3(){
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            echoSocket = new Socket("127.0.0.1", 35000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
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
        String userInput;
        try {
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
            out.close();
            in.close();
            stdIn.close();
            echoSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
