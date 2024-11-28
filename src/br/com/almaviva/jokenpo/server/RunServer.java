package br.com.almaviva.jokenpo.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.com.almaviva.jokenpo.shared.JokenpoServer;

public class RunServer {
	public static void main(String[] args) throws RemoteException, AlreadyBoundException  {
		JokenpoServer server = new ServerImpl();
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.bind("JokenpoServer", server); 
		System.out.println("Server iniciado!");
	}
}
