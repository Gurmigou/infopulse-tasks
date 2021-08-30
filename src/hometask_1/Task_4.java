package hometask_1;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task_4 {

    public static void main(String[] S) {
        Optional<Integer> o = Optional.ofNullable(null);
        o.map(i -> i * 10).orElseThrow(NullPointerException::new);

        // will throw an exception
    }

    public static <T> Stream<? extends T> findFirstNAsStreamFiltered(Stream<T> stream, Predicate<? super T> predicate, int n) {
        return stream.filter(predicate)
                     .limit(n);
    }

    public static <T> List<? extends T> findFirstNAsListFiltered(Stream<T> stream, Predicate<? super T> predicate, int n) {
        return stream.filter(predicate)
                     .limit(n)
                     .collect(Collectors.toList());
    }
}
