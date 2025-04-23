package com.portfolio.Shrysth.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api")
public class VisitController {

    private static final String DATA_FILE = "visit-data.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/track-visit")
    public String trackVisit(HttpServletRequest request) throws IOException {
        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        Map<String, Object> visit = new HashMap<>();
        visit.put("timestamp", LocalDateTime.now().toString());
        visit.put("ip", ip);
        visit.put("userAgent", userAgent);

        List<Map<String, Object>> visits = loadVisits();
        visits.add(visit);
        objectMapper.writeValue(new File(DATA_FILE), visits);

        return "Visit tracked";
    }

    @GetMapping("/stats")
    public List<Map<String, Object>> getStats() throws IOException {
        return loadVisits();
    }

    private List<Map<String, Object>> loadVisits() throws IOException {
        File file = new File(DATA_FILE);
        if (!file.exists()) return new ArrayList<>();
        return objectMapper.readValue(file, new TypeReference<List<Map<String, Object>>>(){});
    }
}
