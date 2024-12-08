import application.service.FooBarQuixService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class FooBarQuixServiceTest {
    private final FooBarQuixService fooBarQuixService = new FooBarQuixService();

    @Test
    void testnumberToFooBarQuixTransformation() {
        assertEquals("1", fooBarQuixService.numberToFooBarQuixTransformation(1), "Test for input 1 failed");
        assertEquals("FOOFOO", fooBarQuixService.numberToFooBarQuixTransformation(3), "Test for input 3 failed");
        assertEquals("BARBAR", fooBarQuixService.numberToFooBarQuixTransformation(5), "Test for input 5 failed");
        assertEquals("QUIX", fooBarQuixService.numberToFooBarQuixTransformation(7), "Test for input 7 failed");
        assertEquals("FOO", fooBarQuixService.numberToFooBarQuixTransformation(9), "Test for input 9 failed");
        assertEquals("FOOBAR", fooBarQuixService.numberToFooBarQuixTransformation(51), "Test for input 51 failed");
        assertEquals("BARFOO", fooBarQuixService.numberToFooBarQuixTransformation(53), "Test for input 53 failed");
        assertEquals("FOOFOOFOO", fooBarQuixService.numberToFooBarQuixTransformation(33), "Test for input 33 failed");
        assertEquals("FOOFOOFOO", fooBarQuixService.numberToFooBarQuixTransformation(33), "Test for input 33 failed");
        assertEquals("FOOBARBAR", fooBarQuixService.numberToFooBarQuixTransformation(15), "Test for input 15 failed");
    }
}
