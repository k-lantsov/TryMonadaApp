@FunctionalInterface
public interface ThrowableOperation<T> {
    T execute() throws Throwable;
}
