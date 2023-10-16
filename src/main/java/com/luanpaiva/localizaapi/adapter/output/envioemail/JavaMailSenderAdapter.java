package com.luanpaiva.localizaapi.adapter.output.envioemail;

import com.luanpaiva.localizaapi.adapter.config.properties.EmailProperties;
import com.luanpaiva.localizaapi.domain.model.DadosEmail;
import com.luanpaiva.localizaapi.domain.model.Reserva;
import com.luanpaiva.localizaapi.domain.port.EnvioEmailPort;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JavaMailSenderAdapter implements EnvioEmailPort {

    private static final String EMAIL_CONFIRMACAO_RESERVA_HTML = "email-confirmacao-reserva.html";

    private final EmailProperties emailProperties;
    private final JavaMailSender javaMailSender;
    private final Configuration freeMarkerConfig;

    @Override
    public void enviarEmail(DadosEmail dadosEmail, Reserva reserva) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(dadosEmail.destinatario());
            helper.setSubject(dadosEmail.assunto());

            Template template = freeMarkerConfig.getTemplate(EMAIL_CONFIRMACAO_RESERVA_HTML);
            String corpoEmail = FreeMarkerTemplateUtils.processTemplateIntoString(template, reserva);
            helper.setText(corpoEmail, true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException | TemplateException | IOException e) {
            log.error("Erro ao enviar email. Error: {}", e.getCause());
        }
    }
}
