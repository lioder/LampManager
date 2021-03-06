package ui.viewcontroller.FinancialStaff;

import bl.financialbl.FinanceBLFactory;
import bl.financialbl.FinanceController;
import blservice.financeblservice.FinanceBLService;
import blstubdriver.FinanceBLService_Stub;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import ui.component.BillPane;
import ui.viewcontroller.common.BillController;
import util.BillState;
import util.ResultMessage;
import vo.AccountBillVO;
import vo.AccountVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/25.
 */

public class FinancialPaymentController {


    @FXML
    Label addIcon;

    @FXML
    VBox vBox;

    FinancialViewController financialViewController;
    FinancialPaymentEditController financialPaymentEditController;

    FinanceBLService financeBLService = FinanceBLFactory.getBLService();

    ArrayList<AccountBillVO> draft;
    ArrayList<AccountBillVO> submitted;
    ArrayList<AccountBillVO> pass;
    ArrayList<AccountBillVO> failed;

    ArrayList<VBox> billNodes = new ArrayList<>();
    ArrayList<FXMLLoader> fxmlLoaders = new ArrayList<>();
    BillPane billPane;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");

        draft = financeBLService.getPaymentsByState(BillState.DRAFT);
        submitted = financeBLService.getPaymentsByState(BillState.SUBMITTED);
        pass = financeBLService.getPaymentsByState(BillState.PASS);
        failed = financeBLService.getPaymentsByState(BillState.FAILED);

        billPane = new BillPane("草稿单据","待审批单据","审批通过单据","审批通过单据");
        initTabs();
        vBox.getChildren().add(billPane.getTabPane());
        billPane.getTabPane().getSelectionModel().selectLast();
        billPane.getTabPane().getSelectionModel().selectFirst();
    }
    public void initTabs(){
        ArrayList<Tab> tabs = billPane.getAllTabs();
        for (int i = 0; i < tabs.size(); i++){
            tabs.get(i).setOnSelectionChanged(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    Tab tab = (Tab)event.getSource();
                    if (tab.isSelected()){
                        billNodes.clear();
                        fxmlLoaders.clear();
                        loadBills(tab.getText());
                        billPane.setContent(tab,billNodes);
                    }
                }
            });
        }
    }
    public void loadBills(String tab){
        ArrayList<AccountBillVO> bills = null;
        switch (tab){
            case "草稿单据": bills = draft;break;
            case "待审批单据": bills = submitted;break;
            case "审批通过单据": bills = pass;break;
            case "审批不通过单据": bills = failed;break;

        }
        for (int i = 0; i < bills.size(); i++){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/common/bill.fxml"));
                VBox node = loader.load();
                fxmlLoaders.add(loader);
                billNodes.add(node);
                BillController financialBillController = loader.getController();
                financialBillController.hideCheckbox();
                if (tab == "草稿单据"){
                    financialBillController.showDeleteIcon();
                }
                financialBillController.setFinancialPaymentController(this);
                financialBillController.setBill(bills.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void showPaymentList(){
        financialViewController.showPaymentView();
    }
    public void clickAddButton(){
        showPaymentEditView();
        financialPaymentEditController.addPayment();
    }
    public void showPaymentDetailView(AccountBillVO vo){
        showPaymentEditView();
        financialPaymentEditController.setForDetailView(vo);

    }
    public void showPaymentEditView(){
        try{
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/financialStaff/PaymentEdit.fxml"));
            Pane page = pageLoader.load();
            financialPaymentEditController = pageLoader.getController();
            financialPaymentEditController.setFinancialPaymentController(this);
            financialViewController.showPaymentEditView(page);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }

    public void deletePayment(AccountBillVO accountBill) {
        ResultMessage re = financeBLService.deleteDraftAccountBill(accountBill.ID);
        if (re == ResultMessage.SUCCESS){
            showPaymentList();
        }
    }



}
