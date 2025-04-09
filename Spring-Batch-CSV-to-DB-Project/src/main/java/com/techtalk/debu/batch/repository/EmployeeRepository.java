/**
 * @author debpaul
 *
 */
package com.techtalk.debu.batch.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.techtalk.debu.batch.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
