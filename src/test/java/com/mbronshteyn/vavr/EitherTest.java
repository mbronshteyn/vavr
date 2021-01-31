package com.mbronshteyn.vavr;

import io.vavr.control.Either;
import org.junit.Assert;
import org.junit.Test;

public class EitherTest {
    @Test
    public void testEitherOne() {
        Either<String, Integer> result = calculateStuff(-1);
        Assert.assertEquals(result.getLeft(), "The argument should be above 0");

        result = calculateStuff(3);
        Assert.assertEquals(result.get().intValue(), 30);
    }

    private Either<String, Integer> calculateStuff(Integer argument) {
        if (argument < 0)
            return Either.left("The argument should be above 0");
        else {
            return Either.right(argument * 10);
        }
    }
}
