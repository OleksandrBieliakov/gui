package werner.e02.p1generic;

import java.util.Arrays;
import java.util.List;

public class FilterDemo {

    public static void main(String[] args) {
        List<String> arr = Arrays.asList("Alice", "Sue", "Janet", "Bea");
        System.out.println(arr);
        List<String> a1 = Filter.filter(arr, new LenFilter(4));
        System.out.println(a1);
        List<String> a2 = Filter.filter(arr, new Filter<String>() {
            @Override
            public boolean test(String s) {
                return s.charAt(0) < 'D' && s.charAt(0) >= 'A';
            }
        });
        System.out.println(a2);
        List<String> a3 = Filter.filter(arr, s -> s.charAt(0) <= 'Z' && s.charAt(0) > 'H');
        System.out.println(a3);


        List<Integer> integers = Arrays.asList(1, 3, -5, 0, 6, -9);
        System.out.println(integers);
        System.out.println(Filter.filter(integers, new PositiveFilter()));
        System.out.println(Filter.filter(integers, i -> i < 0));

    }
}
