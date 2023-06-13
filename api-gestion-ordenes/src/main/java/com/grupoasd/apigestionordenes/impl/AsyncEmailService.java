package com.grupoasd.apigestionordenes.impl;

import com.grupoasd.apigestionordenes.util.EnvioCorreo;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncEmailService {
    
    private static final Logger log = LoggerFactory.getLogger(AsyncEmailService.class);
    
    @Autowired
    private EnvioCorreo envioCorreo;

    /**
     * remitente.
     */
    @Value("${spring.mail.properties.mail.smtp.to}")
    private String remitente;
    /**
     * clave clave.
     */
    @Value("${spring.mail.properties.mail.smtp.pass}")
    private String clave;
    /**
     * servidor servidor.
     */
    @Value("${spring.mail.properties.mail.smtp.server}")
    private String servidor;
    /**
     * puerto puerto.
     */
    @Value("${spring.mail.properties.mail.smtp.port}")
    private String puerto;

    
    @Async("asyncExecutor")
    public CompletableFuture<String> enviarCorreoService(List<String> destinatarios, String mensaje) {
        try {
            destinatarios.forEach((dest) -> {
                try {
                    envioCorreo.generarCorreo(dest, servidor,
                            remitente, clave, puerto, mensaje);
                } catch (Exception ex) {
                    log.error("Error del servicio " + ex.getMessage());
                }
            });
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error del servicio " + ex.getMessage());
        }
        return CompletableFuture.completedFuture("Finalizo con éxito");
    }
}
