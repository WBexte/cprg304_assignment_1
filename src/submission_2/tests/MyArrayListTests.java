package submission_2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import submission_2.classes.MyArrayList;
import submission_2.util.ListADT;
import submission_2.util.Iterator;

public class MyArrayListTests {

    private ListADT<Integer> myArrayList;

    @Before
    public void setUp() {
    	myArrayList = new MyArrayList<>();
    }

    @Test
    public void testSize() {
        assertEquals(0, myArrayList.size());

        myArrayList.add(42);
        assertEquals(1, myArrayList.size());
    }

    @Test
    public void testClear() {
    	myArrayList.add(42);
    	myArrayList.clear();
        assertEquals(0, myArrayList.size());
    }

    @Test
    public void testAddAtIndex() {
    	myArrayList.add(0, 42);
        assertEquals(1, myArrayList.size());
        assertEquals(Integer.valueOf(42), myArrayList.get(0));

        myArrayList.add(0, 11);
        assertEquals(2, myArrayList.size());
        assertEquals(Integer.valueOf(11), myArrayList.get(0));
        assertEquals(Integer.valueOf(42), myArrayList.get(1));
    }

    //Required?
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexWithInvalidIndex() {
    	myArrayList.add(1, 42);
    }

    @Test
    public void testAdd() {
    	myArrayList.add(42);
        assertEquals(1, myArrayList.size());
        assertEquals(Integer.valueOf(42), myArrayList.get(0));
    }

    @Test
    public void testAddAll() {
        ListADT<Integer> toAdd = new MyArrayList<>();
        toAdd.add(1);
        toAdd.add(2);

        myArrayList.addAll(toAdd);

        assertEquals(2, myArrayList.size());
        assertEquals(Integer.valueOf(1), myArrayList.get(0));
        assertEquals(Integer.valueOf(2), myArrayList.get(1));
    }

    @Test
    public void testGet() {
    	myArrayList.add(42);
        assertEquals(Integer.valueOf(42), myArrayList.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetWithInvalidIndex() {
    	myArrayList.get(0);
    }

    @Test
    public void testRemoveAtIndex() {
    	myArrayList.add(42);
        Integer removed = myArrayList.remove(0);

        assertEquals(Integer.valueOf(42), removed);
        assertEquals(0, myArrayList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexWithInvalidIndex() {
    	myArrayList.remove(0);
    }

    @Test
    public void testRemoveByElement() {
    	myArrayList.add(42);
    	myArrayList.add(11);

        Integer removed = myArrayList.remove(Integer.valueOf(42));

        assertEquals(Integer.valueOf(42), removed);
        assertEquals(1, myArrayList.size());
        assertEquals(Integer.valueOf(11), myArrayList.get(0));
    }

    @Test
    public void testSet() {
    	myArrayList.add(42);
        Integer previous = myArrayList.set(0, 11);

        assertEquals(Integer.valueOf(42), previous);
        assertEquals(Integer.valueOf(11), myArrayList.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetWithInvalidIndex() {
    	myArrayList.set(0, 11);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(myArrayList.isEmpty());

        myArrayList.add(42);
        assertFalse(myArrayList.isEmpty());
    }

    @Test
    public void testContains() {
        assertFalse(myArrayList.contains(42));

        myArrayList.add(42);
        assertTrue(myArrayList.contains(42));
    }

    @Test
    public void testToArray() {
    	myArrayList.add(1);
    	myArrayList.add(2);

        Integer[] array = new Integer[2];
        array = myArrayList.toArray(array);

        assertArrayEquals(new Integer[]{1, 2}, array);
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayWithNullArray() {
    	myArrayList.toArray(null);
    }

    @Test
    public void testMyArrayListIterator() {
    	myArrayList.add(1);
    	myArrayList.add(2);

        ListADT<Integer> listFromIterator = new MyArrayList<>();
        Iterator<Integer> iterator = myArrayList.iterator();

        while (iterator.hasNext()) {
            listFromIterator.add(iterator.next());
        }

        assertEquals(myArrayList.size(), listFromIterator.size());
        assertEquals(myArrayList.get(0), listFromIterator.get(0));
        assertEquals(myArrayList.get(1), listFromIterator.get(1));
    }
    

    @Test
    public void testIteratorHasNextEmptyList() {
        MyArrayList<Integer> list = new MyArrayList<>();
        MyArrayList<Integer>.MyArrayListIterator iterator = list.new MyArrayListIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorHasNextNonEmptyList() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        MyArrayList<Integer>.MyArrayListIterator iterator = list.new MyArrayListIterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testIteratorNext() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        MyArrayList<Integer>.MyArrayListIterator iterator = list.new MyArrayListIterator();
        assertEquals(Integer.valueOf(1), iterator.next());
    }

    /*
    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextEmptyList() {
        MyArrayList<Integer> list = new MyArrayList<>();
        MyArrayList<Integer>.MyArrayListIterator iterator = list.new MyArrayListIterator();
        iterator.next(); // Should throw NoSuchElementException
    }
    */

    @Test
    public void testIteratorMultipleNext() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        MyArrayList<Integer>.MyArrayListIterator iterator = list.new MyArrayListIterator();
        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(2), iterator.next());
    }

    /*
    @Test(expected = NoSuchElementException.class)
    public void testIteratorPastEnd() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        MyArrayList<Integer>.MyArrayListIterator iterator = list.new MyArrayListIterator();
        iterator.next(); // First call
        iterator.next(); // Second call should throw NoSuchElementException
    }
    */
    
}