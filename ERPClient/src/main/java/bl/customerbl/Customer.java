package bl.customerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Locale.Category;

import com.jfoenix.controls.JFXPopup.PopupHPosition;

import dataservice.customerdataservice.CustomerDataService;
import po.CustomerPO;
import rmi.CustomerRemoteHelper;
import util.CustomerCategory;
import util.Level;
import util.ResultMessage;
import vo.CustomerVO;

/**
 * Created by zlk on 2017/11/5
 */

public class Customer {
	private CustomerDataService customerDataService;
	
	public Customer(){
		customerDataService=CustomerRemoteHelper.getInstance().getCustomerDataService();
	}
	
	/**
	 * 创建客户时得到客户编号
	 * 
	 * @return customerID
	 * @author zlk
	 * 
	 */
	public String getNewCustomerID(){
		return null;
		
	}
      //管理客户的步骤
	/**
	 * 管理客户中的添加客户
	 * 
	 * @param vo
	 * @return 处理信息
	 * @author zlk
	 * @throws RemoteException 
	 */
	public ResultMessage addCustomer(CustomerVO vo) throws RemoteException {
		CustomerPO po=voTopo(vo);
		return customerDataService.add(po);
	}
	/**
	 * 管理客户中的删除客户
	 * 
	 * @param name
	 * @return 处理信息
	 * @author zlk
	 * @throws RemoteException 
	 */
	public ResultMessage deleteCustomer(String customerID) throws RemoteException{
		CustomerPO po=customerDataService.getCustomerData(Integer.parseInt(customerID));
		return customerDataService.delete(po);
		
	}
	/**
	 * 通过关键字查找客户
	 * 
	 * @param keywords
	 * @return 满足条件的客户
	 * @author zlk
	 * @throws RemoteException 
	 */
	public ArrayList<CustomerVO> findCustomerByKeywords(String keywords) throws RemoteException{
		ArrayList<CustomerPO> cuspoList=customerDataService.findByKeywords(keywords);
		ArrayList<CustomerVO> cusvoList=new ArrayList<>();
		for(CustomerPO po:cuspoList){
			cusvoList.add(poTovo(po));
		}
		return cusvoList;
		
	}
	/**
	 * 通过客户编号查找客户
	 * 
	 * @param customerID
	 * @return 满足条件的客户
	 * @author zlk
	 * @throws RemoteException 
	 * @throws NumberFormatException 
	 */
	public ArrayList<CustomerVO> findCustomerByCustomerID(String customerID) throws NumberFormatException, RemoteException {
		ArrayList<CustomerPO> cuspoList=customerDataService.findByCustomerID(Integer.parseInt(customerID));
		ArrayList<CustomerVO> cusvoList=new ArrayList<>();
		for(CustomerPO po:cuspoList){
			cusvoList.add(poTovo(po));
		}
		return cusvoList;
	}
	/**
	 * 管理客户中的更新客户
	 * 
	 * @param vo
	 * @return 处理信息
	 * @author zlk
	 * @throws RemoteException 
	 */
	public ResultMessage updateCustomer(CustomerVO vo) throws RemoteException{
		return customerDataService.update(voTopo(vo));
	}
	
	public ArrayList<CustomerVO> show() throws RemoteException {
		ArrayList<CustomerPO> cuspoList=customerDataService.show();
		ArrayList<CustomerVO> cusvoList=new ArrayList<>();
		for(CustomerPO po:cuspoList){
			cusvoList.add(poTovo(po));
		}
		return cusvoList;
	}
	
	public ArrayList<Integer> getAllCustomerID(){
		return null;
	}
	
	public ArrayList<String> getAllCustomerName(){
		return null;
	}
	
	public CustomerVO getCustomerByID(int ID){
		return null;
	}
	
	
	public CustomerPO voTopo(CustomerVO vo){
		return new CustomerPO(vo.category,vo.level,vo.customerName,vo.phone,vo.address,vo.postCode,vo.mail,vo.receivableLimit,vo.receive,vo.pay,vo.salesman,vo.points,vo.voucher);
	}
	
	public CustomerVO poTovo(CustomerPO po){
		return new CustomerVO(String.valueOf(po.getID()),CustomerCategory.categoryToString(po.getCategory()),Level.levelToString(po.getLevel()),po.getCustomerName(),po.getPhone(),po.getAddress(),po.getPostCode(),po.getMail(),po.getReceivableLimit(),po.getReceive(),po.getPay(),po.getSalesman(),po.getPoints(),po.getVoucher());
	}
}
