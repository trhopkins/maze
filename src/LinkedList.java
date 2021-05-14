/* LinkedList methods */
interface LinkedListInterface {
    public void insert(int key);
    public void delete(int key);
    public int size();
    public boolean empty();
    public boolean search(int key);
    public int[] getKeys();
    public void traverse();

/* LinkedList ADT */
public class LinkedList implements LinkedListInterface {
    private Node head;

    /* constructor */
    public LinkedList() {
        head = null;
    }

    /* create new Node and reassign head */
    public void insert(int key) {
        Node newHead = new Node(key, head);
        head = newHead;
    }

    /* delete Node */
    public void delete(int key) {
        Node tmp = head;
        Node prev = null;
        if (tmp == null) {
            return; // if empty, exit
        }
        if (tmp != null && tmp.getKey() == key) {
            head = tmp.getNext(); // head matches key, no prev
            return;
        }
        while (tmp != null && tmp.getKey() != key) {
            prev = tmp;
            tmp = tmp.getNext();
        }
        if (tmp == null) {
            return; // not found. Return error?
        }
        prev.setNext(tmp.getNext()); // update previous Node's next
    }

    /* return total number of Nodes */
    public int size() {
        int size = 0;
        Node tmp = head;
        while (tmp != null) {
            tmp = tmp.getNext();
            size++;
        }
        return size;
    }

    /* return true if empty */
    public boolean empty() {
        //return (this.numNodes() == 0);
        return (head == null);
    }

    /* return true if Node exists */
    public boolean search(int key) {
        Node tmp = head;
        while (tmp != null) {
            if (tmp.getKey() == key) {
                return true; // found
            } else {
                tmp = tmp.getNext();
            }
        }
        return false; // not found
    }

    /* return array of keys */
    public int[] getKeys() {
        int[] keys = new int[this.size()];
        Node tmp = head;
        for (int i=0; i<keys.length; i++) {
            keys[i] = tmp.getKey();
            tmp = tmp.getNext();
        }
        return keys;
    }

    /* print all Nodes */
    public void traverse() {
        Node tmp = head;
        while (tmp != null) {
            System.out.print(tmp.getKey() + ' ');
            tmp = tmp.getNext();
        }
        //System.out.println();
    }
}
