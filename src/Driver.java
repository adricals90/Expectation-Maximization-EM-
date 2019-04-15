import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	
	public static ArrayList<String> gender = new ArrayList<String>();
	public static ArrayList<Integer> weight= new ArrayList<Integer>();
	public static ArrayList<Integer> height= new ArrayList<Integer>();
	public static ArrayList<Double> probability= new ArrayList<Double>();
	
	

	
	public static void main(String[] args) {    
		
		String fileN= "hw2dataset_10.txt"; // uncoment 1 at the time to run the code
	//	String fileN= "hw2dataset_30.txt";
	//	String fileN= "hw2dataset_50.txt";
	//	String fileN= "hw2dataset_100.txt";
		File newData= new File(fileN);
		readFile(newData);
		
		int iterations=0;
	
		double conv=0;
		probabilityTable pT= new probabilityTable();
	//*
	    pT.setHeightGen(0, 0, 0.7);
		pT.setHeightGen(1, 0, 0.3);				// uncoment the blook to run, 1 blook at the time
		pT.setHeightGen(0, 1, 0.3);
		pT.setHeightGen(1, 1, 0.7);
		
		pT.setWeightGen(0, 0, 0.8);
		pT.setWeightGen(1, 0, 0.2);
		pT.setWeightGen(0, 1, 0.4);
		pT.setWeightGen(1, 1, 0.6);

	    pT.setPgender(0.7, 0.3);
	// */

		
		
	/*	pT.setHeightGen(0, 0, 0.6);			// second starting points
		pT.setHeightGen(1, 0, 0.4);			
		pT.setHeightGen(0, 1, 0.5);
		pT.setHeightGen(1, 1, 0.5);
		
		pT.setWeightGen(0, 0, 0.7);
		pT.setWeightGen(1, 0, 0.3);
		pT.setWeightGen(0, 1, 0.6);
		pT.setWeightGen(1, 1, 0.4);
		
		pT.setPgender(0.6, 0.4);
	*/
	/*	pT.setHeightGen(0, 0, 0.2);
		pT.setHeightGen(1, 0, 0.8);
		pT.setHeightGen(0, 1, 0.3);
		pT.setHeightGen(1, 1, 0.7);
		
		pT.setWeightGen(0, 0, 0.4);
		pT.setWeightGen(1, 0, 0.6);
		pT.setWeightGen(0, 1, 0.6);
		pT.setWeightGen(1, 1, 0.4);
		
		pT.setPgender(0.3, 0.7);
	*/	
		
		calculations calc= new calculations();
		

		do {
			
			
			conv= calc.likelihood(pT);
			
			emStep emS= new emStep(gender,weight,height);
			emS.splitTable(pT);
			
		
			System.out.println(" Input data and porbability row");
			printColumnB(emS.getGenderCol(),emS.getWeightCol(),emS.getHeightCol(),emS.getProbabilityCol());
		
			calc.updatePgender(pT, emS);
			calc.cpt(pT, emS);

			System.out.println(" CTP P(Height/Gender)");
			pT.printTable(pT.getHeihgtGen());
			System.out.println(" CTP P(Weight/Gender)");
			pT.printTable(pT.getWeightGen());

			calc.likelihood(pT);

			iterations++;
			System.out.println("Number of iterations: "+iterations);
			
			System.out.println("Likelihood  "+  calc.likelihood(pT));
			
			
		}while (thresshold(conv, calc.likelihood(pT))== false);


	
	}
	
	
	public static void readFile(File dataFile) {
		try {
			Scanner data = new Scanner(dataFile);
			
			data.nextLine();
			while(data.hasNextLine()) {
				
				String newString = data.nextLine();

				String [] columns = newString.split("\\s+");
				
				gender.add(columns[0]);
				weight.add(Integer.parseInt(columns[1]));
				height.add(Integer.parseInt(columns[2]));
				
				
			}
				data.close();	
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

	}
	
	public static void printColumn() {
		
		for(int i=0; i< gender.size();i++) {
			System.out.println(" gender  "+ gender.get(i));
		}
		
	}
	
	
	public static boolean thresshold(double oldVal, double newVal) {
	
		double test= Math.abs((newVal- oldVal));
	
		System.out.println("likelihood  diff " + test);
	
		if (test<= 0.001) {
			return true;
		}
		else {
			return false;
		}
	
	}
	
	

	public static void printColumnA(ArrayList<Integer> m) {
		
		for(int i=0; i< m.size();i++) {
			System.out.println(" column  "+ m.get(i));
		}
		
	}

	public static void printColumnB(ArrayList<String> g,ArrayList<Integer> w,ArrayList<Integer> h,ArrayList<Double> p) {
	
		for(int i=0; i< p.size();i++) { 
			System.out.println(g.get(i)+"   "+w.get(i)+"    "+h.get(i)+"    "+p.get(i));
		}
		System.out.println(" ");
	
 }	
	
}
