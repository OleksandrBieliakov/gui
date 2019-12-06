package werner.e02.p1generic;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface Filter<T> {

    boolean test(T s);

    static <T> List<T> filter(List<T> arr, Filter<T> filter) {
        List<T> list = new ArrayList<>();
        for (T s : arr) {
            if (filter.test(s))
                list.add(s);
        }
        return list;
    }
}
