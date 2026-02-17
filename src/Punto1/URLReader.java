package  Punto1;

import java.net .*;
import java.util.Scanner;

public class URLReader {

    public static void main(String[] args){
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

    public void urlReader(String args) throws Exception {
        URL google = new URL(args);
        System.out.println("Protocol: " + google.getProtocol());
        System.out.println("Authority: " + google.getAuthority());
        System.out.println("Host: " + google.getHost());
        System.out.println("Port: " + google.getPort());
        System.out.println("Path: " + google.getPath());
        System.out.println("Query: " + google.getQuery());
        System.out.println("File: " + google.getFile());
        System.out.println("Ref: " + google.getRef());
    }

}