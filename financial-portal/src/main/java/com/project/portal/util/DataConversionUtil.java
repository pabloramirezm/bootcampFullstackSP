package com.project.portal.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataConversionUtil {

    private final ObjectMapper objectMapper;

    @Autowired
    public DataConversionUtil(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> String dtoToJson(final T dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
        System.out.println("error");
            return null;
        }
    }
}
