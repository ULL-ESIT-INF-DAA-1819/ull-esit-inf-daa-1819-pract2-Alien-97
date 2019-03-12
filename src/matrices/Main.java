package matrices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {
	public static void main(String args[]) throws IOException{
		
		Matriz matrixOne = new Matriz(2,2);
		Matriz matrixTwo = new Matriz(2,2);
		System.out.println(matrixOne);
		System.out.println(matrixTwo);
		Matriz c = matrixOne.tresBucles(matrixTwo);
		FileWriter fichero = null;
        PrintWriter pw = null;
        System.out.println("pipo");
        try{
            fichero = new FileWriter("/home/alien/Escritorio/P2_ALGORITMOS_MULTIPLICACION/ull-esit-inf-daa-1819-pract2-Alien-97/src/matrices/Resultado.txt");
            pw = new PrintWriter(fichero);
            for(int i=0;i<c.getMatrixRowsSize();i++){
    			for(int j=0;j<c.getMatrixColumnsSize();j++){
    				pw.print(c.get(i,j)); 
    				pw.print(" ");
    			}
    			pw.println(" ");
    			pw.println(" ");
    		}
            
            System.out.println(c.getMatrixRowsSize());
            System.out.println(c.getMatrixColumnsSize());
            pw.close();    
        } catch (Exception e) {
        	System.out.println("ohoh");
            e.printStackTrace();
        }/* finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
        	  if (null != fichero)
              pw.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }*/ 
        
	}
	
	
}
