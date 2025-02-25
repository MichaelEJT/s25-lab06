package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        //mQueue = new ArrayIntQueue();
        mQueue = new LinkedIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        // This is an example unit test
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        // TODO: write your own unit test
        // fail("Test not implemented");
        mQueue.enqueue(1);
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        // TODO: write your own unit test
        // fail("Test not implemented");
        assertNull(mQueue.peek());
    }

    @Test
    public void testPeekNoEmptyQueue() {
        // TODO: write your own unit test
        mQueue.enqueue(1);
        assertEquals(Integer.valueOf(1), mQueue.peek());
    }

    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    @Test
    public void testDequeue() {
        // TODO: write your own unit test
        // fail("Test not implemented");
        // Dequeue on empty queue should return null
        assertNull(mQueue.dequeue());
        // Test FIFO order
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.enqueue(3);
        assertEquals(Integer.valueOf(1), mQueue.dequeue());
        assertEquals(Integer.valueOf(2), mQueue.dequeue());
        assertEquals(Integer.valueOf(3), mQueue.dequeue());
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }

    @Test
    public void testAfterPeek() {
        // peek should not change the queue
        mQueue.enqueue(1);
        assertEquals(Integer.valueOf(1), mQueue.peek());
        assertEquals(Integer.valueOf(1), mQueue.peek());
        assertEquals(1, mQueue.size());
    }

    @Test
    public void testClear() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.enqueue(3);
        assertEquals(3, mQueue.size());
        assertEquals(Integer.valueOf(1), mQueue.peek());
        mQueue.clear();
        assertTrue(mQueue.isEmpty());
        assertNull(mQueue.peek());
        assertEquals(0, mQueue.size());
    }

    @Test
    public void testSize() {
        assertEquals(0, mQueue.size());
        mQueue.enqueue(1);
        assertEquals(1, mQueue.size());
        mQueue.enqueue(2);
        assertEquals(2, mQueue.size());
        mQueue.enqueue(3);
        assertEquals(3, mQueue.size());
        mQueue.dequeue();
        assertEquals(2, mQueue.size());
        mQueue.clear();
        assertEquals(0, mQueue.size());
    }

    @Test
    public void testMultipleEnqueueDequeue() {
        for (int i = 0; i < 1000; i++) {
            mQueue.enqueue(i);
            assertEquals(i + 1, mQueue.size());
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(Integer.valueOf(i), mQueue.dequeue());
        }
        assertTrue(mQueue.isEmpty());
    }

    @Test
public void testEnsureCapacityWithWraparound() {
    // Initial enqueues (0-4)
    mQueue.enqueue(0);
    mQueue.enqueue(1);
    mQueue.enqueue(2);
    mQueue.enqueue(3);
    mQueue.enqueue(4);
    
    // Dequeue first 4 elements
    assertEquals(Integer.valueOf(0), mQueue.dequeue());
    assertEquals(Integer.valueOf(1), mQueue.dequeue());
    assertEquals(Integer.valueOf(2), mQueue.dequeue());
    assertEquals(Integer.valueOf(3), mQueue.dequeue());
    
    // Enqueue more elements (5-19)
    mQueue.enqueue(5);
    mQueue.enqueue(6);
    mQueue.enqueue(7);
    mQueue.enqueue(8);
    mQueue.enqueue(9);
    mQueue.enqueue(10);
    mQueue.enqueue(11);
    mQueue.enqueue(12);
    mQueue.enqueue(13);
    mQueue.enqueue(14);
    mQueue.enqueue(15);
    mQueue.enqueue(16);
    mQueue.enqueue(17);
    mQueue.enqueue(18);
    mQueue.enqueue(19);
    
    // Dequeue remaining elements (4-19)
    assertEquals(Integer.valueOf(4), mQueue.dequeue());
    assertEquals(Integer.valueOf(5), mQueue.dequeue());
    assertEquals(Integer.valueOf(6), mQueue.dequeue());
    assertEquals(Integer.valueOf(7), mQueue.dequeue());
    assertEquals(Integer.valueOf(8), mQueue.dequeue());
    assertEquals(Integer.valueOf(9), mQueue.dequeue());
    assertEquals(Integer.valueOf(10), mQueue.dequeue());
    assertEquals(Integer.valueOf(11), mQueue.dequeue());
    assertEquals(Integer.valueOf(12), mQueue.dequeue());
    assertEquals(Integer.valueOf(13), mQueue.dequeue());
    assertEquals(Integer.valueOf(14), mQueue.dequeue());
    assertEquals(Integer.valueOf(15), mQueue.dequeue());
    assertEquals(Integer.valueOf(16), mQueue.dequeue());
    assertEquals(Integer.valueOf(17), mQueue.dequeue());
    assertEquals(Integer.valueOf(18), mQueue.dequeue());
    assertEquals(Integer.valueOf(19), mQueue.dequeue());
    
    assertTrue(mQueue.isEmpty());
}

}
