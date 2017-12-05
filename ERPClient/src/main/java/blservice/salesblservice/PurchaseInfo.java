package blservice.salesblservice;

import java.sql.Date;
import java.util.ArrayList;

import util.BillType;

public interface PurchaseInfo {
	/**
	 * 得到所有进货单时间
	 */
	public ArrayList<Date> getAllPurchaseDate();
	/**
	 * 通过查看时间区间得到进货单ID
	 */
	public ArrayList<String> getPurchaseIDByDate(Date startDate,Date endDate);
	/**
	 * 通过查找单据类型得到进货单ID
	 */
	public ArrayList<String> getPurchaseIDByType(BillType type);
	/**
	 * 通过查找客户得到进货单ID
	 */
	public ArrayList<String> getPurchaseIDByCustomerID(String customerID);
	/**
	 * 通过查找业务员得到进货单ID
	 */
	public ArrayList<String> getPurchaseIDBySalesman(String salesman);
	/**
	 * 通过查找仓库得到进货单ID
	 */
	public ArrayList<String> getPurchaseIDByInventory(String inventory);
}