package bl.promotionbl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import util.Level;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;

public class MockPromotionCustomer extends PromotionCustomer{
	private PromotionCustomerVO promotionCustomer = new PromotionCustomerVO("会员促销策略","000002", LocalDate.now().toString(), LocalDate.now().toString(), 0, 0, new ArrayList<GoodsItemVO>(), Level.LEVEL_THREE);
	
	@Override
	public ArrayList<PromotionCustomerVO> show(){
		ArrayList<PromotionCustomerVO> promotionCustomerList = new ArrayList<PromotionCustomerVO>();
		promotionCustomerList.add(promotionCustomer);
		return promotionCustomerList;
	}

	@Override
	public ResultMessage submit(PromotionCustomerVO vo){
		return ResultMessage.SUCCESS;
	}
	
	@Override
	public PromotionCustomerVO findPromotionByID(String promotionID){
		if(promotionID.equals(promotionCustomer.promotionID)){
			return promotionCustomer;
		}
		else{
			System.out.println("Can't find promotion!");
			return null;
		}
	}
	
}
