package com.codebrothers.services.customer.runners;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helpers {
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	public LocalDate getRandomDate() {
		
		return LocalDate.of(getRandomNumberInRange(1950,2017), getRandomNumberInRange(1,12), getRandomNumberInRange(1,28));
	}
	
	public String getRandomName() {
		List<String> names = new ArrayList<String>();
		names.add("Maria");
		names.add("Paulo");
		names.add("Jhon");
		names.add("Ricardo");
		names.add("Saldanha");
		names.add("Tião");
		names.add("Penelope");
		names.add("Eliana");
		names.add("Alam");
		names.add("Teotonio");
		names.add("Marcos");
		names.add("Daniel");
		names.add("Beth");
		
		Random r = new Random();
		Integer i = getRandomNumberInRange(0, names.size()-1);
		return names.get(i);
	}	
	
	public String getRandomSecondName(Integer qtd) {
		List<String> names = new ArrayList<String>();
		names.add("Martins");
		names.add("Silva");
		names.add("Lima");
		names.add("Romeiro");
		names.add("Jhon");
		names.add("Carreiro");
		names.add("Charmosa");
		names.add("Fiotk");
		names.add("Obama");
		names.add("De Jesus");
		names.add("Oliveira");
		names.add("Cheira Bife");		
		
		Random r = new Random();
		String retorno = "";
		for (int i = 0; i < qtd; i++) {			
			Integer indice = getRandomNumberInRange(0, names.size() -1);					
			retorno = retorno +" "+  names.get(indice);
		}		
		return retorno;
	}
	
	public String getRandomCPF() {
						
		return getRandomNumberInRange(100, 998) + "." + getRandomNumberInRange(100, 998) + "." + getRandomNumberInRange(100, 998) + '-' + getRandomNumberInRange(10, 98);
	}
	
	public String getRandomRG() {
		
		return getRandomNumberInRange(10, 99) + "." + getRandomNumberInRange(100, 998) + "." + getRandomNumberInRange(100, 998) + '-' + getRandomNumberInRange(1,9);
	}
	
	
}
