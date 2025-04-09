package com.motaharinia.batch.business.service.creditcard.writer;

import com.motaharinia.batch.persistence.orm.creditcard.CreditCard;
import com.motaharinia.batch.persistence.orm.creditcard.CreditCardRepository;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2021-01-03 <br>
 * Time: 01:38:22 <br>
 * Description: <br>
 */
public class CreditCardItemWriter implements ItemWriter<CreditCard> {

    private CreditCardRepository respository;

    public CreditCardItemWriter(CreditCardRepository respository) {
        this.respository=respository;
    }

    @Override
    public void write(List<? extends CreditCard> list) throws Exception {
        this.respository.saveAll(list);
    }
}
