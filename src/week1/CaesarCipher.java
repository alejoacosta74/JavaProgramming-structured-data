package week1;

import edu.duke.FileResource;

public class CaesarCipher {
	
	final String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
	static final String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	String encrypt (String input, int key) {
		int i, idx;
		char ch, newChar;
		StringBuilder encryptedStr = new StringBuilder(input);
		String shiftedLower = lowerAlphabet.substring(key) + lowerAlphabet.substring(0,key);
		String shiftedUpper = upperAlphabet.substring(key) + upperAlphabet.substring(0,key);
		for (i=0; i < input.length(); i++ ) {
			ch=input.charAt(i);
			if (Character.isAlphabetic(ch)) {
				if (Character.isLowerCase(ch)) {
					idx = lowerAlphabet.indexOf(ch);
					newChar = shiftedLower.charAt(idx);
				}
				else {
					idx = upperAlphabet.indexOf(ch);
					newChar = shiftedUpper.charAt(idx);					
				}
				encryptedStr.setCharAt(i, newChar);
			}
		}			
		
		return encryptedStr.toString();
	}
	
	String encryptTwoKeys (String input, int key1, int key2) {
		int i, idx;
		char ch, newChar=' ';
		StringBuilder encryptedStr = new StringBuilder(input);
		String k1shiftedLower = lowerAlphabet.substring(key1) + lowerAlphabet.substring(0,key1);
		String k1shiftedUpper = upperAlphabet.substring(key1) + upperAlphabet.substring(0,key1);
		String k2shiftedLower = lowerAlphabet.substring(key2) + lowerAlphabet.substring(0,key2);
		String k2shiftedUpper = upperAlphabet.substring(key2) + upperAlphabet.substring(0,key2);		
		for (i=0; i < input.length(); i++ ) {
			ch=input.charAt(i);			
			if (Character.isAlphabetic(ch)) {				
				if (Character.isLowerCase(ch)) {					
					idx = lowerAlphabet.indexOf(ch);
					if ((i+2)%2==0) { 
						newChar = k1shiftedLower.charAt(idx); //lowerCase & EVEN
					//	System.out.println("Input char EVEN: " + ch + " Output char: " + newChar);
					}
					else {
						newChar = k2shiftedLower.charAt(idx); //lowercase & ODD
					}
				}
				else {
					idx = upperAlphabet.indexOf(ch);
					if ((i+2)%2==0) {
						newChar = k1shiftedUpper.charAt(idx); //UPPERCASE & EVEN
				//		System.out.println("Input char EVEN: " + ch + " Output char: " + newChar);
					}
					else {
						newChar = k2shiftedUpper.charAt(idx); //UPPERCASE & ODD
					}									
				}
			} 
			else newChar=ch;
			
			encryptedStr.setCharAt(i, newChar);
		}			
		
		return encryptedStr.toString();
	}
	
	void testCaesar () {
		FileResource fr = new FileResource();
		String message = fr.asString();
		int key=23;
		String encrypted = encrypt(message, key);
		System.out.println("key is " + key + "\n" + encrypted);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CaesarCipher w1 = new CaesarCipher();
		String test = w1.encrypt("First eeeee", 23);
		System.out.println("First legion encrypted: " + test);
//		String q5 = w1.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15);
//		System.out.println("Q5: " + q5);
//		String q6= w1.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
//		System.out.println("Q6: " + q6);
//		String test2Keys= w1.encryptTwoKeys("First Legion", 23, 17);
		

	}

}
