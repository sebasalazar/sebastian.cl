package cl.sebastian.servicio.impl;

import cl.sebastian.servicio.ServicioEmail;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sebastián Salazar Molina <ssalazar@orangepeople.cl>
 */
@Service("servicioEmail")
public class ServicioEmailImpl implements ServicioEmail, Serializable {

    @Resource(name = "mailSender")
    private MailSender mailSender = null;
    private JavaMailSender sender = null;
    private static final Logger logger = LoggerFactory.getLogger(ServicioEmailImpl.class);

    @PostConstruct
    public void init() {
        try {
            this.sender = (JavaMailSender) mailSender;
            if (sender == null) {
                logger.error("No se puede cargar el servicio de Email");
            }
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug("Error al crear servicio de envio de correo", e);
        }
    }

    @Override
    public boolean sendMail(final String to, final String from, final String subject, final String message) {
        boolean result = true;
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mm) throws Exception {

                    logger.debug("Preparando envio de correo electronico a " + to);
                    MimeMessageHelper helper = new MimeMessageHelper(mm, "UTF-8");
                    helper.setTo(to);
                    helper.setFrom(from);
                    helper.setSubject(subject);
                    helper.setText(message, true);
                    logger.debug("Mensaje de correo: " + message);
                }
            };

            sender.send(preparator);
            logger.debug("Corrreo electronico enviado!");
        } catch (Exception exception) {
            logger.error(exception.toString());
            logger.debug("Error al enviar correo electronico", exception);
            result = false;
        }

        return result;
    }

    @Override
    public boolean sendMail(final String to, final String from, final String subject, final String message, final String path) {
        boolean result = true;
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mm) throws Exception {

                    logger.debug("Preparando envio de correo electronico a " + to);
                    MimeMessageHelper helper = new MimeMessageHelper(mm, true, "UTF-8");
                    helper.setTo(to);
                    helper.setFrom(from);
                    helper.setSubject(subject);

                    FileSystemResource file = new FileSystemResource(path);
                    if (file.exists()) {
                        helper.addAttachment(file.getFilename(), file.getFile());
                    }

                    helper.setText(message, true);
                    logger.debug("Mensaje de correo: " + message);
                }
            };

            sender.send(preparator);
            logger.debug("Corrreo electronico enviado!");
        } catch (Exception exception) {
            logger.error(exception.toString());
            logger.debug("Error al enviar correo electronico", exception);
            result = false;
        }

        return result;
    }

    @Override
    public boolean sendMail(final String to, final String from, final String subject, final String message, final byte[] file, final String fileName, final String mimeType) {
        boolean result = true;
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mm) throws Exception {

                    logger.debug("Preparando envio de correo electronico a " + to);
                    MimeMessageHelper helper = new MimeMessageHelper(mm, true, "UTF-8");
                    helper.setTo(to);
                    helper.setFrom(from);
                    helper.setSubject(subject);

                    ByteArrayResource finalFile = new ByteArrayResource(file);
                    if (fileName != null && finalFile.exists()) {
                        helper.addAttachment(fileName, finalFile, mimeType);
                    }

                    helper.setText(message, true);
                    logger.debug("Mensaje de correo: " + message);
                }
            };

            sender.send(preparator);
            logger.debug("Corrreo electronico enviado!");
        } catch (Exception exception) {
            logger.error(exception.toString());
            logger.debug("Error al enviar correo electronico", exception);
            result = false;
        }
        return result;
    }
}
