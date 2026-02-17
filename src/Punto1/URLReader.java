package Punto1;

import java.net .*;

public class URLReader {

    // public static void Ur(String[] args) throws Exception {
    //     URL google = new URL("http://www.google.com/");
    //     try (BufferedReader reader = new BufferedReader(new InputStreamReader(google.openStream()))) {
    //         String inputLine = null;
    //         while ((inputLine = reader.readLine()) != null) {
    //             System.out.println(inputLine);
    //         }
    //     } catch (IOException x) {
    //         System.err.println(x);
    //     }
    // }

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