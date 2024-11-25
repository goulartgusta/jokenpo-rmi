package br.com.almaviva.jokenpo.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import br.com.almaviva.jokenpo.shared.JokenpoServer;

public class ServerImpl implements JokenpoServer {
    private final Scanner sc; // Scanner inicializado apenas uma vez
	
	public ServerImpl() throws RemoteException {
		UnicastRemoteObject.exportObject(this, 0); // exportar this object para porta padrão '0'
        this.sc = new Scanner(System.in); // Scanner inicializado no construtor e não no método
	}

	@Override
	public String jogarJokenpo(String jogadaDoCliente) {
		System.out.println("Olá, anfitrião, como dono deste servidor, escolha sua jogada: 'Pedra', 'Papel', 'Tesoura' ");
		String jogadaDoServidor = sc.nextLine();

		String resultado = validaJogada(jogadaDoServidor, jogadaDoCliente);		
		
		return " O visitante jogou: " + jogadaDoCliente + "\n O anfitrião jogou: " + jogadaDoServidor + "\n Resultado: "
				+ resultado;
	}
	
	public String validaJogada(String jogadaDoCliente, String jogadaDoServidor) {
		if ((jogadaDoCliente.equals("Pedra") && jogadaDoServidor.equals("Tesoura"))
				|| (jogadaDoCliente.equals("Papel") && jogadaDoServidor.equals("Pedra"))
				|| (jogadaDoCliente.equals("Tesoura") && jogadaDoServidor.equals("Papel"))) {
			return "O jogador visitante venceu!";

		} else if ((jogadaDoServidor.equals("Pedra") && jogadaDoCliente.equals("Tesoura"))
				|| (jogadaDoServidor.equals("Papel") && jogadaDoCliente.equals("Pedra"))
				|| (jogadaDoServidor.equals("Tesoura") && jogadaDoCliente.equals("Papel"))) {
			return "Como previsto... O anfitrião venceu!";

		} else if (jogadaDoCliente.equals(jogadaDoServidor)) {
			return "Deu empate!";
		
		} else {
			return "Jogada inválida, por favor, escolha as opções sugeridas...";
		}
	}

}
