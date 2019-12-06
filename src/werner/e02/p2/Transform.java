package werner.e02.p2;

@FunctionalInterface
interface Transform<T,R> {
    R apply(T t);
}
