package parser;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONArray;


public class JSONParser {
	private static String[] orInclusivo = {"nationality", "birthCountry", "birthCity", "birthState"};
	private static String[] orExclusivo = {"identifier.lattes", "identifier.orcid"};
	private static String[] atomicos = {"title", "publicationDate", "language"};
	
	public static String classifyFields(String key) {
		for (String element : orInclusivo) {
		    if (element.equals(key)) {
		        return "inclusivo";
		    }
		}
		for (String element : orExclusivo) {
		    if (element.equals(key)) {
		        return "exclusivo";
		    }
		}
		for (String element : atomicos) {
		    if (element.equals(key)) {
		        return "atomico";
		    }
		}
		return null;
	}
	
	public static String jsonToString(String filePath) {
		try (FileReader fileReader = new FileReader(filePath)) {
			StringBuilder contentBuilder = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				String line;
				while ((line = br.readLine()) != null) {
				    contentBuilder.append(line).append("\n");
				}
			}
            return contentBuilder.toString();
		} catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	public static Object[] parseJSONFile(String filePath) {
		String jsonString = jsonToString(filePath);
		JSONArray jsonArray = new JSONArray(jsonString);
		
        Object[] testCases = new Object[jsonArray.length()][];
        
        for (int i = 0; i < jsonArray.length(); i++) {
        	testCases[i] = (Object[]) parseJSONObject(jsonArray.getJSONObject(i));
        }
        
        return (Object[][]) testCases;
	}
	
	public static Object[][] parseJSONArray(JSONArray jsonArray) {
		Object[][] campos = new Object[jsonArray.length()][];
            
        for (int i = 0; i < jsonArray.length(); i++) {
        	Object[] campo = new Object[3];
        	campo[0] = Integer.toString(i);
        	campo[1] = (Object[][]) parseJSONObject(jsonArray.getJSONObject(i));
        	campo[2] = classifyFields((String) campo[0]);
            campos[i] = campo;
        }
		
		return campos;
	}
    
	public static Object[][] parseJSONObject(JSONObject json) {
        Map<String, Object> fields = new HashMap<>();
        
        for (String key : json.keySet()) {
            Object value = json.get(key);

        	if(value instanceof JSONObject) {
        		fields.put(key, parseJSONObject((JSONObject) value));
        	}
        	else if(value instanceof JSONArray && ((JSONArray) value).length() > 0) {
        		if(((JSONArray) value).get(0) instanceof JSONObject) {
        			fields.put(key, parseJSONArray((JSONArray) value));
        			continue;
        		}
        	}
        	else {
        		fields.put(key, value);
        	}
        	
        }
        
        Object[][] campos = new Object[fields.size()][];
        int j = 0;
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            Object[] campo = new Object[3];
            campo[0] = entry.getKey();
            campo[1] = entry.getValue();
            campo[2] = classifyFields((String) campo[0]);
            campos[j++] = campo;
        }
        return campos;
    }
}
