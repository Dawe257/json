import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String json = readString("new_data.json");

        System.out.println(jsonToList(json));
    }

    private static String readString(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder result = new StringBuilder();
            while (reader.ready()) {
                result.append(reader.readLine());
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Employee> jsonToList(String json) {
        List<Employee> result = new ArrayList<>();
        JsonArray array = JsonParser.parseString(json).getAsJsonArray();
        for (JsonElement element : array) {
            Gson gson = new GsonBuilder().create();
            Employee employee = gson.fromJson(element, Employee.class);
            result.add(employee);
        }
        return result;
    }
}
