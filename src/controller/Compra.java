package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Compra extends Thread {
	private int idThread;
	private Semaphore semaforo;
	String comprar;
	Random random = new Random();
	private static int ingressos = 100;

	public Compra(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		login();
	}

	private void login() {
		int tempo = 0;
		tempo = random.nextInt(2051) + 50;
		System.out.println("Login iniciado.");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("TEMPO==" + tempo);
		if (tempo < 1000) {
			processo();
		} else {
			System.out.println("Timeout no login!");
		}
	}

	private void processo() {
		int tempo = 0;
		tempo = random.nextInt(2001) + 1000;
		System.out.println("Processo " + idThread + " iniciado.");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("TEMPO==" + tempo);
		if (tempo < 2500) {
			validacao();
		} else {
			System.out.println("Final de sessão!");
		}
	}

	private void validacao() {
		int bilh_quant = random.nextInt(4) + 1;
		if (bilh_quant <= ingressos) {
			try {
				semaforo.acquire();
				ingressos = ingressos - bilh_quant;
				System.out.println("Compra " + idThread + " finalizada.");
				System.out.println("Ingressos disponpiveis:" + ingressos);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
			System.out.println("QTD==" + bilh_quant);
		} else {
			System.out.println("Ingressos Esgotados!");
		}
	}
}