package week2;

import java.util.ArrayList;

import edu.duke.FileResource;

public class WordFrequencies {
	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
	
	WordFrequencies (){
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}
	
	void findUnique() {
		myWords.clear();
		myFreqs.clear();
		int idx, currValue;
		FileResource fr = new FileResource();
		for (String s : fr.words()) {			
			idx = myWords.indexOf(s.toLowerCase());
			if (idx ==-1) {
				myWords.add(s.toLowerCase());
				myFreqs.add(1);				
			}
			else {
				currValue = myFreqs.get(idx);
				myFreqs.set(idx, currValue+1);
			}			
		}
	}
	
	int findIndexOfMax() {
		int valueK, currValue, idx=0;
		currValue = myFreqs.get(idx);
		for (int k=1; k< myFreqs.size(); k++) {
			valueK = myFreqs.get(k);
			if (valueK > currValue) {
				//System.out.println("curr max: " + max + " - value: " + value);
				currValue=valueK;
				idx = k;
				//System.out.println("new max: " + max);
			}
		}
		return idx;		
	}

	void tester() {
		findUnique();
		System.out.println("Number of unique words: " + myWords.size());
		int max = findIndexOfMax();
		System.out.println("word: " + myWords.get(max) + " has highest freq of:" + myFreqs.get(max));
/*		System.out.println("List of unique words: ");
		for (int k=0; k< myWords.size(); k++) {
			System.out.println(myWords.get(k) + " : " + myFreqs.get(k));			
		}
*/
	}
	
	
	public static void main(String[] args) {
		WordFrequencies w2 = new WordFrequencies();
		w2.tester();
	}
}
