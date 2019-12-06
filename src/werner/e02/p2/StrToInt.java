package werner.e02.p2;

public class StrToInt implements Transform<String,Integer>{

    public Integer apply (String s) {
        return s.length();
    }

}
