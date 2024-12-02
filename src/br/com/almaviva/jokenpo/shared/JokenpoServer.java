package br.com.almaviva.jokenpo.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JokenpoServer extends Remote {
    void registrarCliente(String nome) throws RemoteException;

    String jogarJokenpo(String nome, int jogada) throws RemoteException;
}
