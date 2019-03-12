package matrices;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @param fichero
 * @author Alien Embarec Riadi
 * @since 3/03/2019
 */


public class Matriz{
	private int nFilas_;
	private int nColumnas_;
	private static String nombreFichero_;
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
    				rowList.add(Double.parseDouble(numeros[i]));
	    		}
	    		if(lineCounter < nFilas_)
					matrix_.add(rowList);
	    		lineCounter++;
	    	}
	    }
	    checkSquareMatrix();
	    b.close();
	}
	
	public Matriz(int n) {
		List<List<Double>> mat = new ArrayList<>(n);
		nFilas_ = n;
		nColumnas_ = n;
	}
	
	public Matriz(int nCols, int nRows) {
		FileWriter fichero = null;
        PrintWriter pw = null;
        try{
        	nFilas_=nRows;
        	nColumnas_=nCols;
            fichero = new FileWriter("/home/alien/Escritorio/P2_ALGORITMOS_MULTIPLICACION/ull-esit-inf-daa-1819-pract2-Alien-97/src/matrices/Input1.txt");
            pw = new PrintWriter(fichero);
            for (int i = 0; i < nRows; i++) {
            	ArrayList<Double> fila = new ArrayList<>(nCols);
            	for(int j=0; j < nCols; j++) {
            		Double temp = (Math.random() * 50) + 1;// for example, 123.4568567
            		//temp = Double.parseDouble(String.format("%.3f", temp));  // 123.456
            		fila.add(temp);
            	}
            	matrix_.add(fila);
            }
            for(int i=0;i<matrix_.size();i++){
    			for(int j=0;j<matrix_.get(i).size();j++){
    				pw.print(matrix_.get(i).get(j)); 
    				pw.print(" ");
    			}
    			pw.println(" ");
    			pw.println(" ");
    		}
            checkSquareMatrix();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
        	  if (null != fichero)
              pw.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        } 
	}
	
	public int getMatrixRowsSize() {
		return matrix_.size();
	}
	
	public int getMatrixColumnsSize() {
		return matrix_.get(0).size();
	}
	
	public void checkSquareMatrix() {
		if(getMatrixRowsSize()>getMatrixColumnsSize()) {
			int temp = getMatrixRowsSize() - getMatrixColumnsSize();
			ArrayList<Double> col = new ArrayList<>(temp);
			for(int i=0;i<temp;i++) {
				col.add(0.0);
			}
			addColumn(col);
			//nColumnas_=nFilas_;
		}
		else {
			int numberOfRows= getMatrixColumnsSize() - getMatrixRowsSize();
			ArrayList<Double> row = new ArrayList<>();
			for(int i=0;i<matrix_.get(0).size();i++) {
				row.add(0.0);
			}
			addRow(row, numberOfRows);
			//nFilas_=nColumnas_;
		}
	}
	
	public void powerOfTwo() {
		checkSquareMatrix();
		int pow = 1;
		while(pow<getMatrixRowsSize()){
			pow=pow*2;
		}
		resizeMatrix(pow);
	}
	
	public void resizeMatrix(int pow) {
		int additionalRows = pow - matrix_.size();
		int additionalCols = pow - matrix_.get(0).size();
		ArrayList<Double> vector = new ArrayList<>();
		for(int i=0;i<additionalRows;i++) {
			vector.add(0.0);
		}
		addRow(vector,additionalRows);
		for(int i=0;i<additionalCols;i++) {
			vector.add(0.0);
		}
		addColumn(vector);
	}
	
	public void add (int i,int j, Double value) { // Matriz de 4x4, añadir elemento en la pos 5 5 , 4 5 y 3 6
		int contI = getMatrixRowsSize(); // 4
		int contJ = getMatrixColumnsSize(); // 4
		if(contJ<=j) {
			contJ=j+1;
			int temp = contJ - getMatrixColumnsSize();
			ArrayList<Double> vec = new ArrayList<>(temp);
			for(int k=0; k<temp; k++) {
				vec.add(0.0);
			}
			addColumn(vec);
		}
		while(contI<i && contJ<j) {
			ArrayList<Double> fila = new ArrayList<>();
			for(int k=0;k<contJ;k++) {
				fila.add(0.0);
			}
			addRow(fila,1);
			contI++;
		}
		set(i,j,value);
	}
	
	public void addRow(ArrayList<Double> newRow,int numberOfRows) {	
		for(int i=0;i<numberOfRows;i++) {
			matrix_.add(newRow);
			nFilas_++;
		}
	}

	public void addColumn(ArrayList<Double> newColumn) {
	    for (int i = 0; i < matrix_.size(); i++) {
	        ArrayList<Double> row = (ArrayList<Double>) getRow(i);
	        nColumnas_++;
	        for(int j=0; j< newColumn.size(); j++ ) {
	        	row.add(newColumn.get(j));
	        }
	    }              
	}
	
	public Double getElementByPosition(int i,int j) {
		return  matrix_.get(i).get(j);
	}
	
	public List<Double> getRow(int rowIndex) {
		return  matrix_.get(rowIndex);
	}
	
	public Matriz tresBucles(Matriz b) {
		if(this.getMatrixColumnsSize()==b.getMatrixRowsSize()) {
			return tresBucles(this,b);
		}
		return null;
	}
	public Matriz tresBucles(Matriz a, Matriz b) {
		Matriz result = new Matriz(a.getMatrixRowsSize());
		for(int i=0; i<a.getMatrixRowsSize();i++) {
			for(int j=0;j<a.getMatrixColumnsSize();j++) {
				for(int k=0;k<a.getMatrixRowsSize();k++) {
					Double temp = a.get(i, k) * b.get(k, j);
					result.add(i, j, temp);
				}
			}
		}
		return a;
	}

	public String toString() {
		String entrada= "Matriz " + "\n";
		entrada+="Numero de filas " + getMatrixRowsSize() + "\n";
		entrada+="Numero de columnas " + nColumnas_ + "\n";
		for(int i=0;i<matrix_.size();i++){
			for(int j=0;j<matrix_.get(i).size();j++){
				entrada += matrix_.get(i).get(j) + " ";
			}
			entrada += "\n";
		}
		entrada += "\n";
		return entrada;
	}

	public Matriz strassen(Matriz b) {

		powerOfTwo();
		
		if(sameSize(this,b)) {
			return strassen(this,b);
		}
		return null;
	}
	
	public boolean sameSize(Matriz a, Matriz b) {
		boolean mismoSize=false;
		if((a.getMatrixRowsSize()==b.getMatrixRowsSize()) && (a.getMatrixColumnsSize()==b.getMatrixColumnsSize())) {
			mismoSize=true;
		}
		return mismoSize;
	}
	
	public static Matriz strassen(Matriz a,Matriz b) 
	{
		int n = a.getMatrixRowsSize();
		//System.out.println(n);
		Matriz result = new Matriz(n);//Duda
		if(n == 1)
		{
			Double temp = result.getElementByPosition(0,0);
			temp= a.getElementByPosition(0,0) * b.getElementByPosition(0, 0);
			result.add(0,0,temp);
		}
		else
		{
			//System.out.println("Hola, estoy en Strassen");
			int  nm = a.getMatrixRowsSize()/2;
			Matriz A11 = new Matriz(nm);
			Matriz A12 = new Matriz(nm);
			Matriz A21 = new Matriz(nm);
			Matriz A22 = new Matriz(nm);
			
			Matriz B11 = new Matriz(nm);
			Matriz B12 = new Matriz(nm);
			Matriz B21 = new Matriz(nm);
			Matriz B22 = new Matriz(nm);
			
			divide(a, A11, 0 , 0);
			divide(a, A12, 0 , n/2);
			divide(a, A21, n/2, 0);
			divide(a, A22, n/2, n/2);
	
			divide(b, B11, 0 , 0);
			divide(b, B12, 0 , n/2);
			divide(b, B21, n/2, 0);
			divide(b, B22, n/2, n/2);
	
			Matriz P1 = strassen(add(A11, A22), add(B11, B22));
			Matriz P2 = strassen(add(A21, A22), B11);
			Matriz P3 = strassen(A11, sub(B12, B22));
			Matriz P4 = strassen(A22, sub(B21, B11));
			Matriz P5 = strassen(add(A11, A12), B22);
			Matriz P6 = strassen(sub(A21, A11), add(B11, B12));
			Matriz P7 = strassen(sub(A12, A22), add(B21, B22));
	
			Matriz C11 = add(sub(add(P1, P4), P5), P7);
			Matriz C12 = add(P3, P5);
			Matriz C21 = add(P2, P4);
			Matriz C22 = add(sub(add(P1, P3), P2), P6);
	
			copy(C11, result, 0 , 0);
			copy(C12, result, 0 , n/2);
			copy(C21, result, n/2, 0);
			copy(C22, result, n/2, n/2);
		}
		return result;
	}
	
	public static Matriz add(Matriz A, Matriz B)
	{
		int n = A.getMatrixRowsSize();
		Matriz result = new Matriz(n);
		for(int i=0; i<n; i++)
			for(int j=0; j<A.getMatrixColumnsSize(); j++) {
				Double temp = result.getElementByPosition(i, j);
				temp = A.getElementByPosition(i, j) + B.getElementByPosition(i, j);
				result.add(i, j, temp);
			}
		return result;
	}
	
	public static Matriz sub(Matriz A, Matriz B)
	{
		int n = A.getMatrixRowsSize();
		Matriz result = new Matriz(n);
		for(int i=0; i<n; i++)
			for(int j=0; j<A.getMatrixColumnsSize(); j++) {
				Double temp = result.getElementByPosition(i, j);
				temp = A.getElementByPosition(i,j) - B.getElementByPosition(i, j);
				result.add(i, j, temp);
			}
		return result;
	}
	
	public static void divide(Matriz p1, Matriz c1, int iB, int jB)
	{
		for(int i1 = 0, i2=iB; i1<c1.getMatrixRowsSize(); i1++, i2++)
			for(int j1 = 0, j2=jB; j1<c1.getMatrixColumnsSize(); j1++, j2++)
			{
				Double temp = c1.getElementByPosition(i1, j1);
				temp = p1.getElementByPosition(i2,j2);
				c1.add(i1,j1,temp);
			}
	}
	
	public static void copy(Matriz c1, Matriz p1, int iB, int jB)
	{
		for(int i1 = 0, i2=iB; i1<c1.getMatrixRowsSize(); i1++, i2++)
			for(int j1 = 0, j2=jB; j1<c1.getMatrixColumnsSize(); j1++, j2++)
			{
				Double temp = p1.getElementByPosition(i2,j2);
				temp = c1.getElementByPosition(i1, j1);
				p1.add(i2, j2, temp);
			}
	}
	
	public void set(int i,int j,Double number) {
		matrix_.get(i).set(j,number);
	}
	
	public Double get(int i, int j) {
		return matrix_.get(i).get(j);
	}
	
}
