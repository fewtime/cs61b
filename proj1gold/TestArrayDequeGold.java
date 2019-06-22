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
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        String message = "\n";

        while (true) {
            int rand = StdRandom.uniform(10000);
            if (rand < 2500) {
                sad.addFirst(rand);
                ads.addFirst(rand);
                message = message + "addFirst(" + rand + ")\n";
            } else if (rand < 5000){
                sad.addLast(rand);
                ads.addLast(rand);
                message = message + "addLast(" + rand + ")\n";
            } else if ((rand < 7500) && (!sad.isEmpty())) {
                Integer actual = sad.removeFirst();
                Integer expected = ads.removeFirst();
                message = message + "removeFirst()\n";
                assertEquals(message, expected, actual);
            } else if (!sad.isEmpty()){
                Integer actual = sad.removeLast();
                Integer expected = ads.removeLast();
                message = message + "removeLast()\n";
                assertEquals(message, expected, actual);
            }
        }
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
