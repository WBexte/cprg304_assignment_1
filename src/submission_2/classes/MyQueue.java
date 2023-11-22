/**
 * 
 */
package submission_2.classes;

import submission_2.exceptions.EmptyQueueException;
import submission_2.util.Iterator;
import submission_2.util.QueueADT;

/**
 * 
 */
public class MyQueue<E> implements QueueADT<E> {

    private static final int MAX_SIZE = 3; // Set your desired maximum size

    private MyDLL<E> QueueList;

    public MyQueue() {
        QueueList = new MyDLL<>();
    }

    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (size() >= MAX_SIZE) {
            throw new IllegalStateException("Queue is full");
        }
        QueueList.add(toAdd);
    }

	@Override
	public E dequeue() throws EmptyQueueException {
	     if (QueueList.isEmpty()) {
	           throw new EmptyQueueException("Queue is empty");
	     }
		E item = QueueList.get(0);
		QueueList.remove(0); 
		return item;
	}

	@Override
	public E peek() throws EmptyQueueException {
	       if (QueueList.isEmpty()) {
	           throw new EmptyQueueException("Queue is empty");
	       }
	   	E item = QueueList.get(0);
		return item;
   }

	@Override
	public void dequeueAll() {
		// TODO Auto-generated method stub
		while(QueueList.size() > 0) {
			QueueList.remove(0);
		}
	}

	@Override
	public boolean isEmpty() {

		return QueueList.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {

		return QueueList.iterator() ;
	}

	@Override
	public boolean equals(QueueADT<E> that) {
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

	@Override
	public Object[] toArray() {
		
		return QueueList.toArray();
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		return QueueList.toArray(holder);
	}
	
	@Override
	public boolean isFull() {
	    return QueueList.size() >= MAX_SIZE;
	}

	@Override
	public int size() {

		return QueueList.size();
	}

}