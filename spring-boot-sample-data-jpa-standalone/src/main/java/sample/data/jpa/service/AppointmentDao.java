package sample.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.data.jpa.domain.*;

public interface AppointmentDao extends JpaRepository<Appointment, Long> {


}
