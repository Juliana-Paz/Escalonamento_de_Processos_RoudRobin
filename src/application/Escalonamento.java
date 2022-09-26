package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import model.Processo;

/**
 * 
 * @spetacularAuthor Juliana Chaves R. S. Paz
 * 
 */

// FIFO
public class Escalonamento {

	static Scanner scan = new Scanner(System.in);
	static List<Processo> arrayProcessos;
	static Processo processo;
	static Integer quantum;

	public static void main(String[] args) {

		char opcao = 's';
		do {
			
			quantum = returnQuantum();
			
			System.out.println("+---------------------------------+");
			System.out.println(" Quantidade de processos: [1 - 50] ");
			int qtdProcessos = scan.nextInt();
			if (qtdProcessos > 50 || qtdProcessos < 1) {
				System.out.println("Opcao invalida!");
				continue;
			}

			setProcesso(qtdProcessos);
			sortArrivalTime();
			printEscalonamento();
			System.out.println("+---------------------------------+");

			System.out.println("\nGerar outros processos? [S]im/[N]ao");
			opcao = scan.next().toLowerCase().charAt(0);

		} while (opcao == 's');

	}

	/**
	 * Retorna o quantum de escalonamento
	 */
	public static int returnQuantum() {
		return returnRandomValue(4, 12);
	}	
	
	
	/**
	 * Instancia uma nova lista de processos e seta cada atributo dentro de cada
	 * posicao na lista
	 */
	private static void setProcesso(int qtdProcessos) {
		arrayProcessos = new ArrayList<>();
		for (int i = 0; i < qtdProcessos; i++) {
			processo = new Processo();
			processo.setPid(Processo.returnPidTime(i + 1));
			processo.setArrivalTime(Processo.returnArrivalTime(i));
			processo.setBurstTime(Processo.returnBurstTime());
			arrayProcessos.add(processo);
		}
	}

	/**
	 * Ordena a lista de processos de acordo com chegada dos processos (ArrivelTime)
	 */
	private static void sortArrivalTime() {
		arrayProcessos.sort(Comparator.comparing(Processo::getArrivalTime));
	}

	/**
	 * Imprime cada posicao da lista de processos
	 */
	private static void printEscalonamento() {
		System.out.println("Quantum: " + quantum);		
		arrayProcessos.forEach(System.out::println);
	}

	public static int returnRandomValue(int min, int max) {
		int random = (int) Math.floor(Math.random() * (max - min + 1) + min);
		return random;
	}
	
}
