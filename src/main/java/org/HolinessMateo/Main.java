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

public class Main {

    //Manages the game
    private static final GameManager gm = new GameManager();

    /**
     * Main game method
     * @param args
     */
    public static void main(String[] args) {

        //Runs the game
        gm.runGame();

    }
}