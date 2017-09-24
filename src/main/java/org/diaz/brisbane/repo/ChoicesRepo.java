package org.diaz.brisbane.repo;

import org.diaz.brisbane.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoicesRepo extends JpaRepository<Choice, Long> {

}
