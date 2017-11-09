package blservice.promotionblservice.promotionbargain;

import java.util.ArrayList;
import java.util.Date;

import util.ResultMessage;
import vo.GoodsVO;
import vo.PromotionBargainVO;

/** 
 * Created by Aster on 2017/10/20
 */
public interface PromotionBargainBLService {
	/**
     * 显示所有特价包策略
     * 
     * @return ArrayList<PromotionBargainVO>
     */
	public ArrayList<PromotionBargainVO> show();
	
	/**
     * 添加商品到特价包中
     * 
     * @param GoodsVO
     */
	public void addBargain(GoodsVO vo);
	
	/**
     * 设置特价包价格
     * 
     * @param price
     */
	public void setPrice(double price);
	
	/**
     * 设置促销策略开始时间
     * 
     * @param date
     */
	public void setStartDate(Date date);
	
	/**
     * 设置促销策略结束时间
     * 
     * @param date
     */
	public void setEndDate(Date date);
	
	/**
     * 提交促销策略
     * 
     * @param PromotionCustomerVO
     * @return ResultMessage
     */
	public ResultMessage submit(PromotionBargainVO vo);
	
	public PromotionBargainVO findPromotionByID(String promotionID);
}