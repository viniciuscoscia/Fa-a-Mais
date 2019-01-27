package com.udacity.gradle.builditbigger;

import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AsyncTaskTest implements JokeRepository.AsyncTaskDelegate{

    private static final String TAG = "NonEmptyStringTest";
    CountDownLatch countDownLatch;

    @Test
    public void test() throws InterruptedException   {
        Log.i(TAG, "Running NonEmptyStringTest test...");
        countDownLatch = new CountDownLatch(1);
        JokeRepository jokeRepository = new JokeRepository(this);
        jokeRepository.execute();
        countDownLatch.await(10, TimeUnit.SECONDS);
    }

    @Override
    public void processFinish(String joke) {
        assertNotNull(joke);
        assertTrue(joke.length() > 0);
        countDownLatch.countDown();
    }
}