package br.com.biopark.cpa.config.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class ValidacaoException extends Exception{

    private String message;
    private HttpStatus status;

}

