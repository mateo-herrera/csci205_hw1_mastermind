/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Mateo Herrera
 * Section: 2:00pm
 * Date: 3/21/2024
 * Time: 12:41 PM
 *
 * Project: csci205_hw1_mastermind
 * Package: org.HolinessMateo
 * Class: MiniMaxSolver
 *
 * Description:
 *
 * ****************************************
 */
package org.HolinessMateo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A MiniMax Solver Algorithm
 */
public class MiniMaxSolver extends  Solver{

    //The initial guess
    private final String[] INITIAL_GUESS = {"1", "1", "2", "2"};

    /**
     * Constructs a new MiniMax solver with a number of games
     * @param numGames ,number of games to be played
     */
    public MiniMaxSolver(int numGames) {
        super(numGames);
    }

    /**
     * Solves the game and maps the results to a string that will be displayed on the screen
     */

    public void solve(){

        //Time starts
        long startTime = System.nanoTime();

        //Algorithm is run
        Integer[] numOfTurnsPerGame = runMiniMaxSolver();

        //Time Ends
        double elapsedTime = (System.nanoTime()-startTime)/1000000.0;
        this.stats[0] = "MiniMaxSolver";
        this.stats[1] = Integer.toString(this.numberOfGames);
        this.stats[2] = String.format("%.1f turns",this.calculateAverage(numOfTurnsPerGame));
        this.stats[3] = String.format("%d turns",this.getSmallestValue(numOfTurnsPerGame));
        this.stats[4] = String.format("%d turns", this.getLargestValue(numOfTurnsPerGame));
        this.stats[5] = String.format("%.2f sec",elapsedTime);

        //Call Result to String
        String result = resultToString(this.stats);
        System.out.println(result);

    }

    /**
     * Runs the MiniMax solver for the specified number of games
     * @return an array containing the number of turns to win the game
     */

    public Integer[] runMiniMaxSolver(){
        //Counts the number of turns per game
        Integer[] numOfTurnsPerGame = new Integer[this.numberOfGames];
        boolean gameWon = false;


        for (int i = 0; i < numberOfGames; i++) {
            //Getting the code to be generated
            String[] code = CodeMaker.generateCode();

            int numOfTurns = 1;
            //Guessing with the first
            gameWon = CodeBreaker.checkScorePegs(INITIAL_GUESS);

            if(!gameWon) {
                //End everything!
                String[] nextGuess = generateNextGuess(code);
                gameWon = CodeBreaker.checkScorePegs(nextGuess);
                numOfTurns++;

            }

            numOfTurnsPerGame[i] = numOfTurns;
        }

        return numOfTurnsPerGame;
    }

    /**
     * If the first guess was not solved,then it returns the next guess
     * @return the next guess
     */
    String[] generateNextGuess(String[] code) {
        String bestGuess = null;
        int minMaxScore = Integer.MAX_VALUE;
        List<String> allGuesses = generateAllPossibleGuesses();

        for (String guess : allGuesses) {
            int maxScore = Integer.MIN_VALUE;

            for (String response : getAllPossibleResponses()) {
                String[] scorePegs = CodeBreaker.checkGuess(guess, code);
                boolean gameWon = CodeBreaker.checkScorePegs(code);

                if (gameWon) {
                    return guess.split("");
                }

                int score = countCorrect(scorePegs);
                maxScore = Math.max(maxScore, score);
            }

            if (maxScore < minMaxScore) {
                minMaxScore = maxScore;
                bestGuess = guess;
            }
        }
        assert bestGuess != null;
        return bestGuess.split("");
    }


    /**
     * Generates all possible responses for a guess within a checked range
     * @return an array containing all possible responses
     */

    private String[] getAllPossibleResponses() {
        List<String> allPossibleResponses = new ArrayList<>();

        for (int numCorrect = 0; numCorrect <= 4; numCorrect++) {
            for (int numPosition = 0; numPosition <= 4 - numCorrect; numPosition++) {
                String[] response = new String[4];
                int index = 0;
                for (int i = 0; i < numCorrect; i++) {
                    response[index++] = "*";
                }
                for (int i = 0; i < numPosition; i++) {
                    response[index++] = "+";
                }
                while (index < 4) {
                    response[index++] = "-";
                }
                allPossibleResponses.add(Arrays.toString(response));
            }
        }
        return allPossibleResponses.toArray(new String[0]);
    }

    /**
     * Counts the number of correct score pegs
     * @param scorePegs to be counted
     * @return the number of correct guesses
     */
    private int countCorrect(String [] scorePegs) {

        int count = 0;

        for (String peg : scorePegs) {
            if (peg.equals("*")) {
                count++;
            }
        }
        return count;

    }

    /**
     * Generates all possible permutations of the game
     * @return a list of string containing all values
     */

    private List<String> generateAllPossibleGuesses() {
        List <String>  allPossibleCodes = new ArrayList<>();

        for (int i = 1111; i <= 6666; i++) {
            String guess;
            guess = String.format("%04d", i);

            if (!guess.contains("0") && !guess.contains("7")&&!guess.contains("8")&&!guess.contains("9")) {
                allPossibleCodes.add(guess);
            }


        }
        return allPossibleCodes;
        
    }

   


}