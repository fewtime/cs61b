public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int res = x - y;
        res = res < 0 ? -res : res;
        return (res == 1);
    }
}
