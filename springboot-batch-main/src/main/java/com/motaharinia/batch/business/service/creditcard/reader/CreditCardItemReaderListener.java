package com.motaharinia.batch.business.service.creditcard.reader;

import com.motaharinia.batch.presentation.creditcard.CreditCardModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2021-01-03 <br>
 * Time: 01:52:38 <br>
 * Description: <br>
 */
public class CreditCardItemReaderListener implements ItemReadListener<CreditCardModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardItemReaderListener.class);

    @Override
    public void beforeRead() {
        LOGGER.info("beforeRead");
    }

    @Override
    public void afterRead(CreditCardModel creditCardModel) {
        LOGGER.info("afterRead: " + creditCardModel.toString());
    }

    @Override
    public void onReadError(Exception e) {
        LOGGER.info("onReadError");
    }

}
