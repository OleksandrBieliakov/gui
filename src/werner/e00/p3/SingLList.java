package werner.e00.p3;

public class SingLList {

    private static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(int data) {
            this.data = data;
            next = null;
        }
    }

    private Node head;

    public boolean empty() {
        return head == null;
    }

    public void addFront(int d) {
        head = new Node(d, head);
    }

    public void addBack(int d){
        if(empty()) head = new Node(d);
        else {
            Node tmp = head;
            while(tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = new Node(d);
        }
    }

    public static SingLList arrayToList(int[] arr) {
        SingLList list = new SingLList();
        for(int i : arr) {
            list.addBack(i);
        }
        return list;
    }

    public void removeOdd() {
        while(head != null && head.data % 2 != 0) {
            head = head.next;
        }
        Node curr = head;
        while (curr.next != null) {
            if (curr.next.data % 2 != 0) {
                curr.next = curr.next.next;
                continue;
            }
            curr = curr.next;
        }
    }

    public boolean contains (int d) {
        Node tmp = head;
        while(tmp != null) {
            if(tmp.data == d)
                return true;
            tmp = tmp.next;
        }
        return false;
    }

    public void showList() {
        if(empty()) System.out.println("Empty list");
        else {
            Node tmp = head;
            while(tmp != null) {
                System.out.print(tmp.data + " ");
                tmp = tmp.next;
            }
            System.out.println();
        }
    }

    public void clear() {
        head = null;
    }

}
