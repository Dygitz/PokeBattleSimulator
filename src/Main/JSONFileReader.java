package Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONFileReader {
	
	public static ArrayList<String> readJSON(String type, String sub) {
		//Type -> Ex: Water, Fire, Ice, etc.
		//Sub -> weaknesses, immunes, strengths
		try {
			ArrayList<String> temp = new ArrayList<String>();
			Reader reader = Files.newBufferedReader(Paths.get("src\\Main\\PokeTypes.json"));
			JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();
			JsonObject typeJson = parser.get(type).getAsJsonObject();
			JsonArray subJson = typeJson.get(sub).getAsJsonArray();
			for(JsonElement element : subJson)
				temp.add(element.getAsString());
			return temp;
		}
		catch(FileNotFoundException e) { e.printStackTrace(); }
		catch(IOException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
		return null;
	}
}
