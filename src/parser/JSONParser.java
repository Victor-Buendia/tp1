package parser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import completude.Completude;

public class JSONParser {
    public static Completude parseJSONFile(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            JSONObject json = new JSONObject(fileReader);

            // Parse the fields
            Map<String, Object> fields = new HashMap<>();
            for (String key : json.keySet()) {
                Object value = json.get(key);
                if (value instanceof JSONArray) {
                    JSONArray jsonArray = (JSONArray) value;
                    Object[][] subFields = new Object[jsonArray.length()][];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONArray subArray = jsonArray.getJSONArray(i);
                        Object[] subField = new Object[subArray.length()];
                        for (int j = 0; j < subArray.length(); j++) {
                            subField[j] = subArray.get(j);
                        }
                        subFields[i] = subField;
                    }
                    fields.put(key, new Completude(subFields));
                } else {
                    fields.put(key, value);
                }
            }

            // Create and return the Completude object
            Object[][] campos = new Object[fields.size()][];
            int i = 0;
            for (Map.Entry<String, Object> entry : fields.entrySet()) {
                Object[] campo = new Object[2];
                campo[0] = entry.getKey();
                campo[1] = entry.getValue();
                campos[i++] = campo;
            }
            return new Completude(campos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
