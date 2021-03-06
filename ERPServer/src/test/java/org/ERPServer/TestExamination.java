package org.ERPServer;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

import dataimpl.examinationdataimpl.ExaminationDataServiceImpl;
import dataservice.examinationdataservice.ExaminationDataService;
import po.BillPO;

public class TestExamination {
	public static void main(String[] args){
		ExaminationDataService exaImpl=ExaminationDataServiceImpl.getInstance();
		
		try{
			ArrayList<BillPO> billList=exaImpl.show();
	
			System.out.println("共有"+billList.size()+"条记录");
			for(BillPO po:billList){
				System.out.println(po.getState()+" "+po.getType());
			}
			System.out.println("查询结束");
			
			ArrayList<BillPO> bill=exaImpl.finds(Date.valueOf("2017-12-01"), Date.valueOf("2017-12-02"));
			System.out.println("共有"+bill.size()+"条记录");
			for(BillPO po:bill){
				System.out.println(po.getState()+" "+po.getType());
			}
			System.out.println("查询结束");
			
		}catch(RemoteException e){
			System.out.println("Exception!");
			e.printStackTrace();
		}
	}
}
