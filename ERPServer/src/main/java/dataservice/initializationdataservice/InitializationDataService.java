package dataservice.initializationdataservice;

import po.InitAccountPO;
import util.ResultMessage;

/**
 * Created by Kry·L on 2017/10/30.
 */
public interface InitializationDataService {

    /**
     * 期初建账
     * @param po
     * @return
     */
    public ResultMessage init(InitAccountPO po);
    /**
     * 展示所有期初建账信息
     * @return
     */
   public  InitAccountPO show();
}