package com.fatec.lab.atividade02.controller;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.UnexpectedTypeException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fatec.lab.atividade02.exception.ApiErro;

import javassist.tools.rmi.ObjectNotFoundException;


@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ApiErro> objectNotFound(ObjectNotFoundException e,HttpServletRequest request){
		ApiErro error = new ApiErro(HttpStatus.NOT_FOUND.value(), e.getMessage().replace(" is not exported", "."));		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErro> dataIntegrity(DataIntegrityViolationException e,HttpServletRequest request){
		ApiErro error = new ApiErro(HttpStatus.BAD_REQUEST.value(), e.getMessage().replace(" is not exported", "."));		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErro> typeMistach(MethodArgumentTypeMismatchException e,HttpServletRequest request){
		ApiErro error = new ApiErro(HttpStatus.BAD_REQUEST.value(), e.getMessage().replace(" is not exported", "."));		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<ApiErro> typeException(UnexpectedTypeException e,HttpServletRequest request){
		ApiErro error = new ApiErro(HttpStatus.BAD_REQUEST.value(), e.getMessage().replace(" is not exported", "."));		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErro> noElement(NoSuchElementException e,HttpServletRequest request){
		ApiErro error = new ApiErro(HttpStatus.BAD_REQUEST.value(), e.getMessage().replace(" is not exported", "."));		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	
	
	
}
