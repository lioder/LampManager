package bl.userbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import dataservice.userdataservice.UserDataService;
import po.UserPO;
import rmi.UserRemoteHelper;
import util.ResultMessage;
import util.UserPosition;
import vo.UserVO;

public class User {

	private UserDataService userDataService;
	private static String currentUserID;
	ArrayList<UserPO> userPOs = new ArrayList<>();
	
	public User(){
		userDataService = UserRemoteHelper.getInstance().getUserDataService();
	}
	
	public ResultMessage login(String userID, String password) throws RemoteException{
		ResultMessage re = userDataService.login(userID, password);
		if(re==ResultMessage.SUCCESS){
			currentUserID = userID;
		}
		return re;
	}

	public ResultMessage addUser(UserVO vo) throws RemoteException{
		ResultMessage re = ResultMessage.FAILED;
		if(userDataService.find(vo.userID)==null){
			UserPO userPO = voTOpo(vo);
			re = userDataService.add(userPO);
		}
		return re;
	}

	public ResultMessage deleteUser(String userID) throws RemoteException{
		ResultMessage re = userDataService.delete(userID);
		return re;
	}

	public ResultMessage modifyUser(UserVO vo) throws RemoteException{
		userPOs = userDataService.show();
		for(UserPO userPO:userPOs){
			if(userPO.getUserID().equals(vo.userID)){
				userPO.setPassword(vo.password);
				userPO.setName(vo.name);
				userPO.setPosition(vo.position);
				userPO.setLimit(vo.limit);
				return userDataService.update(userPO);
			}
		}
		return ResultMessage.FAILED;
	}

	public ArrayList<UserVO> show() throws RemoteException{
		userPOs = userDataService.show();
		ArrayList<UserVO> userVOs = new ArrayList<>();
		for(UserPO po:userPOs){
			userVOs.add(poTOvo(po));
		}
		return userVOs;
	}
	
	public String getCurrentUserID(){
		return currentUserID;
	}

	public ArrayList<UserVO> findUsersByKeywords(String keywords) throws RemoteException{
		ArrayList<UserPO> resultPOs = userDataService.findUsersByKeyword(keywords);
		ArrayList<UserVO> resultVOs = new ArrayList<>();
		for(UserPO po:resultPOs){
			resultVOs.add(poTOvo(po));
		}
		return resultVOs;
	}

	public ArrayList<UserVO> findUsersByID(String UserID) throws RemoteException{
		ArrayList<UserPO> resultPOs = userDataService.findUsersByID(UserID);
		ArrayList<UserVO> resultVOs = new ArrayList<>();
		for(UserPO po:resultPOs){
			resultVOs.add(poTOvo(po));
		}
		return resultVOs;
	}
	
	public UserVO findUserByID(String UserID) throws RemoteException{
		UserPO user = userDataService.find(UserID);
		return poTOvo(user);
	}
	
	public ArrayList<UserVO> getAllSalesmen() throws RemoteException{
		userPOs = userDataService.show();
		ArrayList<UserVO> salesmen = new ArrayList<>();
		for(UserPO po:userPOs){
			if(po.getPosition() == UserPosition.SALES_STAFF){
				salesmen.add(poTOvo(po));
			}
		}
		return salesmen;
	}
	
	public UserPO voTOpo(UserVO userVO){
		return new UserPO(userVO.userID, userVO.password, userVO.name, userVO.position, userVO.limit);
	}
	
	public UserVO poTOvo(UserPO userPO){
		return new UserVO(String.valueOf(userPO.getUserID()), userPO.getPassword(), userPO.getName(), userPO.getPosition(), userPO.getLimit());
	}
}
