import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestArrayDequeGold {
    private ArrayDequeSolution<Integer> expectedArr = new ArrayDequeSolution<>();
    private StudentArrayDeque<Integer> actualArr = new StudentArrayDeque<>();

    private static int testSize = 10;

    @Test
    public void testArrayDeque() {
        // addFirst
        testAddFirst();
        // addLast
        testAddLast();
        // removeFirst
        testRemoveFirst();
        // removeLast
        testRemoveLast();
    }

    private void testAddFirst() {
        // add
        for (int i = 0; i < testSize; ++i) {
            int random = StdRandom.uniform(100);
            expectedArr.addFirst(random);
            actualArr.addFirst(random);
        }

        // verify
        for (int i = 0; i < testSize; ++i) {
            assertEquals("addFirst()", expectedArr.get(i), actualArr.get(i));
        }
    }

    private void testAddLast() {
        // add
        for (int i = 0; i < testSize; ++i) {
            int random = StdRandom.uniform(100);
            expectedArr.addLast(random);
            actualArr.addLast(random);
        }

        // verify
        for (int i = 0; i < testSize; ++i) {
            assertEquals("addLast()", expectedArr.get(i), actualArr.get(i));
        }
    }

    private void testRemoveFirst() {
        List<Integer> expectedRemoveList = new ArrayList<>();
        List<Integer> actualRemoveList = new ArrayList<>();

        for (int i = 0; i < testSize; ++i) {
            expectedRemoveList.add(expectedArr.removeFirst());
            actualRemoveList.add(actualArr.removeFirst());
        }

        assertEquals("removeFirst()", expectedArr.size(), actualArr.size());

        // remain values
        for (int i = 0; i < expectedArr.size(); ++i) {
            assertEquals(expectedArr.get(i), actualArr.get(i));
        }

        for (int i = 0; i < testSize; ++i) {
            assertEquals("removeFirst()", expectedRemoveList.get(i), actualRemoveList.get(i));
        }
    }

    private void testRemoveLast() {
        List<Integer> expectedRemoveList = new ArrayList<>();
        List<Integer> actualRemoveList = new ArrayList<>();

        for (int i = 0; i < testSize; ++i) {
            expectedRemoveList.add(expectedArr.removeLast());
            actualRemoveList.add(actualArr.removeLast());
        }

        assertEquals("removeLast()", expectedArr.size(), actualArr.size());

        // remain values
        for (int i = 0; i < expectedArr.size(); ++i) {
            assertEquals(expectedArr.get(i), actualArr.get(i));
        }

        for (int i = 0; i < testSize; ++i) {
            assertEquals("removeLast()", expectedRemoveList.get(i), actualRemoveList.get(i));
        }
    }
}
