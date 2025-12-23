/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Mateo Herrera, Holiness Kerandi
 * Section: 2:00pm
 * Date: 3/7/2024
 * Time: 10:39 AM
 *
 * Project: csci205_hw1_mastermind
 * Package: org.HolinessMateo
 * Class: GameManager
 *
 * Description:
 *
 * ****************************************
 */
package org.HolinessMateo;

import java.util.Scanner;

public class GameManager {
    //guess inputted by user
    private String playersGuesses;

    //Board being used in the game
    private final Board gameBoard = new Board();

    //Gets guessCount from Board
    private int guessCount = Board.getNumberOfGuesses();

    //Creates a new codeMaker object
    private final CodeMaker codeMaker = new CodeMaker();

    //Creates a new codeBreaker Object
    private final CodeBreaker codeBreaker = new CodeBreaker();

    //Determines if the game continues to loop
    private boolean quit = false;

    //If the player has won
    private boolean winner = false;
    //Initializes code to be guessed from codeMaker object
    private String[] code;
    //Defines gamemMode boolean to determine whether player wants play game mode or solver mode
    private boolean gameMode = false;


    /**
     * Runns the game
     */
    public void runGame() {

        Scanner scnr = new Scanner(System.in);

        //Loops through game till user quits
        while (!quit) {

            //Resets the guessCount
            Board.clearBoard();
            guessCount = Board.getNumberOfGuesses();

            //Resets in case they in the previous game
            winner = false;

            //Creates new code
            code = codeMaker.generateCode();

            //Start the game
            System.out.println("Welcome to Mastermind!");
            System.out.println("Crack the code, using numbers between 1 and 6." +
                    "\nIf you guess a correct number '+' will appear. If you guess the correct number in the correct space " +
                    "'*' will appear.");


            //Asks user if the would like to play game mode or use a solver mode
            boolean mode = askUserForMode(scnr);

            if (mode) {
                System.out.println("You have 12 guesses left:");
                runGameMode(scnr);

            } else {
                askUserForSolverType(scnr);
                quit = true;
            }

        }
        System.out.println("GoodBye!");


    }


    /**
     * Asks the user for the type of solver, the number of games simulated
     * Ensures the input entered is valid
     */

    public void askUserForSolverType(Scanner scnr) {


        //Used to ensure correct value for type of solver was inputted
        boolean valid1 = false;

        SolverType sType = null;
        int numGames;


        do {
            System.out.println("Which solver would you like to use?");
            System.out.println("Input 1 for RandomSolver, \n2 for MiniMaxSolver, \n3 for GeneticAlgorithmSolver");
            String input = scnr.next();

            switch (input) {
                case "1" -> {
                    sType = SolverType.RandomSolver;
                    valid1 = true;
                }
                case "2" -> {
                    sType = SolverType.MinimaxSolver;
                    valid1 = true;
                }
                case "3" -> {
                    sType = SolverType.GeneticAlgorithmSolver;
                    valid1 = true;
                }
            }
        } while (!valid1);


        do {
            System.out.println("Enter the number of games you wish to simulate.");
        }
        while (!scnr.hasNextInt());

        numGames = scnr.nextInt();
        if (sType.equals(SolverType.RandomSolver)) {
            RandomSolver rSolver = new RandomSolver(numGames);
            rSolver.solve();
        }else if(sType.equals(SolverType.MinimaxSolver)){
            MiniMaxSolver miniMaxSolver = new MiniMaxSolver(numGames);

        }else {
            GeneticAlgorithmSolver geneticAlgorithmSolver = new GeneticAlgorithmSolver(numGames);
        }


    }

    /**
     * Runs the game in game mode with user input for the guesses
     *
     * @param scnr - {@code Scanner} that scans for user input
     */

    public void runGameMode(Scanner scnr) {

        //loops through till there are no guesses left or the player won
        while ((12 - guessCount > 0) && !winner) {

            //Gets users guess
            validUserInput(scnr);

            //Evalutes the guess and returns score pegs
            String[] guessEvaluation = codeBreaker.checkGuess(playersGuesses, code);

            //Places score pegs and returns whether player is a Winner
            boolean winnerCheck = gameBoard.placeScoringPegs(playersGuesses, guessEvaluation);
            if (winnerCheck) {
                winner = true;
            }
        }

        //If the ends or if the guesses are over, then call play again?
        if (guessCount == 12) {
            System.out.println("No more guesses left");
        }
        playAgain(scnr);

    }


    /**
     * Asks the user what mode they would like to play either game or solver mode
     *
     * @param scnr - Scanner object
     * @return gamemode - {@code boolean} determines the game mode played
     */
    public boolean askUserForMode(Scanner scnr) {
        boolean valid = false;
        do {
            System.out.println("Would you like to play in game mode or solver mode?(S= solver mode, G = game mode)");
            String input = scnr.next();

            if (input.equalsIgnoreCase("g")) {
                gameMode = true;
                valid = true;

            } else if (input.equalsIgnoreCase("s")) {
                gameMode = false;
                valid = true;
            }


        } while (!valid);

        return gameMode;
    }


    /**
     * Checks that the input is valid and puts the guess in an array of Strings
     *
     * @param scnr ,the scanner that helps us read the input
     */


    public void validUserInput(Scanner scnr) {
        //For loop that executes till valid input is inserted
        boolean valid = false;
        do {
            //Asks user for input
            guessCount = Board.getNumberOfGuesses();
            String s = String.format("Guess  %d: ", guessCount);
            System.out.print(s);
            playersGuesses = scnr.next();


            //Checks if the users guess is valid:  4 numbers and between 1 and 6:(maybe throw an exception)
            if (!playersGuesses.matches("[1-6]{4}") || playersGuesses.length() != 4) {
                System.out.println("Invalid Input: The characters should be 4 digits long and between 1 & 6");

            } else {
                valid = true;

            }
        } while (!valid);

        String[] splitStringGuess = playersGuesses.split("");
        String[] addGuessToArray = new String[splitStringGuess.length];

        System.arraycopy(splitStringGuess, 0, addGuessToArray, 0, splitStringGuess.length);
    }


    /**
     * Takes user input on whether they would like to play another game
     * @param scnr - object used to read screen for input inserted by user
     */
    public void playAgain(Scanner scnr) {

        System.out.println("Would you like to play again? [Y,N]");

        String playAgainResponse = scnr.next();

        while (!playAgainResponse.equalsIgnoreCase("Y") &&
                !playAgainResponse.equalsIgnoreCase("N")) {
            System.out.println("Enter N to quit or Y to continue playing ");
        }

    }

}




