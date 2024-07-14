package net.atmacacode.backend.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import net.atmacacode.backend.core.config.GiderTakibiProperties;
import net.atmacacode.backend.service.abstracts.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailImpl implements EmailService {

    JavaMailSenderImpl mailSender;

    @Autowired
    GiderTakibiProperties giderTakibiProperties;

    @Autowired
    MessageSource messageSource;

    @Override
    public void initialize() {
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost(giderTakibiProperties.getEmail().host());
        mailSender.setPort(giderTakibiProperties.getEmail().port());
        mailSender.setUsername(giderTakibiProperties.getEmail().username());
        mailSender.setPassword(giderTakibiProperties.getEmail().password());
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.ssl.enable", "true");
    }

    String activationEmail = """
                <html>
                    <body>
                        <h1>${title}</h1>
                        ${clickText} <a href="${url}">${clickHere}</a>
                    </body>
                </html>
            """;

    @Override
    public void sendActivationEmail(String email, String activationToken) {
        var acctivationUrl = giderTakibiProperties.getClient().host() + "/activation?" + activationToken;
        var title = messageSource.getMessage("gidertakibi.mail.user.created.title", null, LocaleContextHolder.getLocale());
        var clickHere = messageSource.getMessage("gidertakibi.mail.click.here", null, LocaleContextHolder.getLocale());
        var clickText = messageSource.getMessage("gidertakibi.mail.click.text", null, LocaleContextHolder.getLocale());

        var mailBody = activationEmail.replace("${url}", acctivationUrl).replace("${title}", title).replace("${clickHere}", clickHere).replace("${clickText}", clickText);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");

        try{
            message.setFrom(giderTakibiProperties.getEmail().from());
            message.setTo(email);
            message.setSubject(title);
            message.setText(mailBody, true);
        } catch (MessagingException e){
            e.printStackTrace();
        }

        this.mailSender.send(mimeMessage);
    }
}
