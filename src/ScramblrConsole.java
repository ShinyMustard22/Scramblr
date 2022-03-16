import java.util.Scanner;

/**
 * This program is a simple implementation of scrambling words in a block of
 * text, which in this case, is just a line.
 * 
 * @author  Ritam Chakraborty
 * @version February 24, 2022
 */
public class ScramblrConsole {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What should I scramble?");
        String line = scanner.nextLine();

        String scrambledLine = Scramble.processText(line);
        System.out.println(scrambledLine);

        scanner.close();
    }
}