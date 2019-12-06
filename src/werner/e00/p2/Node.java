package werner.e00.p2;

public class Node {
    private A_Type type;
    private Node next;

    public Node (A_Type p, Node next) {
        type = p;
        this.next = next;
    }

    public A_Type getType() {return type;}
    public Node getNext() {return next;}

}
