package com.cs157a.studentmanagement.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseHelper {

   /**
    * @param parameters  The Json object params
    * @return            A response entity in which you choose the status code
    *                    , or a 500 internal server error
    */
   public static ResponseEntity<String> createJsonResponse(Map<String, Object> parameters) {
      String jsonResponse;

      try {
         ObjectMapper objectMapper = new ObjectMapper();

         // Convert map to JSON
         jsonResponse = objectMapper.writeValueAsString(parameters);
         return ResponseEntity.ok(jsonResponse);
      }
      // If JSON conversion fails
      catch (JsonProcessingException e) {
         e.printStackTrace();
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body("An unexpected error occurred.");
      }
   }
}
