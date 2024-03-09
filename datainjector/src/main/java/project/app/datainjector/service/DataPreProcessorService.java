package project.app.datainjector.service;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataPreProcessorService {

    public void preProcessSensorData(List<Map<String, String>> msgList) {
        Long tsEpoch = 0L;
        int readingDataValue = 0;

        Map<String, Map<String, Integer>> dayWiseDataValues = new HashMap<>();
        Map<String, Integer> avgDayWiseDate = new HashMap<>();

        Map<String, Integer> defaultDataMap = new HashMap<>();

        defaultDataMap.put("total", 0);
        defaultDataMap.put("nRecords", 0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        for (Map<String, String> msgMap : msgList) {
            tsEpoch = Long.parseLong(msgMap.get("received_ts"));
            readingDataValue = Integer.parseInt(msgMap.get("reading_mgdl"));

            Date tsDate = new Date(tsEpoch);
            String tsDateString = sdf.format(tsDate);

            dayWiseDataValues.put(tsDateString, dayWiseDataValues.getOrDefault(tsDateString, defaultDataMap));
            Integer totalValue = dayWiseDataValues.getOrDefault(tsDateString, defaultDataMap).getOrDefault("total", 0) + readingDataValue;
            Integer nRecordsValue = dayWiseDataValues.getOrDefault(tsDateString, defaultDataMap).getOrDefault("nRecords", 0) + 1;
            dayWiseDataValues.get(tsDateString).put("total", totalValue);
            dayWiseDataValues.get(tsDateString).put("nRecords", nRecordsValue);

        }

        // get average daywise data

        for (Map.Entry<String, Map<String, Integer>> entryObj : dayWiseDataValues.entrySet()) {
            try {
                int avgRecord = entryObj.getValue().getOrDefault("total", 0) / entryObj.getValue().getOrDefault("nRecords", 0);
                avgDayWiseDate.put(entryObj.getKey(), avgRecord);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
