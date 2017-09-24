package org.diaz.brisbane.repo;

import org.diaz.brisbane.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Long> {

}
