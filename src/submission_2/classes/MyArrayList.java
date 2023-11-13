package submission_2.classes;

import java.util.Arrays;
import java.util.NoSuchElementException;

import submission_2.util.Iterator;
import submission_2.util.ListADT;

public class MyArrayList<E> implements ListADT<E> {
	
    private int DEFAULT_CAPACITY = 10;
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
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null element");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = toAdd;
        size++;
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null element");
        }

        ensureCapacity(size + 1);
        array[size++] = toAdd;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
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
            throw new NullPointerException("Cannot remove null element");
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
            throw new NullPointerException("Cannot set null element");
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
            throw new NullPointerException("Cannot search for null element");
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
            toHold = Arrays.copyOf(toHold, size);
        }

        System.arraycopy(array, 0, toHold, 0, size);
        return toHold;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = Math.max(array.length * 2, minCapacity);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    public class MyArrayListIterator implements Iterator<E> {
        private int currentIndex;

        public MyArrayListIterator() {
            this.currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the iterator");
            }
            return (E) array[currentIndex++];
        }
    }
}
