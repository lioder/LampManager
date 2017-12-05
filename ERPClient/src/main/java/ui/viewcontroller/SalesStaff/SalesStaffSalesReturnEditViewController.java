package ui.viewcontroller.SalesStaff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import bean.GoodsItemBean;
import blservice.salesblservice.SalesBLService;
import blservice.userblservice.UserBLService;
import blstubdriver.SalesBLService_Stub;
import blstubdriver.UserBLService_Stub;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;
import ui.component.DialogFactory;
import ui.component.GoodsSelecter;
import ui.component.GoodsTable.GoodsBean;
import util.BillState;
import util.BillType;
import util.Money;
import vo.CustomerVO;
import vo.GoodsItemVO;
import vo.PromotionVO;
import vo.SalesVO;

public class SalesStaffSalesReturnEditViewController {
	SalesStaffSalesReturnOrderViewController salesStaffSalesReturnOrderViewController;
	
	SalesBLService salesBLService = new SalesBLService_Stub();
	UserBLService userBLService = new UserBLService_Stub();
	ArrayList<GoodsItemVO> goodsItemList = new ArrayList<GoodsItemVO>();
	ArrayList<CustomerVO> customers = new ArrayList<CustomerVO>();
	ArrayList<String> inventories = new ArrayList<String>();
	ArrayList<PromotionVO> promotions = new ArrayList<PromotionVO>();
	
	TableView<GoodsItemBean> itemTable;
    ObservableList<GoodsItemBean> data =
            FXCollections.observableArrayList();
    DoubleProperty total = new SimpleDoubleProperty(0);
    
    @FXML
    Label deleteIcon;
    
    @FXML
    Label addIcon;

    @FXML
    Label BillID;

    @FXML
    Text Username;
    
    @FXML
    Text Salesman;

    @FXML
    VBox vbox;

    @FXML
    Text Total;
    
    @FXML
    JFXTextArea remark;

    @FXML
    JFXComboBox inventory;
    
    @FXML
    JFXComboBox customer;
    
