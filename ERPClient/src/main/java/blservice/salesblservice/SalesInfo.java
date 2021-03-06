package blservice.salesblservice;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.table.TableStringConverter;

import util.BillType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PurchaseVO;
import vo.SalesVO;

/**
 * Created by zlk on 2017/11/5
 */

public interface SalesInfo {
	/**
	 * 得到所有销售出货单
	 * @return
	 */
	public ArrayList<SalesVO> getAllSalesOrder(String startDate,String endDate);
	/**
	 * 得到所有销售退货单
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public ArrayList<SalesVO> getAllSalesReturnOrder(String startDate,String endDate);
	/**
	 * 审批销售单（销售出货单和销售退货单），如果通过， 更改库存数据和客户的应收应付数据
	 * 
	 * @param vo
	 * @return
	 */
	public ResultMessage examine(SalesVO vo);
	/**
	 * 得到所有提交的销售单
	 * @return
	 */
	public ArrayList<SalesVO> getAllSubmittedSales();
	/**
	 * 通过时间区间、仓库得到通过的销售单据的商品信息
	 * @param inventory
	 * @param type
	 * @return
	 */
	public ArrayList<SalesVO> getSalesByDateAndInventory(String startDate,String endDate,String inventory,BillType type);
	/**
	 * 生成红冲单据（原本数量取负以抵消原单据影响）
	 * 
	 * @param vo 单据VO
	 * @return 是否成功添加红冲单据
	 */
	public ResultMessage redCover(SalesVO vo);
	/**
	 * 生成红冲单据和复制单据（原本数量取负以抵消原单据影响，再新生成一张草稿单据）
	 * 
	 * @param vo 单据VO
	 * @return 是否成功添加红冲单据和草稿单据
	 */
	public ResultMessage redCoverAndCopy(SalesVO vo);
	
}
