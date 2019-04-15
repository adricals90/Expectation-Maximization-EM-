import java.util.ArrayList;

public class emStep {

	private ArrayList<String> genderCol = new ArrayList<String>();
	private ArrayList<Integer> weightCol= new ArrayList<Integer>();
	private ArrayList<Integer> heightCol = new ArrayList<Integer>();
	private ArrayList<Double> probabilityCol= new ArrayList<Double>();
	
	
	emStep(){
		
	}
	
	emStep(ArrayList<String> g,ArrayList<Integer> w,ArrayList<Integer> h){
		genderCol=copyS(g);
		weightCol=copyI(w);
		heightCol=copyI(h);
	}
	
	public void splitTable(probabilityTable pT) { // splits the tables and creates a column of probabilities
		
		calculations calc1= new calculations ();
	  
	         for(int i=0; i<genderCol.size();i++) {
	        	 
	        	 			 if(genderCol.get(i).equals("-")){
	        	 			      	probabilityCol.add(1.0);
	        	 				    probabilityCol.add(1.0);
	        	 				 
	        	 				 }else {
	        	 					probabilityCol.add(1.0);
	        	 				 } 
	         }
	  
			 for(int i=0;i<genderCol.size();i++) {
				
				 if(genderCol.get(i).equals("-")) {
					  
					 genderCol.set(i, "0");
				     genderCol.add(i+1,"1");
					   
					 heightCol.add(i+1, heightCol.get(i));
					 weightCol.add(i+1, weightCol.get(i));
					 
					 calc1.setConditionalP(pT, weightCol.get(i), heightCol.get(i));
					 probabilityCol.set(i,calc1.getConditionalGender0());
					
					 calc1.setConditionalP(pT, weightCol.get(i+1), heightCol.get(i+1));
					 probabilityCol.set(i+1,calc1.getConditionalGender1());
					
				 }
					 
		     }
			 
			 
	}
	
	
	public ArrayList<Integer> copyI(ArrayList<Integer> m) {
		
		ArrayList<Integer> copy= new ArrayList<Integer>();
		for (int i=0; i< m.size(); i++) {
			copy.add(m.get(i));
		}
		return copy;
		
	}

	public ArrayList<String> copyS(ArrayList<String> m) {
		
		ArrayList<String> copy= new ArrayList<String>();
		for (int i=0; i< m.size(); i++) {
			copy.add(m.get(i));
		}
		return copy;
		
	}

	
	public ArrayList<String> getGenderCol(){
		return genderCol;
	}

	public ArrayList<Integer> getWeightCol(){
		return weightCol;
	}
	public ArrayList<Integer> getHeightCol(){
		return heightCol;
	}

	public ArrayList<Double> getProbabilityCol(){
		return probabilityCol;
		
	}
	
	
	
	
	
}
