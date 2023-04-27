package br.com.biopark.cpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")

public class contaController {
    


        @PostMapping("/upload")
        public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
            try {
                // salva o arquivo no sistema de arquivos ou em um banco de dados
                // ...
                return new ResponseEntity<>("Arquivo salvo com sucesso", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Erro ao salvar o arquivo", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

