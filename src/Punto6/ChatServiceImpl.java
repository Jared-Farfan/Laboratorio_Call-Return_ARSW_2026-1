package Punto6;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatServiceImpl implements ChatService {

    public ChatServiceImpl(int miPuerto, String miNombre) {
        try {
            Registry registry = LocateRegistry.createRegistry(miPuerto);
            
            ChatService chatService = 
                (ChatService) UnicastRemoteObject.exportObject(this, 0);
            registry.rebind(miNombre, chatService);
            
            System.out.println("Chat listo en puerto " + miPuerto + " con nombre " + miNombre);
            
        } catch (Exception e) {
            System.err.println("Error al publicar servicio:");
            e.printStackTrace();
        }
    }
    
    public void recibirMensaje(String mensaje) throws RemoteException {
        System.out.println("Mensaje recibido: " + mensaje);
    }
}