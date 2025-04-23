package com.portfolio.Shrysth.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class VisitService {

    private static final String DATA_FILE = "visit-data.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void trackVisit(String ip, String userAgent) throws IOException {
        Map<String, Object> visit = new HashMap<>();
        visit.put("timestamp", LocalDateTime.now().toString());
        visit.put("ip", ip);
        visit.put("userAgent", userAgent);

        List<Map<String, Object>> visits = loadVisits();
        visits.add(visit);
        objectMapper.writeValue(new File(DATA_FILE), visits);
    }

    public List<Map<String, Object>> getAllVisits() throws IOException {
        return loadVisits();
    }

    private List<Map<String, Object>> loadVisits() throws IOException {
        File file = new File(DATA_FILE);
        if (!file.exists()) return new ArrayList<>();
        return objectMapper.readValue(file, new TypeReference<List<Map<String, Object>>>(){});
    }
}
