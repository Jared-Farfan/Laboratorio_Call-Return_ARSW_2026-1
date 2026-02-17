package Punto6;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ChatApp {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("puerto para publicar tu servicio: ");
        int miPuerto = sc.nextInt();
        sc.nextLine();
        
        System.out.print("nombre para tu servicio: ");
        String miNombre = sc.nextLine();
        
        ChatServiceImpl miChat = new ChatServiceImpl(miPuerto, miNombre);
        
        System.out.print("IP del chat remoto: ");
        String ipRemota = sc.nextLine();
        
        System.out.print("puerto del chat remoto: ");
        int puertoRemoto = sc.nextInt();
        sc.nextLine();
        
        System.out.print("nombre del servicio remoto: ");
        String nombreRemoto = sc.nextLine();
        
        try {
            Registry registry = LocateRegistry.getRegistry(ipRemota, puertoRemoto);
            ChatService chatRemoto = (ChatService) registry.lookup(nombreRemoto);
            
            System.out.println("conectado escribe tus mensajes");
            
            while (true) {
                String mensaje = sc.nextLine();
                if (mensaje.equals("salir")) {
                    break;
                }
                chatRemoto.recibirMensaje(mensaje);
            }
            
        } catch (Exception e) {
            System.err.println("problema:");
            e.printStackTrace();
        }
        
        sc.close();
    }
}
