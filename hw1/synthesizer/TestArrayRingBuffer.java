package synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<>(3);

        assertEquals(3, arb.capacity());

        assertTrue(arb.isEmpty());
        assertEquals(0, arb.fillCount());

        arb.enqueue("apple");
        arb.enqueue("banana");

        assertEquals(2, arb.fillCount());

        assertFalse(arb.isFull());

        assertEquals("apple", arb.dequeue());

        arb.enqueue("cake");
        arb.enqueue("duck");

        Iterator<String> seer = arb.iterator();
        assertTrue(seer.hasNext());
        assertEquals("banana", seer.next());
        assertEquals("cake", seer.next());
        assertEquals("duck", seer.next());
        assertFalse(seer.hasNext());

        assertEquals("banana", arb.peek());

        assertEquals(3, arb.fillCount());
        assertTrue(arb.isFull());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
