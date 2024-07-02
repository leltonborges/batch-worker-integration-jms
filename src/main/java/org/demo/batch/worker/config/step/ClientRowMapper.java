package org.demo.batch.worker.config.step;

import org.demo.batch.worker.model.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper
        implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        client.setSeqClient(rs.getLong("SEQ_CLIENT"));
        client.setName(rs.getString("NAME_CLIENT"));
        client.setEmail(rs.getString("EMAIL_CLIENT"));
        return client;
    }
}
