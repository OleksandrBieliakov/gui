package werner.e02.p2;

import java.util.Arrays;

public class Main {

    private static <T,R> void transform(T[] in, R[] out, Transform<T,R> trans) {
        for(int i = 0, len = in.length; i < len; ++i ) {
            out[i] = trans.apply(in[i]);
        }
    }

    public static void main (String[] args) {

        String[] sin = {"Alice", "Sue", "Janet", "Bea"};
        System.out.println(Arrays.toString(sin) + '\n');
        Integer[] iout = new Integer[sin.length];
        transform(sin, iout, new StrToInt());
        System.out.println(Arrays.toString(iout));
        Character[] cout = new Character[sin.length];
        transform(sin, cout, new Transform<String, Character>() {
            @Override
            public Character apply(String s) {
                return s.charAt(0);
            }
        });
        System.out.println(Arrays.toString(cout));
        String[] sout = new String[sin.length];
        transform(sin, sout, s -> s.toUpperCase() );
        System.out.println(Arrays.toString(sout));

    }

}
