@FunctionalInterface
interface ThrowableFunction<T, R> {
    R apply(T t) throws Throwable;
}