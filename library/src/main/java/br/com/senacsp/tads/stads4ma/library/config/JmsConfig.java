package br.com.senacsp.tads.stads4ma.library.config;

import jakarta.jms.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 * Configuração JMS para filas e tópicos.
 * - Queue para redirecionamentos (point-to-point)
 * - Topic para notificações (pub-sub com 2 consumers)
 * 
 * O Spring Boot cria automaticamente o ConnectionFactory quando
 * spring-boot-starter-activemq está no classpath.
 */
@EnableJms
@Configuration
public class JmsConfig {

    /**
     * Factory para Queue (point-to-point)
     * Uma mensagem é consumida por apenas um consumer
     */
    @Bean
    public DefaultJmsListenerContainerFactory queueJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);  // Queue (point-to-point)
        factory.setConcurrency("1");      // Apenas 1 consumer
        return factory;
    }

    /**
     * Factory para Topic (pub-sub)
     * Uma mensagem é broadcastada para todos os subscribers
     */
    @Bean
    public DefaultJmsListenerContainerFactory topicJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);    // Topic (pub-sub)
        factory.setConcurrency("1-2");    // Até 2 consumers simultâneos
        return factory;
    }

    /**
     * JmsTemplate para enviar mensagens para Queue
     */
    @Bean
    @Qualifier("queueJmsTemplate")
    public JmsTemplate queueJmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(false);  // Queue
        return jmsTemplate;
    }

    /**
     * JmsTemplate para enviar mensagens para Topic
     */
    @Bean
    @Qualifier("topicJmsTemplate")
    public JmsTemplate topicJmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(true);   // Topic
        return jmsTemplate;
    }
}

