package sample.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.data.jpa.domain.*;

public interface WorkerDao extends JpaRepository<Worker, Long> {

}