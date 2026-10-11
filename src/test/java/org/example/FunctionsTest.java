package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class FunctionsTest {

    @Test
    void writeMessage() {
        Functions functions = new Functions("gjri666");
        String result = functions.writeMessage("bjki");
        assertEquals("bjki", result);
    }
}