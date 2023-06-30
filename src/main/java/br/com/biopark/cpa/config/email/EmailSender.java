package br.com.biopark.cpa.config.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class EmailSender {

    @Value("${application.client.url}")
    private String clientURL;

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void enviarCodigoRecuperacao(String toEmail, String codigoRecuperacao) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        // toEmail = "luka.pc.pc@gmail.com";

        helper.setFrom("joaocletto@gmail.com");
        helper.setTo(toEmail);

        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/main/resources/email.html"));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        String content = new String(contentBuilder.toString().getBytes(), StandardCharsets.UTF_8);

        String emailTemplate = content;
        // TODO: Colocar a variavel de ambiente para o endereço do cliente (front end)

        String linkRecuperacao = clientURL.concat("/recuperacao?codigo=").concat(codigoRecuperacao);
        LocalDate LT = LocalDate.now();
        String anoAtual = String.valueOf(LT.getYear());

        emailTemplate = emailTemplate.replace("${ano_atual}", anoAtual);
        emailTemplate = emailTemplate.replace("${link_recuperacao}", linkRecuperacao);

        helper.setText(emailTemplate, true);
        message.setSubject("CPA: Recuperação de acesso");
        javaMailSender.send(message);

        System.out.println("Email enviado com sucesso para: " + toEmail);
    }

}
