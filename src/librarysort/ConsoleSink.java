package librarysort;

import librarysort.models.ISink;

public class ConsoleSink implements ISink {
	
	private static final Character ReplaceChar = '\r';
	
	private final Boolean enabled;
	
	public ConsoleSink(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public void ReplaceLine(String content) {
		if (enabled) {
			System.out.printf("%s%c", content, ReplaceChar);
		}
	}
	
	@Override
	public void PrintLine(String content) {
		if (enabled) {
			System.out.println(content);
		}
	}

	@Override
	public void PrintLine() {
		if (enabled) {
			System.out.println();
		}
	}
	
}
