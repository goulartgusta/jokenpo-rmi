package br.com.almaviva.jokenpo.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JokenpoServer extends Remote {
	String jogarJokenpo(String jogadaDoCliente) throws RemoteException;
}
