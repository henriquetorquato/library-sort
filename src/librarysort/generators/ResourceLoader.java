package librarysort.generators;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ResourceLoader {

	private static Map<String, List<String>> resources = new HashMap<String, List<String>>();
	
	public static List<String> Get(String name) throws Exception {
		try
		{
			var resource = resources.get(name);
			
			if (resource == null) {
				resource = Load(name);
				resources.put(name, resource);
			}
			
			return resource;
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	
	public static List<String> Load(String name) throws Exception {
		try
		{		
			var path = GetPath(name);
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
				throw new Exception(String.format("The resource '%s' is empty", name));
			}
			
			return resource;
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	
	private static String GetPath(String name) {
		var currentPath = System.getProperty("user.dir");
		return String.format("%s\\bin\\%s", currentPath, name);
	}
	
}
