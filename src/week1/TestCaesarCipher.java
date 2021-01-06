package week1;

import edu.duke.FileResource;

public class TestCaesarCipher {
	
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
	
	void breakCaesarCipher (String encrypted) {
		int dkey, maxDex;
		int[] count = new int[26];
		countLetters(encrypted, count);
		maxDex=maxIndex(count);
		dkey=maxDex-4;
		if (maxDex<4) dkey = 26-(4-maxDex);
		CaesarCipherOO ccX = new CaesarCipherOO(26-dkey);
		String message = ccX.decrypt(encrypted); 
		System.out.println("CaesarBraker - Encription Key is:" + dkey + " - Decrypted message is:" + message);
	}
	
	void simpleTests () {
		FileResource fr = new FileResource();
		String message = fr.asString();
		CaesarCipherOO cc18 = new CaesarCipherOO (18);
		String encrypted = cc18.encrypt(message);
		System.out.println("orig message is:" + message);
		System.out.println("Encrypted message is:" + encrypted);
		String decrypted = cc18.decrypt(encrypted);
		System.out.println("Decrypted message is:" + encrypted);
		
		
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TestCaesarCipher test = new TestCaesarCipher();
		CaesarCipherOO q1 = new CaesarCipherOO(15);		
		String s = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
		String answ1 = q1.encrypt(s);
		CaesarCipher q1bis = new CaesarCipher();
		String answ2 = q1bis.encrypt(s, 15);
		System.out.println("Q1: Encrypted message is:");
		System.out.println("Answ1 (OO):" + answ1);
		System.out.println("   Answer2:" + answ2);
		
		CaesarCipherTwo q2 = new CaesarCipherTwo(21,8);
		answ1 = q2.encrypt(s);
		answ2= q1bis.encryptTwoKeys(s, 21, 8);
		System.out.println("Q2: Encrypted message is:");
		System.out.println("Answ1 (OO):" + answ1);
		System.out.println("   Answer2:" + answ2);		
		
		System.out.println("Q4 - word length errors.txt");
		WordLengths q4 = new WordLengths ();
		//FileResource frTest=new FileResource();
		String path="C:\\Users\\ceaaco\\eclipse-workspace\\Coursera Structured Data\\src\\week1\\errors.txt";
		q4.testCountWordLengths(path);
		
		System.out.println("Q5 - words length manywords.txt");
		WordLengths q5 = new WordLengths ();
		path="src/week1/manywords.txt";
		q5.testCountWordLengths(path);
		
		String encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy";
		CaesarCipherTwo q6 = new CaesarCipherTwo(14,24);
		answ1 = q6.decrypt(encrypted);
		answ2= q1bis.encryptTwoKeys(encrypted, 26-14, 26-24);
		System.out.println("Q6: Decrypted message is:");
		System.out.println("Answ1 (OO):" + answ1);
		System.out.println("   Answer2:" + answ2);
		
		System.out.println("Q7: Encrypted key:");
		encrypted= "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
		TestCaesarCipherTwo q7 = new TestCaesarCipherTwo();
		q7.breakCaesarCipher(encrypted);
		
		System.out.println("Q8: First 5 words:");
		FileResource fr=new FileResource("C:\\Users\\ceaaco\\eclipse-workspace\\Coursera Structured Data\\src\\week1\\mysteryTwoKeysQuiz.txt");
		String message = fr.asString();
		q7.breakCaesarCipher(message);
		
		System.out.println("Q9: key1 and Key2:");		
		
		System.out.println("Q13: Simple class object:");
		Simple item = new Simple(3, "blue");     	
        System.out.println(item);        
        System.out.println(item.mystery(5, "ho"));
		}

}
