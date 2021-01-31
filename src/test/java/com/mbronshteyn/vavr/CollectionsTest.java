package com.mbronshteyn.vavr;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class CollectionsTest {


    @Test
    public void testList(){
        Map<Integer, List<Integer>> listMap = List.of(1, 2, 3, 4).groupBy(i -> i % 2);
        System.out.println(listMap.toString());

        List<Tuple2<Character, Integer>> zipWithIndex = List.of('a', 'b', 'c').zipWithIndex();
        System.out.println(zipWithIndex.toString());
    }
}
