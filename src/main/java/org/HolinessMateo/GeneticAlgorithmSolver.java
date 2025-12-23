/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Holiness Kerandi
 * Section: 02
 * Date: 3/22/24
 * Time: 8:18 AM
 *
 * Project: csci205_hw1_mastermind
 * Package: org.HolinessMateo
 * Class: GeneticAlgorithmSolver
 *
 * Description:
 *
 * ****************************************
 */
package org.HolinessMateo;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Random;


public class GeneticAlgorithmSolver extends Solver {

    //The length of the code used
    private final int CODE_LENGTH = 4;


    public GeneticAlgorithmSolver(int numGames) {
        super(numGames);
    }

    /**
     * Runs the GeneticAlgorithmSolver and prints results
     */
    public void solve() {

        //Time starts
        long startTime = System.nanoTime();
        //The algorithm runs
        Integer[] numOfTurnsPerGame = runGeneticSolver();
        //Time Ends
        long endTime = System.nanoTime();

        double elapsedTime = (endTime - startTime) / 1E9;


        this.stats[0] = "Genetic Algorithm Solver - Statistics: ";
        this.stats[1] = Integer.toString(this.numberOfGames);
        this.stats[2] = String.format("%.1f turns", this.calculateAverage(numOfTurnsPerGame));
        this.stats[3] = String.format("%d turns", this.getSmallestValue(numOfTurnsPerGame));
        this.stats[4] = String.format("%d turns", this.getLargestValue(numOfTurnsPerGame));
        this.stats[5] = String.format("%.2f sec", elapsedTime);

        //Call Result to String
        String result = resultToString(this.stats);
        System.out.println(result);
    }


    /**
     * Manages the game simulations and the number of turns used in each
     *
     * @return numOfTurnsPerGame - {@code Integer[]} the number of turns each game took
     */
    public Integer[] runGeneticSolver() {
        Integer[] numOfTurnsPerGame = new Integer[this.numberOfGames];

        for (int i = 0; i < this.numberOfGames; i++) {
            String[] code = codeMaker.generateCode();
            boolean winner = false;
            int guessesThisGame = 0;
            String previousguess = "";
            do {
                String guess = generateNextGuess(previousguess, guessesThisGame, code);
                String[] scorePegs = codeBreaker.checkGuess(guess, code);
                guessesThisGame++;
                winner = CodeBreaker.checkScorePegs(scorePegs);
                previousguess = guess;
            } while (!winner);
            numOfTurnsPerGame[i] = guessesThisGame;
        }


        return numOfTurnsPerGame;
    }


    /**
     * Generates a new guess based on previous guesses
     *
     * @param previousGuess - {@code String} the last code guessed
     * @param guessCount    - {@code int} the number of guesses in the game so far
     * @param code          - {@code String[]} the code being guessed
     * @return sGuess - {@code String} the next guess being inputted
     */
    private String generateNextGuess(String previousGuess, int guessCount, String[] code) {
        String sGuess = "";

        String[] guess = new String[4];

        //If it's the first guess it generates a random number
        if (guessCount == 0) {
            //Randomly generates a guess
            for (int i = 0; i < guess.length; i++) {
                Random r = new Random();
                int num = r.nextInt(6) + 1;
                guess[i] = Integer.toString(num);
            }

            //If it's not the first guess it uses the evaluation to formulate a new guess
        } else {
            String[] evaluationOfPreviousGuess = evaluateGuess(previousGuess, code);
            for (int i = 0; i < CODE_LENGTH; i++) {
                if (evaluationOfPreviousGuess[i] == null) {
                    //Generate random number since number did not match
                    Random r = new Random();
                    int num = r.nextInt(6) + 1;
                    guess[i] = Integer.toString(num);
                } else {
                    guess[i] = evaluationOfPreviousGuess[i];
                }
            }
        }
        //Converts Array into String
        for (String s : guess) {
            sGuess = sGuess.concat(s);
        }
        return sGuess;

    }


    /**
     * Evaluates the scorePegs from the guess and checks for which numbers were correctly guessed
     *
     * @param guess - {@code String} the guess being inputted to return scorepegs
     * @param code  - {@code String[]} the code being guessed
     * @return numsCorrect - {@code String[]} the numbers that were correct in the guess
     */
    private String[] evaluateGuess(String guess, String[] code) {
        String[] guessChecked = codeBreaker.checkGuess(guess, code);

        String[] numsCorrect = new String[4];

        for (int i = 0; i < CODE_LENGTH; i++) {

            if (guessChecked[i].equals("*")) {
                numsCorrect[i] = Character.toString(guess.charAt(i));
            }

        }
        return numsCorrect;
    }


}