package br.com.biopark.cpa.config.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmail (String toEmail, String subject) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setFrom("joaocletto@gmail.com");
        helper.setTo(toEmail);
        helper.setText("<html lang=\"en\">\n" +
                "\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "        <title>Document</title>\n" +
                "    </head>\n" +
                "\n" +
                "    <body style=\"\n" +
                "    font-family: Arial, sans-serif, 'Open Sans';\n" +
                "    margin:0\n" +
                "    \">\n" +
                "        <div style=\"\n" +
                "        text-align:center;\n" +
                "        \">\n" +
                "            <div>\n" +
                "                <div style=\"\n" +
                "                width:320px;\n" +
                "                display:inline-block;\n" +
                "                margin:auto auto 20px auto;\n" +
                "                \">\n" +
                "                    <img src=\"https://cpa-survey.web.app/_nuxt/img/logo.ecad1e2.png\" height=\"120\" />\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <hr style=\"border-color:#eee;  \">\n" +
                "            <div>\n" +
                "                <div style=\"\n" +
                "                padding:20px 10%;\n" +
                "                box-sizing:border-box;\n" +
                "                min-width:320px;\n" +
                "                max-width:100%;\n" +
                "                display:inline-block;\n" +
                "                margin:auto auto 0px auto;\n" +
                "                \">\n" +
                "                    <h1 style=\"\n" +
                "                    margin:0;\n" +
                "                    text-align:left;\n" +
                "                    color:#2c3845\n" +
                "                    \">Recuperação acesso</h1>\n" +
                "                    <p style=\"\n" +
                "                    text-align:justify\n" +
                "                    \">Recebemos o seu pedido de recuperação de senha. Aqui está o código de recuperação da sua conta,\n" +
                "                        acesse o link para recuperar o acesso a sua conta.</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <hr style=\"\n" +
                "            border-color:#eee;\n" +
                "            margin:auto auto 20 auto\n" +
                "            \">\n" +
                "            <div>\n" +
                "                <div style=\"\n" +
                "                padding:20px;\n" +
                "                box-sizing:border-box;\n" +
                "                width:320px;\n" +
                "                display:inline-block;\n" +
                "                border:1px solid #eee;\n" +
                "                margin:auto;\n" +
                "                background:#eee;\n" +
                "                margin:auto auto 20 auto\n" +
                "                \">\n" +
                "                    <a href=\"#\" style=\"\n" +
                "                    color:#bc2345;\n" +
                "                    font-size:18px;\n" +
                "                    font-weight:800;\n" +
                "                    text-decoration:none;\n" +
                "                    \">\n" +
                "                        <span>RECUPERAR SENHA</span>\n" +
                "                    </a>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div style=\"\n" +
                "            background:#2c3845;\n" +
                "            color:#fff;\n" +
                "            padding:20px;\n" +
                "            font-size:14px\n" +
                "            \">     \n" +
                "                <p style=\"margin:0\">Todos os direitos reservados.</p>\n" +
                "                <p style=\"margin:0\">Biopark &copy; 2023.</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "\n" +
                "</html>", true);
        message.setSubject(subject);

        javaMailSender.send(message);

        System.out.println("Email enviado com sucesso para: " + toEmail);
    }

}
