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
	 * 得到所有销售单时间
	 */
	public ArrayList<String> getAllSalesDate();
	/**
	 * 得到所有商品名
	 */
	public ArrayList<String> getAllGoodsName();
	/**
	 * 得到所有业务员
	 */
	public ArrayList<String> getSalesman();
	/**
	 * 得到所有仓库
	 */
	public ArrayList<String> getAllInventory();
	/**
	 * 得到所有客户ID
	 */
	public ArrayList<String> getAllCustomerID();
	//查看销售明细表
	/**
	 * 得到所有销售出货单
	 * @return
	 */
	public ArrayList<SalesVO> getAllSalesOrder(String startDate,String endDate);
	//查看经营历程表
	/**
	 * 通过查看时间区间得到销售单ID
	 */
	public ArrayList<String> getSalesIDByDate(String startDate,String endDate);
	/**
	 * 通过查找单据类型得到销售单ID
	 */
	public ArrayList<String> getSalesIDByType(BillType type);
	/**
	 * 通过查找客户得到销售单ID
	 */
	public ArrayList<String> getSalesIDByCustomerID(String customerID);
	/**
	 * 通过查找业务员得到销售单ID
	 */
	public ArrayList<String> getSalesIDBySalesman(String salesman);
	/**
	 * 通过查找仓库得到销售单ID
	 */
	public ArrayList<String> getSalesIDByInventory(String invenory);
	//通过ID得到进货销售具体信息
	/**
	 * 通过ID得到时间
	 */
	public String getDateByID(String ID);
	/**
	 * 通过ID得到商品名、型号、数量、单价
	 */
	public ArrayList<GoodsItemVO> getGoodsItemByID(String ID);
	/**
	 * 通过ID得到折让
	 */
	public double getAllowance(String ID);
	/**
	 * 通过ID得到总额（折让后总额）
	 */
	public double getSumByID(String ID);
	/**
	 * 审批销售单（销售出货单和销售退货单）
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
}
