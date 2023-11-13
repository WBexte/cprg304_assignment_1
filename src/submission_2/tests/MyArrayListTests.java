package submission_2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import submission_2.classes.MyArrayList;
import submission_2.util.ListADT;
import submission_2.util.Iterator;


public class MyArrayListTests {
    
    private ListADT<String> myList;

    @Before
    public void setUp() {
        // Initialize the list before each test
        myList = new MyArrayList<>();
    }

    @Test
    public void testSize() {
        assertEquals(0, myList.size());
        myList.add("Element 1");
        assertEquals(1, myList.size());
        myList.add("Element 2");
        assertEquals(2, myList.size());
    }

    @Test
    public void testClear() {
        myList.add("Element 1");
        myList.add("Element 2");
        myList.clear();
        assertEquals(0, myList.size());
        assertTrue(myList.isEmpty());
    }

    @Test
    public void testAddAtIndex() {
        myList.add("Element 1");
        myList.add("Element 2");
        myList.add(1, "New Element");
        assertEquals("Element 1", myList.get(0));
        assertEquals("New Element", myList.get(1));
        assertEquals("Element 2", myList.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexOutOfBounds() {
        myList.add(1, "New Element");
    }

    @Test
    public void testAdd() {
        assertTrue(myList.add("Element 1"));
        assertEquals(1, myList.size());
        assertEquals("Element 1", myList.get(0));
    }

    @Test
    public void testAddAll() {
        ListADT<String> toAdd = new MyArrayList<>();
        toAdd.add("Element 1");
        toAdd.add("Element 2");

        assertTrue(myList.addAll(toAdd));
        assertEquals(2, myList.size());
        assertEquals("Element 1", myList.get(0));
        assertEquals("Element 2", myList.get(1));
    }

    @Test
    public void testGet() {
        myList.add("Element 1");
        myList.add("Element 2");
        assertEquals("Element 1", myList.get(0));
        assertEquals("Element 2", myList.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBounds() {
        myList.get(0);
    }

    @Test
    public void testRemoveAtIndex() {
        myList.add("Element 1");
        myList.add("Element 2");

        assertEquals("Element 1", myList.remove(0));
        assertEquals(1, myList.size());
        assertEquals("Element 2", myList.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexOutOfBounds() {
        myList.remove(0);
    }

    @Test
    public void testRemoveByValue() {
        myList.add("Element 1");
        myList.add("Element 2");

        String removed = myList.remove("Element 1");
        assertNotNull(removed);
        assertEquals("Element 1", removed);
        assertEquals(1, myList.size());
        assertEquals("Element 2", myList.get(0));
    }

    @Test
    public void testRemoveByValueNotFound() {
        myList.add("Element 1");
        myList.add("Element 2");

        assertNull(myList.remove("Not Found"));
        assertEquals(2, myList.size());
    }

    @Test
    public void testSet() {
        myList.add("Element 1");
        myList.add("Element 2");

        assertEquals("Element 1", myList.set(0, "New Element"));
        assertEquals("New Element", myList.get(0));
        assertEquals("Element 2", myList.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBounds() {
        myList.set(0, "New Element");
    }

    @Test
    public void testIsEmpty() {
        assertTrue(myList.isEmpty());

        myList.add("Element 1");
        assertFalse(myList.isEmpty());
    }

    @Test
    public void testContains() {
        myList.add("Element 1");
        myList.add("Element 2");

        assertTrue(myList.contains("Element 1"));
        assertFalse(myList.contains("Not Found"));
    }

    @Test
    public void testToArray() {
        myList.add("Element 1");
        myList.add("Element 2");

        String[] array = myList.toArray(new String[0]);
        assertArrayEquals(new String[]{"Element 1", "Element 2"}, array);
    }

    @Test
    public void testToArrayEmptyList() {
        String[] array = myList.toArray(new String[0]);
        assertArrayEquals(new String[]{}, array);
    }

    @Test
    public void testIterator() {
        myList.add("Element 1");
        myList.add("Element 2");

        Iterator<String> iterator = myList.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("Element 1", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("Element 2", iterator.next());

        assertFalse(iterator.hasNext());
    }
}