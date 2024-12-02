package br.com.almaviva.jokenpo.client;

import br.com.almaviva.jokenpo.shared.JokenpoServer;

import java.rmi.Naming;
import java.util.Scanner;

public class RunClient {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Olá. por favor, digite o IP do servidor:");
			String ip = sc.nextLine();
			JokenpoServer servidor = (JokenpoServer) Naming.lookup("//" + ip + ":2099/JokenpoServer");

			System.out.println("Agora, digite seu nome:");
			String nome = sc.nextLine();

			servidor.registrarCliente(nome);
			System.out.println("Bem-vindo ao Jokenpo RMI! Quando quiser sair, digite '0'.");

			int jogada = 9;
			while (jogada != 0) {
				System.out.println("Escolha sua jogada (1 - Pedra, 2 - Papel, 3 - Tesoura):");
				jogada = sc.nextInt();

				if (jogada == 0) {
					System.out.println("Saindo do jogo...");
					break;
				}

				String resultado = servidor.jogarJokenpo(nome, jogada);
				System.out.println("Resultado: " + resultado);

			}
			System.out.println("Triste que está saindo... Volte sempre!");
		} catch (Exception e) {
			System.err.println("Erro ao conectar ao servidor:");
			e.printStackTrace();
		}

	}
}
