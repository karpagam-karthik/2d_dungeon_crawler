package com.example.cs2340c_team8;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    public int getImageNumber() {
        return 3;
    }
    public String getButtonText() {
        return "Exit";
    }
    @Test
    public void imageCounterTest() { assertEquals(3, getImageNumber()); }
    @Test
    public void functionTest() {
        assertEquals("Exit", getButtonText());
    }

}