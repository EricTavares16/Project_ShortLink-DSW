package br.com.senacsp.tads.stads4ma.library.messaging;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Consumer A para o tópico de notificações do sistema.
 * Um dos dois consumers que recebem notificações do ShortLink.
 */
@Component
public class NotificationTopicConsumerA {

    @JmsListener(
        destination = "shortlink.notification.topic",
        containerFactory = "topicJmsListenerContainerFactory"
    )
    public void onNotificationMessage(String message) {
        System.out.println("📢 [NOTIFICATION TOPIC - Consumer A] " + message);
    }
}

