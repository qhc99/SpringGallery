package qhc.gallery.demos.reactDemo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "reactEmployees")
public interface ReactEmployeeRepository extends CrudRepository<ReactEmployee, Long> { // <1>

}
