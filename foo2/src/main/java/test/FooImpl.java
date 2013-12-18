package test;

public class FooImpl implements Foo {
    @Override
    public String foo() {
        System.out.println("Method from foo2 called");
        new Internal().foo();
        return "foo2";
    }
}
