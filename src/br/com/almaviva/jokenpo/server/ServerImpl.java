package br.com.almaviva.jokenpo.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import br.com.almaviva.jokenpo.shared.JokenpoServer;

public class ServerImpl implements JokenpoServer {
	private final Scanner sc; // Scanner inicializado apenas uma vez

	public ServerImpl() throws RemoteException {
		UnicastRemoteObject.exportObject(this, 0); // Exportar this object para porta padrão '0'
		this.sc = new Scanner(System.in); // Scanner precisa inicializar no construtor e não no método
	}

	@Override
	public String jogarJokenpo(String jogadaDoCliente) {
		System.out
				.println("Olá, anfitrião, como dono deste servidor, escolha sua jogada: 'Pedra', 'Papel', 'Tesoura' ");
		String jogadaDoSegundoCliente = sc.nextLine();

		String resultado = validaJogada(jogadaDoCliente, jogadaDoSegundoCliente);

		return " O visitante jogou: " + jogadaDoCliente + "\n O anfitrião jogou: " + jogadaDoCliente + "\n Resultado: "
				+ resultado;
	}

	public String validaJogada(String jogadaDoCliente, String jogadaDoSegundoCliente) {
		if ((jogadaDoCliente.equalsIgnoreCase("Pedra") && jogadaDoSegundoCliente.equalsIgnoreCase("Tesoura"))
				|| (jogadaDoCliente.equalsIgnoreCase("Papel") && jogadaDoSegundoCliente.equalsIgnoreCase("Pedra"))
				|| (jogadaDoCliente.equalsIgnoreCase("Tesoura") && jogadaDoSegundoCliente.equalsIgnoreCase("Papel"))) {
			return "O jogador visitante venceu!";

		} else if ((jogadaDoSegundoCliente.equalsIgnoreCase("Pedra") && jogadaDoCliente.equalsIgnoreCase("Tesoura"))
				|| (jogadaDoSegundoCliente.equalsIgnoreCase("Papel") && jogadaDoCliente.equalsIgnoreCase("Pedra"))
				|| (jogadaDoSegundoCliente.equalsIgnoreCase("Tesoura") && jogadaDoCliente.equalsIgnoreCase("Papel"))) {
			return "Como previsto... O anfitrião venceu!";

		} else if (jogadaDoCliente.equalsIgnoreCase(jogadaDoSegundoCliente)) {
			return "Deu empate!";

		} else {
			return "Jogada inválida, por favor, escolha as opções sugeridas...";
		}
	}

}
