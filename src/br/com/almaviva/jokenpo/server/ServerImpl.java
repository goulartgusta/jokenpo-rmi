package br.com.almaviva.jokenpo.server;

import br.com.almaviva.jokenpo.shared.JokenpoServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements JokenpoServer {
	private String jogador1;
	private String jogador2;
	private int jogadaJogador1;
	private int jogadaJogador2; 
	private int totalJogadores;
	private String resultadoFinal; 

	public ServerImpl() throws RemoteException {
		super();
		jogador1 = null;
		jogador2 = null;
		jogadaJogador1 = 0;
		jogadaJogador2 = 0;
		totalJogadores = 0;
		resultadoFinal = null;
	}

	@Override
	public synchronized void registrarCliente(String nome) throws RemoteException {
		if (totalJogadores == 0) {
			jogador1 = nome;
			totalJogadores++;
			System.out.println("Jogador 1 registrado: " + nome);
		} else if (totalJogadores == 1) {
			jogador2 = nome;
			totalJogadores++;
			System.out.println("Jogador 2 registrado: " + nome);
			System.out.println("Dois jogadores conectados. O jogo pode começar!");
		} else {
			throw new RemoteException("O jogo já está cheio. Tente novamente mais tarde.");
		}
	}

	@Override
	public synchronized String jogarJokenpo(String nome, int jogada) throws RemoteException {

		validarInicioDoJogo();

		registrarJogada(nome, jogada);

		if (ambosJogadoresJogaram()) {
			return finalizarRodada();
		}

		return esperarOutroJogador();
	}

	private void validarInicioDoJogo() throws RemoteException {
		if (totalJogadores < 2) {
			throw new RemoteException("Aguardando o segundo jogador entrar.");
		}
	}

	private void registrarJogada(String nome, int jogada) throws RemoteException {
		if (nome.equals(jogador1)) {
			if (jogadaJogador1 > 0) {
				throw new RemoteException("Você já fez sua jogada nesta rodada.");
			}
			jogadaJogador1 = jogada;
			System.out.println("Jogador 1 (" + jogador1 + ") jogou (" + jogadaJogador1 + ").");
		} else if (nome.equals(jogador2)) {
			if (jogadaJogador2 > 0) {
				throw new RemoteException("Você já fez sua jogada nesta rodada.");
			}
			jogadaJogador2 = jogada;
			System.out.println("Jogador 2 (" + jogador2 + ") jogou (" + jogadaJogador2 + ").");
		} else {
			throw new RemoteException("Jogador não encontrado.");
		}
	}

	private boolean ambosJogadoresJogaram() {
		return jogadaJogador1 > 0 && jogadaJogador2 > 0;
	}

	private String finalizarRodada() {
		String resultado = validarJogadas(jogadaJogador1, jogadaJogador2);
		resetarJogadas();
		resultadoFinal = resultado;
		notifyAll(); 
		return resultado;
	}

	private String esperarOutroJogador() throws RemoteException {
		try {
			wait();
			return resultadoFinal;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RemoteException("Erro ao esperar pela jogada do outro jogador.", e);
		}
	}

	private String validarJogadas(int jogada1, int jogada2) {
		if (jogada1 == jogada2)
			return "Empate!";

		if ((jogada1 == 1 && jogada2 == 3) || (jogada1 == 2 && jogada2 == 1) || (jogada1 == 3 && jogada2 == 2)) {
			return jogador1 + "venceu!";

		} else if ((jogada2 == 1 && jogada1 == 3) || (jogada2 == 2 && jogada1 == 1) || (jogada2 == 3 && jogada1 == 2)) {
			return jogador2 + "venceu!";

		} else {
			return "Jogada inválida";
		}
	}

	private void resetarJogadas() {
		jogadaJogador1 = 0;
		jogadaJogador2 = 0;
		System.out.println("Jogadas resetadas para a próxima rodada.");
	}
}
