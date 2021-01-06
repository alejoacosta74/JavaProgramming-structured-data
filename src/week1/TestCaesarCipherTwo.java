package week1;

import edu.duke.FileResource;

public class TestCaesarCipherTwo {
	
	String halfOfString (String message, int start) {
		char ch;		
		StringBuilder s = new StringBuilder();		
		for (int i=start; i< message.length();i=i+2) {
			ch = message.charAt(i);			
			s.append(ch);
		}
		return s.toString();		
	}	

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
	
	int getKey (String message) {
		int dkey, maxDex;
		int[] count = new int[26];
		countLetters (message, count);
		maxDex=maxIndex(count);
		dkey=maxDex-4;
		if (maxDex<4) dkey = 26-(4-maxDex);
		return dkey;		
	}

	void breakCaesarCipher(String encrypted) {
		String half0=halfOfString(encrypted,0);
		String half1=halfOfString(encrypted,1);
		int key1=getKey(half0);
		int key2=getKey(half1);
		System.out.println("Key1: " + key1 + " Key2:" + key2);
		CaesarCipherTwo cc = new CaesarCipherTwo(26-key1, 26-key2);
		String message = cc.encrypt(encrypted);
		System.out.println("Comienzo del mensaje luego de BreakCaesarCipher");
		System.out.println(message);	
	}

	
	void simpleTests() {
		FileResource fr=new FileResource();
		String message = fr.asString();
		System.out.println("orig message is:" + message);		
		CaesarCipherTwo cc2 = new CaesarCipherTwo(17,3);
		String encrypted = cc2.encrypt(message);
		System.out.println("Encrypted message is:" + encrypted);
		String decrypted = cc2.decrypt(encrypted);
		System.out.println("Decrypted message is:" + decrypted);
	}
	

	public TestCaesarCipherTwo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
