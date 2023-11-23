package submission_2.classes;

import submission_2.exceptions.EmptyQueueException;
import submission_2.util.Iterator;
import submission_2.util.QueueADT;

/**
 * The <code>MyQueue</code> class implements the <code>QueueADT</code> interface
 * to provide a simple queue with various operations.
 * 
 * @param <E> The type of elements this queue holds.
 */
public class MyQueue<E> implements QueueADT<E> {

    private static final int MAX_SIZE = 3; // Set your desired maximum size

    private MyDLL<E> QueueList;

    /**
     * Constructs an empty queue.
     */
    public MyQueue() {
        QueueList = new MyDLL<>();
    }

    /**
     * Adds the specified element to the end of the queue.
     * 
     * @param toAdd The element to be added to the queue.
     * @throws NullPointerException If the specified element is <code>null</code>.
     * @throws IllegalStateException If the queue is full.
     */
    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (size() >= MAX_SIZE) {
            throw new IllegalStateException("Queue is full");
        }
        QueueList.add(toAdd);
    }

    /**
     * Removes and returns the element at the front of the queue.
     * 
     * @return The element at the front of the queue.
     * @throws EmptyQueueException If the queue is empty.
     */
	@Override
	public E dequeue() throws EmptyQueueException {
	     if (QueueList.isEmpty()) {
	           throw new EmptyQueueException("Queue is empty");
	     }
		E item = QueueList.get(0);
		QueueList.remove(0); 
		return item;
	}

    /**
     * Returns the element at the front of the queue without removing it.
     * 
     * @return The element at the front of the queue.
     * @throws EmptyQueueException If the queue is empty.
     */
	@Override
	public E peek() throws EmptyQueueException {
	       if (QueueList.isEmpty()) {
	           throw new EmptyQueueException("Queue is empty");
	       }
	   	E item = QueueList.get(0);
		return item;
   }

    /**
     * Removes all elements from the queue.
     */
	@Override
	public void dequeueAll() {
		// TODO Auto-generated method stub
		while(QueueList.size() > 0) {
			QueueList.remove(0);
		}
	}

    /**
     * Returns <code>true</code> if the queue is empty.
     * 
     * @return <code>true</code> if the queue is empty.
     */
	@Override
	public boolean isEmpty() {

		return QueueList.isEmpty();
	}

    /**
     * Returns an iterator over the elements in the queue, in proper sequence.
     * 
     * @return An iterator over the elements in the queue, in proper sequence.
     */
	@Override
	public Iterator<E> iterator() {

		return QueueList.iterator() ;
	}

    /**
     * Compares two queues for equality.
     * 
     * @param that The queue to be compared to this queue.
     * @return <code>true</code> if the queues are equal.
     */
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

    /**
     * Returns an array containing all of the elements in the queue in proper
     * sequence.
     * 
     * @return An array containing all of the elements in the queue.
     */
	@Override
	public Object[] toArray() {
		
		return QueueList.toArray();
	}

    /**
     * Returns an array containing all of the elements in the queue in proper
     * sequence; the runtime type of the returned array is that of the specified
     * array.
     * 
     * @param holder The array into which the elements of the queue are to be stored.
     * @return An array containing the elements of the queue.
     * @throws NullPointerException If the specified array is <code>null</code>.
     */
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		return QueueList.toArray(holder);
	}
	
    /**
     * Returns <code>true</code> if the queue is at capacity.
     * 
     * @return <code>true</code> if the queue is at capacity.
     */
	@Override
	public boolean isFull() {
	    return QueueList.size() >= MAX_SIZE;
	}

    /**
     * Returns the current size of the queue.
     * 
     * @return The current size of the queue.
     */
	@Override
	public int size() {

		return QueueList.size();
	}

}