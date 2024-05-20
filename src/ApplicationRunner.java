
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ApplicationRunner {

    final static ArrayList<Character> reenteredCharacterList = new ArrayList<>(); //List of repeated characters inputted by the user.
    final static ArrayList<Character> wrongCharactersList = new ArrayList<>(); //List of worng characters inputted by the user.

    public static void startingPoint(char[] letterDisplay, int noOfWrongGuesses) { //This is the order of dipslay when the user first enters to play the game.
        System.out.println();
        String displayLetter = Arrays.toString(letterDisplay).replaceAll("\\[|\\]|\"", "").replaceAll(",", ""); //This regex formats the array after its converted into a string to remove the brackets, and commas.
        System.out.print(displayLetter); //Displays the dashes accroding to the selected word in the console.
        System.out.println();
        wrongCharList(noOfWrongGuesses); //Displays the wrong list to the console.
        showMessage(); //Displays a message to the console.
    }

    public static String wrongCharList(int noOfWrongGuesses) {  //This method is used for displaying the wrong charcters which are inputted by the users. 

        //This is used for formatting the wrong list.
        String wrongCharacters = wrongCharactersList.toString().replaceAll(",", " ").replace("*", "").replace("[", "[ ").replace("]", " ]").replaceAll("\\s+", " ");

        //This prints out the list whenever the user enters a character.
        System.out.println(noOfWrongGuesses + " wrong guess so far " + wrongCharacters);
        return wrongCharacters;
    }

    public static void showMessage() {
        System.out.println("Have a guess (lower case letter or * to give up)"); //This message is being displayed underneth the wrong charcters array list. 
    }

    public static void displayWordCharDashes(String word, char[] letterDisplay) {
        int i = 0;
        while (i < word.length()) { //This checks the length of the word randomly selected
            letterDisplay[i] = '_'; //This dash is used to represent each character of the randomely selected word.
            if (word.charAt(i) == ' ') {
                letterDisplay[i] = ' ';
            }
            i++;
        }
    }

    public static void charEnteredCorrect(String word, char enterCharacter, char[] letterDisplay, int noOfWrongGuesses) {
        if (word.contains(enterCharacter + "")) { //Checking if the character entered is in the actual word.
            for (int y = 0; y < word.length(); y++) { //This loop will check all indexs for the character.
                if (word.charAt(y) == enterCharacter) {    //The character will replace the dashes ('_') by a character.
                    letterDisplay[y] = enterCharacter;  //The actual character  
                    System.out.println();
                    String displayLetter = Arrays.toString(letterDisplay).replaceAll("\\[|\\]|\"", "").replaceAll(",", ""); //This regex formats the array after its converted into a string to remove the brackets, and commas.
                    System.out.print(displayLetter); //Displays the dashes and the correct characters entered in the console.
                    System.out.println();
                    wrongCharList(noOfWrongGuesses); //displays the wong list
                    showMessage(); //Displays message
                }
            }
        }
    }

    public static void alreadyEntered() {
        System.out.println();
        System.out.println("> Already Entered Try Again"); //This message is used to alert the user that they have already entered this character.
    }

    public static String charEnteredIncorret(char enterCharacter, char[] letterDisplay, int noOfWrongGuesses) {
        wrongCharactersList.add(enterCharacter); //When every a user enters a wrong roundDuration then that character gets added to this wrong list.
        System.out.println();
        String displayLetter = Arrays.toString(letterDisplay).replaceAll("\\[|\\]|\"", "").replaceAll(",", ""); //This regex formats the array after its converted into a string to remove the brackets, and commas.
        System.out.print(displayLetter); //Displays the dashes and the correct characters entered in the console.
        System.out.println();
        wrongCharList(noOfWrongGuesses); //Displays the wrong characters list.
        showMessage(); //Displays the message.

        return displayLetter;
    }

    public static void userWins(String word) {
        System.out.println();
        System.out.println();
        System.out.println("The hidden word was " + word.toUpperCase()); //If the user guesses the word correctly then the word will be displayed in upper case.
        System.out.println("You Win! :-)"); //This will alert the user that they have won this round.
        System.out.println();
        System.out.println();
    }

    public static void userLoses(String word) {
        System.out.println();
        System.out.println();
        System.out.println("The hidden word was " + word.toUpperCase()); //If the user is out of guesses the the word will be displayed in upper case.
        System.out.println("You lose :-("); //This will alert the user that they have lost this round.
        System.out.println();
        System.out.println();
    }

    public static void userQuits(char[] letterDisplay, int noOfWrongGuesses, String word) {
        System.out.println("You gave up!"); //This message is used for showing the user that they have quit.
        System.out.println();
        String displayLetter = Arrays.toString(letterDisplay).replaceAll("\\[|\\]|\"", "").replaceAll(",", ""); ////This regex formats the array after its converted into a string to remove the brackets, and commas.
        System.out.println(displayLetter); //This display the letter dashes with any of the correct charcaters that might of been inputted.
        wrongCharList(noOfWrongGuesses); //Displays the wrong list.
        System.out.println();
        System.out.println();
        System.out.println("The hidden word was " + word.toUpperCase()); //When the user quits the word is automaticlly displayed in an upper case.
        System.out.println("You lose :-("); //This will alert the user that they have lost this round.
        System.out.println();
        System.out.println();
    }

    public static void playGame(int life, String word, char[] letterDisplay, int noOfWrongGuesses) { //This is when the user is playing a round

        while (life > 0) { //This loop will run util the life is greater than 0. At the start the life is set to 10.

            Scanner readCharacters = new Scanner(System.in); // To Read the Characters

            System.out.print("> ");
            char enterCharacter = readCharacters.next().charAt(0);  //Character input by the user

            if (reenteredCharacterList.contains(enterCharacter)) { //This is checking if the charcter is already entered.
                alreadyEntered(); //Using this method to display the message.
                continue;   //The loop is contuining.
            } else if (enterCharacter == '*') { //This is when the charcters enters the astrick to quit. 
                userQuits(letterDisplay, noOfWrongGuesses, word); //Runs the method when the users quits.
                break; //It breaks out of this loop to end this round.
            }

            reenteredCharacterList.add(enterCharacter); //This adds all the re-entered charcters into the list.

            charEnteredCorrect(word, enterCharacter, letterDisplay, noOfWrongGuesses); //Rns this method when the chracter enetred by the user is correctly entered.

            if (!word.contains(enterCharacter + "")) { //This means if users enters a character and its not part of the randomely selected word.
                life--;  //The life is decreased depending on how many times the users gets the character wrong. 
                noOfWrongGuesses++; //Shows how many times you have enterted the character incorrectly.
                charEnteredIncorret(enterCharacter, letterDisplay, noOfWrongGuesses); //runs the method of when the wrong character is entered.
            }

            if (word.equals(String.valueOf(letterDisplay))) {  //Checking if the letterDisplay equals the actual word, in this case the user would win the round.
                userWins(word); //Runs the method when the user wins/
                break; //It breaks out of this loop to end this round.
            }

            if (life == 0) { //Once the life is equal to 0 then the users has lost this round.
                userLoses(word); //Runs the method when the user losses.
                break; //It breaks out of this loop to end this round.
            }
        }
    }

    public static void roundDuration(String word, int life, int noOfWrongGuesses) {

        char[] letterDisplay = new char[word.length()]; //varible to display the characters with dashes.

        displayWordCharDashes(word, letterDisplay); //Displays the word in dashes.

        startingPoint(letterDisplay, noOfWrongGuesses); //Displays at the start of a round.

        playGame(life, word, letterDisplay, noOfWrongGuesses); //the user plays the game.

    }

    public static void readFile(List words) {
        String fromPath = System.getProperty("user.dir") + File.separator + "wordlist.txt";
        File myInputFile = new File(fromPath);

        try {

            BufferedReader readingCharacters = new BufferedReader(new FileReader(myInputFile));

            String currentLine;
            while ((currentLine = readingCharacters.readLine()) != null) { //reads all the characters in the file.
                words.add(currentLine); //adds the words/characters to the words list.
            }

        } catch (IOException e) {
            System.out.println("File is not found in " + e.getMessage()); //Shows the error message that the file is not found.
            System.exit(0); //Exits the system is the file is not found.
        }
    }

    public static void round() {
        List<String> words = new ArrayList<>(); //defines the list where the words/characters are going to go inside.

        //Reading the file.
        readFile(words);

        //Amount of life the user has avaliable for one round is set and intilized.
        int life = 10;

        //Amout of wrong guesses are intilized to 0 at the beggining.
        int noOfWrongGuesses = 0;

        //Randomizing to pick up a word from arraylist
        Random rand = new Random();

        //Genereates a random word.
        int generateRandomWord = rand.nextInt(words.size());

        //Gets the randomly selected word. 
        String word = words.get(generateRandomWord);

        //Calling the method to play a round.
        roundDuration(word, life, noOfWrongGuesses);

        reenteredCharacterList.clear(); //Clearing the list after the round.
        wrongCharactersList.clear(); //Clearing the list after the round.

    }

    public static void startWordGuessingGame() {

        Scanner userChoice = new Scanner(System.in); //Creates a scanner object for the users choice.

        System.out.println("Word Guessing Game");
        System.out.print("Play (1) or Exit (0) > "); //This will be displayed when the users starts with the game at the very beggining.

        while (userChoice.hasNextLine()) { //At the beggining of each round a user is given a choice to continue or quit.

            String input = userChoice.next();

            if (input.equalsIgnoreCase("0")) { //if user enters 0.
                System.exit(0); //The program will terminate as the user has decided to quit.

            } else if (input.equalsIgnoreCase("1")) { //if user enters 1.
                round(); //This method will be called to play another round. 
                System.out.print("Play (1) or Exit (0) > "); //At the end of the wroing the user will have the option to chose again and he will be alerted by the meaage.
            }
        }
    }

    public static void main(String[] args) {

        //This method is starting the word guessing game inside the main method.
        startWordGuessingGame();

    }

}
