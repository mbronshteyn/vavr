package com.mbronshteyn.vavr;

import io.vavr.*;
import io.vavr.control.Option;
import org.junit.Assert;
import org.junit.Test;

public class FunctionTest {
    // Composition
    @Test
    public void testComposition() {
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);

        Assert.assertEquals(add1AndMultiplyBy2.apply(2).intValue(), 6);
    }

    // Lifting
    @Test
    public void testLifting(){
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;

        // Lift the function
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

        // = None
        Option<Integer> i1 = safeDivide.apply(1, 0);
        Assert.assertEquals(i1, Option.none());

        // = Some(2)
        Option<Integer> i2 = safeDivide.apply(4, 2);
        Assert.assertEquals(i2, Option.of(2));

        // Catch IllegalArgumentException
        Function2<Integer, Integer, Option<Integer>> sum = Function2.lift(this::sum);

        // = None
        Option<Integer> optionalResult = sum.apply(-1, 2);
        Assert.assertEquals(optionalResult.toString(), "None");
    }

    int sum(int first, int second) {
        if (first < 0 || second < 0) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }
        return first + second;
    }

    @Test
    public void partialApplication(){
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> add2 = sum.apply(2);

        Assert.assertEquals(add2.apply(4).longValue(), 6L);


        Function5<Integer, Integer, Integer, Integer, Integer, Integer> sumFive = (a, b, c, d, e) -> a + b + c + d + e;
        Function2<Integer, Integer, Integer> add6 = sumFive.apply(2, 3, 1);

        Assert.assertEquals(add6.apply(4, 3).longValue(), 13);
    }

    @Test
    public void testCurrying(){
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> add2 = sum.curried().apply(2);

        Assert.assertEquals(add2.apply(4).longValue(), 6);

        //////////////////////////////////////

        Function3<Integer, Integer, Integer, Integer> sumThree = (a, b, c) -> a + b + c;
        final Function1<Integer, Function1<Integer, Integer>> addThree = sumThree.curried().apply(2);

        Assert.assertEquals(addThree.apply(4).apply(3).longValue(), 9);
    }

    /*
    Memoization is a form of caching. A memoized function executes only once and then returns the result from a cache.
    The following example calculates a random number on the first invocation and returns the cached number on the second invocation.
     */
    @Test
    public void testMemorization(){
        Function0<Double> hashCache =
                Function0.of(Math::random).memoized();

        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();

        Assert.assertEquals(randomValue1, randomValue2, 0.0000001);

    }
}
