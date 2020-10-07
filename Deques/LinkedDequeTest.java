package deques;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Performs some basic linked deque tests. */
public class LinkedDequeTest {

    /** Adds a few strings to the deque, checking isEmpty() and size() are correct. */
    @Test
    public void addIsEmptySizeTest() {
        LinkedDeque<String> lld = new LinkedDeque<>();
        assertTrue(lld.isEmpty());

        lld.addFirst("front");
        assertEquals(1, lld.size());
        assertFalse(lld.isEmpty());

        lld.addLast("middle");
        assertEquals(2, lld.size());

        lld.addLast("back");
        assertEquals(3, lld.size());
    }

    /**
     * Adds an item, then removes an item, and ensures that the deque is empty afterwards.
     */
    @Test
    public void addRemoveTest() {
        LinkedDeque<Integer> lld = new LinkedDeque<>();
        assertTrue(lld.isEmpty());

        lld.addFirst(10);
        assertFalse(lld.isEmpty());

        lld.removeFirst();
        assertTrue(lld.isEmpty());
    }

    @Test
    public void testaddFirst() {
        LinkedDeque<Integer> lld = new LinkedDeque<>();
        lld.addLast(1);
        lld.addLast(22);
        lld.addLast(34);
        lld.addFirst(1);
        lld.addFirst(2);
        lld.addFirst(3);

        assertTrue(lld.get(2) == 1);
        assertTrue(lld.get(1) == 2);
        assertTrue(lld.get(0) == 3);
        assertTrue(lld.get(3) == 1);
        lld.removeLast();
        lld.removeLast();
        lld.removeLast();
        lld.addFirst(3);
        lld.addLast(2);
        lld.addLast(333333);
     }

}
