import java.util.Random;

public class main {
	static int width, height, entrance, exit;
	static Graph grid, spanningTree;
	static LinkedList visited = new LinkedList();
	static LinkedList path = new LinkedList();

	/* main method */
	public static void main(String[] args) {
		width = Integer.parseInt(args[0]);
		height = Integer.parseInt(args[1]);
		entrance = 0; // top left corner
		exit = width * height - 1; // bottom right corner
		grid = new Graph(width, height);
		spanningTree = new Graph(width, height);
		grid.makeGrid(); // backbone for maze
		recursiveBacktrack(); // currently using depth-first
		analyzeSpanningTree(); // Edge count statistics
		traverse(); // solve the maze
		drawMaze(); // print everything
	}

	/* find path from entrance to exit */
	static void traverse() {
		visited = new LinkedList(); // wipe visited list
		Stack searcher = new Stack();
		LinkedList available = new LinkedList();
		int next;
		searcher.push(0); // starting point
		while (searcher.ontop() != exit) { // end is exit
			available = availableEdges(searcher.ontop(), spanningTree);
			if (available.empty()) { // dead end?
				searcher.pop();
			} else { // continue onward
				next = getRandomEdge(available);
				visited.insert(next);
				searcher.push(next);
			}
		}
		System.out.println("you reached the end");
		while (!searcher.empty()) {
			path.insert(searcher.pop());
		}
		//searcher.traverse(); // prints contents and empties
	}

	/* maze generator */
	static void recursiveBacktrack() {
		Stack rb = new Stack(); // recursive backtracker path taken
		LinkedList available;
		int next;
		rb.push(0); // starting point, chosen arbitrarily/randomly?
		visited.insert(0);
		while (visited.size() < grid.size()) {
			available = availableEdges(rb.ontop(), grid);
			if (available.empty()) { // backtrack
				rb.pop();
			} else { // go to next unvisited Vertex
				next = getRandomEdge(available);
				spanningTree.insert(rb.ontop(), next);
				spanningTree.insert(next, rb.ontop());
				visited.insert(next);
				rb.push(next);
			}
        }
    }

	/* return random Edge from AdjacencyList */
	static int getRandomEdge(LinkedList ll) {
		Random random = new Random();
		int[] choices = ll.getKeys();
		return choices[random.nextInt(ll.size())];
	}

	/* remove visited Vertices from AdjacencyList */
	static LinkedList availableEdges(int v, Graph graph) {
		int[] edges = graph.getEdges(v).getKeys(); // temporary options
		LinkedList test = new LinkedList();
		for (int i=0; i< edges.length; i++) { // populate temporary LL
			test.insert(edges[i]);
		}
		for (int i=0; i<edges.length; i++) { // get rid of available visited Vertices
			if (visited.search(edges[i])) {
				test.delete(edges[i]);
			}
		}
		return test;
	}

	/* draw the maze */
	static void drawMaze() {
		int current = 0;
		System.out.print("+."); // opening for start of maze
		for (int i=0; i<width-1; i++) { // top
			System.out.print("+-");
		}
		System.out.println("+"); // end of top
		current = drawHorizontalWalls(current); // top row only
		for (int i=0; i<height-1; i++) { // for each row
			current = drawVerticalWalls(current);
			current -= width;
			current = drawHorizontalWalls(current);
		}
		for (int i=0; i<width-1; i++) { // bottom
			System.out.print("+-");
		}
		System.out.println("+.+"); // end of bottom
	}

	/* compares current and to right */
	static int drawHorizontalWalls(int current) { // also draws Vertices
		System.out.print("|");
		for (int i=0; i<width; i++) { // for each row
			drawVertex(current);
			if (i == width-1) { // end of row, prevents OOB
				System.out.println("|");
			} else { // everything except end of row
				if (spanningTree.hasEdge(current, current+1)) { // compare with right
					drawHorizontalEdge(current);
				} else { // wall
					System.out.print("|");
				}
			}
			current++;
		}
		return current;
	}

	/* compares current and above */
	static int drawVerticalWalls(int current) {
		for (int i=0; i<width; i++) { // for each row
			System.out.print("+");
			if (spanningTree.hasEdge(current, current-width)) { // compare with above
				drawVerticalEdge(current);
			} else { // wall
				System.out.print("-");
			}
			current++;
		}
		System.out.println("+");
		return current;
	}

	static void drawVertex(int v) {
		if (path.search(v)) {
			System.out.print(".");
		} else {
			System.out.print(" ");
		}
	}

	static void drawHorizontalEdge(int v) {
		if (path.search(v) && path.search(v+1)) { // current and next
			System.out.print(".");
		} else {
			System.out.print(" ");
		}
	}

	static void drawVerticalEdge(int v) {
		if (path.search(v) && path.search(v-width)) { // current and above
			System.out.print(".");
		} else {
			System.out.print(" ");
		}
	}

	/* count number of Edges in each Vertex of the spanning tree */
	static void analyzeSpanningTree() { // only useful for grid right now
		int deadEnds = 0;
		int hallways = 0;
		int forks = 0;
		int crossroads = 0;
		for (int i=0; i<spanningTree.size(); i++) {
			if (spanningTree.getEdges(i).size() == 1) { // dead ends
				deadEnds++;
			} else if (spanningTree.getEdges(i).size() == 2) { // hallways
				hallways++;
			} else if (spanningTree.getEdges(i).size() == 3) { // forks
				forks++;
			} else if (spanningTree.getEdges(i).size() == 4) { // crossroads
				crossroads++; // rare for depth-first generation, don't worry
			}
		}
		System.out.println("dead ends: " + deadEnds);
		System.out.println("hallways: " + hallways);
		System.out.println("forks: " + forks);
		System.out.println("cross roads: " + crossroads);
	}
}
