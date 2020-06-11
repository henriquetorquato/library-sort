package librarysort;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

public class TimedResult<T> {
	// Execution time in milliseconds
	private long time;
	
	// Resource result
	private T resource;
	
	public TimedResult(long time, T resource) {
		this.time = time;
		this.resource = resource;
	}
	
	public long getTime() {
		return this.time;
	}
	
	public T getResource() {
		return this.resource;
	}
	
	public static <T> TimedResult<T> measure(Callable<T> callable) {		
		try {
			T result = null;
			
			var start = Instant.now();
			result = callable.call();
			var end = Instant.now();
			
			return new TimedResult<T>(
					Duration.between(start, end).toMillis(), result);
		} catch (Exception ex) {
			return null;
		}
	}
}