    public void initialize(){
    	deleteIcon.setText("\ue606");
        addIcon.setText("\ue61e");
        String name = userBLService.findUserByID(userBLService.getCurrentUserID()).name;
        Username.setText(name);
        customers = salesBLService.getAllCustomer();
        inventories = salesBLService.getAllInventory();

        //初始化supplier选择框
        ArrayList<String> customerNames = new ArrayList<>();
        for (CustomerVO temp : customers){
            customerNames.add(temp.customerName);
        }
        customer.getItems().addAll(customerNames);
        
        //初始化inventory选择框
        inventory.getItems().addAll(inventories);

        //初始化表格
        itemTable = new TableView<>();
        itemTable.setEditable(true);

        TableColumn IDColumn = new TableColumn("商品编号");
        IDColumn.setPrefWidth(70);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn nameColumn = new TableColumn("条目名");
        nameColumn.setPrefWidth(60);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn modelColumn = new TableColumn("型号");
        modelColumn.setPrefWidth(60);
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        TableColumn<GoodsItemBean, Integer> amountColumn = new TableColumn("数量");
        amountColumn.setPrefWidth(60);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableColumn retailPriceColumn = new TableColumn("单价");
        retailPriceColumn.setPrefWidth(60);
        retailPriceColumn.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        TableColumn totalPriceColumn = new TableColumn("总价");
        totalPriceColumn.setPrefWidth(60);
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        TableColumn<GoodsItemBean, String> remarkColumn = new TableColumn("备注");
        remarkColumn.setPrefWidth(78);
        remarkColumn.setCellValueFactory(new PropertyValueFactory<>("remark"));

        amountColumn.setCellFactory(TextFieldTableCell.<GoodsItemBean, Integer>forTableColumn(new IntegerStringConverter()));
        amountColumn.setOnEditCommit(
        		(CellEditEvent<GoodsItemBean, Integer> t)->{
        			((GoodsItemBean) t.getTableView().getItems().get(
        					t.getTablePosition().getRow())
        					).setAmount(t.getNewValue());
        			
        			((GoodsItemBean) t.getTableView().getItems().get(
        					t.getTablePosition().getRow())
        					).setTotalPrice(t.getNewValue() 
        							* ((GoodsItemBean) t.getTableView().getItems().get(
        		        					t.getTablePosition().getRow())
        		        					).getRetailPrice()
        							);
        			total.set(total.get()+((GoodsItemBean) t.getTableView().getItems().get(
        					t.getTablePosition().getRow())
        					).getTotalPrice());
        		});
        
        remarkColumn.setCellFactory(TextFieldTableCell.<GoodsItemBean>forTableColumn());
        remarkColumn.setOnEditCommit(
        		(CellEditEvent<GoodsItemBean, String> t)->{
        			((GoodsItemBean) t.getTableView().getItems().get(
        					t.getTablePosition().getRow())
        					).setRemark(t.getNewValue());
        		});
        
        itemTable.setItems(data);
        itemTable.getColumns().addAll(IDColumn, nameColumn, modelColumn, amountColumn, retailPriceColumn, totalPriceColumn, remarkColumn);
        vbox.getChildren().add(itemTable);

        //折让前总额Text与商品总额金额之和绑定
        total.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Total.setText(Money.getMoneyString(total.get()));
            }
        });
    }
    
    public void addSalesReturnOrder() {
        String ID = salesBLService.getnewSalesReturnID();
        BillID.setText(ID);
    }
    
    public void clickDeleteButton(){
    	int index = itemTable.getSelectionModel().getSelectedIndex();
    	total.set(total.get()-data.get(index).getTotalPrice());
    	data.remove(index);
    }

    public void clickAddButton(){
    	GoodsSelecter selecter = new GoodsSelecter();
    	Dialog dialog = selecter.getGoodsDialog();
    	Optional<GoodsBean> result = dialog.showAndWait();
    	
    	GoodsBean bean = null;
    	if (result.isPresent()){
    		bean = result.get();
    	}
    	data.add(new GoodsItemBean(bean.getID(), bean.getName(), bean.getModel(), 0, bean.getRecentPurchasePrice(), 0,""));
    }

    public void clickSubmitButton(){
        CustomerVO customerVO = customers.get(customer.getSelectionModel().getSelectedIndex());
        String inventoryName = inventories.get(inventory.getSelectionModel().getSelectedIndex());
        SalesVO salesVO = new SalesVO(BillType.SALESRETURN, BillState.SUBMITTED, BillID.getText(), customerVO.customerName, customerVO.customerID, 
        		customerVO.salesman, Username.getText(), inventoryName, goodsItemList, 0, 0,remark.getText(),LocalDate.now().toString());
        salesBLService.submitSales(salesVO);
        salesStaffSalesReturnOrderViewController.showSalesReturnOrderList();
    }
    
    public void clickCancelButton(){
        Dialog dialog = DialogFactory.getConfirmationAlert();
        dialog.setHeaderText("需要保存为草稿吗？");
        Optional result = dialog.showAndWait();


        if (result.isPresent()){
            if (result.get() == ButtonType.OK) {
            	String customerName = "";
                String inventoryName = "";
                String customerID = "";
                String customerSalesman = "";
                if (customer.getSelectionModel().getSelectedIndex() >= 0){
                    customerName = customers.get(customer.getSelectionModel().getSelectedIndex()).customerName;
                    customerID = customers.get(customer.getSelectionModel().getSelectedIndex()).customerID;
                    customerSalesman = customers.get(customer.getSelectionModel().getSelectedIndex()).salesman;
                }
                if (inventory.getSelectionModel().getSelectedIndex() >= 0){
                	inventoryName = inventories.get(inventory.getSelectionModel().getSelectedIndex());
                }
                SalesVO salesVO = new SalesVO(BillType.SALESRETURN, BillState.DRAFT, BillID.getText(), customerName, customerID, customerSalesman,
                		Username.getText(), inventoryName, goodsItemList, 0, 0,remark.getText(), LocalDate.now().toString());
                salesBLService.saveSales(salesVO);
            }

            salesStaffSalesReturnOrderViewController.showSalesReturnOrderList();
        }
    }


    public void setSalesStaffSalesReturnOrderViewController(SalesStaffSalesReturnOrderViewController salesStaffSalesReturnOrderViewController){
        this.salesStaffSalesReturnOrderViewController = salesStaffSalesReturnOrderViewController;
    }
}