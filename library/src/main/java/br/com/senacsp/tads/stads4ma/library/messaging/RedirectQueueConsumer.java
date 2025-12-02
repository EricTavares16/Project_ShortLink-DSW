package br.com.senacsp.tads.stads4ma.library.messaging;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Consumer para a fila de redirecionamentos.
 * Processa mensagens sobre redirecionamentos de links.
 */
@Component
public class RedirectQueueConsumer {

    @JmsListener(
        destination = "shortlink.redirect.queue",
        containerFactory = "queueJmsListenerContainerFactory"
    )
    public void onRedirectMessage(String message) {
        System.out.println("🔗 [REDIRECT QUEUE] " + message);
    }
}

