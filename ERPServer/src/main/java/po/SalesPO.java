package po;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import util.BillState;
import util.BillType;

/**
 * created by zlk on 2017/10/20
 */

@Entity
@Table(name = "sales")
public class SalesPO extends BillPO{
	/**客户*/
	private String customer;
	/**客户ID*/
	private int customerID;
	/**业务员*/
	private String salesman;
	/**操作员*/
	private String user;
	/**仓库*/
	private String inventory;
	/**商品列表*/
	private List<GoodsItemPO> goodsItemList;
	/**折让前总额*/
	private double beforeSum;
	/**折让*/
	private double allowance;
	/**使用代金券金额*/
	private double voucher;
	/**折让后总额*/
	private double afterSum;
	/**备注*/
	private String remarks;
	/**促销策略编号*/
	private String promotionName;
	
	public SalesPO(){};
	
	public SalesPO( BillType type, BillState state, String customer,int customerID, String salesman,
			String user, String inventory,List<GoodsItemPO> goodsItemList,
			double allowance, double voucher, String remarks, String endDate,int turn,String promotionName) {
		super( endDate, type, state,turn);
		super.setType(type);
		super.setState(state);
		this.customer = customer;
		this.customerID=customerID;
		this.salesman = salesman;
		this.user = user;
		this.inventory = inventory;
		this.goodsItemList = goodsItemList;
		this.beforeSum = calBeforeSum();
		this.allowance = allowance;
		this.voucher = voucher;
		this.afterSum = calAfterSum();
		this.remarks = remarks;
		super.setDate(endDate);
		this.promotionName=promotionName;
	}
	
	@Deprecated
	public SalesPO( BillType type, BillState state,int ID, String customer,int customerID, String salesman,
			String user, String inventory,List<GoodsItemPO> goodsItemList,
			double allowance, double voucher, String remarks, String endDate) {
		super(ID, endDate, type, state);
		super.setID(ID);
		super.setType(type);
		super.setState(state);
		this.customer = customer;
		this.customerID=customerID;
		this.salesman = salesman;
		this.user = user;
		this.inventory = inventory;
		this.goodsItemList = goodsItemList;
		this.beforeSum = calBeforeSum();
		this.allowance = allowance;
		this.voucher = voucher;
		this.afterSum = calAfterSum();
		this.remarks = remarks;
		super.setDate(endDate);
	}
	
	private double calBeforeSum(){
		double sum=0;
		for(int i=0;i<goodsItemList.size();i++){
			sum+=goodsItemList.get(i).getSum();
		}
		return sum;
	}
	
	private double calAfterSum(){
		double sum=0;
		for(int i=0;i<goodsItemList.size();i++){
			sum+=goodsItemList.get(i).getSum();
		}
		sum=sum-allowance-voucher;
		return sum;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getID() {
		return super.getID();
	}

	public void setID(int iD) {
		super.setID(iD);
	}

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	public BillType getType() {
		return super.getType();
	}

	public void setType(BillType type) {
		super.setType(type);
	}

	@Column(name="state")
	@Enumerated(EnumType.STRING)
	public BillState getState() {
		return super.getState();
	}

	public void setState(BillState state) {
		super.setState(state);
	}

	@Column(name = "customer")
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	@Column(name = "customerID")
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	@Column(name = "salesman")
	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	@Column(name = "user")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Column(name = "inventory")
	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "sales")    
	public List<GoodsItemPO> getGoodsItemList() {
		return goodsItemList;
	}

	public void setGoodsItemList(List<GoodsItemPO> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}

	@Column(name = "beforeSum")
	public double getBeforeSum() {
		return beforeSum;
	}

	public void setBeforeSum(double beforeSum) {
		this.beforeSum = beforeSum;
	}

	@Column(name = "allowance")
	public double getAllowance() {
		return allowance;
	}

	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}

	@Column(name = "voucher")
	public double getVoucher() {
		return voucher;
	}

	public void setVoucher(double voucher) {
		this.voucher = voucher;
	}

	@Column(name = "afterSum")
	public double getAfterSum() {
		return afterSum;
	}

	public void setAfterSum(double afterSum) {
		this.afterSum = afterSum;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "date")
	public String getDate() {
		return super.getDate();
	}

	public void setDate(String date) {
		super.setDate(date);
	}
	
	@Column(name = "turn")
    public int getTurn() {
		return super.getTurn();
	}

	public void setTurn(int turn) {
		super.setTurn(turn);
	}

	@Column(name="prommotionName")
	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	
	@Override
	public String toString() {
		return "ID:" + super.buildID() + ", 类型:" + this.getType().getValue() + ", 状态:" + this.getState().getValue()
				+ ", 操作员:" + user + ", 仓库:" + inventory;
	}
	
}
