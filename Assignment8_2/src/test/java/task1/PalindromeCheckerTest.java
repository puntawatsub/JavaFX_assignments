package task1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeCheckerTest {

    private static PalindromeChecker checker;

    @BeforeAll
    static void setup() {
        checker = new PalindromeChecker();
    }

    @Test
    void isPalindrome() {
        assertTrue(checker.isPalindrome("radar"));
        assertTrue(checker.isPalindrome("A man, a plan, a canal, Panama"));
        assertFalse(checker.isPalindrome("hello"));
        assertTrue(checker.isPalindrome("Saippuakivikauppias"));
        assertFalse(checker.isPalindrome("Metropolia"));
    }
}