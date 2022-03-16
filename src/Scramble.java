/**
 * This program is used to scramble the words in a block of text.
 * 
 * @author  Ritam Chakraborty
 * @version Februar®y 24, 2022
 */
public class Scramble {

    /**
     * Scrambles the text provided by the user.
     * 
     * @param text the text to scramble
     * @return the scrambled text
     */
    public static String processText(String text) {
        StringBuffer scrambledText = new StringBuffer(text.length());
        StringBuffer currentWord = new StringBuffer();
        char[] characters = text.toCharArray();

        for (char ch : characters) {
            if (!Character.isAlphabetic(ch) && !Character.isDigit(ch)) {
                String scrambledWord = scrambleWord(currentWord.toString());
                scrambledText.append(scrambledWord);
                scrambledText.append(ch);
                currentWord.delete(0, currentWord.length());
            }

            else {
                currentWord.append(ch);
            }
        }

        String scrambledWord = scrambleWord(currentWord.toString());
        scrambledText.append(scrambledWord);

        return scrambledText.toString();
    }

    private static String scrambleWord(String word) {
        StringBuffer regularWord = new StringBuffer(word);
        StringBuffer scrambledWord = new StringBuffer(word.length());

        while (!regularWord.isEmpty()) {
            int pos = (int) (Math.random() * regularWord.length());
            char currentChar = regularWord.charAt(pos);

            scrambledWord.append(currentChar);
            regularWord.deleteCharAt(pos);
        }

        return scrambledWord.toString();
    }
    
}
