package bl.userbl;

import java.util.ArrayList;
import java.util.HashMap;

import blservice.userblservice.UserBLService;
import blservice.userblservice.UserInfo;
import util.ResultMessage;
import vo.UserVO;

public class UserController implements UserBLService, UserInfo{

	private User user;
	
	public UserController(){
		user = new User();
	}

	public ResultMessage login(String userID, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage addUser(UserVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage deleteUser(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage modifyUser(UserVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> getCurrentUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<UserVO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCurrentUserID() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCurrentUserNameByID(String UserID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<UserVO> show() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserVO> findUsersByID(String UserID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserVO> findUsersByKeywords(String keywords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO findUserByID(String UserID) {
		// TODO Auto-generated method stub
		return null;
	}
		
}
