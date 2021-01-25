package com.mbronshteyn.vavr;

import io.vavr.control.Try;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Try is a container for a computation which may result in an exception.
 */
public class TryTest {

    private Object ArithmeticException;

    /*
        With Vavr, we can wrap the same code in a Try instance and get a result:
         */
    @Test
    public void givenBadCode_whenTryHandles_thenCorrect() {

        Try<Integer> result = Try.of(() -> 1 / 0);

        assertTrue(result.isFailure());
    }

    /*
    We can also choose to return a default value:
     */
    @Test
    public void givenBadCode_whenTryHandles_thenCorrect2() {
        Try<Integer> result = Try.of(() -> 1 / 0);
        int errorSentinel = result.getOrElse(-1);

        assertEquals(-1, errorSentinel);
    }

    /*
    Throw exception of choice
     */
    @Test(expected = ArithmeticException.class)
    public void givenBadCode_whenTryHandles_thenCorrect3() {
        Try<Integer> result = Try.of(() -> 1 / 0);
        result.getOrElseThrow( () -> new ArithmeticException());
    }
}
