package week2;

import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {
	
	ArrayList<String> myCharacters;
	ArrayList<Integer> myFreqs;
	
	public CharactersInPlay () {
	myCharacters = new ArrayList<String>();
	myFreqs = new ArrayList<Integer>();
	}
	
	void update (String person) {
		int idx, currValue;
		idx = myCharacters.indexOf(person.toUpperCase());
			if (idx ==-1) {
				myCharacters.add(person.toUpperCase());
				myFreqs.add(1);				
			}
			else {
				currValue = myFreqs.get(idx);
				myFreqs.set(idx, currValue+1);
			}
	}
	
	void  charactersWithNumParts (int num1, int num2) {
			int freq;
			System.out.println("Characters with speaking lines between " + num1 + " and " + num2);
			for (int k=0; k< myCharacters.size(); k++) {
				freq = myFreqs.get(k);
				if (freq > num1 && freq < num2) {
				System.out.println("Character: " + myCharacters.get(k) + " speaks: " + myFreqs.get(k) + " times");
				}
			}		
	}	
	
	void findAllCharacters () {
		FileResource fr = new FileResource();
		myCharacters.clear();
		myFreqs.clear();
		int periodIdx;
		for (String s : fr.lines()) {
			periodIdx = s.indexOf('.');
			if (periodIdx!=-1 && periodIdx < 15) {
				String name = s.substring(0,periodIdx);
				update(name);
			}
		}
	}
	
	void printMainCharacters(int threshold) {
		int freq;
		for (int k=0; k< myCharacters.size(); k++) {
			freq = myFreqs.get(k);
			if (freq > threshold) {
			System.out.println("Character: " + myCharacters.get(k) + " speaks: " + myFreqs.get(k) + " times");
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
		findAllCharacters();
		System.out.println("My characters size: " + myCharacters.size());
/*
		for (int k=0; k< myCharacters.size(); k++) {
			System.out.println(myCharacters.get(k) + " : " + myFreqs.get(k));			
		}
*/		
		printMainCharacters (10);
		charactersWithNumParts(10,15);
		int indexMax=findIndexOfMax();
		System.out.println("Character with most speaking part is: " + myCharacters.get(indexMax) + "  with quantity:" + myFreqs.get(indexMax));
		
	}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CharactersInPlay w2 = new CharactersInPlay();
		w2.tester();

	}

}
