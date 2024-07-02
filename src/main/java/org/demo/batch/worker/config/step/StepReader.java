package org.demo.batch.worker.config.step;

import org.demo.batch.worker.model.Client;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@StepScope
public class StepReader
        extends JdbcCursorItemReader<Client>
        implements StepExecutionListener {

    public StepReader(@Qualifier("dbDemo") DataSource dataSource) {
        super.setDataSource(dataSource);
        super.setFetchSize(100);
        super.setVerifyCursorPosition(false);
        super.setRowMapper(new ClientRowMapper());
        super.setSql(getQuery());
    }

    private static String getQuery() {
        return "select SEQ_CLIENT, NAME_CLIENT, EMAIL_CLIENT from demo.TB_CLIENT c";
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        stepExecution.getStepName();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return stepExecution.getExitStatus();
    }

}
