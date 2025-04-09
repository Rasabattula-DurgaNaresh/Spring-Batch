/**
 * @author debpaul
 *
 */

package com.techtalk.debu.batch.step;

import com.techtalk.debu.batch.entity.Employee;
import com.techtalk.debu.batch.repository.EmployeeRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBItemWriter implements ItemWriter<Employee> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void write(List<? extends Employee> emplyees) throws Exception {

        System.out.println("Data Saved for Employees: " + emplyees);
        employeeRepository.saveAll(emplyees);
    }
}
