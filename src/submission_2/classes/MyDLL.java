package submission_2.classes;

import submission_2.util.Iterator;
import submission_2.util.ListADT;

/**
 * <p>
 * The <code>MyDLL</code> class implements the <code>ListADT</code> interface to
 * provide a doubly linked list with various operations.
 * </p>
 * 
 * @param <E> The type of elements this list holds.
 */
public class MyDLL<E> implements ListADT<E> {

	private static final long serialVersionUID = 1L;
	public MyDLLNode<E> head;
	public MyDLLNode<E> tail;
    private int size;
    
    /**
     * Constructs an empty doubly linked list.
     */
    public MyDLL() {
        this.size = 0;
    }

    /**
     * Returns the current element count contained in the list.
     * 
     * @return The current element count.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all of the elements from this list. This list will be empty after
     * this call returns.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
     * Inserts the specified element at the specified position in this list. Shifts
     * the element currently at that position (if any) and any subsequent elements
     * to the right (adds one to their indices).
     * 
     * @param index
     *            The index at which the specified element is to be inserted. The
     *            element is inserted before the existing element at [index], or at
     *            the end if index is equal to the size (<code>size()</code>).
     * @param toAdd
     *            The element to be inserted.
     * @return <code>true</code> if the element is added successfully.
     * @throws NullPointerException
     *             If the specified element is <code>null</code> and the list
     *             implementation does not support having <code>null</code>
     *             elements.
     * @throws IndexOutOfBoundsException
     *             If the index is out of range:
     *             i.e. (<code>index < 0 || index > size()</code>).
     */
    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null element");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

