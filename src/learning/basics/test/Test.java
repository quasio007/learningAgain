package learning.basics.test;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        C c = new C();
        c.m5();
        C.m5();
        I1.m5();
        I2.m5();
        I1 i1 = new C();
    }
}

abstract class Ac {
    final int b;

    Ac() {
        b = 3;
    }

    public Ac(int b) {
        this.b = b;
    }

    abstract void m();

    abstract protected void m1() throws ArithmeticException;
}

interface I1 {
    int a = 12;

    void m();

    void m2() throws Throwable;

    default void m4() {
    }

    static void m5() {
        System.out.println("From interface I1");
    }
}

interface I2 {
    int a = 14;

    void m();

    void m3() throws IOException;

    default void m4() {
    }

    static void m5() {
        System.out.println("From interface I2");
    }
}


class C extends Ac implements I1, I2 {

    C() {

    }

    public C(int b) {
        super(b);
        b = I1.a;
    }

    @Override
    protected void m1() throws ArithmeticException {

    }

    @Override
    public void m() {

    }

    @Override
    public void m3() throws IOException {

    }

    @Override
    public void m2() throws Exception {

    }

    @Override
    public void m4() {

    }


    public static void m5() {
        System.out.println("From class c");
    }
}