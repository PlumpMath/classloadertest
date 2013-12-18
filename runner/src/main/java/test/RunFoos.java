package test;

import java.io.File;

public class RunFoos {
    static private TestClassLoader l1 = new TestClassLoader(new File("foo1/target/classes"), RunFoos.class.getClassLoader());
    static private TestClassLoader l2 = new TestClassLoader(new File("foo2/target/classes"), RunFoos.class.getClassLoader());
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Foo foo1ImplClass = (Foo) l1.loadClass("test.FooImpl").newInstance();
        System.out.println(foo1ImplClass.foo());
        Foo foo2ImplClass = (Foo) l2.loadClass("test.FooImpl").newInstance();
        System.out.println(foo2ImplClass.foo());
    }
}
