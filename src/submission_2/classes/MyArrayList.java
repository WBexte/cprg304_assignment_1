package submission_2.classes;

import submission_2.util.*;

public class MyArrayList<E> implements ListADT<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public MyArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
    }
    
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

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        return (E) array[index];
    }

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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

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

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(array, 0, result, 0, size);
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private void ensureCapacity(int minCapacity) {
        int currentCapacity = array.length;
        if (minCapacity > currentCapacity) {
            int newCapacity = Math.max(currentCapacity * 2, minCapacity);
            Object[] newArray = new Object[newCapacity];
            copyArray(array, newArray, size);
            array = newArray;
        }
    }


    private static void copyArray(Object[] src, Object[] dest, int length) {
        for (int i = 0; i < length; i++) {
            dest[i] = src[i];
        }
    }

    private static void clearArray(Object[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    private class MyIterator implements Iterator<E> {

        private int currentIndex = 0;
        private boolean hasNext = true;

        @Override
        public boolean hasNext() {
            return hasNext;
        }

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
