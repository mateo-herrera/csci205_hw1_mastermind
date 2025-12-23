/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Mateo Herrera
 * Section: 2:00pm
 * Date: 3/7/2024
 * Time: 10:39 AM
 *
 * Project: csci205_hw1_mastermind
 * Package: org.HolinessMateo
 * Class: Board
 *
 * Description:
 *
 * ****************************************
 */
package org.HolinessMateo;

public class Board {

    //Tracks the Number of guesses
    private static int numberOfGuesses = 1;


    /**
     * Prints out the numberOfGuesses and the guess inputted
     *
     * @param inputGuess- {@code String} the guess inputted by user
     */
    public void placeCodePegs(String inputGuess) {
        String result = String.format("Guess  %d: %s", this.numberOfGuesses, inputGuess);
        System.out.println(result);

    }

    /**
     * Places the recent guess with the ScorePegs, and the amount of guesses left
     *
     * @param inputGuess- {@code String} which is the guess inputted by user
     * @param scorePegs   - {@code String[]} the score pegs being printed
     * @return true if all the pegs are *
     */
    public boolean placeScoringPegs(String inputGuess, String[] scorePegs) {

        //Makes the scorePegs Array into a String
        StringBuilder scorePegStringBuilder = new StringBuilder();
        for (String scorePeg : scorePegs) {
            scorePegStringBuilder.append(scorePeg);
        }
        String scorePegString = scorePegStringBuilder.toString();

        int guessesLeft = 12 - numberOfGuesses;
        boolean scorePegCheck = CodeBreaker.checkScorePegs(scorePegs);

        //prints the result of scorePegCheck

        String sResult;
        if (scorePegCheck) {
            sResult = String.format("%s -->  %s   YOU WON! You guessed the code in %d guesses.", inputGuess, scorePegString, numberOfGuesses);
        }

        //Formats the scorePegs result
        sResult = String.format("%s -->  %s   Try again. %d guesses left.", inputGuess, scorePegString, guessesLeft);

        //increments numberOfGuesses
        numberOfGuesses++;

        //Prints ScorePegs
        System.out.println(sResult);


        return scorePegCheck;
    }

    /**
     * Clears the board by setting the numberOfGuesses back to 1
     */

    public static void clearBoard() {
        numberOfGuesses = 1;
    }

    /**
     * Gets the number of guesses.
     */
    public static int getNumberOfGuesses() {
        return numberOfGuesses;
    }

}