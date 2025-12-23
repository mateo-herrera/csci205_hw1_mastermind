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
 * Class: CodeMaker
 *
 * Description:
 *
 * ****************************************
 */
package org.HolinessMateo;


public class CodeMaker {

    /**
     * Generates a random {@code String[]} for the code of the game
     *
     * @return - code {@code String[]} used for the game
     */

    public static String[] generateCode(){
        String[] code = new String[4];

        for(int i = 0; i < code.length; i++){
            //makes random int and converts to string
            int random_int = (int)Math.floor(Math.random() * (6-1 +1) +1);
            String num = Integer.toString(random_int);
            code[i] = num;
        }
        return code;
    }



}