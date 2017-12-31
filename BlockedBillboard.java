import java.util.*;
import java.io.*;
public class BlockedBillboard {
	
	public static boolean submit = true;
	public static String fileName = "billboard";

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		if (submit) {
			s = new Scanner(new File(fileName + ".in"));
			pw = new PrintWriter(new FileWriter(fileName + ".out"));
		}
		BlockedBillboard bb = new BlockedBillboard();
		bb.run(s, pw);
		pw.close();
	}

	public void run(Scanner s, PrintWriter pw) {
		Rectangle board1 = new Rectangle(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt());
		Rectangle board2 = new Rectangle(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt());
		Rectangle truck = new Rectangle(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt());

		int sum = board1.getArea() + board2.getArea() - board1.getOverlap(truck) - board2.getOverlap(truck);
		pw.print(sum);
	}

}

class Rectangle {
	
	int top, bottom, left, right;
	
	public Rectangle(int x1, int y1, int x2, int y2) {
		top = y2;
		bottom = y1;
		left = x1;
		right = x2;
	}

	public int getOverlap(Rectangle rect) {
		int left = Math.max(this.left, rect.left);
        int right = Math.min(this.right, rect.right);
        int bottom = Math.max(this.bottom, rect.bottom);
        int top = Math.min(this.top, rect.top);
        if (left < right && bottom < top) {
            return (right - left) * (top - bottom);
        }
        return 0;
	}

	public int getArea() {
		return (right - left) * (top - bottom); 
	}
}