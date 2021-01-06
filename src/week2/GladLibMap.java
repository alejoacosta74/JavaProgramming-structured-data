package week2;
import edu.duke.*;
import java.util.*;

public class GladLibMap {
	private HashMap<String,ArrayList<String>> myMap;
	private ArrayList<String> wordsUsed = new ArrayList<String>();
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
		myMap = new HashMap<String,ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();		
	}
	
	public GladLibMap(String source){
		myMap = new HashMap<String,ArrayList<String>>();
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		String[] labels = {"adjective", "noun", "color", "country", "name", "animal", "time", "verb", "fruit" } ;
		ArrayList<String> tempList;
		for (String s: labels) {
			tempList = readIt(source+"/" + s +".txt");
			myMap.put(s, tempList);
		}				
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {		
		if (label =="number") {
			return ""+myRandom.nextInt(50)+5;
		}
		ArrayList<String> tempList;
		tempList = myMap.get(label);
		if (tempList.isEmpty()) {
			return "**UNKNOWN**";
		}
		else return randomFrom(tempList);	
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}		
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		boolean seenWord = wordsUsed.contains(sub);
		if (!seenWord) {
			wordsUsed.add(sub);
		}
		else {
			while (seenWord) { 
			sub = getSubstitute(w.substring(first+1,last));
			seenWord = wordsUsed.contains(sub);		
			}					
		}
//		System.out.println("first idx:" +  first + " word:" + w + " last idx:" + last);
//		System.out.println("prefix:" +  prefix + " suffix:" + suffix + " substitute:" + sub);
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	void totalWordsInMap () {
		ArrayList<String> tempList;
		int numWords=0;
		for (String key : myMap.keySet()) {
			tempList = myMap.get(key);				
			numWords+= tempList.size();
		}
		System.out.println("Cantidad total de palabras en el HashMap: " + numWords);
	}
	
	void totalWordsConsidered () {		
		System.out.println("Cantidad total de palabras usadas: " + wordsUsed.size());
	}
	
	public void makeStory(){
		//wordsUsed.add("");
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("\n Words replaced: \n" );
		printOut(wordsUsed.toString(), 60);
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
