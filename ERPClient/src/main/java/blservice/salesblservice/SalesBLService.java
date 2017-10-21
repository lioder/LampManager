package blservice.salesblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.GoodsVO;

/**
 * created by zlk on 2017/10/21
 */

public interface SalesBLService {
     /**
      * 创建进货单时得到进货单编号
      * 	
      * @return 进货单编号
      * @author zlk
      */
	  public String getnewPurchaseID();
	  /**
	   * 创建进货退货单时得到进货退货单编号
	   * 
	   * @return 进货退货单编号
	   * @author zlk
	   */
	  public String getnewReturnID();
	  /**
	   * 创建销售单时得到销售单编号
	   * 
	   * @return 得到销售单编号
	   * @author zlk
	   */
	  public String getnewSalesID();
	  /**
	   * 创建销售退货单时得到销售退货单编号
	   * 
	   * @return 得到销售退货单编号
	   * @author zlk
	   */
	  public String getnewSalesReturnID();
	  /**
	   * 展示促销策略
	   * 
	   * @return 得到促销策略
	   * @author zlk
	   */
	  public ArrayList <PromotionBargainVO> showBargains();
	  /**
	   * 得到合适的会员促销策略
	   * 
	   * @return 会员促销策略
	   * @author zlk
	   */
	  public ArrayList <PromotionCustomerVO> findFitPromotionCustomer();
	  /**
	   * 得到合适的总价促销策略
	   * 
	   * @return 总价促销策略
	   * @author zlk
	   */
	  public ArrayList <PromotionTotalVO> findFitPromotionTotal();
	  /**
	   * 创建一个进货单
	   * 
	   * @param vo
	   * @return 得到创建后的进货单
	   * @author zlk
	   */
	  public purchaseVO addPurchase(PurchaseVO vo);
	  /**
	   * 添加商品清单信息
	   * 
	   * @param item
	   * @author zlk
	   */
	  public void addGoodsItem(GoodsItemVO item);
	  /**
	   * 创建一个销售单
	   * 
	   * @param vo
	   * @return 得到创建后的销售单
	   * @author zlk
	   */
	  public SalesVO addSalesItem(SalesVO vo);
	  /**
	   * 提交进货单
	   * 
	   * @param pur
	   * @return 进货单是否提交成功
	   * @author zlk
	   */
	  public ResultMessage submitPurchase(PurchaseVO pur);
	  /**
	   * 提交销售单
	   * 
	   * @param sal
	   * @return 销售单是否提交成功
	   * @author zlk
	   */
	  public ResultMessage submitSales(SalesVO sal);
	  /**
	   * 保存进货单到草稿中
	   * 
	   * @param bill
	   * @return 进货单据是否成功保存到草稿中
	   * @author zlk
	   */
	  public ResultMessage save(SalesVO bill);
	  /**
	   * 保存销售单到草稿中
	   * 
	   * @param bill
	   * @return 销售单据是否成功保存到草稿中
	   * @author zlk
	   */
	  public ResultMessage save(PurchaseVO bill);
	  
}
