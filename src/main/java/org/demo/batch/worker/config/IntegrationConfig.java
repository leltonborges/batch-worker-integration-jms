package org.demo.batch.worker.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.integration.config.annotation.EnableBatchIntegration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.integration.jms.dsl.JmsDefaultListenerContainerSpec;

import javax.jms.ConnectionFactory;
import java.util.function.Consumer;

@Configuration
@EnableIntegration
@EnableBatchProcessing
@EnableBatchIntegration
public class IntegrationConfig {

    @Bean("inboundRequests")
    public DirectChannel inboundRequests() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow inboundJmsRequests(@Qualifier("mqConnectionFactory")
                                              ConnectionFactory connectionFactory,
                                              @Qualifier("inboundRequests")
                                              DirectChannel directChannel) {
        return IntegrationFlows.from(Jms.messageDrivenChannelAdapter(connectionFactory)
//                                        .configureListenerContainer(configListener())
                                        .destination("DEMO_BATCH")
//                                        .outputChannel(directChannel)
                                    )
                               .channel(directChannel)
                               .get();
    }

    private static Consumer<JmsDefaultListenerContainerSpec> configListener() {
        return config -> {
            config.subscriptionDurable(false);
//            config.maxConcurrentConsumers(10);
//            config.concurrentConsumers(5);
            config.concurrency("5-10");
            config.receiveTimeout(5000);
        };
    }
}
