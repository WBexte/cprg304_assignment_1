/**
 * 
 */
package submission_1;

/**
 * A generic interface representing a Stack data structure.
 * @param <E> the type of elements stored in the Stack.
 */
public interface StackADT<E> {
    /**
     * Pushes an element onto the top of the Stack.
     *
     * @param element the element to be pushed onto the Stack.
     * @throws NullPointerException if the specified element is null.
     */
    void push(E element);

    /**
     * Removes and returns the element at the top of the Stack.
     *
     * @return the element at the top of the Stack.
     * @throws EmptyStackException if the Stack is empty.
     */
    E pop();

    /**
     * Returns the element at the top of the Stack without removing it.
     *
     * @return the element at the top of the Stack.
     * @throws EmptyStackException if the Stack is empty.
     */
    E peek();

    /**
     * Checks if the Stack is empty.
     *
     * @return true if the Stack is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the Stack.
     *
     * @return the number of elements in the Stack.
     */
    int size();
}
