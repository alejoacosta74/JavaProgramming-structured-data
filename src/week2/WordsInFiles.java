package week2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordsInFiles {
	
	private HashMap<String, ArrayList<String>> wordMap;
	
	public WordsInFiles() {
		wordMap = new HashMap<String, ArrayList<String>>();
	}
	
	void addWordsFromFile (File f) {
		FileResource fr = new FileResource(f);
		ArrayList<String> tempList;
		for (String w : fr.words()) {
			if (wordMap.containsKey(w)) {
				tempList = wordMap.get(w);
				if (!tempList.contains(f.getName())) {
					tempList.add(f.getName());
					wordMap.put(w,tempList);
				}
			}
			else {
				ArrayList<String> fileList = new ArrayList<String>();
				fileList.add(f.getName());
				wordMap.put(w, fileList);
			}
		}
	}
	
	void buildWordFileMap () {
		DirectoryResource dr = new DirectoryResource();
		wordMap.clear();
		for (File f : dr.selectedFiles()) {
			addWordsFromFile(f);
		}
	}
	
	void maxNumber () {
		int temp, max=0;
		String maxWord="";
		for (String w : wordMap.keySet()) {
			temp = wordMap.get(w).size();
			if (temp > max) {
				max=temp;
				maxWord = w;
			}
		}
		System.out.println("maximum number of files any word appears in is: " + max + " and the word is: " + maxWord);
	}
	
	private ArrayList<String> wordsInNumFiles (int number){
		int temp;
		ArrayList <String> wordList = new ArrayList <String>();
		for (String w : wordMap.keySet()) {
			temp = wordMap.get(w).size();
			if (temp == number) {
				wordList.add(w);
			}
		}
		return wordList;
		
	}
	
	void printFilesIn (String word) {
		ArrayList<String> fileList;
		fileList = wordMap.get(word);
		System.out.println("The word: " + word + " appears in the files:");
		for (int k=0; k < fileList.size();k++) {
			System.out.println(fileList.get(k));
		}
	}
	
	void tester () {
		buildWordFileMap();
		//maximum number of files any word appears in is
		maxNumber();
		//Q12
		ArrayList<String> tempQ12 = wordsInNumFiles(7);
		System.out.println("Q12. The number of words that appears in 7 files are:" + tempQ12.size());
		//Q13
		ArrayList<String> tempQ13 = wordsInNumFiles(4);
		System.out.println("Q13. The number of words that appears in 4 files are:" + tempQ13.size());
		//Q14
		printFilesIn("sea");
		//Q15
		printFilesIn("tree");

		
		//The words that appears in 3 files
		ArrayList<String> fileList;
		fileList = wordsInNumFiles(3);
/*		
		System.out.println("The words that appears in " + " 3 files are:");
		for (int k=0; k < fileList.size();k++) {
			System.out.println(fileList.get(k) + "appears in file: ");
			
		}
*/
		//The files where cats appear
	//	printFilesIn("cats");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WordsInFiles w2 = new WordsInFiles();
		w2.tester();

	}

}
