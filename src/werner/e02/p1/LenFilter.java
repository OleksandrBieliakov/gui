package werner.e02.p1;

public class LenFilter implements SFilter {

    private int minLen;

    public LenFilter(int ml) {
        minLen = ml;
    }

    public boolean test(String s) {
        return s.length() >= minLen;
    }
}
