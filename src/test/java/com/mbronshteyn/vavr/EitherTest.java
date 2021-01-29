package com.mbronshteyn.vavr;

import io.vavr.control.Either;
import org.junit.Test;

public class EitherTest {
    @Test
    public void testEitherOne() {
        Either<String, Integer> result = calculateStuff(-1);
        System.out.println(result);

        result = calculateStuff(3);
        System.out.println(result);
    }

    private Either<String, Integer> calculateStuff(Integer argument) {
        if (argument < 0)
            return Either.left("The argument should be above 0");
        else {
            return Either.right(argument * 10);
        }
    }
}
