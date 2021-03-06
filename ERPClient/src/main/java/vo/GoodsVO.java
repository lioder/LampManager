package vo;

/**
 * Created on 2017/10/21
 * 
 * @author 巽
 *
 */
public class GoodsVO {
	/**
	 * 商品ID
	 */
	public String ID;
	/**
	 * 商品名称
	 */
	public String name;
	/**
	 * 商品型号
	 */
	public String model;
	/**
	 * 商品所属分类名称
	 */
	public String classification;
	/**
	 * 商品数量
	 */
	public int amount;
	/**
	 * 商品警戒数量
	 */
	public int alarmAmount;
	/**
	 * 商品进价
	 */
	public double buyingPrice;
	/**
	 * 商品零售价
	 */
	public double retailPrice;
	/**
	 * 商品最近进价
	 */
	public double recentBuyingPrice;
	/**
	 * 商品最近零售价
	 */
	public double recentRetailPrice;

	public GoodsVO(String ID) {
		this.ID = ID;
	}

	public GoodsVO(String ID, String name, String model, String classification, int amount, int alarmAmount,
			double buyingPrice, double retailPrice, double recentBuyingPrice, double recentRetailPrice) {
		super();
		this.ID = ID;
		this.name = name;
		this.model = model;
		this.classification = classification;
		this.amount = amount;
		this.alarmAmount = alarmAmount;
		this.buyingPrice = buyingPrice;
		this.retailPrice = retailPrice;
		this.recentBuyingPrice = recentBuyingPrice;
		this.recentRetailPrice = recentRetailPrice;
	}

	/**
	 * 该方法仅限Stub和Mock使用<br>
	 * 请使用没有String inventory参数的构造方法，
	 */
	@Deprecated
	public GoodsVO(String ID, String name, String model, String classification, String inventory, int amount,
			int alarmAmount, double buyingPrice, double retailPrice, double recentBuyingPrice,
			double recentRetailPrice) {
		super();
		this.ID = ID;
		this.name = name;
		this.model = model;
		this.classification = classification;
		this.amount = amount;
		this.alarmAmount = alarmAmount;
		this.buyingPrice = buyingPrice;
		this.retailPrice = retailPrice;
		this.recentBuyingPrice = recentBuyingPrice;
		this.recentRetailPrice = recentRetailPrice;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GoodsVO) {
			GoodsVO object = (GoodsVO) obj;
			if (object.ID != null && this.ID != null) {
				return this.ID.equals(object.ID);
			}
			else {
				return super.equals(obj);
			}
		}
		else {
			return false;
		}
	}

}
