package com.coding.moviereviewservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class MapperUtil {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T mapToModel(Map<String, Object> json, Class<T> object) {
        log.debug("Inside Mapper Util to map int class: " + object);
        try {
            return objectMapper.convertValue(json, objectMapper.getTypeFactory().constructType(object));
        } catch (Exception exception) {
            throw new CustomException().serviceException("Mapper Util Broke!!");
        }
    }
}
