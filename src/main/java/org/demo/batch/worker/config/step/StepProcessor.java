package org.demo.batch.worker.config.step;

import org.demo.batch.worker.model.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class StepProcessor
        implements ItemProcessor<Client, Client> {
    @Override
    public Client process(Client client) {
        return client;
    }
}
