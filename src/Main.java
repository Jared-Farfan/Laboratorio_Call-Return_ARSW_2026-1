
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        // puunto 3.1
        punto1();
        // punto 3.2
        punto2();
        

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
    
}
