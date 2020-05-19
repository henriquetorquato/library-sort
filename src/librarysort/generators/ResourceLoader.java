package librarysort.generators;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public final class ResourceLoader {

	public static List<String> Load(String path) throws Exception {
		try
		{			
			var file = new File(path);
			var stream = new FileInputStream(file);
			
			ArrayList<String> resource = new ArrayList<String>();
			var line = new StringBuilder();
			
			int c;
			while ((c = stream.read()) != -1) {
				if (c == '\n') {
					resource.add(line.toString());
					line = new StringBuilder();
				} else if (c != '\r') {
					line.append((char)c);
				}
			}
			
			resource.add(line.toString());
			stream.close();
			
			if (resource.size() == 0) {
				throw new Exception(String.format("The resource '%s' is empty", path));
			}
			
			return resource;
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	
}
