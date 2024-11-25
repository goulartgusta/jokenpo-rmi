package br.com.almaviva.jokenpo.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RunClient {
	public static void main(String[] args) throws RemoteException, NotBoundException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Por favor, digite o IP do servidor.");
		String serverIP = sc.nextLine();

		JokenpoClient client = new JokenpoClient();
		client.iniciarClient(serverIP);

		String jogada = "";
		while (!jogada.equals("Sair")) {
			System.out.println("Faça sua jogada, visitante: 'Pedra', 'Papel', 'Tesoura' ('Sair' para encerrar o jogo)");
			jogada = sc.nextLine();

			if (jogada.equals("Sair")) {
				System.out.println("Encerrando jogo...");
				break;
			}
			try {
				String resultado = client.jogarJokenpo(jogada);
				System.out.println(resultado);
			} catch (Exception e) {
				System.out.println("Erro: " + e);
			}

		}
		sc.close();
	}
}