public class OffByN implements CharacterComparator {

    private int gaps;

    public OffByN(int gaps) {
        this.gaps = gaps;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int res = x - y;
        res = res < 0 ? -res : res;
        return (res == gaps);
    }
}
