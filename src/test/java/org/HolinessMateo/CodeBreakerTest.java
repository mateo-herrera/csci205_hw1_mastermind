package org.HolinessMateo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeBreakerTest {

    //Declares CodeBreaker object
    private CodeBreaker codeBreaker;


    //Declares guess String
    private String guess;

    @BeforeEach
    void setUp() {
        codeBreaker = new CodeBreaker();
    }

    @AfterEach
    void tearDown() {
    }

    //Tests checkScorePegs to ensure a winner is recognized correctly
    @Test
    void checkScorePegs() {

        //Test 1 should result in a winner
        String[] scorePegs1 = {"*","*","*","*"};
        boolean result1 = CodeBreaker.checkScorePegs(scorePegs1);
        boolean expected1 = true;
        assertEquals(expected1,result1);

        //Test 2 should not result in a winner
        String[] scorePegs2 = {"-","*","*","*"};
        boolean result2 = CodeBreaker.checkScorePegs(scorePegs2);
        boolean expected2 = false;
        assertEquals(expected2, result2);
    }

    //Tests to ensure that the checkGuess properly compares the user guess and the actual code
    @Test
    void checkGuess() {
        //First test, should have the guess match the code
        guess = "2436";
        String[] code1 = {"2","4","3","6"};

        String[] result1 = codeBreaker.checkGuess(guess, code1);
        String[] expected1 = {"*","*","*","*"};
        assertArrayEquals(expected1,result1);

        //Second test should result in half the guess matching the code
        guess = "1345";
        String[] code2 = {"1","3","2","6"};

        String[] result2 = codeBreaker.checkGuess(guess, code2);
        String[] expected2 = {"*","*","-","-"};
        assertArrayEquals(expected2,result2);

        //Third test should result in 2 values of the guess being the code but in the wrong position
        guess = "3345";
        String[] code3 = {"2","2","3","3"};

        String[] result3 = codeBreaker.checkGuess(guess, code3);
        String[] expected3 = {"+","+","-","-"};
        assertArrayEquals(expected3, result3);


    }
}