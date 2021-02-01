package com.mbronshteyn.vavr;

import io.vavr.Lazy;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class ValuesTest {

    @Test
    public void OptionalTest() {
        Optional<String> maybeFoo = Optional.of("foo");
        Assert.assertEquals(maybeFoo.get(), "foo");

        Optional<String> maybeFooBar = maybeFoo.map(s -> (String) null)
                                               .map(s -> s.toUpperCase() + "bar");
        Assert.assertFalse(maybeFooBar.isPresent());
    }

    @Test
    public void OptionTestNullWithFlatMap() {
        Option<String> maybeFoo = Option.of("foo");
        Assert.assertEquals(maybeFoo.get(), "foo");

        Option<String> maybeFooBar = maybeFoo.map(s -> (String) null)  // put null on a stream
                                             .flatMap(s -> Option.of(s)
                                                                 .map(t -> t.toUpperCase() + "bar")); // mapper will check for null
        Assert.assertTrue(maybeFooBar.isEmpty());

        //////////////////////////////////////////////////////////////////////////////////

        maybeFooBar = maybeFoo.flatMap(s -> Option.of((String) null))
                              .map(s -> s.toUpperCase() + "bar");
        Assert.assertTrue(maybeFooBar.isEmpty());

    }

    /*
    Try is a monadic container type which represents a computation
    that may either result in an exception, or return a successfully computed value.
    It’s similar to, but semantically different from Either.
    Instances of Try, are either an instance of Success or Failure.
     */
    @Test
    public void TryTest() {
        String result = Try.of(() -> bunchOfWork(0)).getOrElse("Error");
        Assert.assertEquals(result, "Success");

        result = Try.of(() -> bunchOfWork(1)).getOrElse("Error");
        Assert.assertEquals(result, "Error");
    }

    private String bunchOfWork(int i) throws Exception {
        if (i == 0) {
            return "Success";
        } else {
            throw new Exception();
        }
    }

    /*
    Lazy is a monadic container type which represents a lazy evaluated value.
    Compared to a Supplier, Lazy is memoizing, i.e. it evaluates only once and therefore is referentially transparent.
     */
    @Test
    public void LazyTest() {
        Lazy<Double> lazy = Lazy.of(Math::random);
        lazy.isEvaluated(); // = false
        double firstTry = lazy.get();         // = 0.123 (random generated)
        lazy.isEvaluated(); // = true
        double secondTry = lazy.get();         // = 0.123 (memoized)
        Assert.assertEquals(firstTry, secondTry, 0.0001);

        // since ver. 2.0.0
        CharSequence chars = Lazy.val(() -> "Yay!", CharSequence.class);
        Assert.assertEquals(chars.charAt(0), 'Y');
    }

    /*
    Either represents a value of two possible types.
    An Either is either a Left or a Right. If the given Either is a Right and projected to a Left,
    the Left operations have no effect on the Right value.
    If the given Either is a Left and projected to a Right,
    the Right operations have no effect on the Left value.
    If a Left is projected to a Left or a Right is projected to a Right, the operations have an effect.

    Example: A compute() function, which results either in an Integer value (in the case of success)
    or in an error message of type String (in the case of failure).
    By convention the success case is Right and the failure is Left.
     */
    @Test
    public void EitherTest(){
        Either<String,Integer> value = compute(0).right().map(i -> i * 2).toEither();
        if( value.isRight() ){
            Assert.assertEquals( value.right().get().longValue(), 0 );
        }

        value = compute(1).right().map(i -> i * 2).toEither();
        if( value.isLeft() ){
            Assert.assertEquals( value.getLeft(), "Error" );
        }
    }

    public Either<String, Integer> compute(int i) {
        if (i == 0) {
            return Either.right(0);
        } else {
            return Either.left("Error");
        }
    }
}
