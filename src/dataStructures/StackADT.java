/**
 * 
 */
package dataStructures;

/**
 * 
 */
public interface StackADT<E> {
	
	public void push(E item);
	
	public E pop();
	
	public E peek();
	
	public boolean equals( StackADT<E> that );
	
	public Iterator<E> iterator();
	
	public Object[] toArray();
	
	public int search(E obj);
	
	public int size();
	
	public boolean isEmpty();
	
	public void clear();
	
}
