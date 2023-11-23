package submission_2.classes;

import submission_2.util.*;

/**
 * <p>
 * The <code>MyArrayList</code> class implements the <code>ListADT</code>
 * interface to provide a dynamic array-based list with various operations.
 * </p>
 * 
 * @param <E> The type of elements this list holds.
 */
public class MyArrayList<E> implements ListADT<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;
    
    /**
     * Constructs an empty list with the default initial capacity.
     */
    public MyArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
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
            throw new NullPointerException("Element to add cannot be null");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        ensureCapacity(size + 1);  // Ensure capacity for at least size + 1 elements

        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }

        array[index] = toAdd;
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
            throw new NullPointerException("Element to add cannot be null");
        }

        ensureCapacity(size + 1);  // Ensure capacity for at least size + 1 elements

        array[size] = toAdd;
        size++;
        return true;
    }

    /**
     * Appends all of the elements in the specified
     * <code>java.utilCollection</code> to the end of this list, in the order that
     * they are returned by the specified collection's <code>Iterator</code>. The
     * behavior of this operation is unspecified if the specified collection is
     * modified while the operation is in progress. (Note that this will occur if
     * the specified collection is this list, and it's nonempty.)
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
            throw new NullPointerException("List to add cannot be null");
        }

        ensureCapacity(size + toAdd.size());

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
    @SuppressWarnings("unchecked")
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        return (E) array[index];
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
    @SuppressWarnings("unchecked")
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        E removedElement = (E) array[index];

        System.arraycopy(array, index + 1, array, index, size - index - 1);

        array[size - 1] = null;
        size--;

        return removedElement;
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
    @SuppressWarnings("unchecked")
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) {
            throw new NullPointerException("Element to remove cannot be null");
        }

        for (int i = 0; i < size; i++) {
            if (toRemove.equals(array[i])) {
                return remove(i);
            }
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
            throw new NullPointerException("Element to set cannot be null");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        E previousElement = get(index);
        array[index] = toChange;
        return previousElement;
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
    @SuppressWarnings("unchecked")
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException("Element to find cannot be null");
        }

        for (int i = 0; i < size; i++) {
            if (toFind.equals(array[i])) {
                return true;
            }
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
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException("Array to hold elements cannot be null");
        }

        if (toHold.length < size) {
            toHold = (E[]) new Object[size];
        } else {
            clearArray(toHold);
        }

        System.arraycopy(array, 0, toHold, 0, size);

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
        Object[] result = new Object[size];
        System.arraycopy(array, 0, result, 0, size);
        return result;
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
     * Ensures that the underlying array has the capacity to hold at least
     * <code>minCapacity</code> elements.
     * 
     * @param minCapacity
     *            The minimum capacity to ensure.
     */
    private void ensureCapacity(int minCapacity) {
        int currentCapacity = array.length;
        if (minCapacity > currentCapacity) {
            int newCapacity = Math.max(currentCapacity * 2, minCapacity);
            Object[] newArray = new Object[newCapacity];
            copyArray(array, newArray, size);
            array = newArray;
        }
    }

    /**
     * Copies elements from the source array to the destination array.
     * 
     * @param src
     *            The source array.
     * @param dest
     *            The destination array.
     * @param length
     *            The number of elements to copy.
     */
    private static void copyArray(Object[] src, Object[] dest, int length) {
        for (int i = 0; i < length; i++) {
            dest[i] = src[i];
        }
    }

    /**
     * Clears all elements in the given array by setting them to null.
     * 
     * @param array
     *            The array to clear.
     */
    private static void clearArray(Object[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    /**
     * The private <code>MyIterator</code> class provides an iterator for the
     * elements in the <code>MyArrayList</code>.
     */
    private class MyIterator implements Iterator<E> {

        private int currentIndex = 0;
        private boolean hasNext = true;

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
                throw new RuntimeException("No more elements in the list");
            }

            E nextElement = (E) array[currentIndex];

            currentIndex++;
            if (currentIndex == size) {
                hasNext = false;
            }

            return nextElement;
        }
    }
}
