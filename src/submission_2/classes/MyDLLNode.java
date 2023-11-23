package submission_2.classes;

/**
 * The <code>MyDLLNode</code> class represents a node in a doubly-linked list
 * used by the <code>MyDLL</code> class.
 * 
 * @param <E> The type of element held by this node.
 */
public class MyDLLNode<E> {

    // Fields
    /**
     * The data item stored in the node.
     */
    public E item;

    /**
     * Reference to the next node in the doubly-linked list.
     */
    public MyDLLNode<E> next;

    /**
     * Reference to the previous node in the doubly-linked list.
     */
    public MyDLLNode<E> prev;

    // Constructor
    /**
     * Constructs a new node with the specified data item.
     * 
     * @param item The data item to be stored in the node.
     */
    public MyDLLNode(E item) {
        // Initialize the node
        this.item = item;
    }
}

