package blservice.customerblservice;

import java.rmi.Remote;
import java.util.ArrayList;

import util.ResultMessage;
import util.UserLimits;
import vo.CustomerAddVO;
import vo.CustomerVO;
import vo.UserVO;

/**
 * created by zlk on 2017/10/21
 */

public interface CustomerBLService extends Remote{
	  //管理客户界面得到客户编号
	/**
	 * 创建客户时得到客户编号
	 * 
	 * @return customerID
	 * @author zlk
	 * 
	 */
	public String getNewCustomerID();
      //管理客户的步骤
	/**
	 * 管理客户中的添加客户
	 * 
	 * @param vo
	 * @return 处理信息
	 * @author zlk
	 */
	public ResultMessage addCustomer(CustomerVO vo);
	/**
	 * 管理客户中的删除客户
	 * 
	 * @param name
	 * @return 处理信息
	 * @author zlk
	 */
	public ResultMessage deleteCustomer(String customerID);
	/**
	 * 通过关键字查找客户
	 * 
	 * @param keywords
	 * @return 满足条件的客户
	 * @author zlk
	 */
	public ArrayList<CustomerVO> findCustomerByKeywords(String keywords);
	/**
	 * 通过客户编号查找客户
	 * 
	 * @param customerID
	 * @return 满足条件的客户
	 * @author zlk
	 */
	public ArrayList<CustomerVO> findCustomerByCustomerID(String customerID);
	/**
	 * 不选择查找方式查找客户
	 * 
	 * @param input
	 * @return
	 */
	public ArrayList<CustomerVO> findCustomer(String input);
	/**
	 * 管理客户中的更新客户
	 * 
	 * @param vo
	 * @return 处理信息
	 * @author zlk
	 */
	public ResultMessage updateCustomer(CustomerVO vo);
	/**
	 * 展示所有客户
	 * @return
	 */
	public ArrayList<CustomerVO> show();
	/**
	 * 得到所有业务员
	 * @return
	 */
	public ArrayList<UserVO> getAllSalesman();
	/**
	 * 得到当前登录用户的权限
	 * @return
	 */
	public UserLimits getCurrentUserLimit();
}
