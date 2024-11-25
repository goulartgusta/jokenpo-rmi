package br.com.almaviva.jokenpo.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.com.almaviva.jokenpo.shared.JokenpoServer;

public class JokenpoClient {

	private JokenpoServer server;

	// quando criamos/iniciamos o client, já estabelecemos a conexao com o server
	public void iniciarClient(String serverIP) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(serverIP, 1099); // serverIP deverá ser digitado
		server = (JokenpoServer) registry.lookup("JokenpoServer");
	}

	public String jogarJokenpo(String jogadaDoCliente) throws RemoteException {
		return server.jogarJokenpo(jogadaDoCliente);
	}

	public JokenpoClient() {
	}

}
