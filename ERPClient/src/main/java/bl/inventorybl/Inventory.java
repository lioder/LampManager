package bl.inventorybl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ExcelUtil.enums.ExcelType;
import ExcelUtil.impl.ExportToExcel;
import ExcelUtil.model.Model;
import bl.goodsbl.GoodsBLFactory;
import bl.initializationbl.InitializationBLFactory;
import bl.logbl.LogBLFactory;
import blservice.goodsblservice.GoodsInfo;
import blservice.initializationblservice.InitInfo;
import blservice.logblservice.LogInfo;
import dataservice.inventorydataservice.InventoryDataService;
import po.GoodsPO;
import po.InventoryPO;
import rmi.InventoryRemoteHelper;
import util.BillState;
import util.BillType;
import util.OperationObjectType;
import util.OperationType;
import util.ResultMessage;
import vo.AlarmVO;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.InventoryBillVO;
import vo.InventoryCheckItemVO;
import vo.InventoryCheckVO;
import vo.InventoryViewVO;

/**
 * Created on 2017/11/5
 * 
 * @author 巽
 *
 */
public class Inventory {
	private InventoryDataService inventoryDataService;
	private InventoryList inventoryList;
	private InventoryBill inventoryBill;
	private GoodsInfo goodsInfo;
	private InitInfo initInfo;
	private LogInfo logInfo;

	protected Inventory() {
		inventoryDataService = InventoryRemoteHelper.getInstance().getInventoryDataService();
		inventoryBill = new InventoryBill();
		inventoryList = new InventoryList();
		initInfo = InitializationBLFactory.getInfo();
		goodsInfo = GoodsBLFactory.getInfo();
		logInfo = LogBLFactory.getInfo();
	}

	public ArrayList<String> showInventory() throws RemoteException {
		ArrayList<InventoryPO> pos = inventoryDataService.showInventory();
		ArrayList<String> ret = new ArrayList<>();
		for (InventoryPO po : pos) {
			ret.add(po.getName());
		}
		return ret;
	}

	public InventoryViewVO show(String startDate, String endDate, String inventory) throws RemoteException {
		InventoryPO inventoryPO = inventoryDataService.findInventoryByName(inventory);
		ArrayList<InventoryBillVO> inventoryBillVOs = inventoryBill.getPassBillsByDateAndInventory(startDate, endDate,
				inventoryPO);
		return inventoryList.show(startDate, endDate, inventory, inventoryBillVOs);
	}

	public InventoryCheckVO check() {
		LocalDate date = LocalDate.now();
		ArrayList<GoodsVO> goods = goodsInfo.getAllGoods();
		HashMap<GoodsVO, Double> averagePrice = new HashMap<>();
		for (GoodsVO vo : goods) {
			averagePrice.put(vo, vo.buyingPrice);
		}
		InventoryCheckVO check = new InventoryCheckVO(date.toString(), averagePrice);
		return check;
	}

	public ResultMessage exportExcel(String filePath, String fileName, InventoryCheckVO vo) {
		fileName = fileName.split("\\.")[0];
		ArrayList<InventoryCheckItemVO> items = new ArrayList<>();
		HashMap<GoodsVO, Double> map = vo.averagePrice;
		for (GoodsVO goodsVO : map.keySet()) {
			double averagePrice = map.get(goodsVO);
			items.add(new InventoryCheckItemVO(goodsVO.ID, goodsVO.name, goodsVO.model, goodsVO.amount, averagePrice));
		}
		ExportToExcel exporter = new ExportToExcel.Builder(filePath, fileName, ExcelType.XLSX)
				.withModel(Model.of(InventoryCheckItemVO.class, items)).build();
		if (exporter.export()) {
			return ResultMessage.SUCCESS;
		}
		else {
			return ResultMessage.FAILED;
		}
	}

	public ArrayList<InventoryBillVO> showBills() throws RemoteException {
		return inventoryBill.show();
	}

	public ArrayList<AlarmVO> getAlarmByInventory(String inventory) throws RemoteException {
		ArrayList<AlarmVO> ret = new ArrayList<>();
		InventoryPO inventoryPO = inventoryDataService.findInventoryByName(inventory);
		Map<GoodsPO, Integer> map = inventoryPO.getNumber();
		for (GoodsPO goodsPO : map.keySet()) {
			int alarmAmount = goodsPO.getAlarmAmount();
			int number = map.get(goodsPO);
			if (number < alarmAmount) {
				int numberSuggestAdding = 2 * alarmAmount - number; // intelligent recommendation
				ret.add(new AlarmVO(goodsPO.buildID(), goodsPO.getName(), goodsPO.getModel(), number, alarmAmount,
						numberSuggestAdding));
			}
		}
		return ret;
	}

	public ArrayList<InventoryBillVO> findBillByStateAndType(BillType type, BillState state) throws RemoteException {
		return inventoryBill.findByStateAndType(type, state);
	}

	public ResultMessage addInventory(String inventory) throws RemoteException {
		if (inventory.isEmpty()) {
			return ResultMessage.NULL;
		}
		InventoryPO repeated = inventoryDataService.findInventoryByName(inventory);
		if (repeated != null) {
			return ResultMessage.EXIST;
		}
		InventoryPO toAdd = new InventoryPO(inventory);
		ResultMessage ret = inventoryDataService.addInventroy(toAdd);
		if (ret == ResultMessage.SUCCESS) {
			logInfo.record(OperationType.ADD, OperationObjectType.INVENTORY, toAdd.toString());
		}
		return ret;
	}

