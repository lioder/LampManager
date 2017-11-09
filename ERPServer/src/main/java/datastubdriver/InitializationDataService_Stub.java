package datastubdriver;

import dataservice.initializationdataservice.InitializationDataService;
import po.*;
import util.CustomerCategory;
import util.Level;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/7.
 */
public class InitializationDataService_Stub implements InitializationDataService{

    public ResultMessage init(InitAccountPO po) {
        return ResultMessage.SUCCESS;
    }

    public InitAccountPO show() {
        AccountPO account = new AccountPO("工商银行账户", 2000.00);
        ArrayList<AccountPO> accountPOS = new ArrayList<AccountPO>();
        accountPOS.add(account);

        CustomerPO customer=new CustomerPO("00000003", CustomerCategory.PUR_AGENT, Level.LEVEL_FIVE,"进货商2","15244358373",
                "南京新街口","421001","34s@163.com",0.8,0.0,2000.0,"业务员2",50.0);
        ArrayList<CustomerPO> customerPOS = new ArrayList<CustomerPO>();
        customerPOS.add(customer);

        ClassificationPO classification = new ClassificationPO("05", "霓虹灯", null, new ArrayList<ClassificationPO>(), new ArrayList<GoodsPO>());
        ArrayList<ClassificationPO> classificationPOS = new ArrayList<ClassificationPO>();
        classificationPOS.add(classification);

        ArrayList<Double> list = new ArrayList<Double>();
        list.add(2000.00);
        GoodsPO goods = new GoodsPO("05000005", "后现代主义七彩霓虹灯", "LLL", "霓虹灯", "栖霞区仓库", 7, 3, 23333.3, 250000, list,list);
        ArrayList<GoodsPO> goodsVOS = new ArrayList<GoodsPO>();
        goodsVOS.add(goods);

        InitAccountPO po = new InitAccountPO(new Date(),customerPOS,accountPOS,goodsVOS,classificationPOS);
        return po;
    }
}