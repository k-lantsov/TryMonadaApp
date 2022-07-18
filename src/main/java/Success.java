import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Represents a successful execution
 */
class Success<T> implements Try<T> {
    private final T value;

    Success(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        // write your code here
        return value;
    }

    @Override
    public T getUnchecked() {
        // write your code here
        return value;
    }

    @Override
    public Optional<T> toOptional() {
        // write your code here
        return Optional.ofNullable(value);
    }

    @Override
    public boolean isSuccess() {
        // write your code here
        return true;
    }

    @Override
    public T getOrElse(T defaultValue) {
        // write your code here
        return value;
    }

    @Override
    public T getOrElseSupply(Supplier<? extends T> supplier) {
        // write your code here
        return value;
    }

    @Override
    public <X extends Throwable> T getOrElseThrow(Supplier<? extends X> exceptionSupplier) {
        // write your code here
        return value;
    }

    @Override
    public <E extends Throwable> Try<T> onSuccess(ThrowableConsumer<T, E> action) throws E {
        // write your code here
        action.accept(value);
        return this;
    }

    @Override
    public <E extends Throwable> Try<T> onFailure(ThrowableConsumer<Throwable, E> action) {
        // write your code here
        return this;
    }

    @Override
    public Try<T> filter(Predicate<T> predicate) {
        // write your code here
        Objects.requireNonNull(predicate);
        if (!predicate.test(value)) {
            return new Failure<>(new NoSuchElementException("Predicate does not match for " + value));
        }
        return this;
    }

    @Override
    public <U> Try<U> map(ThrowableFunction<? super T, ? extends U> function) {
        Objects.requireNonNull(function);
        try {
            return new Success<>(function.apply(value));
        } catch (Throwable t) {
            return new Failure<>(t);
        }
    }

    @Override
    public <U> Try<U> flatMap(ThrowableFunction<? super T, Try<U>> function) {
        Objects.requireNonNull(function);
        try {
            return function.apply(value);
        } catch (Throwable t) {
            return new Failure<>(t);
        }
    }

    @Override
    public Try<T> recover(ThrowableFunction<? super Throwable, T> function) {
        return this;
    }

    @Override
    public Try<T> recoverWith(ThrowableFunction<? super Throwable, Try<T>> function) {
        return this;
    }

    @Override
    public String toString() {
        return "Success[" + value + "]";
    }
}
