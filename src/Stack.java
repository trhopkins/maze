interface StackInterface {
	public void push(int key); // insert a Node on top 
	public int pop();
	public int ontop();
	public int size();
	public boolean empty();
	public void traverse();
}

class Stack implements StackInterface {
	private Node top;
	private int size;

	/* constructor */
	public Stack() {
		top = null;
		size = 0;
	}

	/* add a new Node to the top of the Stack */
	public void push(int key) {
		Node newNode = new Node(key, top);
		top = newNode;
		size++;
	}

	/* return the key of the top Node and remove it */
	public int pop() {
		int i = top.getKey();
		top = top.getNext();
		size--;
		return i;
	}

	/* return the key of the top Node without removing it */
	public int ontop() {
		return top.getKey();
	}

	/* return number of Nodes */
	public int size() {
		return size;
	}

	/* return true if empty */
	public boolean empty() {
		return (top == null);
	}

	public void traverse() { // empties stack, beware
		while (!this.empty()) {
			System.out.println(this.pop());
		}
	}
}
