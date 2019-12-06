package werner.e02.p1;

import java.util.Arrays;

public class StringFilter {

    public static void main(String[] args) {
        String[] arr = {"Alice", "Sue", "Janet", "Bea"};
        System.out.println(Arrays.toString(arr));
        String[] a1 = SFilter.filter(arr, new LenFilter(4));
        System.out.println(Arrays.toString(a1));
        String[] a2 = SFilter.filter(arr, new SFilter() {
            @Override
            public boolean test(String s) {
                return s.charAt(0) < 'D' && s.charAt(0) >= 'A';
            }
        });
        System.out.println(Arrays.toString(a2));
        String[] a3 = SFilter.filter(arr, s -> s.charAt(0) <= 'Z' && s.charAt(0) > 'H');
        System.out.println(Arrays.toString(a3));
    }
}
