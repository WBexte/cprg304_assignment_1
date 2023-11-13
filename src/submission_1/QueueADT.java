/**
 * 
 */
package submission_1;

import submission_2.util.Iterator;

/**
 * 
 */
public interface QueueADT<E> {
	
	/**
	 * Creates an instance of the QueueADT class.
	 */
	public void create();

	
	/**
	 * Adds an integer item to the back of the queue.
	 *
	 * @param item the integer item to be added to the queue
	 */
	public void enqueue(E Element);
	

	/**
	 * Removes an integer item from the queue.
	 *
	 * @param item the integer item to be removed from the queue
	 */
	public void dequeue();

	
	/**
	 * Returns an iterator over the elements in the queue.
	 *
	 * @return an iterator for iterating over the elements in the queue
	 */
	public Iterator<E> iterator();

	
	/**
	 * Retrieves, but does not remove, the element at the front of the queue.
	 */
	public void peek();
	

	/**
	 * Checks if two elements are equal.
	 *
	 * @param e1 the first element
	 * @param e2 the second element
	 * @return true if the elements are equal, false otherwise
	 */
	public boolean equals(E Element1, E Elemnet2);

	
	/**
	 * Converts the elements in the queue to an array of Objects.
	 *
	 * @return an array of Object containing the elements in the queue
	 */
	public Object[] toArray(Object E);
	

	/**
	 * Converts the elements in the queue to an array.
	 */
	public void ElementtoArray(E Element);
	

	/**
	 * Checks if an queue is empty.
	 *
	 * @param e the queue to be checked for emptiness
	 * @return true if the queue is empty, false otherwise
	 */
	public boolean isEmpty();

	
	/**
	 * Checks if the queue is full.
	 *
	 * @return true if the queue is full, false otherwise
	 */
	public boolean isFull();

	/**
	 * Retrieves the number of elements in the queue.
	 *
	 * @return the number of elements in the queue
	 */
	public int size();

	/**
	 * Removes all elements from the queue.
	 */
	public void DequeueAll();

}
