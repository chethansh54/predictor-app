package project.predictor.app.utils;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DataMapperUtil {

    public String getJsonStringFromMapLevel2(Map<String, Map<String, Integer>> mapItem) {

        StringBuilder jsonStringBuilder = new StringBuilder();
        jsonStringBuilder.append("{");

        for (Map.Entry<String, Map<String, Integer>> entryObj : mapItem.entrySet()) {
            try {
                int avgRecord = entryObj.getValue().getOrDefault("total", 0) / entryObj.getValue().getOrDefault("nRecords", 0);
                jsonStringBuilder.append("'" + entryObj.getKey() + "':");
                jsonStringBuilder.append(avgRecord + ",");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        jsonStringBuilder.append("}");

        return jsonStringBuilder.toString().replace(",}", "}");
    }

}