package com.udacity.gradle.builditbigger;

import android.test.ActivityInstrumentationTestCase2;

import java.util.concurrent.TimeUnit;

/**
 * Created by chan1cyrus2 on 11/21/2015.
 */

public class EndpointsAsyncTaskTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public EndpointsAsyncTaskTest(){
        super(MainActivity.class);
    }

    private MainActivity mMainActivity;

    @Override
    protected void setUp() throws Exception {
        mMainActivity = getActivity();
    }

    public void testVerifyAsyncTask(){
        try {
            EndpointsAsyncTask jokeTask = new EndpointsAsyncTask();
            jokeTask.execute(mMainActivity);
            String joke = jokeTask.get(30, TimeUnit.SECONDS);
            assertNotNull(joke);
            assertFalse(joke.length() == 0);
        } catch (Exception e){
            fail("Timed out");
        }
    }
}

