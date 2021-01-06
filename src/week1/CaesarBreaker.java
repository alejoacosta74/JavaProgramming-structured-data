package week1;

import edu.duke.FileResource;

public class CaesarBreaker {
	
	void countLetters (String message, int[] counts) {
		char ch;
		int idx;
		CaesarCipher myCaesar = new CaesarCipher();
		for(int i=0; i< message.length(); i++){
			ch=message.charAt(i);
			ch=Character.toLowerCase(ch);
			idx= myCaesar.lowerAlphabet.indexOf(ch);
			if (idx!=-1) {
				counts[idx]++;
			}
		}		
	}
	
	int maxIndex (int[] counts) {
		WordLengths myIdx = new WordLengths();
		return (myIdx.indexOfMax(counts));
	}
	
	String decrypt (String encrypted) {
		int dkey, maxDex;
		int[] count = new int[26];
		countLetters(encrypted, count);
		maxDex=maxIndex(count);
		dkey=maxDex-4;
		if (maxDex<4) dkey = 26-(4-maxDex);
		CaesarCipher cc = new CaesarCipher();
		String message = cc.encrypt(encrypted, 26 - dkey);
		return message;
	}
	
	void testDecrypt (String encrypted) {
		String message = decrypt(encrypted);
		System.out.println("Encrypted: " + encrypted + "// Decrypted:"+ message);
	}
	
	String halfOfString (String message, int start) {
		char ch;		
		StringBuilder s = new StringBuilder();		
		for (int i=start; i< message.length();i=i+2) {
			ch = message.charAt(i);			
			s.append(ch);
		}
		return s.toString();
		
	}
	
	int getKey (String message) {
		int dkey, maxDex;
		int[] count = new int[26];
		countLetters (message, count);
		maxDex=maxIndex(count);
		dkey=maxDex-4;
		if (maxDex<4) dkey = 26-(4-maxDex);
		return dkey;		
	}
	
	void decryptTwoKeys(String encrypted) {
		String half0=halfOfString(encrypted,0);
		String half1=halfOfString(encrypted,1);
		int key1=getKey(half0);
		int key2=getKey(half1);
		System.out.println("Key1: " + key1 + " Key2:" + key2);
		CaesarCipher cc = new CaesarCipher();
		String message = cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);		
		System.out.println("Message is:" + message );
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CaesarBreaker w1 = new CaesarBreaker();
//		w1.testDecrypt("Cfopq bbbbb");
//		String half= w1.halfOfString("Qbkm Zgis", 1);
//		System.out.println("Half string: " + half);
//		FileResource fr = new FileResource("src/week1/mysteryTwoKeysQuiz.txt");
//		String encrypted=fr.asString();
//		System.out.println("Encrypted mesage: " + encrypted);
		String q8="Top ncmy qkff vi vguv vbg ycpx";
		System.out.println("Q8: Encrypted mesage: " + q8);
		w1.decryptTwoKeys(q8);
		CaesarCipher w1b = new CaesarCipher();
		String resp= w1b.encryptTwoKeys(q8,2, 20);
		System.out.println("Q8: resp2: " + resp);
		String resp3= w1b.encryptTwoKeys(q8,24, 6);
		System.out.println("Q8: resp2: " + resp3);

	}

}
