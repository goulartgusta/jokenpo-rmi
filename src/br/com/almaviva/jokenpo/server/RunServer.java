package br.com.almaviva.jokenpo.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RunServer {
	public static void main(String[] args) {
		try {
			ServerImpl servidor = new ServerImpl();
			LocateRegistry.createRegistry(1099);
			Naming.rebind("//192.168.208.73/JokenpoServer", servidor);
			System.out.println("Servidor iniciado!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
