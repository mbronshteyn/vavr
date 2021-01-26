package com.mbronshteyn.vavr;

import io.vavr.Function1;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FuncInterfaceTest {
    @Test
    public void givenVavrFunction_whenWorks_thenCorrect() {
        Function1<Integer, Integer> square = (num) -> num * num;
        int result = square.apply(2);

        assertEquals(4, result);
    }
}
