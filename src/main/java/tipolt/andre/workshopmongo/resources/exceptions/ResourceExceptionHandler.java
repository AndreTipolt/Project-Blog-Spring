package tipolt.andre.workshopmongo.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import tipolt.andre.workshopmongo.services.exception.ObjectNotFoundException;


@ControllerAdvice // Responsavel por tratar erros nas requisições
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest httpRequest){
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(),httpStatus.value(), 
				"Not Found", e.getMessage(), httpRequest.getRequestURI());
		
		
		return ResponseEntity.status(httpStatus).body(err);
	}
}
