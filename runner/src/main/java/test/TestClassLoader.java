package test;

import java.io.*;

public class TestClassLoader extends ClassLoader {
    private final File dir;

    public TestClassLoader(File dir, ClassLoader parent) {
        super(parent);
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.printf("TCL: %s\n", name);
        File classFileName = new File(dir, name.replace('.', '/') + ".class");
        if (!classFileName.exists()) {
            throw new ClassNotFoundException(name);
        }
        byte[] classData = loadClassData(classFileName);
        return defineClass(name, classData, 0, classData.length);
    }

    private byte[] loadClassData(File classFileName) {
        int length = (int) classFileName.length();
        byte[] result = new byte[length];
        InputStream in = null;
        try {
            in = new FileInputStream(classFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            in.read(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
