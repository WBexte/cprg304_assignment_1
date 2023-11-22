package submission_2.tests;


import submission_2.classes.MyQueue;
import submission_2.exceptions.EmptyQueueException;
import submission_2.util.Iterator;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyQueueTests {
	
	 	private MyQueue<String> myQueue;
	    private MyQueue<String> fixedQueue;

	    @Before
	    public void setUp() {
	        myQueue = new MyQueue<>();
	        fixedQueue = new MyQueue<>(); 
	    }

	    @After
	    public void tearDown() {
	        myQueue = null;
	        fixedQueue = null;
	    }
	    
	    @Test
	    public void testIsEmpty() {
	        assertTrue("Queue should be empty after initialization", myQueue.isEmpty());
	        assertEquals("Size of an empty queue should be 0", 0, myQueue.size());
	    }

	    @Test
	    public void testEnqueueAndSize() {
	        myQueue.enqueue("a");
	        myQueue.enqueue("b");
	        myQueue.enqueue("c");
	        assertFalse("Queue should not be empty after enqueuing elements", myQueue.isEmpty());
	        assertEquals("Queue size should be equal to the number of enqueued elements", 3, myQueue.size());
	    }

	    @Test
	    public void testDequeue() throws EmptyQueueException {
	        myQueue.enqueue("a");
	        myQueue.enqueue("b");
	        myQueue.enqueue("c");
	        assertEquals("Dequeue should return 'a'", "a", myQueue.dequeue());
	        assertEquals("Dequeue should return 'b'", "b", myQueue.dequeue());
	        assertEquals("Dequeue should return 'c'", "c", myQueue.dequeue());
	    }

	    @Test(expected = EmptyQueueException.class)
	    public void testDequeueOnEmptyQueue() throws EmptyQueueException {
	        myQueue.dequeue();
	    }

	    @Test
	    public void testPeek() throws EmptyQueueException {
	        myQueue.enqueue("a");
	        assertEquals("Peek should return 'a'", "a", myQueue.peek());
	    }

	    @Test(expected = EmptyQueueException.class)
	    public void testPeekOnEmptyQueue() throws EmptyQueueException {
	        myQueue.peek();
	    }

	    @Test
	    public void testClearQueue() {
	        myQueue.enqueue("a");
	        myQueue.enqueue("b");
	        myQueue.dequeueAll();
	        assertTrue("Queue should be empty after calling dequeueAll", myQueue.isEmpty());
	    }

	    @Test
	    public void testIsFull() {
	        MyQueue<String> fixedSizeQueue = new MyQueue<>();
	        fixedSizeQueue.enqueue("a");
	        fixedSizeQueue.enqueue("b");
	        fixedSizeQueue.enqueue("c");
	        assertTrue("Queue should be full when it reaches its max size", fixedSizeQueue.isFull());
	    }

	    @Test
	    public void testEquals() {
	        MyQueue<String> otherQueue = new MyQueue<>();
	        myQueue.enqueue("a");
	        otherQueue.enqueue("a");
	        assertTrue("Queues with the same elements should be considered equal", myQueue.equals(otherQueue));
	    }

	    @Test
	    public void testToArrayWithoutParameter() {
	        myQueue.enqueue("a");
	        myQueue.enqueue("b");
	        myQueue.enqueue("c");

	        Object[] array = myQueue.toArray();
	        assertEquals("Array length should match queue size", 3, array.length);
	        assertEquals("Array element 0 should match queue element 'a'", "a", array[0]);
	        assertEquals("Array element 1 should match queue element 'b'", "b", array[1]);
	        assertEquals("Array element 2 should match queue element 'c'", "c", array[2]);
	    }

	    @Test
	    public void testToArrayWithParameter() {
	        myQueue.enqueue("a");
	        myQueue.enqueue("b");
	        myQueue.enqueue("c");

	        String[] array = new String[3];
	        array = myQueue.toArray(array);
	        assertEquals("Array length should match queue size", 3, array.length);
	        assertEquals("Array element 0 should match queue element 'a'", "a", array[0]);
	        assertEquals("Array element 1 should match queue element 'b'", "b", array[1]);
	        assertEquals("Array element 2 should match queue element 'c'", "c", array[2]);
	    }
}