package dataimpl.promotiondataimpl;

import java.rmi.RemoteException;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.promotiondataservice.PromotionDataService;
import po.PromotionBargainPO;
import po.PromotionCustomerPO;
import po.PromotionTotalPO;
import util.PromotionType;
import util.ResultMessage;

public class PromotionDataServiceImpl implements PromotionDataService{
	private static PromotionDataServiceImpl promotionDataServiceImpl;
	private DataHelper<PromotionCustomerPO> promotionCustomerDataHelper;
	private DataHelper<PromotionBargainPO> promotionBargainDataHelper;
	private DataHelper<PromotionTotalPO> promotionTotalDataHelper;
	
	public static PromotionDataServiceImpl getInstance(){
		if(promotionDataServiceImpl == null){
			promotionDataServiceImpl = new PromotionDataServiceImpl();
		}
		return promotionDataServiceImpl;
	}
	
	private PromotionDataServiceImpl(){
		this.promotionCustomerDataHelper = new HibernateDataHelper<PromotionCustomerPO>(PromotionCustomerPO.class);
		this.promotionBargainDataHelper=new HibernateDataHelper<PromotionBargainPO>(PromotionBargainPO.class);
		this.promotionTotalDataHelper=new HibernateDataHelper<PromotionTotalPO>(PromotionTotalPO.class);
	}

	@Override
	public PromotionCustomerPO findPC(String ID) throws RemoteException {
		return promotionCustomerDataHelper.exactlyQuery("promotionID", ID);
	}

	@Override
	public PromotionBargainPO findPB(String ID) throws RemoteException {
		return promotionBargainDataHelper.exactlyQuery("promotionID", ID);
	}

	@Override
	public PromotionTotalPO findPT(String ID) throws RemoteException {
		return promotionTotalDataHelper.exactlyQuery("promotionID", ID);
	}

	public ResultMessage addPC(PromotionCustomerPO po) throws RemoteException {
		return promotionCustomerDataHelper.save(po);
	}
	
	public ResultMessage addPB(PromotionBargainPO po) throws RemoteException{
		return promotionBargainDataHelper.save(po);
	}
	
	public ResultMessage addPT(PromotionTotalPO po) throws RemoteException{
		return promotionTotalDataHelper.save(po);
	}

	@Override
	public ResultMessage deletePC(PromotionCustomerPO po) throws RemoteException {
		return promotionCustomerDataHelper.delete(po);
	}

	@Override
	public ResultMessage deletePB(PromotionBargainPO po) throws RemoteException {
		return promotionBargainDataHelper.delete(po);
	}

	@Override
	public ResultMessage deletePT(PromotionTotalPO po) throws RemoteException {
		return promotionTotalDataHelper.delete(po);
	}

	@Override
	public ResultMessage updatePC(PromotionCustomerPO po) throws RemoteException {
		return promotionCustomerDataHelper.update(po);
	}

	@Override
	public ResultMessage updatePB(PromotionBargainPO po) throws RemoteException {
		return promotionBargainDataHelper.update(po);
	}

	@Override
	public ResultMessage updatePT(PromotionTotalPO po) throws RemoteException {
		return promotionTotalDataHelper.update(po);
	}

}