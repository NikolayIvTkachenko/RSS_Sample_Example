package com.rsh.nikolay.samplerssexample.View;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rsh.nikolay.samplerssexample.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() throws Exception {


    }


    @Test
    public void pressFabDownloadRss(){
        onView(withId(R.id.fab)).perform(click());

        SystemClock.sleep(10000);
    }




}