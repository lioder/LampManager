package bl.salesbl;

import java.util.ArrayList;
import java.util.Date;

import po.GoodsItemPO;
import po.SalesPO;
import util.BillState;
import util.BillType;
import vo.GoodsItemVO;
import vo.SalesVO;

public class MockSalesList {
	ArrayList<SalesVO> salesBill=new ArrayList<SalesVO>();
	ArrayList<GoodsItemVO> goodsItemList=new ArrayList<GoodsItemVO>();
	
	GoodsItemVO gi1=new GoodsItemVO( "霓虹灯", 20, 35.0,
			"耐用");
	GoodsItemVO gi2=new GoodsItemVO( "挂灯", 10, 35.0,
			"好看");
	
	{
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
		SalesVO s1=new SalesVO(BillType.SALES, BillState.DRAFT, "XSD-20171022-00001", "销售商1", "业务员1",
				"阿强", "默认仓库",goodsItemList , 100,500,  "满足客户需求", new Date());
	    SalesVO s2=new SalesVO(BillType.SALES, BillState.FAILED, "XSTHD-20171022-00002", "销售商2", "业务员2",
					"阿奇", "默认仓库",goodsItemList , 100,500, "满足客户需求", new Date());
	    salesBill.add(s1);
	    salesBill.add(s2);
	}
	
	public ArrayList<SalesVO> showSales(){
		return salesBill;
	}
}