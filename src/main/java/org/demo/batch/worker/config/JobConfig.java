package org.demo.batch.worker.config;

import org.demo.batch.worker.common.Constants.Worker;
import org.demo.batch.worker.config.step.StepProcessor;
import org.demo.batch.worker.config.step.StepReader;
import org.demo.batch.worker.config.step.StepWriter;
import org.demo.batch.worker.model.Client;
import org.springframework.batch.core.Step;
import org.springframework.batch.integration.partition.RemotePartitioningWorkerStepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConfig {
    private final RemotePartitioningWorkerStepBuilderFactory remoteStepBuilder;

    @Autowired
    public JobConfig(RemotePartitioningWorkerStepBuilderFactory remoteStepBuilder) {
        this.remoteStepBuilder = remoteStepBuilder;
    }

    //    @JobScope
    @Bean(Worker.STEP_REMOTE_PARTITION_NAME)
    public Step step(StepReader reader,
                     StepProcessor processor,
                     StepWriter writer,
                     @Qualifier("inboundRequests") DirectChannel channel,
                     @Qualifier("transactionDemo") PlatformTransactionManager transactionManager) {
        return remoteStepBuilder.get(Worker.STEP_SLAVE_NAME)
                                .inputChannel(channel)
                                .<Client, Client>chunk(100)
                                .reader(reader)
                                .processor(processor)
                                .writer(writer)
//                                .transactionManager(transactionManager)
                                .build();
    }
}
