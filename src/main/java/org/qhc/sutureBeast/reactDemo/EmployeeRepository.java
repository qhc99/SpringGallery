package org.qhc.sutureBeast.reactDemo;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> { // <1>

}
