package com.github.fengyuchenglun.apidoc.springmvc.example.common;

public class Tuple<T1, T2> {
    final public T1 a;
    final public T2 b;

    public Tuple(T1 a, T2 b) {
        this.a = a;
        this.b = b;
    }
}
