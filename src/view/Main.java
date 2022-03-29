package view;

import java.util.concurrent.Semaphore;
import controller.Compra;

public class Main {
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		for (int idThread = 1; idThread < 301; idThread++) {
			Compra tCompra = new Compra(idThread, semaforo);
			tCompra.start();
		}
	}
}