package week1;

public class CaesarCipherTwo {

	private String lowerAlphabet ;
	private String shiftedLowerAlphabet1;
	private String shiftedLowerAlphabet2;
	private String upperAlphabet;
	private String shiftedUpperAlphabet1;
	private String shiftedUpperAlphabet2;
	public int key1, key2;
	
	public CaesarCipherTwo(int key1, int key2) {
		// TODO Auto-generated constructor stub
		lowerAlphabet="abcdefghijklmnopqrstuvwxyz";
		shiftedLowerAlphabet1=lowerAlphabet.substring(key1)+lowerAlphabet.substring(0,key1);
		shiftedLowerAlphabet2=lowerAlphabet.substring(key2)+lowerAlphabet.substring(0,key2);
		upperAlphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedUpperAlphabet1=upperAlphabet.substring(key1)+upperAlphabet.substring(0,key1);
		shiftedUpperAlphabet2=upperAlphabet.substring(key2)+upperAlphabet.substring(0,key2);
		this.key1=key1;
		this.key2=key2;
	}
	
	public String encrypt (String input) {
		int i, idx;
		char ch, newChar=' ';
		StringBuilder encryptedStr = new StringBuilder(input);			
		for (i=0; i < input.length(); i++ ) {
			ch=input.charAt(i);			
			if (Character.isAlphabetic(ch)) {				
				if (Character.isLowerCase(ch)) {					
					idx = lowerAlphabet.indexOf(ch);
					if ((i+2)%2==0) { 
						newChar = shiftedLowerAlphabet1.charAt(idx); //lowerCase & EVEN					
					}
					else {
						newChar = shiftedLowerAlphabet2.charAt(idx); //lowercase & ODD
					}
				}
				else {
					idx = upperAlphabet.indexOf(ch);
					if ((i+2)%2==0) {
						newChar = shiftedUpperAlphabet1.charAt(idx); //UPPERCASE & EVEN				
					}
					else {
						newChar = shiftedUpperAlphabet2.charAt(idx); //UPPERCASE & ODD
					}									
				}
			} 
			else newChar=ch;
			
			encryptedStr.setCharAt(i, newChar);
		}
		return encryptedStr.toString();
	}

	public String decrypt(String encrypted) {
/*		String half0=halfOfString(encrypted,0);
		String half1=halfOfString(encrypted,1);
		int key1=getKey(half0);
		int key2=getKey(half1);
		System.out.println("Key1: " + key1 + " Key2:" + key2);*/
		int key1 = this.key1;
		int key2=this.key2;
		CaesarCipherTwo cc = new CaesarCipherTwo(26-key1, 26-key2);
		String message = cc.encrypt(encrypted);		
		System.out.println("Message is:" + message );
		return message;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
