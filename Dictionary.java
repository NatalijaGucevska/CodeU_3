import java.util.ArrayList;

public class Dictionary {
	private ArrayList<String> dict;

	public Dictionary(String[] dict) {
		this.dict = new ArrayList<>();
		for (int i = 0; i < dict.length; i++) {
			this.dict.add(dict[i]);
		}
	}

	public boolean isWord(String word) {
		return dict.contains(word);
	}

	public boolean isPrefix(String prefix) {
		for (String word : dict) {
			if (word.startsWith(prefix)) {
				return true;
			}
		}
		return false;
	}
}
