package po;

import util.BillState;
import util.BillType;
import util.Money;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Created by Kry·L on 2017/10/22.
 */
@Entity
@Table(name = "accountbill")
public class AccountBillPO extends BillPO{
	private static final long serialVersionUID = -5634919115638049374L;

	/**
     * 客户的ID
     */
    private int customerID;

    /**
     * 操作员
     */
    private String userName;

    /**
     * 转账列表
     */
    private List<AccountBillItemPO> accountBillItemPOS;

    /**
     * 总额汇总
     */
    private double sum;

	public AccountBillPO(){ }

    public AccountBillPO(String date, BillType type, BillState state, int customerID, String userName, ArrayList<AccountBillItemPO> accountBillItemPOS, int turn) {
        super(date, type, state, turn);
        this.customerID = customerID;
        this.userName = userName;
        this.accountBillItemPOS = accountBillItemPOS;
        calSum();
    }

	/**
	 * 请使用无需设置ID的构造方法，因为：<br>
	 * 1、要新增的PO的ID应由数据库自动生成，而非手动填入<br>
	 * 2、要修改的PO应从数据库中得到，而非代码生成
	 */
    @Deprecated
    public AccountBillPO(int ID, String date, BillType type, BillState state, int customerID, String userName, ArrayList<AccountBillItemPO> accountBillItemPOS) {
        super(ID, date, type, state);
        this.customerID = customerID;
        this.userName = userName;
        this.accountBillItemPOS = accountBillItemPOS;
        calSum();
    }
    
    @Column(name = "date")
    public String getDate() {
		return super.getDate();
	}
    
	public void setDate(String date) {
		super.setDate(date);
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

    @Column(name = "state")
	@Enumerated(EnumType.STRING)
	public BillState getState() {
		return super.getState();
	}
	
	public void setState(BillState state) {
		super.setState(state);
	}

    @Column(name = "type")
	@Enumerated(EnumType.STRING)
	public BillType getType() {
		return super.getType();
	}
	
	public void setType(BillType type) {
		super.setType(type);
	}

    @Column(name = "customerid")
	public int getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	/**
	 * 计算当前总金额，同时更新sum变量
	 * @return 当前总金额
	 */
	public double calSum(){
        double sum = 0;
        for (int i = 0; i < accountBillItemPOS.size(); i++){
            sum += accountBillItemPOS.get(i).getMoney();
        }
        setSum(sum);
        return sum;
    }

    @Column(name = "name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "accountbillid")
    public List<AccountBillItemPO> getAccountBillItemPOS() {
        return accountBillItemPOS;
    }

    public void setAccountBillItemPOS(List<AccountBillItemPO> accountBillItemPOS) {
        this.accountBillItemPOS = accountBillItemPOS;
    }

    @Column(name = "sum")
    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Column(name = "turn")
    public int getTurn() {
		return super.getTurn();
	}

	public void setTurn(int turn) {
		super.setTurn(turn);
	}
    @Override
    public String toString() {
        return "ID:" + super.buildID() + ", 类型:" + this.getType().getValue() + ", 状态:" + this.getState().getValue()
                + ", 客户ID:" + String.format("%08d",customerID) +", 操作员:" + userName + ", 总额:" + Money.getMoneyString(sum);
    }
}
