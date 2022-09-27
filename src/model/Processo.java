package model;

import utilities.Random;

public class Processo {

	private String pid;
	private Integer arrivelTime;
	private Integer burstTime;

	public Processo() {
	}

	public Processo(String pid, Integer arrivelTime, Integer burstTime) {
		super();
		this.pid = pid;
		this.arrivelTime = arrivelTime;
		this.burstTime = burstTime;
	}

	@Override
	public String toString() {
		return "Nome: " + pid + "  AT: " + arrivelTime + "  BT: " + burstTime;
	}
	
	/**
	 * Retorna o ID do processo 
	 */
	public static String returnPidTime(int idProcesso) {		
		return "P" + idProcesso;
	}

	/**
	 * Retorna o tempo de chegada de cada processo
	 * Caso seja o primeiro processo, o retorno é 0
	 */
	public static Integer returnArrivalTime(int fistProcesso) {
		if (fistProcesso == 0) {			
			return 0;
		}		
		return Random.returnIntValue(1, 30);
	}

	/**
	 * Retorna o tempo estimado de execução de cada processo
	 */
	public static int returnBurstTime() {
		return Random.returnIntValue(1, 35);
	}
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getArrivalTime() {
		return arrivelTime;
	}

	public void setArrivalTime(Integer arrivelTime) {
		this.arrivelTime = arrivelTime;
	}

	public Integer getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(Integer burstTime) {
		if(burstTime < 0) {
			this.burstTime = 0;
		} else {
			this.burstTime = burstTime;
		}
		
	}

}
