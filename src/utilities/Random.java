package utilities;

public class Random {

	/**
	 * Metodo para retornar um valor inteiro aleatorio
	 * dentro de um intervalo passado nos parametros 
	 */
	public static int returnIntValue(int min, int max) {
		int random = (int) Math.floor(Math.random() * (max - min + 1) + min);
		return random;
	}
	
}
