package project.app.datainjector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.app.datainjector.utils.DataMapperUtil;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataPreProcessorService {

    @Autowired
    public DataMapperUtil dataMapperUtil;

    public String preProcessSensorData(List<Map<String, String>> msgList) {
        Long tsEpoch = 0L;
        int readingDataValue = 0;

        Map<String, Map<String, Integer>> dayWiseDataValues = new HashMap<>();
        Map<String, Integer> avgDayWiseDate = new HashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Map<String, String> msgMap : msgList) {
            readingDataValue = Integer.parseInt(msgMap.get("reading_mgdl"));
            tsEpoch = Long.parseLong(msgMap.get("received_ts"));

            Date tsDate = new Date(tsEpoch * 1000);
            String tsDateString = sdf.format(tsDate);

            Map<String, Integer> defaultDataMap = new HashMap<>();
            defaultDataMap.put("total", 0);
            defaultDataMap.put("nRecords", 0);

            dayWiseDataValues.put(tsDateString, dayWiseDataValues.getOrDefault(tsDateString, defaultDataMap));
            Integer totalValue = dayWiseDataValues.getOrDefault(tsDateString, defaultDataMap).getOrDefault("total", 0) + readingDataValue;
            Integer nRecordsValue = dayWiseDataValues.getOrDefault(tsDateString, defaultDataMap).getOrDefault("nRecords", 0) + 1;
            dayWiseDataValues.get(tsDateString).put("total", totalValue);
            dayWiseDataValues.get(tsDateString).put("nRecords", nRecordsValue);

        }

        // get average day wise data
        return dataMapperUtil.getJsonStringFromMapLevel2(dayWiseDataValues);
    }
}
