package blstubdriver;

import java.util.ArrayList;

import blservice.customerblservice.CustomerBLService;
import po.UserPO;
import util.CustomerCategory;
import util.Level;
import util.ResultMessage;
import util.UserLimits;
import util.UserPosition;
import vo.CustomerAddVO;
import vo.CustomerVO;
import vo.UserVO;

public class CustomerBLService_Stub implements CustomerBLService{
	ArrayList<CustomerVO> customerData=new ArrayList<CustomerVO>();
		{
		CustomerVO c1=new CustomerVO("00000001",CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545786610",
					"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0,500);
		CustomerVO c2=new CustomerVO("00000002",CustomerCategory.PUR_AGENT,Level.LEVEL_ONE,"进货商1","15247678373",
					"南京新街口","421001","dds@163.com",1.0,0.0,2000.0,"业务员2",224.0,600);
		CustomerVO c3=new CustomerVO("00000003",CustomerCategory.PUR_AGENT,Level.LEVEL_THREE,"进货商2","15244358373",
				"南京新街口","421001","34s@163.com",0.8,0.0,2000.0,"业务员2",50.0,400);
		CustomerVO c4=new CustomerVO("00000004",CustomerCategory.PUR_AGENT,Level.LEVEL_TWO,"进货商3","15244358397",
				"南京新街口","421001","12s@163.com",0.8,0.0,2000.0,"业务员2",50.0,400);
		CustomerVO c5=new CustomerVO("00000005",CustomerCategory.SELLER,Level.LEVEL_FOUR,"金主2","15546674310",
				"南京仙林大学城","421000","ddk@163.com",1.0,15000.0,0.0,"业务员1",150.0,500);
		customerData.add(c1);
		customerData.add(c2);
		customerData.add(c3);
		customerData.add(c4);
		customerData.add(c5);
	}
	    ArrayList<UserVO> userList=new ArrayList<>();
	    UserVO po1=new UserVO("00002", "123", "bobule", UserPosition.SALES_STAFF, UserLimits.MANAGER);
	    {
	       userList.add(po1);
	    }
	  
	
	public String getNewCustomerID() {
		int len=customerData.size();
		String ID=Integer.toString(len+1);
		int size=ID.length();
		for(int i=0;i<8-size;i++){
			ID="0"+ID;
		}
		return ID;
	}

	public ResultMessage addCustomer(CustomerVO vo) {
		for(CustomerVO cus:customerData){
			if(vo.address.equals(cus.address)&&vo.customerName.equals(cus.customerName)&&vo.phone.equals(cus.phone)){
				System.out.println("Add customer failed");
				return ResultMessage.EXIST;
			}
		}
		customerData.add(vo);
		System.out.println("Add customer success");
		return ResultMessage.SUCCESS;
	}

	public ResultMessage deleteCustomer(String customerID) {
		for(CustomerVO cus:customerData){
			if(cus.customerID.equals(customerID)){
				customerData.remove(cus);
				System.out.println("Delete customer success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("Delete customer failed");
		return ResultMessage.FAILED;
	}

	public ArrayList<CustomerVO> findCustomerByKeywords(String keywords) {
		ArrayList<CustomerVO> findList=new ArrayList<CustomerVO>();
		for(CustomerVO cus:customerData){
			if(cus.customerName.contains(keywords)){
				findList.add(cus);
			}
		}
		System.out.println("Find customer success");
		return findList;
	}

	public ArrayList<CustomerVO> findCustomerByCustomerID(String customerID) {
		ArrayList<CustomerVO> findList=new ArrayList<CustomerVO>();
		for(CustomerVO cus:customerData){
			if(cus.customerID.contains(customerID)){
				findList.add(cus);
			}
		}
		System.out.println("Find customer success");
		return findList;
	}

	public ResultMessage updateCustomer(CustomerVO vo) {
		for(CustomerVO cus:customerData){
			if(cus.customerID.equals(vo.customerID)){
				customerData.remove(cus);
				customerData.add(vo);
				System.out.println("Update customer success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("Update customer failed");
		return ResultMessage.FAILED;
	}

	public ArrayList<CustomerVO> show() {
		// TODO Auto-generated method stub
		return customerData;
	}

	@Override
	public ArrayList<UserVO> getAllSalesman() {
		return userList;
	}

	@Override
	public UserLimits getCurrentUserLimit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CustomerVO> findCustomer(String input) {
		return customerData;
	}
	
}
