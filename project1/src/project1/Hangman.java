package project1;
import java.util.*;

public class Hangman {

	public static void main(String[]args) {
		
		while(true) {
			String difficulty = "";
			int diffLevel;
			String secretWord = "";
			System.out.println("Enter your difficulty: Easy (e), Intermediate (i), or Hard (h)");           //creates a random word and sets the difficulty level
			Scanner sc = new Scanner(System.in);
			difficulty = sc.nextLine();
			diffLevel = difficulty(difficulty);
			RandomWord word = new RandomWord();
			secretWord = word.newWord();
			secretWord = secretWord.toLowerCase();
			System.out.println("the secret word is " + secretWord);
			
			if(diffLevel == 1) {
				easyGame(secretWord);
			}
			
		}
	}
	
	
	public static int difficulty(String d) {
		
		int result = 0;
		
		for(int i = 0;i < d.length();i++) {
			if(d.charAt(i) == 'e') {
				result = 1;
			}else if(d.charAt(i) == 'i') {
				result = 2;
			}else if(d.charAt(i) == 'h') {
				result = 3;
			}
		}
		
		return result;
	}
	
	public static int easyGame(String secretWord) {
        int numGuesses = 15;
        int remainingGuess = secretWord.length();
        char[] wordArr = new char[secretWord.length()]; 
        String[] dashedArr = new String[wordArr.length];
        for(int i = 0; i < wordArr.length;i++) {
            wordArr[i] = secretWord.charAt(i);
        }
        for(int i = 0; i < dashedArr.length;i++) {
            dashedArr[i] = "- ";
        }        
        
        while(numGuesses > 0 && remainingGuess != 0) {
            
        	String letterGuess;
            String spacesGuess;
            Scanner sc = new Scanner(System.in);
            Scanner sc2 = new Scanner(System.in);
            
            System.out.println("You have the following the number of guesses: " + numGuesses);
            System.out.print("Please enter he letter you want to guess: ");
            letterGuess = sc.next();
            System.out.println("Please enter the spaces you want to check (seperated by spaces):");   
            spacesGuess = sc2.next();
            int[] spaceArr = new int[spacesGuess.length()];
            String[] spaceInputArr = spacesGuess.trim().split("\\s+");
            
            //Converts individual string inputs for spaces to integer
            for(int a = 0; a < spaceArr.length; a++) {
                spaceArr[a] = Integer.parseInt(spaceInputArr[a]);
            }
            
            //Runs through the array to check multiple spaces within the word.
            //NOT WORKING AS INTENDED. ex. guess space 4 and 5
            for(int b=0; b < spaceArr.length; b++) {
                int space = spaceArr[b];
                if(wordArr[space] == letterGuess.charAt(0)) {
                    dashedArr[space] = letterGuess;
                    remainingGuess -=1;
                }else {
                    numGuesses-=1;
                }
            }
            
            //Prints what is known of the word (including dashes)
            System.out.println("the word is: ");
            for(int i = 0; i < dashedArr.length;i++) {
                System.out.print(dashedArr[i]);
            }
            System.out.println();
            
            //DEBUG CODE
            System.out.println(remainingGuess);
            
            //If the player fails to guess the correct word.
            if (numGuesses == 0) {
            	System.out.println("You have failed to guess the word... :(");
            
            }
            //If the player guesses the correct word.
            else if(remainingGuess == 0) {
            	System.out.println("You have guessed the word! Congratulations");
            }
        }    
        System.out.println("");
        return 1;
    }
}

/* Get the string input for the number of spaces to work
 	* EX. if they want to check spaces 4 and 5,it should check 4th and 5th index.
 * Difficulties
 * As for the difficulties, I think it would be best to just have 1 game function
 	* rather than an easy, intermediate, and hard function.
 	* I think it could be done by giving e, i, and h values of something like 
 	* 4, 6, and 8. Then, for example, if the user picked e, and the random word
 	* was longer than 4 digits, then the function would go and pick another word
 	* until the word was 4 digits (can be adjusted).
 	* Could be done with a while loop.
 	* If you were to do this, then you'd have to generate the secret word
 	* inside of the while loop, which would also solve the problem of
 	* when the user wants to play a different difficulty.
 * */
