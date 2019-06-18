public class ArrayDequeTest {
    public static void main(String[] args) {
        addRemoveIsEmptyAndGetTest();
        deepCopyTest();
    }

    public static void addRemoveIsEmptyAndGetTest() {
        ArrayDeque<String> arr = new ArrayDeque<String>(1);

        System.out.println(arr.isEmpty());
        System.out.println(arr.size());
        System.out.println();

        arr.addFirst("apple");
        arr.addLast("banana");
        arr.addFirst("cake");
        arr.addLast("duck");

        arr.printDeque();

        System.out.println(arr.removeFirst());
        System.out.println(arr.removeLast());
        System.out.println();

        System.out.println(arr.isEmpty());
        System.out.println(arr.size());
        System.out.println();

        for (int i = 0; i < arr.size(); ++i) {
            System.out.println(arr.get(i));
        }
        System.out.println();
    }

    public static void deepCopyTest() {
        ArrayDeque<String> arr = new ArrayDeque<String>();

        arr.addFirst("apple");
        arr.addLast("banana");

        ArrayDeque<String> target = new ArrayDeque<String>(arr);

        target.addFirst("cake");
        target.addLast("duck");

        target.printDeque();
    }
}
