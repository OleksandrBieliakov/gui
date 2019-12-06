package werner.e02.p1generic;

public class PositiveFilter implements Filter<Integer> {

    @Override
    public boolean test(Integer s) {
        return s > 0;
    }
}
