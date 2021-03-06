package dataservice.goodsdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsPO;
import util.Criterion;
import util.ResultMessage;
/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public interface GoodsDataService extends Remote{
	/**
	 * 得到所有商品
	 * @return 所有商品的PO
	 * @throws RemoteException
	 */
	public ArrayList<GoodsPO> show() throws RemoteException;
	/**
	 * 查找商品
	 * @param ID 商品ID
	 * @return 找到的商品PO
	 * @throws RemoteException
	 */
	public GoodsPO find(String ID) throws RemoteException;
	/**
	 * 添加商品
	 * @param po 待添加的商品PO
	 * @return 是否添加成功
	 * @throws RemoteException
	 */
	public ResultMessage add(GoodsPO po) throws RemoteException;
	/**
	 * 删除商品
	 * @param po 待删除的商品PO
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public ResultMessage delete(GoodsPO po) throws RemoteException;
	/**
	 * 修改商品
	 * @param po 已修改的商品PO
	 * @return 是否修改成功
	 * @throws RemoteException
	 */
	public ResultMessage update(GoodsPO po) throws RemoteException;
	/**
	 * 获取新建商品时自动生成的ID
	 * @return 自动生成的ID
	 * @throws RemoteException
	 */
	public String getNewID(String classificationID) throws RemoteException;
	/**
	 * 自定义高级查询（详见Criterion）
	 * @param criteria 条件列表
	 * @return 符合条件的商品PO的列表
	 * @throws RemoteException
	 * @see Criterion
	 */
	public ArrayList<GoodsPO> advancedQuery(ArrayList<Criterion> criteria) throws RemoteException;
}
