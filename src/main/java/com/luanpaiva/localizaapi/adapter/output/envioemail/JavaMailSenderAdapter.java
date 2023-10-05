package com.luanpaiva.localizaapi.adapter.output.envioemail;

import com.luanpaiva.localizaapi.domain.model.DadosEmail;
import com.luanpaiva.localizaapi.domain.model.Reserva;
import com.luanpaiva.localizaapi.domain.port.EnvioEmailPort;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.StringWriter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JavaMailSenderAdapter implements EnvioEmailPort {

    @Value("${spring.mail.username}")
    private String emailFrom;

    private final JavaMailSender javaMailSender;
    private final VelocityEngine velocityEngine;

    @Override
    public void enviarEmail(DadosEmail dadosEmail, Reserva reserva) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(emailFrom);
            helper.setTo(dadosEmail.destinatario());
            helper.setSubject(dadosEmail.assunto());

            StringWriter writer = new StringWriter();
            VelocityContext context = new VelocityContext();
            context.put("reserva", reserva);

            Template template = velocityEngine.getTemplate("src/main/resources/templates/email-reserva.vm");
            template.merge(context, writer);

            String htmlContent = writer.toString();
            helper.setText(htmlContent, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error("Erro ao enviar email. Error: {}", e.getCause());
        }
    }
}
