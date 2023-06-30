package br.com.biopark.cpa.controller.form;

import lombok.Data;

@Data
public class EmailForm {

    private String toEmail;
    private String subject;
    private String body;
}
