package com.codebrothers.services.customer.runners.test;

import org.junit.jupiter.api.Test;

import com.codebrothers.services.customer.runners.Helpers;

class HelperTest {

	@Test
	void getNameTest() {
		Helpers h = new Helpers();		
		String name = h.getRandomName();
		System.out.println(name);
	}
	
	
	@Test
	void Get10Names() {
		Helpers h = new Helpers();
		for (int i = 0; i < 10; i++) {
			System.out.println(h.getRandomName());
		}
	}
	
	@Test
	void Get10SecondNames() {
		Helpers h = new Helpers();
		
		//Random r = new Random();
		System.out.println("Iniciando teste SobreNomes");
		for (int i = 0; i < 10; i++) {
			
			System.out.println(h.getRandomSecondName(3));
		}
	}
	
	
	@Test
	void Get10RandomDates() {
		Helpers h = new Helpers();
		
		//Random r = new Random();
		System.out.println("Iniciando teste de datas aleatorias");
		for (int i = 0; i < 10; i++) {
			
			System.out.println(h.getRandomDate());
		}
	}
	
	
	@Test
	void Get10RandomCPFS() {
		Helpers h = new Helpers();
		
		//Random r = new Random();
		System.out.println("Iniciando teste de cpfs Aleatorios");
		for (int i = 0; i < 10; i++) {
			
			System.out.println(h.getRandomCPF());
		}
	}
	
	
	@Test
	void Get10RandomRGs() {
		Helpers h = new Helpers();
		
		//Random r = new Random();
		System.out.println("Iniciando teste de rgs Aleatorios");
		for (int i = 0; i < 10; i++) {
			
			System.out.println(h.getRandomRG());
		}
	}

}
