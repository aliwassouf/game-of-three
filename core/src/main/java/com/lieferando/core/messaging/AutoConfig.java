package com.lieferando.core.messaging;

import com.lieferando.core.gamesessionmanagment.GameSessionService;
import com.lieferando.core.properties.MessagingProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({MessagingProperties.class})
@Configuration
@Slf4j
public class AutoConfig {

    @Bean
    Queue queue(MessagingProperties messagingProperties) {
        var queueName = messagingProperties.getCurrentConnection().getQueue();
        log.info("Creating queue with name "+ queueName);
        return new Queue(queueName, true);
    }

    @Bean
    DirectExchange exchange(MessagingProperties messagingProperties) {
        return new DirectExchange(messagingProperties.getCurrentConnection().getExchange());
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange, MessagingProperties messagingProperties) {
        return BindingBuilder.bind(queue).to(exchange).with(messagingProperties.getCurrentConnection().getRoutingKey());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Publisher publisher(AmqpTemplate rabbitTemplate, MessagingProperties messagingProperties) {
        return new PublisherImpl(rabbitTemplate, messagingProperties);
    }

    @Bean
    public Consumer consumer(GameSessionService gameSessionService) {
        return new Consumer(gameSessionService);
    }
}
