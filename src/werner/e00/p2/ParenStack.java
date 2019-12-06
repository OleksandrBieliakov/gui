package werner.e00.p2;

public class ParenStack {
    private Node top;

    public void push(A_Type p) {
        top = new Node(p, top);
    }

    public A_Type pop() {
        A_Type tmp = top.getType();
        top = top.getNext();
        return tmp;
    }

    public boolean empty() {
        return top == null;
    }
}
