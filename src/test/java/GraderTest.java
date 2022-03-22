import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraderTest {

    @Test
    void fiftyNineShouldReturnF() {
        Grader grader = new Grader();
        assertEquals('F', grader.determineLetterGrade(59));
    }

    @Test
    void eightyShouldReturnB() {
        Grader grader = new Grader();
        assertEquals('B', grader.determineLetterGrade(80));
    }

    @Test
    void negativeOneShouldReturnIllegalArgumentException() {
        Grader grader = new Grader();

        assertThrows(IllegalArgumentException.class,
                () -> grader.determineLetterGrade(-1));

    }
}