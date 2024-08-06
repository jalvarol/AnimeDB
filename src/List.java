/**
* List.java
* @author Jose Leos
*/

import java.util.NoSuchElementException;

public class List<T extends Comparable<T>> {
    private class Node {
        private T data;
        private Node next;
        private Node prev;
        
        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    private int length;
    private Node first;
    private Node last;
    private Node iterator;
    
    /****CONSTRUCTOR****/
    
    /**
     * Instantiates a new List with default values
     * @postcondition the constructor initialize with null & 0.
     */
    public List() {
    	first = null;
    	last = null;
    	iterator = null;
    	length = 0;
 
    }
    
    /**
     * Instantiates a new List by copying another List
     * @param original the List to make a copy of
     * @postcondition a new List object, which is an identical
     * but separate copy of the List original
     */
    public List(List<T> original) {
        if (original == null) { 
            return;
        }
        if (original.length == 0) {
            length = 0;
            first = null;
            last = null;
            iterator = null;
        } else {
            Node temp = original.first;
            while (temp != null) {
                addLast(temp.data);
                temp = temp.next;
            }
            iterator = null;
        }
    }
    
    /****ACCESSORS****/
    
    /**
     * Returns the value stored in the first node
     * @precondition the list is not empty
     * @return the value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException{
        if (length == 0) {
            throw new NoSuchElementException("getFirst: List is Empty. No data to access!");
        }
        return first.data;
    }
    
    /**
     * Returns the value stored in the last node
     * @precondition the list is not empty
     * @return the value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
    public T getLast() throws NoSuchElementException{
    	if (length == 0) {
            throw new NoSuchElementException("getLast: List is Empty. No data to access!");
        }
        return last.data;
    }
    
    /**
     * Returns the current length of the list
     * @return the length of the list from 0 to n
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Returns whether the list is currently empty
     * @return whether the list is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }
    
    /**
     * returns whether the iterator is off the end of the list
     * @return whether the iterator is off the end
     */
    public boolean offEnd() {
    	return iterator == null;
    }
    
    
    /**
     * returns the value currently pointed at by the iterator
     * @precondition iterator != null
     * @return the value stored in the node pointed by the iterator
     * @throws NoSuchElementException when precondition is violated
     */
    public T getIterator() throws NullPointerException{
    	if (offEnd()) {
    		throw new NullPointerException("getIterator(): "+"iterator is off the end, no data to access.");
    	}
    	return iterator.data;
    }
    
    
    /**
     * Determines whether two Lists have the same data
     * in the same order
     * @param L the List to compare to this List
     * @return whether the two Lists are equal
     */
    @SuppressWarnings("unchecked")
    @Override public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if (!(o instanceof List)) {
            return false;
        } else {
            List<T> L = (List<T>) o; 
            if (this.length != L.length) {
                return false;
            } else {
                Node temp1 = this.first;
                Node temp2 = L.first;
                while (temp1 != null) { //Lists are same length
                    if (temp1.data != temp2.data) {
                        return false;
                    }
                    temp1 = temp1.next;
                    temp2 = temp2.next;
                }
                return true;
            }
        }
    }
    
    /**
     * Determines whether a List is sorted
     * by calling its recursive helper method
     * isSorted
     * Note: An empty List can be
     * considered to be (trivially) sorted
     * @return whether this List is sorted
     */
    public boolean inSortedOrder() {
    	return inSortedOrder(first);
    }


