import java.util.ArrayList;

public class probabilityTable {
	
	private double [] pGender= new double [2];
	private double  [][] pWeightGen= new double [2][2];
	private double [][] pHeightGen= new double[2][2];
	
	probabilityTable () {
		
	}
	
	
	public void setPgender(double gen0 , double gen1 ) {	
		pGender[0]= gen0;
		pGender[1]= gen1;	
	}
	
	public void setWeightGen(int weight, int gen, double value) {// set conditional probability p(weight/gender)
		
		if (weight==0 && gen==0) {
			pWeightGen[0][0]= value;		
		}if(weight==0 && gen==1) {
			pWeightGen[1][0]= value;
		}if(weight==1 && gen==0) {
			pWeightGen[0][1]= value;
		}if(weight==1 && gen==1) {
			pWeightGen[1][1]= value;
		}	
		
	}
	
	
public void setHeightGen(int height, int gen, double value) {// set conditional probability p(weight/gender)
		
		if (height==0 && gen==0) {
			pHeightGen[0][0]= value;		
		}if(height==0 && gen==1) {
			pHeightGen[1][0]= value;
		}if(height==1 && gen==0) {
			pHeightGen[0][1]= value;
		}if(height==1 && gen==1) {
			pHeightGen[1][1]= value;
		}	
		
	}

public double getHeightGen(int height, int gen) {// get conditional probability p(weight/gender)
	
	if (height==0 && gen==0) {
		return pHeightGen[0][0];		
	}if(height==0 && gen==1) {
		return pHeightGen[1][0];
	}if(height==1 && gen==0) {
		return pHeightGen[0][1];
	}if(height==1 && gen==1) {
		return pHeightGen[1][1];
	}	
	return -99;// error
}

public double getWeightGen(int weight, int gen) {// get conditional probability p(weight/gender)
	
	if (weight==0 && gen==0) {
		return pWeightGen[0][0];		
	}if(weight==0 && gen==1) {
		return pWeightGen[1][0];
	}if(weight==1 && gen==0) {
		return pWeightGen[0][1];
	}if(weight==1 && gen==1) {
		return pWeightGen[1][1];
	}	
	return -99; //error
}



public void printTable(double [][] arr) {
	for(int i=0; i<arr.length;i++) {
		for(int j=0;j<arr.length;j++) {
			System.out.print(arr[i][j]+"   ");
		}
		   System.out.println("");

	}
	 System.out.println("");
}


public double getGender0() {
	return pGender[0];
}
public double getGender1() {
	return pGender[1];
}

public double [][] getHeihgtGen(){
	return pHeightGen;
}

public double [][] getWeightGen(){
	return pWeightGen;
}
 


}
