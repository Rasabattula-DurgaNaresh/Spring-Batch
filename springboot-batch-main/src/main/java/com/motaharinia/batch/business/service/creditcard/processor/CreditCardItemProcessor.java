package com.motaharinia.batch.business.service.creditcard.processor;

import com.motaharinia.batch.persistence.orm.creditcard.CreditCard;
import com.motaharinia.batch.presentation.creditcard.CreditCardModel;
import org.springframework.batch.item.ItemProcessor;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2021-01-02 <br>
 * Time: 22:45:36 <br>
 * Description: <br>
 * implements Spring Batch’s ItemProcessor interface. This makes it easy to wire the code into a batch job.
 * According to the interface, you receive an incoming PersonModel object, after which you transform it to an upper-cased PersonModel.
 */
public class CreditCardItemProcessor implements ItemProcessor<CreditCardModel, CreditCard> {
    @Override
    public CreditCard process(final CreditCardModel creditCardModel) throws Exception {
        CreditCard creditCard=new CreditCard();
        creditCard.setCardNumber(creditCardModel.getCardNumber());
        creditCard.setLastPay(creditCardModel.getLastPay());
        creditCard.setRemainAmount(creditCardModel.getRemainAmount());
        return creditCard;
    }
}
