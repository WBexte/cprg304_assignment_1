package submission_2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import submission_2.classes.MyDLL;
import submission_2.util.Iterator;

public class MyDLLTests {

	private MyDLL<Integer> list;

	@Before
	public void setUp() throws Exception {
		// Initialize the list before each test
		list = new MyDLL<Integer>();
	}

	// Tests for all DLL functions:
	@Test
	public void testSize() {
		assertEquals(0, list.size());
		list.add(1);
		assertEquals(1, list.size());
		list.add(2);
		assertEquals(2, list.size());
	}

	@Test
	public void testClear() {
		list.add(1);
		list.add(2);
		assertNotNull(list.head);
		assertNotNull(list.tail);
		list.clear();
		assertNull(list.head);
		assertNull(list.tail);
	}

	@Test
	public void testAddAtIndex() {
		list.add(1);
		list.add(2);
		list.add(1, 3);
		assertEquals(3, list.size());
		assertEquals(1, (int) list.get(0));
		assertEquals(3, (int) list.get(1));
		assertEquals(2, (int) list.get(2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddAtIndexOutOfBounds() {
		list.add(1, 3);
	}

	@Test
	public void testAdd() {
		assertTrue(list.add(1));
		assertEquals(1, list.size());
		assertEquals(1, (int) list.get(0));
	}

	@Test
	public void testAddAll() {
		MyDLL<Integer> list2 = new MyDLL<Integer>();
		list2.add(1);
		list2.add(2);
		list.addAll(list2);
		assertEquals(2, list.size());
		assertEquals(1, (int) list.get(0));
		assertEquals(2, (int) list.get(1));
	}

	@Test
	public void testGet() {
		list.add(1);
		list.add(2);
		assertEquals(1, (int) list.get(0));
		assertEquals(2, (int) list.get(1));
	}

	@Test
	public void testRemoveAtIndex() {
	    list.add(1);
	    list.add(2);
	    list.add(3);
	    assertEquals(3, list.size());
	    int expected = list.remove(0);
	    assertEquals(1, expected);
	    assertEquals(2, list.size());
	    expected = list.remove(1);
	    assertEquals(3, expected);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveAtIndexOutOfBounds() {
	    list.remove(0);
	}

	@Test
	public void testRemoveByValue() {
	    list.add(1);
	    list.add(2);
	    list.add(3);
	    assertEquals(3, list.size());
	    assertEquals(1, (int) list.remove((Integer) 1));
	    assertEquals(2, list.size());
	    assertEquals(3, (int) list.remove((Integer) 3));
	    assertEquals(1, list.size());
	}

	@Test
	public void testRemoveByValueNotFound() {
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(3, list.size());
		assertNull(list.remove((Integer) 4));
		assertEquals(3, list.size());
	}

	@Test
	public void testSet() {
	    list.add(1);
	    list.add(2);
	    list.add(3);
	    assertEquals(3, list.size());
	    int expected = list.set(0, 4);
	    assertEquals(1, expected);
	    assertEquals(4, (int) list.get(0));
	    assertEquals(3, list.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetOutOfBounds() {
		list.set(0, 1);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(list.isEmpty());
		list.add(1);
		assertFalse(list.isEmpty());
	}

	@Test
	public void testContains() {
		list.add(1);
		list.add(2);
		assertTrue(list.contains(1));
		assertTrue(list.contains(2));
		assertFalse(list.contains(3));
	}


	@Test
	public void testToArrayWithInput() {
		list.add(1);
		list.add(2);
		Integer[] array = new Integer[2];
		array = list.toArray(array);
		Integer[] expected = { 1, 2 };
		assertEquals(2, array.length);
		assertEquals(1, (int) array[0]);
		assertEquals(2, (int) array[1]);
		assertArrayEquals(expected, array);
	}

	@Test
	public void testToArray() {
		list.add(1);
		list.add(2);
		Object[] array = list.toArray();
		Object[] expected = { 1, 2 };
		assertEquals(2, array.length);
		assertEquals(1, array[0]);
		assertEquals(2, array[1]);
		assertArrayEquals(expected, array);
	}

	@Test
	public void testToArrayEmptyList() {
		Object[] array = list.toArray();
		Object[] expected = {};
		assertEquals(0, array.length);
		assertArrayEquals(expected, array);
	}

	@Test
	public void testIterator() {
		list.add(1);
		list.add(2);

		Iterator<Integer> iterator = list.iterator();

		assertTrue(iterator.hasNext());
		assertEquals(1, (int) iterator.next());

		assertTrue(iterator.hasNext());
		assertEquals(2, (int) iterator.next());

		assertFalse(iterator.hasNext());
	}

}
