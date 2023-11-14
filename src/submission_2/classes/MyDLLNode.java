package submission_2.classes;

public class MyDLLNode<E> {

	// Fields
		public E item;
		public MyDLLNode<E> next;
		public MyDLLNode<E> prev;

		// Constructor
		public MyDLLNode(E item) {
			// Initialize the node
			this.item = item;
		}

}
