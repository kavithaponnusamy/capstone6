package co.grandcircus.capstone6;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByUserId(Long id);

	List<Task> findByUserIdOrderByDueDate(Long id);

	//List<Task> findByNameContainsIgnoreCase(String name);

	//List<Task> findAllByDescriptionContainsIgnoreCaseAndDueDate(String description, Date date);
	
	
	

	
	

	
}