        if (index == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            } else {
                tail = newNode; // If the list is empty
            }
            head = newNode;
        } else {
            MyDLLNode<E> curr = head;

            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }

            newNode.prev = curr;
            newNode.next = curr.next;

            if (curr.next != null) {
                curr.next.prev = newNode;
            } else {
                tail = newNode; // If adding at the end
            }

            curr.next = newNode;
        }

        size++;
        return true;
    }

    /**
     * Appends the specified element to the end of this list.
     * 
     * @param toAdd
     *            Element to be appended to this list.
     * @return <code>true</code> if the element is appended successfully.
     * @throws NullPointerException
     *             If the specified element is <code>null</code> and the list
     *             implementation does not support having <code>null</code>
     *             elements.
     */
    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null element");
        }
        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }
    
    /**
     * Appends all of the elements in the specified <code>java.utilCollection</code>
     * to the end of this list, in the order that they are returned by the specified
     * collection's <code>Iterator</code>. The behavior of this operation is
     * unspecified if the specified collection is modified while the operation is
     * in progress. (Note that this will occur if the specified collection is this
     * list, and it's nonempty.)
     * 
     * @param toAdd
     *            The new sub list to be added.
     * @return <code>true</code> if the operation is successful.
     * @throws NullPointerException
     *             If the specified element is <code>null</code> and the list
     *             implementation does not support having <code>null</code>
     *             elements.
     */
    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Added list cannot be null");
        }

        Iterator<? extends E> iterator = toAdd.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }

        return true;
    }
    
    /**
     * Returns the element at the specified position in this list.
     * 
     * @param index
     *            Index of element to return.
     * @return The element at the specified position in this list.
     * @throws IndexOutOfBoundsException
     *             If the index is out of range:
     *             i.e. (<code>index < 0 || index >= size()</code>).
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        MyDLLNode<E> curr = head;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.item;
    }

    /**
     * Removes the element at the specified position in this list. Shifts any
     * subsequent elements to the left (subtracts one from their indices). Returns
     * the element that was removed from the list.
     * 
     * @param index
     *            The index of the element to remove.
     * @return The removed element.
     * @throws IndexOutOfBoundsException
     *             If the index is out of range:
     *             i.e. (<code>index < 0 || index >= size()</code>).
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        MyDLLNode<E> curr = head;

        if (index == 0) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null; // If the list is now empty
            }
        } else {
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }

            curr.prev.next = curr.next;

            if (curr.next != null) {
                curr.next.prev = curr.prev;
            } else {
                tail = curr.prev; // If removing the last element
            }
        }

        size--;
        return curr.item;
    }

    /**
     * Removes the first occurrence in this list of the specified element. If this
     * list does not contain the element, it is unchanged. More formally, removes
     * the element with the lowest index <code>i</code> such that
     * <code>o.equals(get(i))</code> (if such an element exists).
     * 
     * @param toRemove
     *            The element to be removed from this list.
     * @return The element which is being removed, or null if the list does not
     *         contain the element.
     * @throws NullPointerException
     *             If the specified element is <code>null</code> and the list
     *             implementation does not support having <code>null</code>
     *             elements.
     */
    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) {
            throw new NullPointerException("Cannot remove null element");
        }

        MyDLLNode<E> curr = head;

        while (curr != null) {
            if (curr.item.equals(toRemove)) {
                if (curr.prev == null) {
                    // If removing the first element
                    head = curr.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null; // If the list is now empty
                    }
                } else {
                    curr.prev.next = curr.next;
                    if (curr.next != null) {
                        curr.next.prev = curr.prev;
                    } else {
                        tail = curr.prev; // If removing the last element
                    }
                }
                size--;
                return curr.item;
            }
            curr = curr.next;
        }

        return null;
    }

    /**
     * Replaces the element at the specified position in this list with the specified
     * element.
     * 
     * @param index
     *            The index of the element to replace.
     * @param toChange
     *            Element to be stored at the specified position.
     * @return The element previously at the specified position.
     * @throws NullPointerException
     *             If the specified element is <code>null</code> and the list
     *             implementation does not support having <code>null</code>
     *             elements.
     * @throws IndexOutOfBoundsException
     *             If the index is out of range:
     *             i.e. (<code>index < 0 || index >= size()</code>).
     */
    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) {
            throw new NullPointerException("Cannot set null element");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        MyDLLNode<E> curr = head;

        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        E oldValue = curr.item;
        curr.item = toChange;

        return oldValue;
    }

    /**
     * Returns <code>true</code> if this list contains no elements.
     * 
     * @return <code>true</code> if this list contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns true if this list contains the specified element. More formally,
     * returns true if and only if this list contains at least one element
     * <code>e</code> such that <code>toFind.equals(e)</code>.
     * 
     * @param toFind
     *            The element whose presence in this list is to be tested.
     * @return <code>true</code> if this list contains the specified element.
     * @throws NullPointerException
     *             If the specified element is <code>null</code> and the list
     *             implementation does not support having <code>null</code>
     *             elements.
     */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		// TODO Auto-generated method stub
		if (toFind == null) {
			throw new NullPointerException("Cannot search for null element");
		}
		MyDLLNode<E> curr = head;
		while (curr != null) {
			if (curr.item.equals(toFind)) {
				return true;
			}
			curr = curr.next;
		}
		return false;
	}

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence; the runtime type of the returned array is that of the specified
     * array. Obeys the general contract of the
     * <code>java.util.Collection.toArray(Object [])</code> method.
     * 
     * @param toHold
     *            The array into which the elements of this list are to be stored,
     *            if it is big enough; otherwise, a new array of the same runtime
     *            type is allocated for this purpose.
     * @return An array containing the elements of this list.
     * @throws NullPointerException
     *             If the specified array is <code>null</code>.
     */
	 @Override
	    public E[] toArray(E[] toHold) throws NullPointerException {
	        if (toHold == null) {
	            throw new NullPointerException("Cannot hold null element");
	        }
	        int listSize = size();
	        if (toHold.length >= listSize) {
	            MyDLLNode<E> curr = head;
	            for (int i = 0; i < listSize; i++) {
	                toHold[i] = curr.item;
	                curr = curr.next;
	            }
	        }
	        return toHold;
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence. Obeys the general contract of the
     * <code>java.util.Collection.toArray()</code> method.
     * 
     * @return An array containing all of the elements in this list in proper
     *         sequence.
     */
    @Override
    public Object[] toArray() {
        int listSize = size();
        Object[] arrayList = new Object[listSize];
        MyDLLNode<E> curr = head;
        for (int i = 0; i < listSize; i++) {
            arrayList[i] = curr.item;
            curr = curr.next;
        }
        return arrayList;
    }

    /**
     * Returns an iterator over the elements in this list, in proper sequence.
     * 
     * @return An iterator over the elements in this list, in proper sequence. NB:
     *         The return is of type <code>linearUtilities.Iterator<E></code>, not
     *         <code>java.util.Iterator</code>.
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }
    
    /**
     * The private <code>MyIterator</code> class provides an iterator for the
     * elements in the <code>MyDLL</code>.
     */
    private class MyIterator implements Iterator<E> {

        private int currentIndex = 0;
        private boolean hasNext = true;
        private MyDLLNode<E> curr = head;

        /**
         * Returns <code>true</code> if the iteration has more elements.
         * 
         * @return <code>true</code> if the iterator has more elements.
         */
        @Override
        public boolean hasNext() {
            return hasNext;
        }
        
        /**
         * Returns the next element in the iteration.
         * 
         * @return The next element in the iteration.
         * @throws NoSuchElementException
         *             If the iteration has no more elements.
         */
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No more elements");
            }

            E result = curr.item;
            curr = curr.next;
            currentIndex++;

            if (curr == null) {
                hasNext = false;
            }

            return result;
        }
    }

}