    /**
     * Helper method to inSortedOrder 
     * Determines whether a List is 
     * sorted in ascending order recursively
     * @return whether this List is sorted
     */
    private boolean inSortedOrder(Node node) {
    	if(node == null) {
    		return true;
    	}else if(node == last.prev && node.data.compareTo(node.next.data) < 0) {
    		return true;
    	}else {
    		if (node.data.compareTo(node.next.data) < 0) {
    			return inSortedOrder(node.next);
    			
    		} else {
        		return false;
    		}
    	}
    	
    
    }

    
    /**
     * Returns the index of the iterator
     * from 1 to n. Note that there is 
     * no index 0. Does not use recursion.
     * @precondition !isEmpty()
     * @return the index of the iterator
     * @throws NullPointerException when
     * the precondition is violated
     */
    public int getIndex() throws NullPointerException{
    	if(offEnd()) {
    		throw new NullPointerException("getIndex(): " + "List is empty, cannot get index.");
    	}
    	int index = 0;
    	while(!offEnd()) {
    		this.reverseIterator();
    		index++;
    	}
    	this.advanceToIndex(index);
        return index;
    }
    
    
    /**
     * Uses the iterative linear search
     * algorithm to locate a specific
     * element in the list
     * @param element the value to search for
     * @return the location of value in the
     * List or -1 to indicate not found
     * Note that if the List is empty we will
     * consider the element to be not found
     * @postcondition: position of the iterator remains
     * unchanged!
     */
 	public int linearSearch(T element) {
		Node temp = first;
		for (int i = 1; i <= length; i++) {
			if (temp.data.equals(element)) {
				return i;
			}
			temp = temp.next;
		}
		return -1;
	}

    
    /**
     * Returns the index from 1 to length
     * where value is located in the List
     * by calling the private helper method
     * binarySearch
     * @param value the value to search for
     * @return the index where value is 
     * stored from 1 to length, or -1 to
     * indicate not found
     * @precondition isSorted()
     * @postcondition the position of the
     * iterator must remain unchanged! 
     * @throws IllegalStateException when the
     * precondition is violated.
     */
    public int binarySearch(T value) throws IllegalStateException {
		if (!inSortedOrder()) {
			throw new IllegalStateException("binarySearch(): List is not sorted!");
		} else
			
			return binarySearch(1, length, value);
	}
    
    /**
     * Searches for the specified value in
     * the List by implementing the recursive
     * binarySearch algorithm
     * @param low the lowest bounds of the search
     * @param high the highest bounds of the search
     * @param value the value to search for
     * @return the index at which value is located
     * or -1 to indicate not found
     * @postcondition the location of the iterator
     * must remain unchanged
     */
	private int binarySearch(int low, int high, T value) {
		if (high < low)
			return -1;
		int mid = low + (high - low) / 2;
		Node temp = first;
		for (int i = 1; i < mid; i++) {
			temp = temp.next;
		}
		if (value.compareTo(temp.data) == 0) {
			return mid;
		} else if (value.compareTo(temp.data) < 0)
			return binarySearch(low, mid - 1, value);
		else
			return binarySearch(mid + 1, high, value);
	}

    
    /****MUTATORS****/
    
