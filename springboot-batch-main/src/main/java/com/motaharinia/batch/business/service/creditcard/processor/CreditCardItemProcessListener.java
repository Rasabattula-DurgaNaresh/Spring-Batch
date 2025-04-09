package com.motaharinia.batch.business.service.creditcard.processor;

import com.motaharinia.batch.presentation.creditcard.CreditCardModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2021-01-03 <br>
 * Time: 01:55:15 <br>
 * Description: <br>
 */
public class CreditCardItemProcessListener  implements ItemProcessListener<CreditCardModel, CreditCardModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardItemProcessListener.class);

    @Override
    public void beforeProcess(CreditCardModel creditCardModel) {
        LOGGER.info("beforeProcess");
    }

    @Override
    public void afterProcess(CreditCardModel creditCardModel, CreditCardModel creditCardModel1) {
        LOGGER.info("afterProcess: " + creditCardModel + " ---> " + creditCardModel1);
    }

    @Override
    public void onProcessError(CreditCardModel creditCardModel, Exception e) {
        LOGGER.info("onProcessError");
    }
}
