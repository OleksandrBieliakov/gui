package werner.e00.p4;

public class SimpleArrayList {
    private final static int INITIAL_CAPACITY = 5;
    private int cap = INITIAL_CAPACITY;
    private int size = 0;
    private int[] arr = new int[cap];

    public SimpleArrayList() {
        cap = INITIAL_CAPACITY;
    }

    public SimpleArrayList(int val) {
        size = 1;
        arr[0] = val;
    }

    public SimpleArrayList(int[] brr) {
        size = brr.length;
        capSet();
        for (int i = 0; i < size; ++i) {
            arr[i] = brr[i];
        }
    }

    public SimpleArrayList(SimpleArrayList other) {
        size = other.size;
        capSet();
        for (int i = 0; i < size; ++i) {
            arr[i] = other.arr[i];
        }
    }

    private void capSet() {
        if (size > cap) {
            cap = size * 2;
            arr = new int[cap];
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
        cap = INITIAL_CAPACITY;
        arr = new int[cap];
    }

    public void trim() {
        cap = size;
        int[] tmp = new int[cap];
        for (int i = 0; i < size; ++i) {
            tmp[i] = arr[i];
        }
        arr = tmp;
    }

    private void incCap(int sizeInc) {
        cap = 2 * (size + sizeInc);
        int[] tmp = new int[cap];
        for (int i = 0; i < size; ++i) {
            tmp[i] = arr[i];
        }
        arr = tmp;
    }

    public SimpleArrayList insert(int ind, int[] other) throws IndexOutOfBoundsException {
        if (ind > size || ind < 0) throw new IndexOutOfBoundsException();
        int otherLen = other.length;
        if (size + otherLen > cap) incCap(otherLen);
        for (int k = size - 1; k >= ind; --k) {
            arr[k + otherLen] = arr[k];
        }
        for (int i = 0; i < otherLen; ++i) {
            arr[ind + i] = other[i];
        }
        size += otherLen;
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
        SimpleArrayList tmp = new SimpleArrayList(a);
        tmp.trim();
        return insert(size, tmp.arr);
    }

    public int get(int ind) throws IndexOutOfBoundsException {
        if (ind > size - 1) throw new IndexOutOfBoundsException();
        return arr[ind];
    }

    public SimpleArrayList set(int ind, int val) throws IndexOutOfBoundsException {
        if (ind > size - 1) throw new IndexOutOfBoundsException();
        arr[ind] = val;
        return this;
    }

    @Override
    public String toString() {
        if (size == 0) return "Empty array";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            sb.append(arr[i] + " ");
        }
        return "Cap=" + cap + ", size=" + size + ": [ " + sb.toString() + "]";
    }
}
