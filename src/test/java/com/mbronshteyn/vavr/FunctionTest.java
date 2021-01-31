package com.mbronshteyn.vavr;

import io.vavr.Function1;
import io.vavr.Function2;
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

        System.out.println(add1AndMultiplyBy2.apply(2));
    }

    // Lifting
    @Test
    public void testLifting(){
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;

        // Lift the function
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

        // = None
        Option<Integer> i1 = safeDivide.apply(1, 0);
        System.out.printf( "None: %s\n",i1.toString());

        // = Some(2)
        Option<Integer> i2 = safeDivide.apply(4, 2);
        System.out.printf("Some: %s\n\n", i2.get());

        // Catch IllegalArgumentException
        Function2<Integer, Integer, Option<Integer>> sum = Function2.lift(this::sum);

        // = None
        Option<Integer> optionalResult = sum.apply(-1, 2);
        System.out.println(optionalResult.toString());
    }

    int sum(int first, int second) {
        if (first < 0 || second < 0) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }
        return first + second;
    }
}
