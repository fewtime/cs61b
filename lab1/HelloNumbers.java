public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
	int ans = 0;
	while (x < 10) {
	    ans += x;
	    System.out.print(ans + " ");
	    x = x + 1;
	}
    }
}
