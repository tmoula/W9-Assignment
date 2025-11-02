package com.example.w9_assignment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ConfigFibController { // ← No @RequestMapping("/") here!

    private final ObjectMapper mapper = new ObjectMapper();

    // --- /config ---
    @GetMapping("/config")
    public Map<String, String> getConfig() throws JsonProcessingException {
        Map<String, String> env = System.getenv();
        Map<String, String> result = new TreeMap<>(env);
        String json = mapper.writeValueAsString(result);
        System.out.println(json);
        return result;
    }

    // --- /fib ---
    @RequestMapping(value = "/fib", method = RequestMethod.GET)
    public String generateFibonacci(@RequestParam("length") int length) throws JsonProcessingException {
        if (length < 0) length = 0;
        List<Long> fib = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (i == 0) fib.add(0L);
            else if (i == 1) fib.add(1L);
            else fib.add(fib.get(i - 1) + fib.get(i - 2));
        }
        String json = mapper.writeValueAsString(fib);
        System.out.println(json);
        return json;
    }
}
