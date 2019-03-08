package matrices;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @param fichero
 * @author Alien Embarec Riadi
 * @since 3/03/2019
 */


public class Matriz{
	int nFilas_;
	int nColumnas_;
	static String nombreFichero_;
	private List<List<Double>> matrix_ = new ArrayList<>();
	
	public Matriz(String nombreFichero) throws IOException {
		String linea;
		nombreFichero_=nombreFichero; 
		FileReader f = new FileReader(nombreFichero_);
		BufferedReader b = new BufferedReader(f);
	    b = new BufferedReader(f);
	    linea = b.readLine();
	    String[] numeros = linea.split(" ");
	    nFilas_ = Integer.parseInt(numeros[0]);
	    System.out.println(nFilas_);
	    nColumnas_ = Integer.parseInt(numeros[1]);
	    System.out.println(nColumnas_);
	    int lineCounter=0;
	    while(( linea= b.readLine())!=null) {
	    	if(!linea.isEmpty()){
	    		numeros = linea.split(" ");
	    		ArrayList<Double> rowList= new ArrayList<>(nColumnas_);
	    		for(int i=0;i<nColumnas_;i++) {
    				System.out.println("numero de linea " + lineCounter +  " elemento de la linea " + numeros[i]);
    				rowList.add(Double.parseDouble(numeros[i]));
	    		}
	    		if(lineCounter < nFilas_)
					matrix_.add(rowList);
	    		lineCounter++;
	    	}
	    }
	    b.close();
	}
	
	public Matriz(int squareMatrixSize) {
		FileWriter fichero = null;
        PrintWriter pw = null;
        try{
            fichero = new FileWriter("/home/alien/Escritorio/P2_ALGORITMOS_MULTIPLICACION/ull-esit-inf-daa-1819-pract2-Alien-97/src/matrices/1.txt");
            pw = new PrintWriter(fichero);
            for (int i = 0; i < 10; i++) {
            	pw.println("IMPRIMETE YA MUCHACHO");
                pw.println("Linea " + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        } 
	}
	
	public int getMatrixARowsSize() {
		return matrix_.size();
	}
	public int getMatrixAColumnsSize() {
		return matrix_.get(0).size();
	}
	public void checkSquareMatrix() {
		if(getMatrixARowsSize()>getMatrixAColumnsSize()) {
			int temp = getMatrixAColumnsSize();
			int cont=0;
			ArrayList<Double> col = new ArrayList<>(getMatrixARowsSize() - getMatrixAColumnsSize());
			for(int i=0;i<col.size();i++) {
				col.add(0.0);
			}
			while(cont < (getMatrixARowsSize() - temp)) {
				addColumn(matrix_.size(),col);
				cont++;
			}
			nColumnas_=nFilas_;
		}
		else {
			int cont=0;
			ArrayList<Double> col = new ArrayList<>(getMatrixAColumnsSize() - getMatrixARowsSize());
			for(int i=0;i<col.size();i++) {
				col.add(0.0);
			}
			while(cont < (getMatrixAColumnsSize() - getMatrixARowsSize())){
				addRow(matrix_.size(),col);
				cont++;
			}
			nFilas_=nColumnas_;
		}
	}
	
	public void powerOfTwo() {
		checkSquareMatrix();
		int pow = 1;
		while(pow<nFilas_){
			pow=pow*2;
		}
		resizeMatrix(pow);
	}
	
	public void resizeMatrix(int pow) {
		int delta = pow - matrix_.size();
		ArrayList<Double> newRowAndColumn = new ArrayList<>(matrix_.size() + delta);
		for(int i=0;i<newRowAndColumn.size();i++) {
			newRowAndColumn.add(0.0);
		}
		for(int i=0;i<delta;i++) {
			addRow(matrix_.size(),newRowAndColumn);
			addColumn(matrix_.size(),newRowAndColumn);
		}
	}
	
	public void addRow(int rowIndexWhereInsert, ArrayList<Double> newRow) {	
		matrix_.add(rowIndexWhereInsert, newRow);
	}

	public void addColumn(int columnIndexWhereInsert, ArrayList<Double> newColumn) {
	    for (int k = 0; k < matrix_.size(); k++) {
	        ArrayList<Double> row = (ArrayList<Double>) getRow(false,k);
	        row.add(columnIndexWhereInsert, newColumn.get(k));
	    }              
	}
	
	public Double getElementByPosition(boolean matrixId,int i,int j) {
		return matrixId? matrix_.get(i).get(j): matrix_.get(i).get(j);
	}
	
	public List<Double> getRow(boolean matrixId, int rowIndex) {
		return matrixId? matrix_.get(rowIndex) : matrix_.get(rowIndex);
	}

	public String toString() {
		String entrada= "Matriz " + "\n";
		for(int i=0;i<matrix_.size();i++){
			for(int j=0;j<matrix_.get(i).size();j++){
				entrada += matrix_.get(i).get(j) + " ";
			}
			entrada += "\n";
		}
		entrada += "\n";
		return entrada;
	}
}
