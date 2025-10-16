package com.book.usecase;

@FunctionalInterface
public interface UseCase<T, R>{
    public R exe(T t);
}
