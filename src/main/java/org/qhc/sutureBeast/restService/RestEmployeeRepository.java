package org.qhc.sutureBeast.restService;

import org.springframework.data.jpa.repository.JpaRepository;

interface RestEmployeeRepository extends JpaRepository<RestEmployee, Long> {

}