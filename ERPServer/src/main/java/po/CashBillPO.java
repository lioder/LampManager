package po;

import util.BillState;
import util.BillType;
import util.Money;

import java.util.List;

import javax.persistence.*;

/**
 * Created by Kry·L on 2017/10/22.
 */
@Entity
@Table(name = "cashbill")
public class CashBillPO extends BillPO{
	private static final long serialVersionUID = -8095653272093963280L;

	/**
     * 操作员
     */
    private String userName;

    /**
     * 银行账户
     */
    private int accountID;

    /**
     * 条目清单
     */
    private List<CashBillItemPO> cashBillItemPOS;

    /**
     * 总额
     */
    private double sum;
    
    public CashBillPO(){ }

    public CashBillPO(String date, BillType type, BillState state, String userName, int accountID, List<CashBillItemPO> cashBillItemPOS, int turn) {
        super(date, type, state, turn);
        this.userName = userName;
        this.accountID = accountID;
        this.cashBillItemPOS = cashBillItemPOS;
        calSum();
    }
    
    public CashBillPO(String date, BillType type, BillState state, String userName, int accountID, List<CashBillItemPO> cashBillItemPOS, double sum, int turn) {
        super(date, type, state, turn);
        this.userName = userName;
        this.accountID = accountID;
        this.cashBillItemPOS = cashBillItemPOS;
        this.sum = sum;
        calSum();
    }

	/**
	 * 请使用无需设置ID的构造方法，因为：<br>
	 * 1、要新增的PO的ID应由数据库自动生成，而非手动填入<br>
	 * 2、要修改的PO应从数据库中得到，而非代码生成
	 */
	@Deprecated
    public CashBillPO(int ID, String date, BillType type, BillState state, String userName, int accountID, List<CashBillItemPO> cashBillItemPOS, double sum) {
        super(ID, date, type, state);
        this.userName = userName;
        this.accountID = accountID;
        this.cashBillItemPOS = cashBillItemPOS;
        this.sum = sum;
    }

	@Column(name = "username")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

	@Column(name = "accountid")
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cashbillid")
    public List<CashBillItemPO> getCashBillItemPOS() {
        return cashBillItemPOS;
    }

    public void setCashBillItemPOS(List<CashBillItemPO> cashBillItemPOS) {
        this.cashBillItemPOS = cashBillItemPOS;
    }

    @Column(name = "sum")
    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
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
	
    @Column(name = "turn")
    public int getTurn() {
		return super.getTurn();
	}

	public void setTurn(int turn) {
		super.setTurn(turn);
	}
	
	/**
	 * 计算当前总金额，同时更新sum变量
	 * @return 当前总金额
	 */
	public double calSum(){
        double sum = 0;
        for (int i = 0; i < cashBillItemPOS.size(); i++){
            sum += cashBillItemPOS.get(i).getMoney();
        }
        setSum(sum);
        return sum;
	}
    public String toString() {
        return "ID:" + super.buildID() + ", 类型:" + this.getType().getValue() + ", 状态:" + this.getState().getValue()
                + ", 银行账户ID:" + String.format("%02d",accountID) +", 操作员:" + userName + ", 总额:" + Money.getMoneyString(sum);
    }
}
