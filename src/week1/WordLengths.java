package week1;

import edu.duke.FileResource;

public class WordLengths {
	
	void countWordLengths (FileResource resource, int[] counts) {
		for(String word : resource.words()){
		//	StringBuilder w = new StringBuilder(word);
			int firstChar=0, lastChar=word.length();
			if (!Character.isLetter(word.charAt(0))) // remove first non-letter character 
				firstChar=1;
			if (!Character.isLetter(word.charAt(word.length()-1))) // remove last non-letter character
				lastChar=word.length()-1;
//			System.out.println("charindex1: " + firstChar + " charindex2:" + lastChar + " Word:" + word);
			if (lastChar >= firstChar) {
				String trimWord = word.substring(firstChar, lastChar);		
				if (trimWord.length() > counts.length) {
				counts[counts.length]++;
				}
				else counts[trimWord.length()]++;
			}
		
		for (int k=0; k <counts.length; k++) {
		//	System.out.println ("Words of length " + k + " :" + counts[k]);
		}
	}
	}		
	
	int indexOfMax (int[] values) {
		int idxMax=0, valueMax=0;
		for (int k=0; k< values.length; k++) {
			if (values[k] > valueMax) {
				valueMax=values[k];
				idxMax=k;
			}
			
		}
		return idxMax;
	}
	
	void  testCountWordLengths(String path) {
		FileResource fr = new FileResource(path);
		//FileResource fr = new FileResource();
	//	System.out.println("file:" + fr.toString());
		int[] counts = new int[31];
		countWordLengths(fr,counts);
		System.out.println ("Max length :" + indexOfMax(counts));	
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordLengths w1 = new WordLengths();
		//w1.testCountWordLengths();
	}

}
