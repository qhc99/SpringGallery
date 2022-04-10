package org.qhc.sutureBeast.reactApp;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> { // <1>

}
