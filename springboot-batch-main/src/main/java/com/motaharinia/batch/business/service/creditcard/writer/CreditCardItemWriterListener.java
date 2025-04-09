package com.motaharinia.batch.business.service.creditcard.writer;

import com.motaharinia.batch.persistence.orm.creditcard.CreditCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2021-01-03 <br>
 * Time: 01:58:02 <br>
 * Description: <br>
 */
public class CreditCardItemWriterListener implements ItemWriteListener<CreditCard> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardItemWriterListener.class);

    @Override
    public void beforeWrite(List<? extends CreditCard> list) {
        LOGGER.info("beforeWrite");
    }


    @Override
    public void afterWrite(List<? extends CreditCard> list) {
        for (CreditCard creditCard : list) {
            LOGGER.info("afterWrite :" + creditCard.toString());
        }
    }

    @Override
    public void onWriteError(Exception e, List<? extends CreditCard> list) {
        LOGGER.info("onWriteError");
    }
}
