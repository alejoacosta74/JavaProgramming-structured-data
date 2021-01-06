package week1;

public class WordPlay {
	
	boolean isVowel (char ch) {
		char chLower= Character.toLowerCase(ch);
		if (chLower=='a' || chLower=='e' || chLower=='i' || chLower=='o' || chLower=='u') return true;
		else return false;
	}
	
	String replaceVowels (String phrase, char ch) {
		int i;
		StringBuilder outPutStr = new StringBuilder(phrase);
		for (i=0; i < phrase.length(); i++ ) {
			char currChar=phrase.charAt(i);
			if (isVowel(currChar)){
				outPutStr.setCharAt(i, ch);
			}			
		}
		return outPutStr.toString();
	}
	
	String emphasize (String phrase, char ch) {
		int i, location;
		StringBuilder outPutStr = new StringBuilder(phrase);
		for (i=0; i < phrase.length(); i++ ) {
			char currChar=phrase.charAt(i);
			location=i+1;
			if (currChar==ch){
				if (location%2==0) { // even location
					outPutStr.setCharAt(i, '+');					
				}
				else {
					outPutStr.setCharAt(i, '*');
				}
				
			}			
		}
		return outPutStr.toString();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
