package _API.api_utils;

import _UI.step_definition.ScenarioContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import pojo.Member;


public class APIutils {
    ScenarioContext context;

    public APIutils(ScenarioContext testContext){
        context = testContext;
    }

    public static String printPrettyJSONObject(String jsonString){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(jsonString);
        return gson.toJson(jsonElement);
    }

    public static String pojoToJsonString(String pojoName){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString;

        switch (pojoName.toLowerCase()){
            case "member": Member member = new Member();
                jsonString = gson.toJson(new Member());
            break;
            default:
                jsonString = null;
                System.out.println("Pojo class name is wrong or doesn't exist");
        }
        return jsonString;
    }
}
