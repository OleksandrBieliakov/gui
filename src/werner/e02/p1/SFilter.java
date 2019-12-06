package werner.e02.p1;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface SFilter {

    boolean test(String s);

    static String[] filter(String[] arr, SFilter filt) {
        List<String> list =  new ArrayList<>();
        for (String s : arr) {
            if (filt.test(s))
                list.add(s);
        }
        return list.toArray(new String[0]);
    }

}
