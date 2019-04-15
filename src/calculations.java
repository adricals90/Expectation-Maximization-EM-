
public class calculations {
	
	private double conditionalGen0;
	private double conditionalGen1;
	
	calculations(){
			
	}
	
	
	public void setConditionalP(probabilityTable Cpt, int w, int h) { //P(G/w,h) = P(g,w,h)/p(w,h)
		 	
		    conditionalGen0= (Cpt.getGender0()*Cpt.getWeightGen(w, 0)*Cpt.getHeightGen(h, 0))/(
		    					  (Cpt.getGender0()*Cpt.getWeightGen(w, 0)*Cpt.getHeightGen(h, 0))+
		    					  (Cpt.getGender1()*Cpt.getWeightGen(w, 1)*Cpt.getHeightGen(h, 1)));
		
	        conditionalGen1= 1-conditionalGen0;
	
	}
	
	public void updatePgender(probabilityTable pt, emStep e) {
		   double gender0=0;
		   double gender1=0;

	     gender0=pGenderZero(e);
		gender0= gender0/Driver.gender.size();
		gender1= 1-gender0;
		pt.setPgender(gender0, gender1);
		System.out.println(" P(Gender) ");
		System.out.println("Gender 0 :  "+gender0);
		System.out.println("Gender 1 :  "+ gender1);
		System.out.println("  ");
		
	}
	
	public double pGenderZero(emStep em) {
		   double gender0=0;
		for(int i=0; i< em.getGenderCol().size();i++) {
			
			if(em.getGenderCol().get(i).equals("0")) {
				gender0+=em.getProbabilityCol().get(i);
				//System.out.println(">>>>"+gender0);
			}
			
		}
		return gender0;
		
	}
	public double pGenderOne(emStep em) {
		   double gender1=0;
		for(int i=0; i< em.getGenderCol().size();i++) {
			
			if(em.getGenderCol().get(i).equals("1")) {
				gender1+=em.getProbabilityCol().get(i);
 			}
			
		}
		return gender1;
		
	}
	
	
	
	public void cpt(probabilityTable pt, emStep em) {
		
		for (int i=0; i<em.getProbabilityCol().size(); i++) {
			 int gen= Integer.parseInt(em.getGenderCol().get(i));
			 
			if(gen ==0 &&em.getWeightCol().get(i)==0) {
				
				int weight=em.getWeightCol().get(i);
				pt.setWeightGen(weight, gen,  conditionalProbabilityW(em,gen,weight));// update CPT Weight=0/ Gender=0	
				pt.setWeightGen(1, gen,(1-conditionalProbabilityW(em,gen,weight)));// update CTP Weight=1/ Gender=0
			}
			
			if(gen ==1 &&em.getWeightCol().get(i)==0) {
				
				int weight1=em.getWeightCol().get(i);
				pt.setWeightGen(weight1, gen ,  conditionalProbabilityW(em,gen,weight1));// update CPT Weight=0/ Gender=1
				pt.setWeightGen(1, gen,(1-conditionalProbabilityW(em,gen,weight1)));// update CTP Weight=1/ Gender=1
			}
			
			if(gen ==0 &&em.getHeightCol().get(i)==0) {
				int height1=em.getHeightCol().get(i);
				pt.setHeightGen(height1, gen,  conditionalProbabilityH(em,gen,height1));// update CPT Height=0/ Gender=0
				pt.setHeightGen(1, gen,(1-conditionalProbabilityH(em,gen,height1)));// update CTP Height=1/ Gender=0
			}
			
			if(gen ==1 &&em.getHeightCol().get(i)==0) {
				int height2=em.getHeightCol().get(i);
				pt.setHeightGen(height2, gen, conditionalProbabilityH(em,gen,height2));// update CPT Height=0/ Gender=0
				pt.setHeightGen(1,gen ,(1-conditionalProbabilityH(em,gen,height2)));// update CTP Height=1/ Gender=1
			}
			
		}
		
		
	}
	
	
	public double conditionalProbabilityW(emStep em, int g, int w) { // probability P(W/G)
		double val =0;
		double prob=0;
		
		for (int i=0; i<em.getGenderCol().size();i++) {
			 int gen= Integer.parseInt(em.getGenderCol().get(i));
			 if(gen==g&& em.getWeightCol().get(i)==w) {
				 
				 prob+= em.getProbabilityCol().get(i);
			 }
			
		}	
		if (g==0) {
			val= prob/ pGenderZero(em);
			} else {
				val= prob/ pGenderOne(em);
			}	
		return val;
	}
	
	public double conditionalProbabilityH(emStep em, int g, int h) {  // probability P(H/G)
		double val =0;
		double prob=0;
		
		for (int i=0; i<em.getGenderCol().size();i++) {
			
			 int gen= Integer.parseInt(em.getGenderCol().get(i));
			 
			 if(gen==g && em.getHeightCol().get(i)==h) {
				 
				 prob+= em.getProbabilityCol().get(i);
			 }
			
		}
		
		
		if (g==0) {
		val= prob/ pGenderZero(em);
		} else {
			val= prob/ pGenderOne(em);
		}
		return val;
	}
	
	
	
	public double likelihood(probabilityTable CPT) {
		
		double logProb=0;
		
		for (int i=0; i<Driver.gender.size(); i++) {
			 
			String gender= Driver.gender.get(i);
			int weight= Driver.weight.get(i);
			int height= Driver.height.get(i);
			
			if (gender.equals("0")) {
				logProb+= Math.log(CPT.getGender0());
			}else if (gender.equals("1")) {
				logProb+= Math.log(CPT.getGender1());
			}
			if (gender.equals("-")) {
				
				
				
				
				if (height==0) {
				logProb+= Math.log(CPT.getHeightGen(height, 0));
				//logProb+= Math.log(CPT.getHeightGen(height, 1));

				}else if(height==1) {
				logProb+= Math.log(CPT.getHeightGen(height, 1));
				
			//	logProb+= Math.log(CPT.getHeightGen(height, 1));
				}
				if(weight==0) {
				logProb+= Math.log(CPT.getWeightGen(weight, 0));
				//logProb+= Math.log(CPT.getWeightGen(weight, 1));

				}else if(weight==1) {
				logProb+= Math.log(CPT.getWeightGen(weight, 1));
			//	logProb+= Math.log(CPT.getWeightGen(weight, 1));

				}
				
			}else {
		
			logProb+= Math.log(CPT.getHeightGen(height, Integer.parseInt(gender)));
			logProb+= Math.log(CPT.getWeightGen(weight, Integer.parseInt(gender)));
			}
		
			
			
		}
		return Math.abs(logProb);
		
	}
	
	
	
	
	
	public double getConditionalGender0() {
		return conditionalGen0;
	}
	
	public double getConditionalGender1() {
		return conditionalGen1;
	}

}
