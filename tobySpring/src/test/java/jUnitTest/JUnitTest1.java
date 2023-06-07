package jUnitTest;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitTest1 {

    static JUnitTest1 testObject;

    @Test
    public void test1() {
        Assertions.assertNotEquals(this, testObject);
        testObject = this;
    }

    @Test
    public void test2() {
        Assertions.assertNotEquals(this, testObject);
        testObject = this;
    }

    @Test
    public void test3() {
        Assertions.assertNotEquals(this, testObject);
        testObject = this;
    }
}
