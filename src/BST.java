/**
 * BST.java
 * @author Jose Leos
 */

import java.util.Comparator;
import java.util.NoSuchElementException;

public class BST<T extends Comparable<T>> {
	private class Node {
		private T data;
		private Node left;
		private Node right;

		public Node(T data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	private Node root;

	/***CONSTRUCTORS***/

	/**
	 * Default constructor for BST
	 * sets root to null
	 */
	public BST() {
		root = null;
	}

	/**
	 * Copy constructor for BST
	 * @param bst the BST to make
	 * a copy of 
	 */
	public BST(BST<T> bst) {
		copyHelper(bst.root);
	}
	
	public BST(BST<T> bst, T data) {
		copyHelper1(bst.root, data);
	}

	/**
	 * Helper method for copy constructor
	 * @param node the node containing
	 * data to copy
	 */
	private void copyHelper(Node node) {
        if (node != null) {
        this.insert1(node.data);
        copyHelper(node.left);
        copyHelper(node.right);
        }

	}
	
	private void copyHelper1(Node node, T data) {
		if (node != null) {
			if (comp1.compare((AnimeChar)data, (AnimeChar)node.data) == 0) {
				this.insert2(node.data);
			}
			copyHelper1(node.left, data);
			copyHelper1(node.right, data);
        }

	}

	/***ACCESSORS***/

	/**
	 * Returns the data stored in the root
	 * @precondition !isEmpty()
	 * @return the data stored in the root
	 * @throws NoSuchElementException when
	 * preconditon is violated
	 */
	public T getRoot() throws NoSuchElementException{
		if(isEmpty()) {
			throw new NoSuchElementException("getRoot() " + "BST is empty, no data to access.");
		}
		return root.data;
	}

	/**
	 * Determines whether the tree is empty
	 * @return whether the tree is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Returns the current size of the 
	 * tree (number of nodes)
	 * @return the size of the tree
	 */
	public int getSize() {
		return getSize(root);
	}

	/**
	 * Helper method for the getSize method
	 * @param node the current node to count
	 * @return the size of the tree
	 */
	private int getSize(Node node) {
		if(node == null) {
			return 0;
		}
		return (getSize(node.left) + 1 + getSize(node.right));
	}

	/**
	 * Returns the height of tree by
	 * counting edges.
	 * @return the height of the tree
	 */
	public int getHeight() {
		return getHeight(root) -1;
	}

	/**
	 * Helper method for getHeight method
	 * @param node the current
	 * node whose height to count
	 * @return the height of the tree
	 */
	private int getHeight(Node node) {
		if(node == null) {
			return 0;
		}
		int lh = getHeight(node.left);
		int rh = getHeight(node.right);

		return 1 + ((lh > rh) ? lh : rh);
	}

	/**
	 * Returns the smallest value in the tree
	 * @precondition !isEmpty()
	 * @return the smallest value in the tree
	 * @throws NoSuchElementException when the
	 * precondition is violated
	 */
	public T findMin() throws NoSuchElementException{
		if(isEmpty()) {
			throw new NoSuchElementException("findMin(): " + "BST is empty, no data to access.");
		}
		return findMin(root);
	}

	/**
	 * Helper method to findMin method
	 * @param node the current node to check
	 * if it is the smallest
	 * @return the smallest value in the tree
	 */
	private T findMin(Node node) {
		if(node.left == null) {
			return node.data;
		}
		return findMin(node.left);	    	
	}

	/**
	 * Returns the largest value in the tree
	 * @precondition !isEmpty()
	 * @return the largest value in the tree
	 * @throws NoSuchElementException when the
	 * precondition is violated
	 */
	public T findMax() throws NoSuchElementException{
		if(isEmpty()) {
			throw new NoSuchElementException("findMax(): " + "BST is empty, no data to access.");
		}
		return findMax(root);
	}

	/**
	 * Helper method to findMax method
	 * @param node the current node to check
	 * if it is the largest
	 * @return the largest value in the tree
	 */
	private T findMax(Node node) {
		if(node.right == null) {
			return node.data;
		}
		return findMax(node.right);	
	}

	/**
	 * Searches for a specified value
	 * in the tree
	 * @param data the value to search for
	 * @return whether the value is stored
	 * in the tree
	 */
	public boolean search1(T data) {
		return search1(data, root);
	}
	
	public boolean search2(T data) {
		return search2(data, root);
	}

	/**
	 * Helper method for the search method
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return whether the data is stored
	 * in the tree
	 */
	private boolean search1(T data, Node node) {
		if(node == null) {
			return false;
		}else
		if(data.equals(node.data)) {
			System.out.println(node.data);
			return true;
		}else if(data.compareTo(node.data) < 0) {
			return search1(data, node.left);
		}else {
			return search1(data, node.right);
		}
		
	}
	
	private boolean search2(T data, Node node) {
		if(node == null) {
			return false;
		}else
		if(comp.compare((AnimeChar) data, (AnimeChar)node.data) == 0) {
			return true;
		}else if(comp.compare((AnimeChar)data, (AnimeChar)node.data) < 0) {
			return search2(data, node.left);
		}else {
			return search2(data, node.right);
		}		
	}


	/**
	 * Determines whether two trees store
	 * identical data in the same structural
	 * position in the tree
	 * @param o another Object
	 * @return whether the two trees are equal
	 */
	@Override public boolean equals(Object o) {
		if(o == this) {
			return true;
		}else if(!(o instanceof BST)) {
			return false;
		}else {
			BST<T> bst = (BST<T>) o;
			return equals(this.root, bst.root);
		}
	}

	/**
	 * Helper method for the equals method
	 * @param node1 the node of the first bst
	 * @param node2 the node of the second bst
	 * @return whether the two trees contain
	 * identical data stored in the same structural
	 * position inside the trees
	 */    
	private boolean equals(Node node1, Node node2) {
		if(node1 == null && node2 == null) {
			return true;
		}else
		if(node1 != null && node2 != null) {
			return node1.data.equals(node2.data) && equals(node1.left, node2.left) && equals(node1.right, node2.right);
		}
		return false;
	}
	
	/***MUTATORS***/

	/**
	 * Inserts a new node in the tree
	 * @param data the data to insert
	 */
	public void insert1(T data) {
		if(root == null) {
			root = new Node(data);
		}else {
			insert1(data, root);
		}
	}
	
	public void insert2(T data) {
		if(root == null) {
			root = new Node(data);
		}else {
			insert2(data, root);
		}
	}

	/**
	 * Helper method to insert
	 * Inserts a new value in the tree
	 * @param data the data to insert
	 * @param node the current node in the
	 * search for the correct location
	 * in which to insert
	 */
	private void insert1(T data, Node node) {
		if(data.compareTo(node.data) <= 0) {
			if(node.left == null) {
				node.left = new Node(data);
			}else {
				insert1(data, node.left);
			}
		}else {
			if(node.right == null) {
				node.right = new Node(data);
			}else {
				insert1(data, node.right);
			}
		}
	}
	
	
	
	private void insert2(T data, Node node) {
		if(comp.compare((AnimeChar)data, (AnimeChar)node.data) <= 0) {
			if(node.left == null) {
				node.left = new Node(data);
			}else {
				insert2(data, node.left);
			}
		}else {
			if(node.right == null) {
				node.right = new Node(data);
			}else {
				insert2(data, node.right);
			}
		}
	}


	/**
	 * Removes a value from the BST
	 * @param data the value to remove
	 * @precondition !isEmpty()
	 * @precondition the data is located in the tree
	 * @throws NoSuchElementException when the
	 * precondition is violated
	 */
	public void remove1(T data) throws NoSuchElementException{
		if(isEmpty()) {
			throw new NoSuchElementException("remove(): " + "BST is empty, no data to access.");
		}
		if(search1(data) == false) {
			throw new NoSuchElementException("remove(): " + "There is no such data in the current BST.");
		}
		
		root = remove1(data, root);
	}
	
	public void remove2(T data) throws NoSuchElementException{
		if(isEmpty()) {
			throw new NoSuchElementException("remove(): " + "BST is empty, no data to access.");
		}
		if(search2(data) == false) {
			throw new NoSuchElementException("remove(): " + "There is no such data in the current BST.");
		}
		
		root = remove2(data, root);
	}

	/**
	 * Helper method to the remove method
	 * @param data the data to remove
	 * @param node the current node
	 * @return an updated reference variable
	 */
	private Node remove1(T data, Node node) { 
		if(data.compareTo(node.data) < 0) {
			node.left = remove1(data, node.left);
			
		}else if(data.compareTo(node.data) > 0) {
			node.right = remove1(data, node.right);
			
		}else {
			if(node.left == null && node.right == null) {
				node = null;
				return node;
				
			}else if(node.right == null) {
				node = node.left;
				
			}else if(node.left == null) {
				node = node.right;
				
			}else {
				node.data = findMin(node.right);
				node = remove1(node.data, node.right);
			}
		}
		return node;
	}
	
	private Node remove2(T data, Node node) { 
		if(comp.compare((AnimeChar)data, (AnimeChar)node.data) < 0) {
			node.left = remove2(data, node.left);
			
		}else if(comp.compare((AnimeChar)data, (AnimeChar)node.data) > 0) {
			node.right = remove2(data, node.right);
			
		}else {
			if(node.left == null && node.right == null) {
				node = null;
				return node;
				
			}else if(node.right == null) {
				node = node.left;
				
			}else if(node.left == null) {
				node = node.right;
				
			}else {
				node.data = findMin(node.right);
				node = remove2(node.data, node.right);
			}
		}
		return node;
	}


	/***ADDITIONAL OPERATIONS***/
	
	Comparator<AnimeChar> comp = new Comparator<AnimeChar>() {
		public int compare(AnimeChar char1, AnimeChar char2) {
			if(char1.equals(char2)) {
				return 0;
			}
			
			int showSize = 0;
			if((int)char1.getShow().length() > (int)char2.getShow().length()) {
				showSize = char2.getShow().length();
			}else {
				showSize = char1.getShow().length();
			}
			
			String show1 = char1.getShow().toLowerCase();
			String show2 = char2.getShow().toLowerCase();
			
			
			for(int i = 0; i < showSize; i++) {
				if((int)show1.charAt(i) != (int)show2.charAt(i)) {
					int char1ShowChar = (int) show1.charAt(i);
					int char2ShowChar = (int) show2.charAt(i);
					
					if(char1ShowChar < char2ShowChar) {
						return -1;
					}else if(char1ShowChar > char2ShowChar) {
						return 1;
					}
					
				}
			}

			
			String name1 = char1.getName().toLowerCase();
			String name2 = char2.getName().toLowerCase();

			int nameSize = 0;
			if((int)char1.getName().length() > (int)char2.getName().length()) {
				nameSize = char2.getName().length();
			}else {
				nameSize = char1.getName().length();
			}
			for(int i = 0; i < nameSize; i++) {

				if((int)name1.charAt(i) != (int)name2.charAt(i)) {
					int char1NameChar = (int) name1.charAt(i);
					int char2NameChar = (int) name2.charAt(i);
					
					if(char1NameChar < char2NameChar) {
						return -2;
					}
					return 2;
				}
			}
			return 0;
		
	}

	};
	
	Comparator<AnimeChar> comp1 = new Comparator<AnimeChar>() {
		public int compare(AnimeChar char1, AnimeChar char2) {
			if(char1.equals(char2)) {
				return 0;
			}
			
			int showSize = 0;
			if((int)char1.getShow().length() > (int)char2.getShow().length()) {
				showSize = char2.getShow().length();
			}else {
				showSize = char1.getShow().length();
			}
			
			String show1 = char1.getShow().toLowerCase();
			String show2 = char2.getShow().toLowerCase();

			for(int i = 0; i < showSize; i++) {
				if((int)show1.charAt(i) != (int)show2.charAt(i)) {
					int char1ShowChar = (int) show1.charAt(i);
					int char2ShowChar = (int) show2.charAt(i);
					
					if(char1ShowChar < char2ShowChar) {
						return -1;
					}else if(char1ShowChar > char2ShowChar) {
						return 1;
					}
					return 2;
				}
			}

		return 0;
	}

	};

	/**
	 * Prints the data in pre-order
	 * to the console
	 */
	public void preOrderPrint() {
		System.out.println();
		preOrderPrint(root);
	}

	/**
	 * Helper method to preOrderPrint method
	 * Prints the data in pre order
	 * to the console
	 */
	private void preOrderPrint(Node node) {
		if(node != null) {
			System.out.println(node.data);
			preOrderPrint(node.left);
			preOrderPrint(node.right);
		}
	}

	/**
	 * Prints the data in sorted order 
	 * to the console
	 */
	public void inOrderPrint() {
		System.out.println();
		inOrderPrint(root);
	}

	/**
	 * Helper method to inOrderPrint method
	 * Prints the data in sorted order
	 * to the console
	 */
	private void inOrderPrint(Node node) {
		if(node != null) {
			inOrderPrint(node.left);
			System.out.println(node.data);
			inOrderPrint(node.right);
		}
	}

	/**
	 * Prints the data in post order
	 * to the console
	 */
	public void postOrderPrint() {
		System.out.println();
		postOrderPrint(root);
	}

	/**
	 * Helper method to postOrderPrint method
	 * Prints the data in post order
	 * to the console
	 */
	private void postOrderPrint(Node node) {
		if(node != null) {
			postOrderPrint(node.left);
			postOrderPrint(node.right);
			System.out.println(node.data);
		}
	}
}