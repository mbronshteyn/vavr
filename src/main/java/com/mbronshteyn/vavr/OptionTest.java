package com.mbronshteyn.vavr;

import io.vavr.control.Option;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class OptionTest {
    @Test
    public void givenValue_whenCreatesOption_thenCorrect() {
        Option<Object> noneOption = Option.of(null);
        Option<Object> someOption = Option.of("val");

        assertEquals("None", noneOption.toString());
        assertEquals("Some(val)", someOption.toString());
    }

    /*
    In the second snippet of this section, we needed a null check,
    in which we would assign a default value to the variable,
    before attempting to use it.
    Option can deal with this in a single line, even if there is a null:
     */
    @Test
    public void givenNull_whenCreatesOption_thenCorrect() {
        String name = null;
        Option<String> nameOption = Option.of(name);

        assertEquals("baeldung", nameOption.getOrElse("baeldung"));
    }

    
}
