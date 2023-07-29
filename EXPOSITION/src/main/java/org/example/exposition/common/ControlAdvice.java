package org.example.exposition.common;

import org.example.domaine.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControlAdvice extends ResponseEntityExceptionHandler {

    /* ne pas mettre ces exceptions ici car ce ne sont pas des exceptions métier, elles sont générées par Spring


    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorModel> handleForbiddenAccessException (ForbiddenAccessException ex){
        ErrorModel error = new ErrorModel("403", ex.getLocalizedMessage(), "Accès refusé, utilisateur non autorisé");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }


     */
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorModel> handleUnauthorizedException (UnauthorizedException ex){
        ErrorModel error = new ErrorModel("401", ex.getLocalizedMessage(), "Accès refusé, utilisateur non authentifié");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorModel> handleInputMissingException (InputMissingException ex) {
        ErrorModel error = new ErrorModel("400", ex.getLocalizedMessage(), "Les input obligatoires ne sont pas renseignées");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorModel> handleUsernameNotFoundException (UsernameNotFoundException ex) {
        ErrorModel error = new ErrorModel("404", ex.getLocalizedMessage(), "utilisateur non connu");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorModel> handleResourceNotFoundException(final ResourceNotFoundException ex){
        ErrorModel model = new ErrorModel("404", ex.getLocalizedMessage(), "Resource Not Found");
        return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorModel> handleResourceAlreadyExistsException(final ResourceAlreadyExistsException ex){
        ErrorModel model = new ErrorModel("409", ex.getLocalizedMessage(), "Resource already exists");
        return new ResponseEntity<>(model, HttpStatus.CONFLICT);
    }
}
