import java.util.*;

public class Main {

	public static void main(String[] args) {
		String[] d = new String[]{"CAR", "CARD", "CART", "CAT"};
		System.out.println(d.length);
		Dictionary dict = new Dictionary(d); 
		char [][]grid = new char[3][2]; 
		grid[0][0] = 'A';
		grid[0][1] = 'T';
		grid[1][0] = 'A';
		grid[1][1] = 'C';
		grid[2][0] = 'R';
		grid[2][1] = 'D';

		Set<String> words = findWords(grid, dict);
		
		for(String w: words) {
			System.out.println(w);
		}
		System.out.println("Done");
	}
	
	public static Set<String> findWords(char [][] grid, Dictionary dict) {
		Set<String> result = new HashSet<String>(); 
		int height = grid.length; 
		int width = grid[0].length; 
		
		for(int i=0; i < width; i++){
			for(int j=0; j<height; j++) {
				Point currentPoint = new Point(i, j, width, height); 
				ArrayList<Point> visitedPoints = new ArrayList<Point>();
				visitedPoints.add(currentPoint);
				result.addAll(recursiveFindWords(""+grid[j][i], visitedPoints, currentPoint, grid, dict)); 
			}
		}
		
		return result; 
	}
	
	public static Set<String> recursiveFindWords(String currentWord, ArrayList<Point> visitedPoints, Point point, char [][] grid, Dictionary dict) {
		Set<String> result = new HashSet<String>(); 
		
		if(!dict.isPrefix(currentWord)) {
			System.out.println(currentWord);
			return result; 
		} else {
			if(dict.isWord(currentWord)) {
				result.add(currentWord); 
			}
			ArrayList<Point> neighbors = point.validNeighbors(visitedPoints); 
			for(Point neighbor: neighbors) {
				ArrayList<Point> newVisitedPoints = (ArrayList<Point>) visitedPoints.clone(); 
				newVisitedPoints.add(neighbor); 
				System.out.println(currentWord + grid[neighbor.x][neighbor.y]);
				result.addAll(recursiveFindWords(currentWord + grid[point.x][point.y], newVisitedPoints, neighbor, grid, dict));
			}
		}
		return result; 	
	}			
}
