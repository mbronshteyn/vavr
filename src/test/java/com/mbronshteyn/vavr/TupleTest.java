package com.mbronshteyn.vavr;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
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

    /*
    A tuple's place is in storing a fixed group of objects of any type
    that are better processed as a unit and can be passed around.
    A more obvious use case is returning more than one object from a function or a method in Java.
     */
    @Test
    public void whenCreatesTuple_thenCorrect2() {
        Tuple3<String, Integer, Double> java8 = Tuple.of("Java", 8, 1.8);
        String element1 = java8._1;
        int element2 = java8._2();
        double element3 = java8._3();

        assertEquals("Java", element1);
        assertEquals(8, element2);
        assertEquals(1.8, element3, 0.1);
    }

}
