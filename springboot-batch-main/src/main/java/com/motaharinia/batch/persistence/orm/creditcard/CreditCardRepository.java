package com.motaharinia.batch.persistence.orm.creditcard;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;



/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس ریپازیتوری کارت اعتباری
 */
@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {


}
