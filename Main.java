import java.util.*;

public class Main {

	public static void main(String[] args) {

		System.out.println("1. Example from assignment. Should print: CAR CAT CARD");

		String[] d = new String[] { "CAR", "CARD", "CART", "CAT" };
		Dictionary dict = new Dictionary(d);
		Character [][] grid = new Character [3][2];
		grid[0][0] = 'A';
		grid[0][1] = 'T';
		grid[1][0] = 'A';
		grid[1][1] = 'C';
		grid[2][0] = 'R';
		grid[2][1] = 'D';

		System.out.print("\tResult: ");
		Set<String> words = findWords(grid, dict);
		for (String w : words) {
			System.out.print(w + " ");
		}

		System.out.println("\n2. Empty grid. Shouldn't find any word");

		grid = new Character[][]{
			{' ', ' '},
			{' ', ' '}, 
			{' ', ' '}
		};
		System.out.print("\tResult: ");
		words = findWords(grid, dict);
		for (String w : words) {
			System.out.print(w + " ");
		}

		System.out.println("\n3. Empty dictionary. Shouldn't find any word");

		grid[0][0] = 'A';
		grid[0][1] = 'T';
		grid[1][0] = 'A';
		grid[1][1] = 'C';
		grid[2][0] = 'R';
		grid[2][1] = 'D';

		dict = new Dictionary(new String[] {});

		System.out.print("\tResult: ");
		words = findWords(grid, dict);
		for (String w : words) {
			System.out.print(w + " ");
		}

		System.out.println("\n4. Empty grid and empty dictionary. Shouldn't find any word");

		grid = new Character[100][100];
		dict = new Dictionary(new String[] {});

		System.out.print("\tResult: ");
		words = findWords(grid, dict);
		for (String w : words) {
			System.out.print(w + " ");
		}

		System.out.println("\n5. Grid without words from dictionary. Shouldn't find any word");

		grid = new Character[100][100];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				grid[i][j] = (i + j % 2 == 0) ? 'A' : 'B';
			}
		}

		dict = new Dictionary(new String[] { "AAAAAAAAAAAAAAAAAAAAAAAAAAAC", "ABABABABABABAAAAAAAAAAAAAAAABBBBAAAAX" });

		System.out.print("\tResult: ");
		words = findWords(grid, dict);
		for (String w : words) {
			System.out.print(w + " ");
		}

		System.out.println(
				"\n5. Big grid with words from dictionary. Should find AAAAAAAAAAAAAAAAAAAAAA BBBBBBBBBBBBBBBBBBBBBBBBBBB ABABABABABABABABAB");

		grid = new Character[100][100];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				grid[i][j] = ((i + j) % 2 == 0) ? 'A' : 'B';
			}
		}

		dict = new Dictionary(
				new String[] { "AAAAAAAAAAAAAAAAAAAAAA", "BBBBBBBBBBBBBBBBBBBBBBBBBBB", "ABABABABABABABABAB" });

		System.out.print("\tResult: ");
		words = findWords(grid, dict);
		for (String w : words) {
			System.out.print(w + " ");
		}
	}

	/**
	 * This method finds words from a given dictionary 
	 * contained in a two dimensional grid
	 * 
	 * @param grid - Two dimensional array containing chars
	 * @param dict - Dictionary with valid words
	 * @return - Set of the valid words from the given grid
	 */
	public static Set<String> findWords(Character [][] grid, Dictionary dict) {
		if (grid == null || dict == null) {
			throw new IllegalArgumentException();
		}

		Set<String> result = new HashSet<String>();
		int height = grid.length;
		int width = grid[0].length;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Point currentPoint = new Point(i, j, width, height);
				result.addAll(recursiveFindWords("", currentPoint, grid, dict));
			}
		}
		return result;
	}

	public static Set<String> recursiveFindWords(String currentWord, Point point, Character [][] grid, Dictionary dict) {
		Set<String> result = new HashSet<String>();

		if (!dict.isPrefix(currentWord)) {
			return result;
		} else {
			char letter = grid[point.y][point.x];
			grid[point.y][point.x] = null;
			currentWord = currentWord + letter;
			
			if (dict.isWord(currentWord)) {
				result.add(currentWord);
				dict.removeWord(currentWord);
			}
			ArrayList<Point> neighbors = point.allNeighbors();
			for (Point neighbor : neighbors) {
				if (grid[neighbor.y][neighbor.x] != null) {
					result.addAll(recursiveFindWords(currentWord, neighbor, grid, dict));
				}
			}
			grid[point.y][point.x] = letter;
		}
		return result;
	}
}
