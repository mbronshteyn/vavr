package com.mbronshteyn.vavr;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TupleTest {
    /*
    Tuples are immutable and can hold multiple objects of different types in a type-safe manner.
     */
    @Test
    public void whenCreatesTuple_thenCorrect1() {
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        String element1 = java8._1;
        int element2 = java8._2();

        assertEquals("Java", element1);
        assertEquals(8, element2);
    }
}
