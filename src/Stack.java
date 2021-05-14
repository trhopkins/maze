/* Stack methods */
interface StackInterface {
    public void push(int key);
    public int pop();
    public int ontop();
    public int size();
    public boolean empty();
    public void traverse();
}

/* Stack ADT */
class Stack implements StackInterface {
    private Node top;
    private int size;

    /* constructor */
    public Stack() {
        size = 0;
        top = null;
    }

    /* add a new Node atop Stack */
    public void push(int key) {
        Node newNode = new Node(key, top);
        top = newNode;
        size++;
    }

    /* remove the top Node and return its key */
    public int pop() {
        int i = top.getKey();
        top = top.getNext();
        size--;
        return i;
    }

    /* return the top Node without removing it */
    public int ontop() {
        return top.getKey();
    }

    /* return the number of Nodes */
    public int size() {
        return size;
    }

    /* return true if empty */
    public boolean empty() {
        //return (size == 0);
        return (top == null);
    }

    /* print all Nodes. Beware, empties Stack */
    public void traverse() {
        while (!this.empty()) {
            System.out.print(this.pop() + ' ');
        }
        //System.out.println();
    }
}
