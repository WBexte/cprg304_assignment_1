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

	@Override 	
	public int size() {
		// TODO Auto-generated method stub
		int count = 0;
        MyDLLNode<E> curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		head = null;
		tail = null;
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (toAdd == null) {
			throw new NullPointerException("Cannot add null element");
		}
		MyDLLNode<E> curr = head;
		MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd);
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index out of range");
		}
		if (index == 0) {
			newNode.next = head;
			head = newNode;
			return true;
		}
		for (int i = 0; i < index - 1; i++) {
			curr = curr.next;
		}
		newNode.prev = curr.prev;
		newNode.next = curr;
		curr.prev = newNode;
		return true;
		
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException {
		// TODO Auto-generated method stub
		if (toAdd == null) {
			throw new NullPointerException("Cannot add null element");
		}
		MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd);
		if (head == null) {
			head = newNode;
			tail = newNode;
			return true;
		}
		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		// TODO Auto-generated method stub
		if(toAdd == null) {
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
		// TODO Auto-generated method stub
		MyDLLNode<E> curr = head;
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index out of range");
		}
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr.item;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		MyDLLNode<E> curr = head;
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index out of range");
		}
		if (index == 0) {
			head = head.next;
			head.prev = null;
			return curr.item;
		}
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		curr.prev.next = curr.next;
		curr.next.prev = curr.prev;
		return curr.item;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		// TODO Auto-generated method stub
		if (toRemove == null) {
			throw new NullPointerException("Cannot remove null element");
		}
		MyDLLNode<E> curr = head;
		if (head == null) {
			return null;
		}
		if (head.item.equals(toRemove)) {
			head = head.next;
			head.prev = null;
			return curr.item;
		}
		while (curr != null) {
			if (curr.item.equals(toRemove)) {
				curr.prev.next = curr.next;
				curr.next.prev = curr.prev;
				return curr.item;
			}
			curr = curr.next;
		}
		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (toChange == null) {
			throw new NullPointerException("Cannot set null element");
		}
		MyDLLNode<E> curr = head;
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index out of range");
		}
		if (index == 0) {
			head.item = toChange;
			return head.item;
		}
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		curr.item = toChange;
		return curr.item;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (head == null) {
			return true;
		}
		return false;
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
		// TODO Auto-generated method stub
		if (toHold == null) {
			throw new NullPointerException("Cannot hold null element");
		}
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
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
