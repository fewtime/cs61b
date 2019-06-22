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
//        // addFirst
//        testAddFirst();
//        // addLast
//        testAddLast();
//        // removeFirst
//        testRemoveFirst();
//        // removeLast
//        testRemoveLast();

        ArrayDequeSolution<Integer> expectedList = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> actualList = new StudentArrayDeque<>();

        int random = StdRandom.uniform(100);
        expectedList.addFirst(random);
        actualList.addFirst(random);
        assertEquals("addFirst(" + random + ")",
                expectedList.get(0), actualList.get(0));

        random = StdRandom.uniform(100);
        expectedList.addLast(random);
        actualList.addLast(random);
        assertEquals("addLast(" + random + ")",
                expectedList.get(1), actualList.get(1));

        int expected = expectedList.removeFirst();
        int actual = actualList.removeFirst();
        assertEquals("removeFirst()", expected, actual);

        expected = expectedList.removeLast();
        actual = actualList.removeLast();
        assertEquals("removeLast()", expected, actual);
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
