package sample.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.data.jpa.domain.*;
public interface JobDao extends JpaRepository<Job, Long> {

    public Job findJobById(Long id);

    public Job findJobByName(String name);


}
