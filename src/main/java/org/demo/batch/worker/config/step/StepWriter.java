package org.demo.batch.worker.config.step;

import lombok.extern.slf4j.Slf4j;
import org.demo.batch.worker.model.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
@Slf4j
public class StepWriter
        implements ItemWriter<Client> {
    @Override
    public void write(List<? extends Client> list) {
        log.info("Writing {} clients", list.size());
    }
}
