import java.util.ArrayList;

public class Point {
	int x;
	int y;
	int width;
	int height;

	public Point(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	private Point move(Move move) {

		if (move == Move.DOWN && y + 1 < height) {
			return new Point(x, y + 1, width, height);
		}
		if (move == Move.UP && y - 1 >= 0) {
			return new Point(x, y - 1, width, height);
		}
		if (move == Move.LEFT && x - 1 >= 0) {
			return new Point(x - 1, y, width, height);
		}
		if (move == Move.RIGHT && x + 1 < width) {
			return new Point(x + 1, y, width, height);
		}
		if (move == Move.LEFT_UP && x - 1 >= 0 && y - 1 >= 0) {
			return new Point(x - 1, y - 1, width, height);
		}
		if (move == Move.LEFT_DOWN && x - 1 >= 0 && y + 1 < height) {
			return new Point(x - 1, y + 1, width, height);
		}
		if (move == Move.RIGHT_UP && x + 1 < width && y - 1 >= 0) {
			return new Point(x + 1, y - 1, width, height);
		}
		if (move == Move.RIGHT_DOWN && x + 1 < width && y + 1 < height) {
			return new Point(x + 1, y + 1, width, height);
		}

		return null;
	}

	public ArrayList<Point> allNeighbors() {
		ArrayList<Point> neighbors = new ArrayList<Point>();

		for (Move move : Move.values()) {
			Point point = move(move);
			if (point != null) {
				neighbors.add(point);
			}
		}
		return neighbors;
	}

	@Override
	public boolean equals(Object other) {
		return this.x == ((Point) other).x && this.y == ((Point) other).y;
	}

}
