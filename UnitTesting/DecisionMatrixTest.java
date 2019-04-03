package UnitTesting;

import src.DecisionMatrix;
import src.Run;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import GUI.GUI;

import java.util.Arrays;

//coded by Randy Lee
public class DecisionMatrixTest {
	GUI gui = new GUI();
	Run run = new Run(gui);
    DecisionMatrix test = new DecisionMatrix(run);
    String file = "0-0.txt", user = "yes";
    String[] files_part2 = {"0","0","txt"}, answers = {"1) yes", "2) no"}, ansYes ={"00","0","txt"}, ansNo ={"01","0","txt"};
    @Test
    void testLetsSplit() {
        assertArrayEquals(test.letsSplit(file),files_part2);
    }
    @Test
    void testAnythingElseYes(){
        assertTrue("loop-0.txt".equals(test.anythingElse(user,gui)));
    }
    @Test
    void testAnythingElseNo(){
        assertTrue("end-0.txt".equals(test.anythingElse("no",gui)));
    }
    @Test
    void testThreeOrTwoYes(){
        assertTrue(Arrays.equals(test.threeOrTwo(gui, 2,"yes",answers,files_part2,file),ansYes));
    }
    @Test
    void testThreeOrTwoNo(){
        assertTrue(Arrays.equals(test.threeOrTwo(gui, 2,"no",answers,files_part2,file),ansNo));
    }
}
