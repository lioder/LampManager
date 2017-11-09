package bl.examinationbl;

import java.util.ArrayList;
import java.util.Date;

import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.AccountBillItemVO;
import vo.AccountBillVO;
import vo.BillVO;

public class MockExamination extends Examination{

	private AccountBillVO accountBill = new AccountBillVO(new Date(), "XJFKD-20171021-00001", BillState.SUBMITTED, BillType.CASH, "ZLK", "Aster",new ArrayList<AccountBillItemVO>());
	
	@Override
	public ArrayList<BillVO> show(){
		ArrayList<BillVO> bill = new ArrayList<BillVO>();
		bill.add(accountBill);
		return bill;
	}
	
	@Override
	public BillVO checkReceipt(String billID){
		if(billID.equals(accountBill.ID)){
			return accountBill;
		}
		else{
			System.out.println("Bill doesn't exist");
			return null;
		}
	}
	
	@Override
	public ResultMessage modifyReceipt(BillVO bill){
		if(bill.ID.equals(accountBill.ID)){
			accountBill = (AccountBillVO)bill;
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}
	
	@Override
	public ResultMessage approveReceipt(BillVO bill){
		if(bill.ID.equals(accountBill.ID)){
			accountBill.state = BillState.PASS;
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}
	
	@Override
	public ArrayList<BillVO> showHistory() {
		ArrayList<BillVO> history = new ArrayList<BillVO>();
		history.add(accountBill);
		return history;
	}
	
}