package werner.e03;

public class SimpleArrayList {
    private final static int INITIAL_CAPACITY = 5;
    private int cap = INITIAL_CAPACITY;
    private int size = 0;
    private int[] arr = new int[cap];

    private void checkCap(int addSize) {
        if (size + addSize > cap) {
            cap = 2 * (size + addSize);
            int[] brr = new int[cap];
            for (int i = 0; i < size; ++i) {
                brr[i] = arr[i];
            }
            arr = brr;
        }
    }

    public SimpleArrayList() {
    }

    public SimpleArrayList(int value) {
        size = 1;
        arr[0] = value;
    }

    public SimpleArrayList(int[] a) {
        int len = a.length;
        checkCap(len);
        size = len;
        for (int i = 0; i < size; ++i) {
            arr[i] = a[i];
        }
    }

    public SimpleArrayList(SimpleArrayList a) {
        checkCap(a.size);
        size = a.size;
        for (int i = 0; i < size; ++i) {
            arr[i] = a.arr[i];
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        cap = INITIAL_CAPACITY;
        size = 0;
        arr = new int[cap];
    }

    public void trim() {
        cap = size;
        int[] brr = new int[cap];
        for (int i = 0; i < size; ++i) {
            brr[i] = arr[i];
        }
        arr = brr;
    }

    public SimpleArrayList insert(int ind, int[] other) throws IndexOutOfBoundsException {
        if (ind > size || ind < 0) throw new IndexOutOfBoundsException();
        int len = other.length;
        checkCap(len);
        for (int i = size - 1; i >= ind; --i) {
            arr[i + len] = arr[i];
        }
        size += len;
        for (int i = 0; i < len; ++i) {
            arr[i + ind] = other[i];
        }
        return this;
    }

    public SimpleArrayList insert(int ind, int e) {
        return insert(ind, new int[]{e});
    }

    public SimpleArrayList append(int e) {
        return insert(size, new int[]{e});
    }

    public SimpleArrayList append(int[] a) {
        return insert(size, a);
    }

    public SimpleArrayList append(SimpleArrayList a) {
        SimpleArrayList b = new SimpleArrayList(a);
        b.trim();
        return insert(size, b.arr);
    }

    public int get(int ind) {
        return arr[ind];
    }

    public int[] getArr() {
        return arr;
    }

    public SimpleArrayList set(int ind, int val) {
        arr[ind] = val;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            sb.append(arr[i]);
            sb.append(" ");
        }
        return "Cap=" + cap + ", size=" + size + ": [ " + sb.toString() + "]";
    }

}
