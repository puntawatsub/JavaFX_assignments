package stringManipulator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringManipulatorTest {

    private static StringManipulator stringManipulator;

    @BeforeAll
    static void setup() {
        stringManipulator = new StringManipulator();
    }

    @Test
    void concatenate() {
        assertEquals("ban1ana", stringManipulator.concatenate("ban", "1ana"));
    }

    @Test
    void findLength() {
        assertEquals(8, stringManipulator.findLength("qwertyui"));
    }

    @Test
    void convertToUpperCase() {
        assertEquals("TEST TEXT1!", stringManipulator.convertToUpperCase("TesT teXt1!"));
    }

    @Test
    void convertToLowerCase() {
        assertEquals("test text2#", stringManipulator.convertToLowerCase("TesT teXt2#"));
    }

    @Test
    void containsSubstring() {
        assertTrue(stringManipulator.containsSubstring("This is a testmessage", " test"));
        assertFalse(stringManipulator.containsSubstring("This is atest", "test "));
    }
}