package com.jshuabo.reportcenter.server.web.controller.transport;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: FinanceSettleAccountController
 * @Description: 财务报表结算
 * @author: peng.wu
 * @date: 2014年10月23日 下午1:48:25
 */
@RequestMapping("financeSettleAccount")
public class FinanceSettleAccountController {

    @RequestMapping(value="/list",method=RequestMethod.GET)
    public String listPage(){
        return "financeSettleAccount/serviceConfigList";
    }
}
