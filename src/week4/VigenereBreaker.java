package week4;
import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
    	String slice = "";    	
    	for (int k= whichSlice ; k< message.length() ; k+=totalSlices) {    		
    		slice = slice + message.charAt(k);
    	}
        return slice;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
      //WRITE YOUR CODE HERE
        CaesarCracker cC = new CaesarCracker (mostCommon);
        for (int k = 0 ; k< klength ; k++) {
        	String slice = sliceString (encrypted,k,klength);        	
        	key[k] = cC.getKey(slice);
        }
        
        return key;
    }
    
    public HashSet<String> readDictionary (FileResource fr){
    	HashSet<String> dictionary = new HashSet();
    	int count=0;
    	
    	for (String w : fr.lines()) {
    		dictionary.add(w.toLowerCase()); 
 /*   		
	   		//code for testing method
    		if (w.toLowerCase().equals("mn") || w.toLowerCase().equals("ax") || w.toLowerCase().equals("ax")) {
    			System.out.println("english word:" + w.toLowerCase());    			
    		}
    		//end test
  */  		     		  
    
    	}
    	return dictionary;
    }
    
    public int countWords (String message, HashSet dictionary) {
    	int count=0;    	
    	HashSet<String> validWords = new HashSet<String>();
    	for (String w : message.split("\\W+")) { 
    		if (dictionary.contains(w.toLowerCase())) {
    			count++;
    			//validWords.add(w.toLowerCase());
    		}    		
    	}
    	return count;
    }
    
    public int breakForLanguage (String encrypted , HashSet<String> dictionary) {
    	char mostCommon = mostCommonCharIn(dictionary);
		//System.out.println ("Most Common char is: " +  mostCommon);
    	int numWords=0, maxWords=0, masterKeyLength=0;
    	for (int klength=1;klength <100;klength++) {    		
        	int[] key = tryKeyLength(encrypted, klength, mostCommon);
        	VigenereCipher vC = new VigenereCipher(key);
        	String decrypted = vC.decrypt(encrypted);
        	numWords = countWords(decrypted,dictionary);
 /*      	
        	if (klength==38) {
        		System.out.println ("Valid words for keyLength: " + klength + " are: " + numWords);
        	}
 */       	
        	if (numWords > maxWords) {
        		maxWords = numWords;
        		masterKeyLength = klength;
        	}
    	}
//    	System.out.println ("Valid words at Keylength: " + masterKeyLength + " (in encrypted string): " + maxWords);
    	return masterKeyLength;
    }
    
    
    public char mostCommonCharIn (HashSet<String> dictionary) {
    	char mostCommon='0', currChar;
    	HashMap<Character,Integer> charMap = new HashMap<Character,Integer>();
    	int maxChar=0, temp=0;
    	for (String w : dictionary) {
    		for (int k=0; k< w.length();k++) {
    			currChar = w.toLowerCase().charAt(k);
    			if (charMap.containsKey(currChar)) {
    				temp = charMap.get(currChar);
    				charMap.put(currChar, temp+1);
    			}
    			else {
    				charMap.put(currChar, 1);
    			}
    		}
    	}
    	for (char c : charMap.keySet()) {
    		temp = charMap.get(c);
    		if (temp > maxChar) {
    			maxChar = temp;
    			mostCommon = c;
    		}
    	}
    	return mostCommon;
    }
    
    public void breakForAllLangs (HashMap <String, HashSet<String>> languages, String encrypted) {
    	HashSet<String> langHash; 
    	String language="";
    	int tempKeyLength, keyLength=0, tempWordsCount, wordsCount=0;
    	for (String lname : languages.keySet()) {
    		langHash = languages.get(lname);
    		//System.out.println ("Most common char for: " + lname);
    		tempKeyLength = breakForLanguage(encrypted, langHash);
    		tempWordsCount = countWords(encrypted,langHash);
    		if (tempWordsCount > wordsCount) {
    			wordsCount = tempWordsCount;
    			keyLength = tempKeyLength; 
    			language = lname;
    		}
    		if (tempWordsCount == wordsCount) {
    			System.out.println ("Laguange: " + lname +" WordsCount:" + wordsCount);
    		}
    	}
    	char mostCommon = mostCommonCharIn(languages.get(language));
    	int[] key = tryKeyLength(encrypted, keyLength, mostCommon);    	 
    	VigenereCipher vC = new VigenereCipher(key);
    	String decrypted = vC.decrypt(encrypted);
    	System.out.println ("Language detected: " + language);
    	System.out.println ("Decrypted message: \n" + decrypted.substring(0, 50));
    	
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
    	//FileResource fr = new FileResource("./src/week4/athens_keyflute.txt");
    	FileResource fr = new FileResource("./src/week4/messages/secretmessage3.txt");
    	String encrypted = fr.asString();
    	DirectoryResource dr = new DirectoryResource();
    	HashMap<String, HashSet<String>> langMap = new HashMap<String, HashSet<String>>();
    	HashSet<String> langHash;
    	String language="";
    	for (File f : dr.selectedFiles()) {
    		FileResource langFile = new FileResource(f.getAbsolutePath());
    		language = f.getName();
    		System.out.println ("Reading dictionary for language: " + language);
    		langHash = readDictionary(langFile);
    		langMap.put(language, langHash);
    		System.out.println ("Done reading dictionary for: " + language);
    	}
    	breakForAllLangs(langMap,encrypted);
/*
     	//FileResource fr = new FileResource();    	
    	FileResource fr2 = new FileResource("./src/week4/dictionaries/English");
    	HashSet<String> english = readDictionary(fr2);   	
    	int klength = breakForLanguage(encrypted, english);
    	char mostCommon = 'e';
    	int[] key = tryKeyLength(encrypted, klength, mostCommon); 
    	VigenereCipher vC = new VigenereCipher(key);
    	String decrypted = vC.decrypt(encrypted);
    	System.out.println ("Decrypted \n" + decrypted.substring(0, 50));
 */   	
    }    
}        
