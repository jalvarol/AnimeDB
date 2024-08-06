
/**
 * BST.java
 * @author Jose Leos
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.List;

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
	private Comparator<T> comparator;

	/*** CONSTRUCTORS ***/

	/**
	 * Default constructor for BST
	 * sets root to null and comparator to
	 * null
	 */
	public BST() {
		root = null;
		comparator = null;
	}

	/**
	 * Constructor for BST sets root to null
	 * and assigns comparator
	 */
	public BST(Comparator<T> comp) {
		root = null;
		comparator = comp;
	}

	/**
	 * Copy constructor for BST
	 * 
	 * @param bst the BST to make
	 *            a copy of
	 */
	public BST(BST<T> bst) {
		if (bst == null) {
			return;
		}
		if (bst.getSize() == 0) {
			this.root = null;
		} else {
			copyHelper(bst.root);
		}
	}

	/**
	 * Helper method for copy constructor
	 * 
	 * @param node the node containing
	 *             data to copy
	 */
	private void copyHelper(Node node) {
		if (node == null) {
			return;
		} else
			insert(node.data);
		copyHelper(node.left);
		copyHelper(node.right);
	}

	/*** ACCESSORS ***/

	/**
	 * Returns the data stored in the root
	 * 
	 * @precondition !isEmpty()
	 * @return the data stored in the root
	 * @throws NoSuchElementException when
	 *                                preconditon is violated
	 */
	public T getRoot() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("getRoot() " + "BST is empty, no data to access.");
		}
		return root.data;
	}

	/**
	 * Determines whether the tree is empty
	 * 
	 * @return whether the tree is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Returns the current size of the
	 * tree (number of nodes)
	 * 
	 * @return the size of the tree
	 */
	public int getSize() {
		return getSize(root);
	}

	/**
	 * Helper method for the getSize method
	 * 
	 * @param node the current node to count
	 * @return the size of the tree
	 */
	private int getSize(Node node) {
		if (node == null) {
			return 0;
		}
		return (getSize(node.left) + 1 + getSize(node.right));
	}

	/**
	 * Returns the height of tree by
	 * counting edges.
	 * 
	 * @return the height of the tree
	 */
	public int getHeight() {
		return getHeight(root) - 1;
	}

	/**
	 * Helper method for getHeight method
	 * 
	 * @param node the current
	 *             node whose height to count
	 * @return the height of the tree
	 */
	private int getHeight(Node node) {
		if (node == null)
			return -1;
		else {
			int lDepth = getHeight(node.left);
			int rDepth = getHeight(node.right);
			if (lDepth > rDepth)
				return (lDepth + 1);
			else
				return (rDepth + 1);
		}

	}

	/**
	 * Returns the smallest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the smallest value in the tree
	 * @throws NoSuchElementException when the
	 *                                precondition is violated
	 */
	public T findMin() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("findMin(): Tree is empty, no data to access!");
		} else
			return findMin(this.root);
	}

	/**
	 * Helper method to findMin method
	 * 
	 * @param node the current node to check
	 *             if it is the smallest
	 * @return the smallest value in the tree
	 */
	private T findMin(Node node) {
		if (node.left != null) {
			return findMin(node.left);
		}
		return node.data;
	}

	/**
	 * Returns the largest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the largest value in the tree
	 * @throws NoSuchElementException when the
	 *                                precondition is violated
	 */
	public T findMax() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("findMax(): Tree is empty, no data to access!");
		}
		return findMax(this.root);
	}

	/**
	 * Helper method to findMax method
	 * 
	 * @param node the current node to check
	 *             if it is the largest
	 * @return the largest value in the tree
	 */
	private T findMax(Node node) {
		if (node.right != null) {
			return findMax(node.right);
		}
		return node.data;
	}

	/**
	 * Searches for a specified value
	 * in the tree
	 * 
	 * @param data the value to search for
	 * @return whether the value is stored
	 *         in the tree
	 */
	public boolean search(T data) {
		return search(data, this.root);
	}

	/**
	 * Helper method for the search method
	 * 
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return whether the data is stored
	 *         in the tree
	 */
	private boolean search(T data, Node node) {
        if (node == null) {
            return false;
        }

        if (compare(data, node.data) == 0) {
            return true;
        } else if (compare(data, node.data) < 0) {
            return search(data, node.left);
        } else {
            return search(data, node.right);
        }
	}

	/**
	 * Compares two elements in the tree
	 * If a custom comparator is provided, it uses that for comparison.
	 * Otherwise, it falls back to the natural ordering defined by the Comparable
	 * interface.
	 * 
	 * @param x the first element to compare
	 * @param y the second element to compare
	 * @return a negative integer, zero, or a positive integer as the first argument
	 *         is less than, equal to, or greater than the second.
	 */
    private int compare(T x, T y) {
        if (comparator == null) {
            return ((Comparable<T>) x).compareTo(y);
        } else {
            return comparator.compare(x, y);
        }
    }
	/**
	 * Determines whether two trees store
	 * identical data in the same structural
	 * position in the tree
	 * 
	 * @param o another Object
	 * @return whether the two trees are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof BST)) {
			return false;
		} else {
			BST<T> L = (BST<T>) o;
			if (L.getSize() != this.getSize()) {
				return false;
			}
			return equals(this.root, L.root);
		}

	}

	/**
	 * Helper method for the equals method
	 * 
	 * @param node1 the node of the first bst
	 * @param node2 the node of the second bst
	 * @return whether the two trees contain
	 *         identical data stored in the same structural
	 *         position inside the trees
	 */
	private boolean equals(Node node1, Node node2) {
		if (node1 == null && node2 == null) {
			return true;
		} else if (node1 != null && node2 != null) {
			return (node1.data.equals(node2.data)
					&& equals(node1.left, node2.left)
					&& equals(node1.right, node2.right));
		} else {
			return false;
		}

	}

	/*** MUTATORS ***/

	/**
	 * Inserts a new node in the tree
	 * 
	 * @param data the data to insert
	 */
	public void insert(T data) {
		root = insert(data, root);
	}

	/**
	 * Helper method to insert
	 * Inserts a new value in the tree
	 * 
	 * @param data the data to insert
	 * @param node the current node in the
	 * search for the correct location
	 * in which to insert
	 */
	private Node insert(T data, Node node) {
		if (node == null) {
			return new Node(data);
		}
		if (compare(data, node.data) <= 0) {
			node.left = insert(data, node.left);
		} else {
			node.right = insert(data, node.right);
		}
		return node;
	}
    /**
     * Removes a value from the BST
     * @param data the value to remove
     * @precondition !isEmpty()
     * @precondition the data is located in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public void remove(T data) throws NoSuchElementException
    {
       if(isEmpty())
       {
    	   throw new NoSuchElementException("remove(): BST is empty, no data to access!");
       }
       else if (search(data) == false)
       {
    	   throw new NoSuchElementException("remove(): Element is not located within the tree!");
       }
       else
       {
    	   root = remove(data,root);
       }
    }
   
    /**
     * Helper method to the remove method
     * @param data the data to remove
     * @param node the current node
     * @return an updated reference variable
     */
    private Node remove(T data, Node node) 
    {	   

        if(data.compareTo(node.data)<0)
        {
				 node.left = remove(data,node.left);
        }	
        else if(data.compareTo(node.data)>0)
    		{
    	   		 node.right = remove(data, node.right);
    		}
       else
       {
    	   if(node.left==null && node.right==null)
    	   { 	
    		  node = null;
    		  return node;
    	   }
    	   else if(node.left==null && node.right != null)
    	   {
    		   return node.right;
    	   }
    	   else if(node.right==null && node.left != null) 
    	   {
    		   return node.left;
    	   }
    	   else
    	   {	   
    	   		node.data = findMin(node.right);
    	   		node.right = remove(node.data,node.right);	
    	   }
       }
        return node;
       
   }  
    
	/*** ADDITIONAL OPERATIONS ***/

	/**
	 * Prints the data in pre-order
	 * to the console
	 */
	public void preOrderPrint() {
		preOrderPrint(root);
	}

	/**
	 * Helper method to preOrderPrint method
	 * Prints the data in pre order
	 * to the console
	 */
	private void preOrderPrint(Node node) {
		if (node == null) {
			return;
		} else
			System.out.println(node.data + " ");
		preOrderPrint(node.left);
		preOrderPrint(node.right);

	}

	/**
	 * Prints the data in sorted order
	 * to the console
	 */
	public void inOrderPrint() {
		inOrderPrint(root);
	}

	/**
	 * Helper method to inOrderPrint method
	 * Prints the data in sorted order
	 * to the console
	 */
	private void inOrderPrint(Node node) {
		if (node == null) {
			return;
		} else
			inOrderPrint(node.left);
		System.out.println(node.data + " ");
		inOrderPrint(node.right);

	}

	/**
	 * Prints the data in post order
	 * to the console
	 */
	public void postOrderPrint() {
		postOrderPrint(root);
	}

	/**
	 * Helper method to postOrderPrint method
	 * Prints the data in post order
	 * to the console
	 */
	private void postOrderPrint(Node node) {
		if (node == null) {
			return;
		} else
			postOrderPrint(node.left);
		postOrderPrint(node.right);
		System.out.println(node.data + " ");

	}

	    // Helper method to collect all occurrences of a specific element
		public List<T> collectOccurrences(T data) {
			List<T> occurrences = new ArrayList<>();
			collectOccurrencesHelper(root, data, occurrences);
			return occurrences;
		}
		private void collectOccurrencesHelper(Node node, T data, List<T> occurrences) {
			if (node == null) {
				return;
			}
			AnimeChar animeCharData = (AnimeChar) data;
			AnimeChar animeCharNodeData = (AnimeChar) node.data;
	
			boolean nameMatch = !animeCharData.getName().isEmpty() && animeCharNodeData.getName().toLowerCase().contains(animeCharData.getName().toLowerCase());
			boolean showMatch = !animeCharData.getShow().isEmpty() && animeCharNodeData.getShow().toLowerCase().contains(animeCharData.getShow().toLowerCase());
	
			if (nameMatch || showMatch) {
				occurrences.add(node.data);
			}
	
			collectOccurrencesHelper(node.left, data, occurrences);
			collectOccurrencesHelper(node.right, data, occurrences);
		}
	}