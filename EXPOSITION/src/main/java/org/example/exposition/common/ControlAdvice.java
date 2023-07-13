package org.example.exposition.common;

import org.example.domaine.exceptions.ForbiddenAccessException;
import org.example.domaine.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControlAdvice extends ResponseEntityExceptionHandler {

    public ResponseEntity<ErrorModel> handleForbiddenAccessException (ForbiddenAccessException ex){
        ErrorModel error = new ErrorModel("403", ex.getLocalizedMessage(), "Accès refusé, utilisateur non autorisé");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<ErrorModel> handleUnauthorizedException (UnauthorizedException ex){
        ErrorModel error = new ErrorModel("401", ex.getLocalizedMessage(), "Accès refusé, utilisateur non authentifié");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

}
