package com.lieferando.core.config;

import com.lieferando.core.functionality.Consumer;
import com.lieferando.core.functionality.Publisher;
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
public class MessagingAutoConfig {

    @Bean
    Queue queue(MessagingProperties messagingProperties) {
        var queueName = messagingProperties.getCurrent().getQueue();
        log.info("Creating queue with name "+ queueName);
        return new Queue(queueName, true);
    }

    @Bean
    DirectExchange exchange(MessagingProperties messagingProperties) {
        return new DirectExchange(messagingProperties.getCurrent().getExchange());
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange, MessagingProperties messagingProperties) {
        return BindingBuilder.bind(queue).to(exchange).with(messagingProperties.getCurrent().getRoutingKey());
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
        return new Publisher(rabbitTemplate, messagingProperties);
    }

    @Bean
    public Consumer consumer(Publisher publisher) {
        return new Consumer(publisher);
    }
}
