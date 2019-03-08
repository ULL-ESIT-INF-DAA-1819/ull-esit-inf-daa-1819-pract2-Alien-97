package matrices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String args[]) throws IOException{
		String nombreFichero=args[0];
		Matriz matrixOne = new Matriz(nombreFichero);
		nombreFichero=args[1];
		Matriz matrixTwo = new Matriz(nombreFichero);
		System.out.println(matrixOne);
		System.out.println(matrixTwo);
	}
}
