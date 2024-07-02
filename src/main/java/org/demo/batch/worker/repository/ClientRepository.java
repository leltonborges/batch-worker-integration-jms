package org.demo.batch.worker.repository;

import org.demo.batch.worker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository
        extends JpaRepository<Client, Long> {
}
