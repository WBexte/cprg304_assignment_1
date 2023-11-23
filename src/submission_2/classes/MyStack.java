package submission_2.classes;

import submission_2.util.Iterator;
import submission_2.util.StackADT;

/**
 * The <code>MyStack</code> class implements the <code>StackADT</code> interface
 * to provide a simple stack with various operations.
 * 
 * @param <E> The type of elements this stack holds.
 */
public class MyStack<E> implements StackADT<E> {

	private static final long serialVersionUID = 1L;
	private MyArrayList<E> list;

    /**
     * Constructs an empty stack.
     */
    public MyStack() {
        this.list = new MyArrayList<>();
    }

    /**
     * Exception thrown when attempting to operate on an empty stack.
     */
    public static class StackEmptyException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public StackEmptyException(String message) {
            super(message);
        }
    }

    /**
     * Adds the specified element to the top of the stack.
     * 
     * @param toAdd The element to be added to the stack.
     * @throws NullPointerException If the specified element is <code>null</code>.
     */
    @Override
    public void push(E toAdd) throws NullPointerException {
        list.add(size(), toAdd);
    }

    /**
     * Removes and returns the element at the top of the stack.
     * 
     * @return The element at the top of the stack.
     * @throws StackEmptyException If the stack is empty.
     */
    @Override
    public E pop() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("Stack is empty");
        }

        return list.remove(size() - 1);
    }

    /**
     * Returns the element at the top of the stack without removing it.
     * 
     * @return The element at the top of the stack.
     * @throws StackEmptyException If the stack is empty.
     */
    @Override
    public E peek() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("Stack is empty");
        }

        return list.get(size() - 1);
    }

    /**
     * Clears all elements from the stack.
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * Returns <code>true</code> if the stack is empty.
     * 
     * @return <code>true</code> if the stack is empty.
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns an array containing all of the elements in the stack in proper sequence.
     * 
     * @return An array containing all of the elements in the stack.
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        int index = 0;

        for (int i = size() - 1; i >= 0; i--) {
            array[index++] = list.get(i);
        }

        return array;
    }

    /**
     * Returns an array containing all of the elements in the stack in proper sequence;
     * the runtime type of the returned array is that of the specified array.
     * 
     * @param holder The array into which the elements of the stack are to be stored.
     * @return An array containing the elements of the stack.
     * @throws NullPointerException If the specified array is <code>null</code>.
     */
    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder.length < size()) {
            holder = (E[]) new Object[size()];
        } else {
            for (int i = 0; i < holder.length; i++) {
                holder[i] = null;
            }
        }

        int index = 0;
        for (int i = size() - 1; i >= 0; i--) {
            holder[index++] = list.get(i);
        }

        return holder;
    }

    /**
     * Returns <code>true</code> if the stack contains the specified element.
     * 
     * @param toFind The element to check for.
     * @return <code>true</code> if the stack contains the specified element.
     * @throws NullPointerException If the specified element is <code>null</code>.
     */
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return list.contains(toFind);
    }

    /**
     * Returns the 1-based position where an object is on this stack.
     * 
     * @param toFind The desired object.
     * @return The 1-based position from the top of the stack where the object is located;
     *         the return value -1 indicates that the object is not on the stack.
     */
    @Override
    public int search(E toFind) {
        for (int i = 0; i < size(); i++) {
            if (toFind.equals(list.get(i))) {
                return size() - i;
            }
        }
        return -1;
    }

    /**
     * Returns an iterator over the elements in the stack in proper sequence.
     * 
     * @return An iterator over the elements in the stack in proper sequence.
     */
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    /**
     * Compares two stacks for equality.
     * 
     * @param that The stack to be compared to this stack.
     * @return <code>true</code> if the stacks are equal.
     */
    @Override
    public boolean equals(StackADT<E> that) {
        if (this.size() != that.size()) {
            return false;
        }

        Iterator<E> thisIterator = this.iterator();
        Iterator<E> thatIterator = that.iterator();

        while (thisIterator.hasNext() && thatIterator.hasNext()) {
            if (!thisIterator.next().equals(thatIterator.next())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the current size of the stack.
     * 
     * @return The current size of the stack.
     */
    @Override
    public int size() {
        return list.size();
    }
}
