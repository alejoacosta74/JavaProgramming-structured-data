package week2;

import java.util.HashMap;

import edu.duke.FileResource;

public class CodonFrames {
	
	private HashMap<String, Integer> codonsMap;
	
	public CodonFrames (){
		codonsMap = new HashMap<String, Integer>();
	}
	
	void buildCodonMap (int start, String dna) {
		String currCodon="";
		codonsMap.clear();		
		for (int k=start; k< dna.length(); k+=3) {
			if (k+3 <= dna.length()) {
				if (k+3 == dna.length()) {
					currCodon = dna.substring(k);
					}
				else {
					currCodon = dna.substring(k,k+3);
					}	
			
				if (codonsMap.containsKey(currCodon)) {
					int currCount = codonsMap.get(currCodon);
					codonsMap.put(currCodon, currCount+1);
				}
				else {
					codonsMap.put(currCodon, 1);
				}
			
			}
			
		}
	}
	
	public String  getMostCommonCodon () {
		String codon="";
		int currCount, maxCount=0;
		for (String currCodon : codonsMap.keySet()) {
			currCount = codonsMap.get(currCodon);
			if (currCount > maxCount) {				
				maxCount = currCount;
				codon = currCodon;			
			}
		}
		return codon;
	}
	
	public void printCodonCounts (int start, int end) {
		int currCount;
		for (String currCodon : codonsMap.keySet()) {
			currCount = codonsMap.get(currCodon);
			if (currCount >= start && currCount <= end) {
				System.out.println("Codon: " + currCodon + " has count=" + currCount);
			}
		}
		
	}
	
	void tester() {
		FileResource fr = new FileResource();
		String common; 
		int count; 
		String uppCaseDNA = fr.asString().toUpperCase();
		uppCaseDNA = uppCaseDNA.trim();
		CodonFrames frame0 = new CodonFrames();
		frame0.buildCodonMap(0, uppCaseDNA);
		System.out.println("Reading frame starting with 0 results in :" + frame0.codonsMap.size() + " unique codons");
		common = frame0.getMostCommonCodon();
		count = frame0.codonsMap.get(common);
		System.out.println("and most common codon is: " + common + " with count: " + count);
		System.out.println("Counts of codons between 1 and 5 inclusive are:");
		frame0.printCodonCounts(1, 5);
		System.out.println("Counts of codons between 7 and 7 inclusive are:");
		frame0.printCodonCounts(7, 7);
		
		//frame1		
		CodonFrames frame1 = new CodonFrames();
		frame1.buildCodonMap(1, uppCaseDNA);
		System.out.println("\n Reading frame starting with 1 results in :" + frame1.codonsMap.size() + " unique codons");
		common = frame1.getMostCommonCodon();
		count = frame1.codonsMap.get(common);
		System.out.println("and most common codon is: " + common + " with count: " + count);
		System.out.println("Counts of codons between 1 and 5 inclusive are:");
		frame1.printCodonCounts(1, 5);
		
		//frame2		
		CodonFrames frame2 = new CodonFrames();
		frame2.buildCodonMap(2, uppCaseDNA);
		System.out.println("\n Reading frame starting with 2 results in :" + frame2.codonsMap.size() + " unique codons");
		common = frame2.getMostCommonCodon();
		count = frame2.codonsMap.get(common);
		System.out.println("and most common codon is: " + common + " with count: " + count);
		System.out.println("Counts of codons between 1 and 5 inclusive are:");
		frame2.printCodonCounts(1, 5);

		
	}
	
	public static void main (String[] args) {
		CodonFrames w2 = new CodonFrames();
		w2.tester();
		
	}

}
