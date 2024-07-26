package com.example.findingFlatmates.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> map = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			map.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ApiResponse> missingServletRequestParameterException(MissingServletRequestParameterException ex){
		String message = "";
		if(ex.getParameterName().equals("tenenttype")) {
			 message = "Please fill Male/Female details";
		}else if(ex.getParameterName().equals("roomType")) {
			 message = "Please fill room type(1BHK/2BHK/3BHk/4BHK) details";
		}else {
			 message = "Please fill " + ex.getParameterName() + " details";
		}
		ApiResponse response = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
}
