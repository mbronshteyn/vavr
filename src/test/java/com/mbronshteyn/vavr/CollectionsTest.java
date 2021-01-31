package com.mbronshteyn.vavr;

import io.vavr.collection.List;
import io.vavr.collection.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class CollectionsTest {

    @Test
    public void testList(){
        Map<Integer, List<Integer>> listMap = List.of(1, 2, 3, 4).groupBy(i -> i % 2);

    }
}
