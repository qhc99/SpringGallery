package qhc.gallery.demos.restService;

import org.springframework.data.jpa.repository.JpaRepository;

interface RestEmployeeRepository extends JpaRepository<RestEmployee, Long> {

}