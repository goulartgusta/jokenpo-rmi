package br.com.almaviva.jokenpo.client;

import br.com.almaviva.jokenpo.shared.JokenpoServer;

import java.rmi.RemoteException;
import java.util.Scanner;

public class JokenpoClient {
	private final JokenpoServer servidor;
	private final String nome;

	public JokenpoClient(JokenpoServer servidor, String nome) {
		this.servidor = servidor;
		this.nome = nome;
	}

    public String jogarJokenpo(int jogada) {
        try {
            return servidor.jogarJokenpo(nome, jogada);
        } catch (RemoteException e) {
            return "Erro ao conectar no servidor: " + e.getMessage();
        }
	}
}
