package com.example.cs2340c_team8;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.cs2340c_team8.models.elements.Wall;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.cs2340c_team8", appContext.getPackageName());
    }

    @Test
    public void test1() {
        Wall test = new Wall(1, 1, 5, 4, null);
        assertEquals(1, test.getX(), 1);
    }

    @Test
    public void test2() {
        Wall test = new Wall(1, 3, 5, 4, null);
        assertEquals(3, test.getY(), 1);
    }
}