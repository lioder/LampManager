package ui.viewcontroller.InventoryStaff;

import bean.InventoryCheckBean;
import bl.inventorybl.InventoryBLFactory;
import bl.inventorybl.InventoryController;
import blservice.inventoryblservice.InventoryBLService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.component.DialogFactory;
import ui.component.Table;
import util.Money;
import util.ResultMessage;
import vo.GoodsVO;
import vo.InventoryCheckVO;

import java.io.File;
import java.time.LocalDate;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Kry·L on 2017/11/27.
 */
public class  InventoryCheckController {
    InventoryBLService inventoryBLService = InventoryBLFactory.getBLService();
    InventoryViewController inventoryViewController;
    InventoryCheckVO inventoryCheck;
    private Executor executor;
    Table<InventoryCheckBean> table;

    int totalNum;
    double totalValue,avgValue;

    @FXML
    Label TotalIcon,ValueIcon,AvgPriceIcon,TotalNum,TotalValue,AvgValue;

    @FXML
    ScrollPane TablePane;

    @FXML
    public void initialize(){
        executor = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        TotalIcon.setText("\ue6e3");
        ValueIcon.setText("\ue605");
        AvgPriceIcon.setText("\ueb73");

        initTable();
        showInventoryCheck();


    }
    public void initTable(){
        table = new Table<>();
        table.setEditable(false);
        table.addColumn("行号","line",50);
        table.addColumn("商品编号","ID",150);
        table.addColumn("商品名称","name",150);
        table.addColumn("型号","model",150);
        table.addColumn("库存数量","amount",89);
        table.addColumn("商品均价","avg",100);
        TablePane.setContent(table.getTable());
    }
    public void clickExportButton(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("导出库存盘点");
        fileChooser.setInitialFileName("库存盘点"+ LocalDate.now().toString());
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel表格", "*.xlxs"));
        File f = fileChooser.showSaveDialog(new Stage());

        //处理保存文件时点击取消的case
        if (f == null){
            return ;
        }
        ResultMessage re = inventoryBLService.exportExcel(f.getParent(),f.getName(),inventoryCheck);
        if (re == ResultMessage.SUCCESS){
            Dialog alert = DialogFactory.getInformationAlert();
            alert.setHeaderText("导出成功");
            alert.show();
        }

    }
    public void showInventoryCheck(){
        Task<InventoryCheckVO> task = new Task<InventoryCheckVO>() {
            @Override
            protected InventoryCheckVO call() throws Exception {
                return inventoryBLService.check();
            }
        };

        task.setOnSucceeded(e -> {
            inventoryCheck = task.getValue();
            int n = 1;
            table.clear();
            for (GoodsVO good : inventoryCheck.averagePrice.keySet()){
                double avg = inventoryCheck.averagePrice.get(good);
                table.addRow(new InventoryCheckBean(n++,good.ID,good.name,good.model,good.amount,Money.getMoneyString(avg)));
                totalNum += good.amount;
                totalValue += good.amount * avg;
                avgValue += avg;
            }
            avgValue /= inventoryCheck.averagePrice.keySet().size();
            TotalNum.setText("库存数量合计："+totalNum+"件");
            TotalValue.setText("库存总价值："+ Money.getMoneyString(totalValue));
            AvgValue.setText("库存均价："+Money.getMoneyString(avgValue));
        });
        executor.execute(task);
    }
    public void setInventoryViewController(InventoryViewController inventoryViewController){
        this.inventoryViewController = inventoryViewController;
    }

}

