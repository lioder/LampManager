package vo;

import java.util.Date;
import java.util.ArrayList;

import util.BillState;
import util.BillType;
import util.UserPosition;

public class SalesVO extends BillVO{
    private static final String seperator = System.lineSeparator();

    /**客户*/
	public String customer;
	/**客户编号*/
	public String customerID;
	/**业务员*/
	public String salesman;
	/**操作员*/
	public String user;
	/**仓库*/
	public String inventory;
	/**商品列表*/
	public ArrayList<GoodsItemVO> goodsItemList;
	/**折让前总额*/
	public double beforeSum;
	/**折让*/
	public double allowance;
	/**使用代金券金额*/
	public double voucher;
	/**折让后总额*/
	public double afterSum;
	/**备注*/
	public String remarks;
	/**促销策略名称*/
	public String promotionName;
	
	
	public SalesVO( BillType type, BillState state, String billID, String customer,String customerID, String salesman,
			String user, String inventory, ArrayList<GoodsItemVO> goodsItemList, double allowance,
			double voucher, String remarks, String endDate,String promotionName) {
		this.type = type;
		this.state = state;
		ID = billID;
		this.customerID=customerID;
		this.customer = customer;
		this.salesman = salesman;
		this.user = user;
		this.inventory = inventory;
		this.goodsItemList = goodsItemList;
		this.beforeSum = calBeforeSum();
		this.allowance = allowance;
		this.voucher = voucher;
		this.afterSum = calAfterSum();
		this.remarks = remarks;
		this.date = endDate;
		this.promotionName=promotionName;
	}
	
	private double calBeforeSum(){
		double sum=0;
		for(int i=0;i<goodsItemList.size();i++){
			sum+=goodsItemList.get(i).sum;
		}
		return sum;
	}
	
	private double calAfterSum(){
		double sum=0;
		for(int i=0;i<goodsItemList.size();i++){
			sum+=goodsItemList.get(i).sum;
		}
		sum=sum-allowance-voucher;
		return sum;
	}

	@Override
    public String toString(){
	    String str =  "客户：" + customer + seperator +
                "业务员: " + salesman + seperator +
                "操作员: " + user + seperator +
                "仓库: " + inventory + seperator +
                "折让前总额: " + beforeSum + seperator +
                "折让: " + allowance + seperator +
                "使用代金券金额: " + voucher + seperator +
                "折让后总额: " + afterSum + seperator +
                "备注：" + remarks + seperator +
                "商品列表：" + seperator;
	    for (GoodsItemVO itemVO : goodsItemList){
	        str += (itemVO.toString() + seperator);
        }
        return str;
    }
}
