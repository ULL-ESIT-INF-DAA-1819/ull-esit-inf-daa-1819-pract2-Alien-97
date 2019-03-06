package matrices;

import java.io.IOException;

public class Main {
	public static void main(String args[]) throws IOException{
		String nombreFichero=args[0];
		Matriz matrixOne = new Matriz(nombreFichero);
		System.out.println(matrixOne);
	}
}
