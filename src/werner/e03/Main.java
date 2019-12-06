package werner.e03;

public class Main {

    public static void main(String[] args) {
        SimpleArrayList a =
                new SimpleArrayList()
                        .append(new int[]{1, 3}).insert(1, 2)
                        .append(6).insert(3, new int[]{4, 5});
        SimpleArrayList b = new SimpleArrayList(a);
        for (int i = 0; i < a.size(); ++i)
            a.set(i, a.get(i) + 6);
        b.append(a).append(13).trim();
        System.out.println("a -> " + a);
        System.out.println("b -> " + b);

        SimpleArrayList c = new SimpleArrayList(a.getArr());
        System.out.println("c -> " + c);
        c.clear();
        System.out.println("c -> " + c);
        SimpleArrayList d = new SimpleArrayList(10);
        System.out.println("d -> " + d);
    }

}
