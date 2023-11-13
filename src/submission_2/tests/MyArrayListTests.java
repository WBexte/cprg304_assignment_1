package submission_2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import submission_2.classes.MyArrayList;
import submission_2.util.ListADT;
import submission_2.util.Iterator;

public class MyArrayListTests {

    private ListADT<Integer> myList;

    @Before
    public void setUp() throws Exception {
        myList = new MyArrayList<>();
    }

    @Test
    public void testSize() {
        assertEquals(0, myList.size());
    }

    @Test
    public void testClear() {
        myList.add(5);
        myList.clear();
        assertEquals(0, myList.size());
    }

    @Test
    public void testAddIntE() {
        myList.add(0, 42);
        assertEquals(1, myList.size());
        assertEquals(Integer.valueOf(42), myList.get(0));
    }

    @Test
    public void testAddE() {
        myList.add(10);
        assertEquals(1, myList.size());
        assertEquals(Integer.valueOf(10), myList.get(0));
    }

    @Test
    public void testAddAll() {
        ListADT<Integer> toAdd = new MyArrayList<>();
        toAdd.add(1);
        toAdd.add(2);
        assertTrue(myList.addAll(toAdd));
        assertEquals(2, myList.size());
        assertEquals(Integer.valueOf(1), myList.get(0));
        assertEquals(Integer.valueOf(2), myList.get(1));
    }

    @Test
    public void testGet() {
        myList.add(5);
        assertEquals(Integer.valueOf(5), myList.get(0));
    }

    @Test
    public void testRemoveInt() {
        myList.add(1);
        myList.add(2);
        myList.add(3);
        assertEquals(Integer.valueOf(2), myList.remove(1));
        assertEquals(2, myList.size());
        assertEquals(Integer.valueOf(3), myList.get(1));
    }

    @Test
    public void testRemoveE() {
        myList.add(1);
        myList.add(2);
        assertTrue(myList.remove(Integer.valueOf(1)) != null); // Check if an element was removed
        assertEquals(1, myList.size());
        assertEquals(Integer.valueOf(2), myList.get(0));
    }

    @Test
    public void testSet() {
        myList.add(100);
        assertEquals(Integer.valueOf(100), myList.set(0, 200));
        assertEquals(1, myList.size());
        assertEquals(Integer.valueOf(200), myList.get(0));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(myList.isEmpty());
        myList.add(10);
        assertFalse(myList.isEmpty());
    }

    @Test
    public void testContains() {
        assertFalse(myList.contains(99));
        myList.add(99);
        assertTrue(myList.contains(99));
    }

    @Test
    public void testToArrayEArray() {
        myList.add(1);
        myList.add(2);
        Integer[] array = new Integer[myList.size()];
        myList.toArray(array);
        assertArrayEquals(new Integer[]{1, 2}, array);
    }

    @Test
    public void testToArray() {
        myList.add(5);
        myList.add(10);
        Object[] array = myList.toArray();
        assertArrayEquals(new Object[]{5, 10}, array);
    }

    @Test
    public void testIterator() {
        myList.add(1);
        myList.add(2);
        myList.add(3);

        Iterator<Integer> iterator = (Iterator<Integer>) myList.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());

        assertFalse(iterator.hasNext());
    }
}
