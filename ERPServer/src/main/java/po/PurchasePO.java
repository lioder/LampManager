package po;

import java.sql.Date;
import java.util.ArrayList;

import util.BillState;
import util.BillType;
import util.UserPosition;

public class PurchasePO {
	/**单据创建时间*/
	public Date startDate;
	/**单据类型*/
	public BillType type;
	/**单据状态*/
	public BillState state;
	/**单据编号*/
	public String ID;
	/**供应商*/
	public String supplier;
	/**仓库*/
	public String inventory;
	/**操作员*/
	public UserPosition user;
	/**商品列表*/
	public ArrayList<GoodsItemPO> goodsItem;
	/**备注*/
	public String remarks;
	/**总额合计*/
	public double sum;
	/**单据最后修改时间*/
	public Date endDate;
	
	public PurchasePO(Date startDate,BillType type,BillState state,String ID,String supplier
			,String inventory,UserPosition user,ArrayList<GoodsItemPO> goodsItem,String remarks
			,double sum,Date endDate){
		this.startDate=startDate;
		this.type=type;
		this.state=state;
		this.ID=ID;
		this.supplier=supplier;
		this.inventory=inventory;
		this.user=user;
		this.goodsItem=goodsItem;
		this.sum=sum;
		this.remarks=remarks;
		this.endDate=endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public BillType getType() {
		return type;
	}

	public void setType(BillType type) {
		this.type = type;
	}

	public BillState getState() {
		return state;
	}

	public void setState(BillState state) {
		this.state = state;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public UserPosition getUser() {
		return user;
	}

	public void setUser(UserPosition user) {
		this.user = user;
	}

	public ArrayList<GoodsItemPO> getGoodsItem() {
		return goodsItem;
	}

	public void setGoodsItem(ArrayList<GoodsItemPO> goodsItem) {
		this.goodsItem = goodsItem;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}