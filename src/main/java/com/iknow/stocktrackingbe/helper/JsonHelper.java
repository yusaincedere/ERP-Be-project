package com.iknow.stocktrackingbe.helper;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JsonHelper {

    private final ObjectMapper objectMapper;

    public ObjectNode messageJson(String message) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("message", message);

        return objectNode;
    }

    public ObjectNode stringJson(String data) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("data", data);

        return objectNode;
    }

    public JsonNode objectJson(Object object) throws JsonMappingException, JsonProcessingException {
        return objectMapper.readTree(objectMapper.writeValueAsString(object));
    }
}
