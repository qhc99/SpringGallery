package org.qhc.sutureBeast.reactTable;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> { // <1>

}
