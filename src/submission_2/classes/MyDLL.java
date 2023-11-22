package submission_2.classes;

import submission_2.util.Iterator;
import submission_2.util.ListADT;

public class MyDLL<E> implements ListADT<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MyDLLNode<E> head;
	public MyDLLNode<E> tail;
    private int size;
    
    public MyDLL() {
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
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

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {

        private int currentIndex = 0;
        private boolean hasNext = true;
        private MyDLLNode<E> curr = head;

        @Override
        public boolean hasNext() {
            return hasNext;
        }

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
