package werner.e02.p1generic;

public class LenFilter implements Filter<String> {

    private int minLen;

    public LenFilter(int ml) {
        minLen = ml;
    }

    public boolean test(String s) {
        return s.length() >= minLen;
    }
}
