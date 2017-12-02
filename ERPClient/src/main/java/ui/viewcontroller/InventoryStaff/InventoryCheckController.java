package ui.viewcontroller.InventoryStaff;

import blservice.inventoryblservice.InventoryBLService;
import blstubdriver.InventoryBLService_Stub;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.component.DialogFactory;
import ui.viewcontroller.FinancialStaff.FinancialSalesDetailsController;
import util.Money;
import util.ResultMessage;
import vo.GoodsVO;
import vo.InventoryCheckVO;

import java.util.HashMap;

/**
 * Created by Kry·L on 2017/11/27.
 */
public class InventoryCheckController {
    InventoryBLService inventoryBLService = new InventoryBLService_Stub();
    InventoryViewController inventoryViewController;
    InventoryCheckVO inventoryCheck;

    TableView<InventoryCheckBean> table;
    ObservableList<InventoryCheckBean> data = FXCollections.observableArrayList();
    int totalNum;
    double totalValue;
    double avgValue;

    @FXML
    Label TotalIcon;

    @FXML
    Label ValueIcon;

    @FXML
    Label AvgPriceIcon;

    @FXML
    Label TotalNum;

    @FXML
    Label TotalValue;

    @FXML
    Label AvgValue;

    @FXML
    ScrollPane TablePane;

    @FXML
    public void initialize(){
        TotalIcon.setText("\ue6e3");
        ValueIcon.setText("\ue605");
        AvgPriceIcon.setText("\ueb73");

        initTable();
        showInventoryCheck();
        TotalNum.setText("库存数量合计："+totalNum+"件");
        TotalValue.setText("库存总价值："+ Money.getMoneyString(totalValue));
        AvgValue.setText("库存均价："+Money.getMoneyString(avgValue));

    }
    public void initTable(){
        table = new TableView<>();
        table.setEditable(false);

        TableColumn lineColumn = new TableColumn("行号");
        lineColumn.setPrefWidth(67);
        lineColumn.setCellValueFactory(new PropertyValueFactory<>("line"));
        TableColumn IDColumn = new TableColumn("编号");
        IDColumn.setPrefWidth(150);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn nameColumn = new TableColumn("商品名称");
        nameColumn.setPrefWidth(160);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn modelColumn = new TableColumn("型号");
        modelColumn.setPrefWidth(150);
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        TableColumn amountColumn = new TableColumn("库存数量");
        amountColumn.setPrefWidth(80);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableColumn avgColumn = new TableColumn("商品均价");
        avgColumn.setPrefWidth(80);
        avgColumn.setCellValueFactory(new PropertyValueFactory<>("avg"));

        table.setItems(data);
        table.getColumns().addAll(lineColumn,IDColumn,nameColumn,modelColumn,amountColumn,avgColumn);
        TablePane.setContent(table);
    }
    public void clickExportButton(){
        ResultMessage re = inventoryBLService.exportExcel(inventoryCheck);
        if (re == ResultMessage.SUCCESS){
            Dialog alert = DialogFactory.getInformationAlert();
            alert.setHeaderText("导出成功");
            alert.show();
        }

    }
    public void showInventoryCheck(){
        inventoryCheck = inventoryBLService.check();
        int n = 1;
        data.clear();
        for (GoodsVO good : inventoryCheck.averagePrice.keySet()){
            double avg = inventoryCheck.averagePrice.get(good);
            data.add(new InventoryCheckBean(n++,good.ID,good.name,good.model,good.amount,avg));
            totalNum += good.amount;
            totalValue += good.amount * avg;
            avgValue += avg;
        }
    }
    public void setInventoryViewController(InventoryViewController inventoryViewController){
        this.inventoryViewController = inventoryViewController;
    }
    public class InventoryCheckBean {
        IntegerProperty line;
        StringProperty ID;
        StringProperty name;
        StringProperty model;
        IntegerProperty amount;
        DoubleProperty avg;

        public InventoryCheckBean(int line, String ID, String name, String model, int amount,double avg) {
            this.line = new SimpleIntegerProperty(line);
            this.ID = new SimpleStringProperty(ID);
            this.name = new SimpleStringProperty(name);
            this.model = new SimpleStringProperty(model);
            this.amount = new SimpleIntegerProperty(amount);
            this.avg = new SimpleDoubleProperty(avg);
        }

        public int getLine() {
            return line.get();
        }

        public IntegerProperty lineProperty() {
            return line;
        }

        public void setLine(int line) {
            this.line.set(line);
        }

        public String getID() {
            return ID.get();
        }

        public StringProperty IDProperty() {
            return ID;
        }

        public void setID(String ID) {
            this.ID.set(ID);
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getModel() {
            return model.get();
        }

        public StringProperty modelProperty() {
            return model;
        }

        public void setModel(String model) {
            this.model.set(model);
        }

        public int getAmount() {
            return amount.get();
        }

        public IntegerProperty amountProperty() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount.set(amount);
        }

        public double getAvg() {
            return avg.get();
        }

        public DoubleProperty avgProperty() {
            return avg;
        }

        public void setAvg(double avg) {
            this.avg.set(avg);
        }
    }
}
