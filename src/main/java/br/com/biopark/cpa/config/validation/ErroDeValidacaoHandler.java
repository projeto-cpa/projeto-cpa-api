package br.com.biopark.cpa.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource; //Classe que auxilia na tradução de idiomas
    
    //Função que é chamada para tratameto de exceptions
    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //Devolve uma BAD REQUEST
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDTO> handle(MethodArgumentNotValidException exception) {

        List<ErroDTO> dto = new ArrayList<>();
        List<FieldError> fielderros =  exception.getBindingResult().getFieldErrors();

        fielderros.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroDTO erro = new ErroDTO(e.getField(), mensagem);
            dto.add(erro);
        });

        return dto;

    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidacaoException.class)
    public ErroDTO handleValidcaoException(ValidacaoException exception) {
        return new ErroDTO(exception.getMessage(), exception.getStatus());
    }
}