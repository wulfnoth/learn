package org.wulfnoth.learning.mirror;

import net.vidageek.mirror.dsl.Mirror;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 */
public class MirrorNewInstance {

    public static void main(String[] args)
            throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        Object[] cargs = new Object[]{5, new TestChild()};

        Constructor<TestMirror> constructor = new Mirror().on(TestMirror.class).reflect().constructor().withoutArgs();
        Method method = TestMirror.class.getMethod("print", TestChild.class);
        TestMirror instance = constructor.newInstance();
        TestChild tc = new TestChild();
        method.invoke(instance, tc);
    }

}

interface TestBase {

}

interface TestBaseBk {

}

class TestChild implements TestBase, TestBaseBk{

}

class TestMirror {

    public void print(TestBaseBk tbb)  {
        System.out.println("tbb");
    }

    public void print(TestBase tb) {
        System.out.println("tb");
    }

}
