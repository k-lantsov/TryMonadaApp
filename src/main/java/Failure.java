import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Failure<T> implements Try<T> {
    private final Throwable e;

    Failure(Throwable e) {
        this.e = e;
    }

    @Override
    public T get() throws Throwable {
        // write your code here
        throw e;
    }

    @Override
    public T getUnchecked() {
        // write your code here
        throw new RuntimeException(e);
    }

    @Override
    public Optional<T> toOptional() {
        // write your code here
        return Optional.empty();
    }

    @Override
    public boolean isSuccess() {
        // write your code here
        return false;
    }

    @Override
    public T getOrElse(T defaultValue) {
        // write your code here
        return defaultValue;
    }

    @Override
    public T getOrElseSupply(Supplier<? extends T> supplier) {
        // write your code here
        return supplier.get();
    }

    @Override
    public <X extends Throwable> T getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        // write your code here
        throw exceptionSupplier.get();
    }

    @Override
    public <E extends Throwable> Try<T> onSuccess(ThrowableConsumer<T, E> action) {
        // write your code here
        return this;
    }

    @Override
    public <E extends Throwable> Try<T> onFailure(ThrowableConsumer<Throwable, E> action) throws E {
        // write your code here
        action.accept(e);
        return this;
    }

    @Override
    public Try<T> filter(Predicate<T> predicate) {
        // write your code here
        return this;
    }

    @Override
    public <U> Try<U> map(ThrowableFunction<? super T, ? extends U> function) {
        return new Failure<>(e);
    }

    @Override
    public <U> Try<U> flatMap(ThrowableFunction<? super T, Try<U>> function) {
        return new Failure<>(e);
    }

    @Override
    public Try<T> recover(ThrowableFunction<? super Throwable, T> function) {
        Objects.requireNonNull(function);
        try {
            return new Success<>(function.apply(e));
        } catch (Throwable ex) {
            return new Failure<>(ex);
        }
    }

    @Override
    public Try<T> recoverWith(ThrowableFunction<? super Throwable, Try<T>> function) {
        Objects.requireNonNull(function);
        try{
            return function.apply(e);
        }catch(Throwable ex){
            return new Failure<>(ex);
        }
    }

    @Override
    public String toString() {
        return "Failure[" + e + "]";
    }
}