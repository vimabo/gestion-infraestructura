/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.apigestionordenes.util;

import org.springframework.stereotype.Service;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author vbocanegra
 */
@Service
@PropertySource("classpath:application.properties")
public class EnvioCorreo {

    private static final Logger log = LoggerFactory.getLogger(EnvioCorreo.class);

    /**
     * ex ex.
     */
    private MessagingException ex;

    /**
     * Exepcion ocurrida en el momento de enviar un correo.
     *
     * @return nula si no hubo excepcion
     */
    public MessagingException getEx() {
        return ex;
    }

    /**
     * Metodo principal para enviar correos.
     *
     * @param destinatario
     * @param servidor
     * @param remitente
     * @param clave
     * @param puerto
     * @param cuerpo
     * @throws Exception
     */
    public void generarCorreo(String destinatario, String servidor, String remitente,
            String clave, String puerto, String cuerpo)
            throws Exception {

        String mailServerName = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", mailServerName);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        /*
		 * Get the Session object passing userid and password
         */
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        session.setDebug(true);

        try {
            // Create a MimeMessage object.
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(remitente));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mimeMessage.setSubject("CAMBIO DE ESTADO EN SU ORDEN DE SERVICIO!");
            mimeMessage.setText(cuerpo);
            Transport.send(mimeMessage);
            log.info("Sent message successfully....");

        } catch (MessagingException e) {
            log.error(e.getMessage());
        }
    }
}
