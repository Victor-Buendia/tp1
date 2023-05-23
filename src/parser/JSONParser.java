package parser;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import completude.Completude;

public class JSONParser {
    public static Completude parseJSONFile(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            StringBuilder contentBuilder = new StringBuilder();

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    contentBuilder.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONObject json = new JSONObject(contentBuilder.toString());
            System.out.println(json.toString(4));

            // Parse the fields
            Map<String, Object> fields = new HashMap<>();
            for (String key : json.keySet()) {
                Object value = json.get(key);
                System.out.println(key);
                if (value instanceof JSONArray) {
                	System.out.println("sou json array");
                    JSONArray jsonArray = (JSONArray) value;
                    System.out.println(jsonArray);
                    Object[][] subFields = new Object[jsonArray.length()][];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Object subValue = jsonArray.get(i);
                        if (subValue instanceof JSONArray) {
                            JSONArray subArray = (JSONArray) subValue;
                            Object[] subField = new Object[subArray.length()];
                            for (int j = 0; j < subArray.length(); j++) {
                                subField[j] = subArray.get(j);
                            }
                            subFields[i] = subField;
                        } else {
                            // Handle the case where the element is not an array
                            // You can choose to ignore it, throw an exception, or handle it differently
                        }
                    }
                    System.out.println(subFields);
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
