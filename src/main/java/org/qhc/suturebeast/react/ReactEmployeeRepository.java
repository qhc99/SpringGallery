package org.qhc.suturebeast.react;

import org.springframework.data.repository.CrudRepository;

public interface ReactEmployeeRepository extends CrudRepository<ReactEmployee, Long> { // <1>

}
