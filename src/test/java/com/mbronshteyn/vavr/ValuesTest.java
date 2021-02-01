package com.mbronshteyn.vavr;

import io.vavr.control.Option;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class ValuesTest {

    @Test
    public void OptionalTest(){
        Optional<String> maybeFoo = Optional.of("foo");
        Assert.assertEquals(maybeFoo.get(),"foo");

        Optional<String> maybeFooBar = maybeFoo.map(s -> (String)null)
                                               .map(s -> s.toUpperCase() + "bar");
        Assert.assertFalse(maybeFooBar.isPresent());
    }

    @Test
    public void OptionTestNullWithFlatMap(){
        Option<String> maybeFoo = Option.of("foo");
        Assert.assertEquals(maybeFoo.get(),"foo");

        Option<String> maybeFooBar = maybeFoo.map(s -> (String)null)  // put null on a stream
                                             .flatMap(s -> Option.of(s)
                                                                 .map(t -> t.toUpperCase() + "bar")); // mapper will check for null
        Assert.assertTrue(maybeFooBar.isEmpty());

       //////////////////////////////////////////////////////////////////////////////////

        maybeFooBar = maybeFoo.flatMap(s -> Option.of((String)null))
                                             .map(s -> s.toUpperCase() + "bar");
        Assert.assertTrue(maybeFooBar.isEmpty());

    }
}
