package week1;

public class CaesarCipherOO {
	
	private String lowerAlphabet ;
	private String shiftedLowerAlphabet;
	private String upperAlphabet;
	private String shiftedUpperAlphabet;
	public int mainKey;

	public CaesarCipherOO(int key) {
		lowerAlphabet="abcdefghijklmnopqrstuvwxyz";
		shiftedLowerAlphabet=lowerAlphabet.substring(key)+lowerAlphabet.substring(0,key);
		upperAlphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedUpperAlphabet=upperAlphabet.substring(key)+upperAlphabet.substring(0,key);
		mainKey=key;		
	}
	
	public String encrypt (String input) {
		int i, idx;
		char ch, newChar;
		StringBuilder encryptedStr = new StringBuilder(input);		
		for (i=0; i < input.length(); i++ ) {
			ch=input.charAt(i);
			if (Character.isAlphabetic(ch)) {
				if (Character.isLowerCase(ch)) {
					idx = lowerAlphabet.indexOf(ch);
					newChar = shiftedLowerAlphabet.charAt(idx);
				}
				else {
					idx = upperAlphabet.indexOf(ch);
					newChar = shiftedUpperAlphabet.charAt(idx);					
				}
				encryptedStr.setCharAt(i, newChar);
			}
		}					
		return encryptedStr.toString();		
	}
	
	public String decrypt (String input) {
		CaesarCipherOO cc = new CaesarCipherOO(26 - mainKey);
		return (cc.encrypt(input));
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
