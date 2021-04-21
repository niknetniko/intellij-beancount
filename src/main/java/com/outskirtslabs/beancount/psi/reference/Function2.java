package com.outskirtslabs.beancount.psi.reference;

/**
 * @author Niko Strijbol
 */
@FunctionalInterface
public interface Function2<T, T1, T2> {
    T2 execute(T t1, T1 t2);
}
