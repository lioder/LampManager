package dataservice.customerdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CustomerPO;
import util.ResultMessage;

/**
 *created by zlk on 2017/10/21
 */

public interface CustomerDataService extends Remote{
	/**
	 * 得到新的客户ID
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public String getNewCustomerID() throws RemoteException;
	/**
	 * 添加客户
	 * 
	 * @param po
	 * @return 是否添加成功
	 * @author zlk
	 */
	public ResultMessage add(CustomerPO po) throws RemoteException;
	/**
	 * 删除客户
	 * 
	 * @param po
	 * @return 是否成功删除客户
	 * @author zlk
	 */
	public ResultMessage delete(CustomerPO po) throws RemoteException;
	/**
	 * 通过客户编号查找客户
	 * 
	 * @param customerID
	 * @return 查找到符合条件的客户po
	 * @author zlk
	 */
	public ArrayList<CustomerPO> findByCustomerID(int customerID) throws RemoteException;
	/**
	 * 通过关键字查找客户
	 * 
	 * @param keywords
	 * @return 查找到符合条件的客户po
	 * @author zlk
	 */
	public ArrayList<CustomerPO> findByKeywords(String keywords) throws RemoteException;
	/**
	 * 不选择查找方法进行查找
	 * 
	 * @param input
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<CustomerPO> findCustomer(String input) throws RemoteException;
	/**
	 * 更新客户信息
	 * 
	 * @param po
	 * @return 是否成功更新客户信息
	 * @author zlk
	 */
	public ResultMessage update(CustomerPO po) throws RemoteException;
	/**
	 * 展示客户
	 * 
	 * @return 客户列表
	 * @author zlk
	 */
	public ArrayList<CustomerPO> show() throws RemoteException;
	/**
	 * 初始化持久化数据库
	 * 
	 * @return
	 * @author zlk
	 */
	public void init() throws RemoteException;
	/**
	 * 得到一个客户的客户信息
	 * 
	 * @return 
	 * @author zlk
	 */
	public CustomerPO getCustomerData(int ID) throws RemoteException;

}
