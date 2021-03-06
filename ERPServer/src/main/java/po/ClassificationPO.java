package po;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
@Entity
@Table(name = "classification")
public class ClassificationPO implements Serializable {
	private static final long serialVersionUID = -7914858691592007808L;
	/**
	 * 商品分类ID
	 */
	private int ID;
	/**
	 * 商品分类名称
	 */
	private String name;
	/**
	 * 该商品分类的父分类
	 */
	private ClassificationPO father;
	/**
	 * 该商品分类的所有子分类
	 */
	private List<ClassificationPO> chidren;
	/**
	 * 该商品分类下的所有商品
	 */
	private List<GoodsPO> goods;
	
	public ClassificationPO(){ }

	public ClassificationPO(String name, ClassificationPO father, List<ClassificationPO> chidren, List<GoodsPO> goods) {
		super();
		this.name = name;
		this.father = father;
		this.chidren = chidren;
		this.goods = goods;
	}

	/**
	 * 请使用无需设置ID的构造方法，因为：<br>
	 * 1、要新增的PO的ID应由数据库自动生成，而非手动填入<br>
	 * 2、要修改的PO应从数据库中得到，而非代码生成
	 */
	@Deprecated
	public ClassificationPO(int ID, String name, ClassificationPO father, List<ClassificationPO> chidren, List<GoodsPO> goods) {
		super();
		this.ID = ID;
		this.name = name;
		this.father = father;
		this.chidren = chidren;
		this.goods = goods;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "claid")
	public ClassificationPO getFather() {
		return father;
	}

	public void setFather(ClassificationPO father) {
		this.father = father;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "claid")
	@Fetch(FetchMode.SUBSELECT)
	public List<ClassificationPO> getChidren() {
		return chidren;
	}

	public void setChidren(List<ClassificationPO> chidren) {
		this.chidren = chidren;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "claid")
	@Fetch(FetchMode.SUBSELECT)
	public List<GoodsPO> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsPO> goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "ID:" + String.format("%02d", ID) + ", 名称:" + name + ", 所属商品分类:" + father.getName();
	}
	
}
