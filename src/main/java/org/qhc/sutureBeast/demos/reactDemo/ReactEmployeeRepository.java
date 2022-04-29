package org.qhc.sutureBeast.demos.reactDemo;

import org.springframework.data.repository.CrudRepository;

public interface ReactEmployeeRepository extends CrudRepository<ReactEmployee, Long> { // <1>

}
