package main.java;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

class JsonReader {

    public static Data readDataFromJsonFile(String filePath) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File jsonFile = new File(filePath);
            return mapper.readValue(jsonFile, Data.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
