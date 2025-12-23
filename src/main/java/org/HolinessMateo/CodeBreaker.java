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
 * Class: CodeBreaker
 *
 * Description:
 *
 * ****************************************
 */
package org.HolinessMateo;

public class CodeBreaker {

    //The number of correct scorePegs needed to win
    private static final int WinnerCount = 4;


    /**
     * Checks the array of scorepegs to see if all the pegs are correct
     *
     * @param scorePegs-{@code Array} the scorePegs being evaluted
     * @return Winner - {@code boolean} whether the player won
     */
    public static boolean checkScorePegs(String[] scorePegs){

        //boolean for whether if the players scorePegs are all correct
        boolean winner = false;
        int correctCount = 0;
        //Checks each peg to see if it is correct
        for (String scorePeg : scorePegs) {
            if (scorePeg.equals("*")) {
                correctCount++;
            }
        }
        //Checks if the correctPegCount matches the amount needed to win
        if(correctCount == WinnerCount){
            winner = true;
        }

        return winner;
    }

    /**
     * Checks the values of guess and compares to the code generated
     *
     * @param guess- {@code String} of the guess inputted by user
     * @param code - {@code String[]} the code generated
     * @return scoringPegs- {@code String[]} the scoring pegs
     *
     */

    public static String[] checkGuess(String guess, String[] code) {
        String[] scoringPegs = {"-", "-", "-", "-"};

        final int  CODE_LENGTH = 4;
        for (int i = 0; i < CODE_LENGTH; i++) {
            Character cCode = code[i].charAt(0);
            Character cguess = guess.charAt(i);
            //Checks if the value is in the same position as the generated code answer
            if (cCode.equals(cguess)) {
                scoringPegs[i] = "*";
            } else {
                //Checks if the value is in the generated code answer
                for (int j = i + 1; j < code.length; j++) {
                    cCode = code[j].charAt(0);
                    if (cguess.equals(cCode)) {
                        scoringPegs[i] = "+";
                    }
                }

            }
        }
        return scoringPegs;
    }


}