	public ResultMessage addBill(InventoryBillVO vo) throws RemoteException {
		return inventoryBill.add(vo);
	}

	public ResultMessage deleteInventory(String inventory) throws RemoteException {
		InventoryPO found = inventoryDataService.findInventoryByName(inventory);
		if (found == null) {
			return ResultMessage.NOT_EXIST;
		}
		else {
			ResultMessage ret = inventoryDataService.deleteInventory(found);
			if (ret == ResultMessage.SUCCESS) {
				logInfo.record(OperationType.DELETE, OperationObjectType.INVENTORY, found.toString());
			}
			return ret;
		}
	}

	public ResultMessage deleteBill(String ID) throws RemoteException {
		return inventoryBill.delete(ID);
	}

	public ResultMessage updateInventory(String before, String after) throws RemoteException {
		if (after.isEmpty()) {
			return ResultMessage.NULL;
		}
		InventoryPO found = inventoryDataService.findInventoryByName(before);
		if (found == null) {
			return ResultMessage.NOT_EXIST;
		}
		else {
			found.setName(after);
			ResultMessage ret = inventoryDataService.updateInventory(found);
			if (ret == ResultMessage.SUCCESS) {
				logInfo.record(OperationType.UPDATE, OperationObjectType.INVENTORY, found.toString());
			}
			return ret;
		}
	}

	public ResultMessage updateBill(InventoryBillVO vo) throws NumberFormatException, RemoteException {
		return inventoryBill.update(vo);
	}

	public InventoryBillVO showBillDetails(String ID) throws NumberFormatException, RemoteException {
		return inventoryBill.showDetails(ID);
	}

	/**
	 * 约定：和addBill方法逻辑相同（仅在使用情境和语义上有所区别）
	 */
	public ResultMessage submitBill(InventoryBillVO vo) throws RemoteException {
		return inventoryBill.submit(vo);
	}

	public ArrayList<InventoryBillVO> findBillByType(BillType type) throws RemoteException {
		return inventoryBill.findByType(type);
	}

	public String getNewBillIDByType(BillType type) throws RemoteException {
		return inventoryBill.getNewIDByType(type);
	}

	public ArrayList<InventoryBillVO> getInventoryBillsByDate(String startDate, String endDate) throws RemoteException {
		return inventoryBill.getPassBillsByDate(startDate, endDate);
	}

	protected InventoryPO getInventoryByName(String name) throws RemoteException {
		return inventoryDataService.findInventoryByName(name);
	}

	public ResultMessage raiseInventory(ArrayList<GoodsItemVO> goodsItems, String inventory) throws RemoteException {
		return this.changeInventory(goodsItems, inventory, 1);
	}

	public ResultMessage reduceInventory(ArrayList<GoodsItemVO> goodsItems, String inventory) throws RemoteException {
		return this.changeInventory(goodsItems, inventory, -1);
	}

	public ResultMessage examine(InventoryBillVO vo) throws RemoteException {
		return inventoryBill.examine(vo);
	}

	public ArrayList<InventoryBillVO> getAllSubmittedInventoryBill() throws RemoteException {
		return inventoryBill.getAllSubmittedBill();
	}

	public ResultMessage redCover(InventoryBillVO vo) throws RemoteException {
		return inventoryBill.redCover(vo);
	}

	public ResultMessage redCoverAndCopy(InventoryBillVO vo) throws RemoteException {
		return inventoryBill.redCoverAndCopy(vo);
	}

	public String getStartDate() {
		return initInfo.getStartDate();
	}

	/**
	 * 改变库存（增加或减少）
	 * 
	 * @param sign 符号（增加：1，减少：-1）
	 */
	private ResultMessage changeInventory(ArrayList<GoodsItemVO> goodsItems, String inventory, int sign)
			throws RemoteException {
		InventoryPO inventoryPO = inventoryDataService.findInventoryByName(inventory);
		if (inventoryPO == null) {
			return ResultMessage.NOT_EXIST;
		}
		else {
			Map<GoodsPO, Integer> map = inventoryPO.getNumber();
			for (GoodsItemVO itemVO : goodsItems) {
				boolean isExistent = false;
				for (GoodsPO goods : map.keySet()) {
					if (goods.buildID().equals(itemVO.ID)) {
						int number = map.get(goods) + sign * itemVO.number;
						if (number < 0) { // 负数检查
							return ResultMessage.INSUFFICIENT;
						}
						map.put(goods, number);
						isExistent = true;
						break;
					}
				}
				if (!isExistent) { // 如果本来仓库里没有这种商品
					if (sign == -1) { // 负数检查
						return ResultMessage.INSUFFICIENT;
					}
					GoodsPO goodsPO = goodsInfo.getGoodsByID(itemVO.ID);
					map.put(goodsPO, itemVO.number);
				}
			}
			inventoryPO.setNumber(map);
			return inventoryDataService.updateInventory(inventoryPO);
		}
	}

}
