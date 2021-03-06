package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataimpl.goodsdataimpl.GoodsDataServiceImpl;
import dataservice.goodsdataservice.GoodsDataService;
import po.GoodsPO;
import util.Criterion;
import util.ResultMessage;

/**
 * Created on 2017/12/4
 * @author 巽
 *
 */
public class GoodsDataRemoteObject extends UnicastRemoteObject implements GoodsDataService{
	private static final long serialVersionUID = -7221832801019526440L;
	GoodsDataService goodsDataService;

	protected GoodsDataRemoteObject() throws RemoteException {
		super();
		goodsDataService = GoodsDataServiceImpl.getInstance();
	}

	@Override
	public ArrayList<GoodsPO> show() throws RemoteException {
		return goodsDataService.show();
	}

	@Override
	public GoodsPO find(String ID) throws RemoteException {
		return goodsDataService.find(ID);
	}

	@Override
	public ResultMessage add(GoodsPO po) throws RemoteException {
		return goodsDataService.add(po);
	}

	@Override
	public ResultMessage delete(GoodsPO po) throws RemoteException {
		return goodsDataService.delete(po);
	}

	@Override
	public ResultMessage update(GoodsPO po) throws RemoteException {
		return goodsDataService.update(po);
	}

	@Override
	public String getNewID(String classificationID) throws RemoteException {
		return goodsDataService.getNewID(classificationID);
	}

	@Override
	public ArrayList<GoodsPO> advancedQuery(ArrayList<Criterion> criteria) throws RemoteException {
		return goodsDataService.advancedQuery(criteria);
	}
	
}
