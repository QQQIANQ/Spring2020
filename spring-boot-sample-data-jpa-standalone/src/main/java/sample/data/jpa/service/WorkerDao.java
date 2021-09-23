package sample.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import sample.data.jpa.domain.*;

@Transactional
public interface WorkerDao extends JpaRepository<Worker, Long> {

}
