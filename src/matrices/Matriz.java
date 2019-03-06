package matrices;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @param fichero
 * @author Alien Embarec Riadi
 * @since 3/03/2019
 */


public class Matriz implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	int nFilas_;
	int nColumnas_;
	static String nombreFichero_;
	private List<List<Double>> matrixA_ = new ArrayList<>();
	private List<List<Double>> matrixB_ = new ArrayList<>();
	
	public Matriz(String nombreFichero) throws IOException {
		String linea;
		nombreFichero_=nombreFichero; 
		FileReader f = new FileReader(nombreFichero);
	    BufferedReader b = new BufferedReader(f);
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
					matrixA_.add(rowList);
				else
					matrixB_.add(rowList);
	    		lineCounter++;
	    	}
	    }
	    b.close();
	}
	
	public void checkSquareMatrix() {
		if(nFilas_!=nColumnas_) {
			if(nFilas_>nColumnas_) {
				nColumnas_=nFilas_;
			}
			else {
				nFilas_=nColumnas_;
			}
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
		
	}
	
	public void addRow(boolean matrixId,int rowIndexWhereInsert, ArrayList<Double> newRow) {
		if(!matrixId) {
			matrixA_.add(rowIndexWhereInsert, newRow);
		}
		else if(matrixId) {
			matrixB_.add(rowIndexWhereInsert,newRow);
		}
	}

	public void addColumn(boolean matrixId,int columnIndexWhereInsert, ArrayList<Double> newColumn) {
		if(!matrixId) {
		    for (int k = 0; k < matrixA_.size(); k++) {
		        ArrayList<Double> row = (ArrayList<Double>) getRow(false,k);
		        row.add(columnIndexWhereInsert, newColumn.get(k));
		    }        
		}
		else if(matrixId){
			for (int k = 0; k < matrixB_.size(); k++) {
		        ArrayList<Double> row = (ArrayList<Double>) getRow(true,k);
		        row.add(columnIndexWhereInsert, newColumn.get(k));
		    }        
		}	
	}
	
	public Double getElementByPosition(boolean matrixId,int i,int j) {
		return matrixId? matrixB_.get(i).get(j): matrixA_.get(i).get(j);
	}
	
	public List<Double> getRow(boolean matrixId, int rowIndex) {
		return matrixId? matrixB_.get(rowIndex) : matrixA_.get(rowIndex);
	}

	public String toString() {
		String entrada= "Matriz A" + "\n";
		for(int i=0;i<matrixA_.size();i++){
			for(int j=0;j<matrixA_.get(i).size();j++){
				entrada += matrixA_.get(i).get(j) + " ";
			}
			entrada += "\n";
		}
		entrada += "\n";
		entrada += "Matriz B" + "\n";
		for(int i=0;i<matrixB_.size();i++) {
			for(int j=0;j<matrixB_.get(i).size();j++) {
				entrada += matrixB_.get(i).get(j) + " ";
			}
			entrada += "\n";
		}
		return entrada;
	}
}
