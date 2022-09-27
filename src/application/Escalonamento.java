package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import model.Processo;
import utilities.Random;

/**
 * 
 * @Author Juliana Chaves R. S. Paz
 * 
 */

// Round Robin
public class Escalonamento {

	static Scanner scan = new Scanner(System.in);
	static List<Processo> processList;
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

			processScheduling();
			
			System.out.println("\n -> Gerar outros processos? [S]im/[N]ao");
			opcao = scan.next().toLowerCase().charAt(0);

		} while (opcao == 's');

	}

	/**
	 * Instancia uma nova lista de processos e seta cada atributo dentro de cada
	 * posicao na lista
	 */
	private static void setProcesso(int qtdProcessos) {
		processList = new ArrayList<>();
		for (int i = 0; i < qtdProcessos; i++) {
			processo = new Processo();
			processo.setPid(Processo.returnPidTime(i + 1));
			processo.setArrivalTime(Processo.returnArrivalTime(i));
			processo.setBurstTime(Processo.returnBurstTime());
			processList.add(processo);
		}
	}

	/**
	 * Ordena a lista de processos de acordo com chegada dos processos (ArrivelTime)
	 */
	private static void sortArrivalTime() {
		processList.sort(Comparator.comparing(Processo::getArrivalTime));
	}

	/**
	 * Retorna o quantum de escalonamento
	 */
	public static int returnQuantum() {
		return Random.returnIntValue(4, 12);
	}

	/**
	 * Imprime cada posicao da lista de processos
	 */
	private static void printEscalonamento() {
		System.out.println("Quantum: " + quantum);
		processList.forEach(System.out::println);
	}

	/**
	 * Processa o escalonamento
	 * 
	 * 		Verifica em toda a lista se ainda ha Burst Time em um processo, se sim,
	 * 		reduz com o valor do quantum.
	 * 
	 * 		Apos finalizar a iteracao na lista, verifica em toda a lista se ainda ha 
	 * 		Burst Time em algum processo, se sim, chama o metodo recursivamente.
	 * 
	 * 		O processo se repete ate o Burst Time de todos os processos finalizar.	 * 
	 */
	public static void processScheduling() {
		System.out.println("------------------------------");
		for (Processo processo : processList) {
			if (verifyBurstTime(processo)) {
				printProcessBefore(processo);
				processo.setBurstTime(processo.getBurstTime() - quantum);
				printProcessAfter(processo);
			}
		}
		System.out.println("------------------------------\n");
		
		if (verifyBurstTime()) {			
			processScheduling();
		}
	}

	/**
	 * Verifica se uma posicao esta com Burst Time diferente de 0  
	 */
	private static boolean verifyBurstTime(Processo processo) {
		if (processo.getBurstTime() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica em toda a lista se ainda ha alguma 
	 * posicao com Burst Time diferente de 0  
	 */
	private static boolean verifyBurstTime() {
		for (Processo processo : processList) {
			if (processo.getBurstTime() > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Imprime a mudanca em um processo antes de sua execucao
	 */
	private static void printProcessBefore(Processo processo) {
		System.out.println("Nome: " + processo.getPid()
			+ " | AT: " + processo.getArrivalTime() 
			+ " | BT: (" + processo.getBurstTime() + " - " + quantum + ")");
	}

	/**
	 * Imprime a mudanca em um processo apos de sua execucao
	 */
	private static void printProcessAfter(Processo processo) {
		System.out.println("Novo Burst Time = " + processo.getBurstTime());
		System.out.println("--");
	}

}