    /**
     * Creates a new first element
     * @param data the data to insert at the 
     * front of the list
     * @postcondition a new node is inserted at the beginning
     */
	public void addFirst(T data) // doubly linked list updated
	{
		if (isEmpty())// edge
		{
			first = last = new Node(data);
		} else // general
		{
			Node n = new Node(data);
			n.next = first;
			first.prev = n;
			first = n;
		}
		length++;
	}

    
    /**
     * Creates a new last element
     * @param data the data to insert at the 
     * end of the list
     * @postcondition a new node is inserted at the end of list
     */
    public void addLast(T data)// doubly linked list updated
	{
		if (isEmpty())// edge
		{
			first = last = new Node(data);
		} else // general
		{
			Node n = new Node(data);
			last.next = n;
			n.prev = last;
			last = n;
		}
		length++;
	}
    /**
    * removes the element at the front of the list
    * @precondition the list is not empty
    * @postcondition the first element of the list is removed
    * @throws NoSuchElementException when precondition is violated
    */
	public void removeFirst() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("removeFirst(): " + "List is empty. No data to access!");
		} else if (length == 1) {
			first = last = null;
		} else {
			first = first.next;
			first.prev = first;
		}
		length--;

	}
    
    /**
     * removes the element at the end of the list
     * @precondition the list is not empty
     * @postcondition the last element of the list is removed
     * @throws NoSuchElementException when precondition is violated
     */
	public void removeLast() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("removeLast(): " + "List is empty. No data to access!");
		} else if (length == 1) {
			last = first = null;
		} else {
			last = last.prev;
			last.next = null;
		}
		length--;
	}

    /**
     * move the iterator to the start of the list
     * @precondition length != 0
     * @postcondition iterator = first
     * 
     */
	public void placeIterator() {
		if (isEmpty()) {
			first = iterator = null;
		} else
			iterator = first;
	}
    
    /**
     * remove the element pointed by the iterator
     * @precondition iterator != null
     * @throws NullPointerException when precondition is violated
     * @postcondition element pointed by the iterator is removed from the list
     */
	public void removeIterator() throws NullPointerException {
		if (offEnd())// precondition
		{
			throw new NullPointerException("removeIterator:" + "Iterator is offEnd. Cannot remove.");
		} else if (iterator == first)// edge case
		{
			removeFirst();
		} else if (iterator == last)// edge case
		{
			removeLast();
		} else// general case
		{
			iterator.prev.next = iterator.next;
			iterator.next.prev = iterator.prev;
			length--;
		}
		iterator = null;
	}
    
    /**
     * inserts an element after the node currently pointed to by the iterator
     * @precondition iterator != null
     * @param data
     * @postcondition an element is inserted after the iterator
     */
	public void addIterator(T data) throws NullPointerException {
		if (offEnd()) // precondition
		{
			throw new NullPointerException("addIterator: " + "Iterator is offend. Cannot add.");
		} else if (iterator == last) // edge case
		{
			addLast(data);
		} else // general case
		{
			Node n = new Node(data);
			n.next = iterator.next;
			n.prev = iterator;
			iterator.next.prev = n;
			iterator.next = n;
			length++;
		}
	}
    /**
     * moves the iterator up by one node
     * @precondition iterator != null
     * @throws NullPointerException when precondition is violated
     * @postcondition iterator up by one node
     */
    public void advanceIterator() throws NullPointerException{
    	if(offEnd()) {
    		throw new NullPointerException("advanceIterator(): "+"iterator is off the end, cannot be advanced.");
    	}else {
    		iterator = iterator.next;
    	}
    }
    
    /**
     * moves the iterator down by one node
     * @precondition iterator != null
     * @throws NullPointerException when precondition is violated
     * @postcondition iterator down by one node
     */
    public void reverseIterator() throws NullPointerException{
    	if(offEnd()) {
    		throw new NullPointerException("reverseIterator(): "+"iterator is off the end, cannot be reversed.");
    	}else {
    		iterator = iterator.prev;
    	}
    }
    

    /**
     * Places the iterator at first
     * and then iteratively advances 
     * it to the specified index
     * no recursion
     * @param index the index where
     * the iterator should be placed
     * @precondition 1 <= index <= length
     * @throws IndexOutOfBoundsException
     * when precondition is violated
     */
    public void advanceToIndex(int index) throws IndexOutOfBoundsException{
    	if(index < 1 || index > this.getLength()) {
    		throw new IndexOutOfBoundsException("advanceToIndex: " + "Index out of bound, cannot advance.");
    	}
    	this.placeIterator();
    	for(int i = 1; i < index; i++) {
    		this.advanceIterator();
    	}
        
    }
    
    
    /****ADDITIONAL OPERATIONS****/
    
    /**
     * List with each value on its own line
     * At the end of the List a new line
     * @return the List as a String for display
     */
	@Override
	public String toString() {
		String result = "";
		Node temp = first;
		while (temp != null) {
			result += temp.data+"\n";
			temp = temp.next;
	
		}
		return result;
	}

    /**
     * Prints a linked list to the console
     * in reverse by calling the private 
     * recursive helper method printInReverse
     */
	public void printInReverse() {
		Node temp = last;
		printInReverse(temp);
	}

    /**
     * Recursively prints a linked list to the console
     * in reverse order from last to first (no loops)
     * Each element separated by a space
     * Should print a new line after all
     * elements have been displayed
     */    

    private void printInReverse(Node node) throws NullPointerException {
		if (node == null) {
			System.out.println();
			return;
		} else
			System.out.printf(node.data + " ");
		printInReverse(node.prev);
	}



}

