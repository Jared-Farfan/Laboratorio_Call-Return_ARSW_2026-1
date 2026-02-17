package Punto6;

import java.rmi.Remote; 
import java.rmi.RemoteException; 

public interface ChatService extends Remote { 
    public void recibirMensaje(String mensaje) throws RemoteException; 
}
