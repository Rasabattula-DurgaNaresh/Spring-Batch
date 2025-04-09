package com.motaharinia.batch.business.service.creditcard.reader;

import com.motaharinia.batch.presentation.creditcard.CreditCardModel;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2021-01-03 <br>
 * Time: 00:58:01 <br>
 * Description: <br>
 */
public class CreditCardExcelRowMapper implements RowMapper<CreditCardModel> {

    @Override
    public CreditCardModel mapRow(RowSet rowSet)  {
        System.out.println(  "CreditCardExcelRowMapper.mapRow rowSet.toString():"+ rowSet.toString());
        CreditCardModel creditCardModel = new CreditCardModel();
        creditCardModel.setCardNumber(rowSet.getColumnValue(0));
        creditCardModel.setLastPay(Date.valueOf(rowSet.getColumnValue(1)));
        creditCardModel.setRemainAmount(BigDecimal.valueOf(Long.valueOf(rowSet.getColumnValue(2))));
        return creditCardModel;
    }
}
