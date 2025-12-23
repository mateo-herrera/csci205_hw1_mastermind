/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Mateo Herrera
 * Section: 2:00pm
 * Date: 3/20/2024
 * Time: 6:43 PM
 *
 * Project: csci205_hw1_mastermind
 * Package: org.HolinessMateo
 * Class: RandomSolver
 *
 * Description:
 *
 * ****************************************
 */
package org.HolinessMateo;

import java.util.Arrays;
import java.util.Random;

public class RandomSolver extends Solver {


    public RandomSolver(int numGames) {
        super(numGames);
    }

    /**
     * Random main method that solves the game
     */

    public void solve(){

        //Time starts
        long startTime = System.nanoTime();
        Integer[] numOfTurnsPerGame = runRandomSolver();
        //Time Ends
        double elapsedTime = (System.nanoTime()-startTime)/1000000.0;
        this.stats[0] = "RandomSolver";
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
     * Simulates the numberOfGames inputted using a RandomSolver algorithm
     *
     * @return numOfTurnsPerGame- {@code Integer[]} the number of turns taken to win each game
     * simulated
     */
    public Integer[] runRandomSolver(){
        //Counts the number of turns per game
        Integer[] numOfTurnsPerGame = new Integer[this.numberOfGames];

        for(int i=0 ; i< this.numberOfGames; i++){
            String[] code = this.codeMaker.generateCode();
            //Defines the turnsThisGame
            int turnsThisGame = 0;
            String[] scorePegs;
            //Loops till guess is correct then game is finished
            String guess;
            do{
                guess = randomSolverGuess();
                scorePegs = this.codeBreaker.checkGuess(guess,code);
                turnsThisGame++;
            }while(!CodeBreaker.checkScorePegs(scorePegs));

            //adds the numOfTurns for previous game to array numOfTurnsPerGame
            numOfTurnsPerGame[i] = turnsThisGame;
        }


        return numOfTurnsPerGame;
    }


    /**
     * Generates a random guess using Random
     *
     * @return guess- {@code String[]} containing values 1-6 for the guess
     */
    public String randomSolverGuess(){
        //Randomly generates a guess
        String[] guess = new String[4];
        for(int i=0; i< guess.length; i++){
            Random r = new Random();
            int num = r.nextInt(6)+1;
            guess[i] = Integer.toString(num);
        }
        //Turns the array guess into a string sGuess
        String sGuess ="";
        for(String s : guess){
            sGuess = sGuess.concat(s);
        }
        return sGuess;
    }
}