package _API.api_utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonUtils {
    public static String readPropertyFromJsonFile(String key, String fileName){
        String value = null;
        JSONParser parser = new JSONParser();
        try{
            Object object = parser.parse(new FileReader(fileName));
            JSONObject jsonObject = (JSONObject)object;
            value = (String)jsonObject.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

    public static JSONArray readArrayFromJsonFile(String key, String fileName){
        JSONParser parser = new JSONParser();
        JSONArray array = new JSONArray();
        try{
            Object object = parser.parse(new FileReader(fileName));
            JSONObject jsonObject = (JSONObject)object;
            array = (JSONArray)jsonObject.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return array;
    }

    public static JSONObject readBodyFromJsonFile(String fileName){
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try{
            Object object = parser.parse(new FileReader(fileName));
            jsonObject = (JSONObject)object;

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String readStringBodyFromJsonFile(String fileName){
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try{
            Object object = parser.parse(new FileReader(fileName));
            jsonObject = (JSONObject)object;

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
