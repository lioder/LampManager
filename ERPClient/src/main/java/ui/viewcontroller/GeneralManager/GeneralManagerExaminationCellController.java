package ui.viewcontroller.GeneralManager;

import java.io.IOException;

import com.jfoenix.controls.JFXCheckBox;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ui.viewcontroller.FinancialStaff.FinancialCashBillEditController;
import ui.viewcontroller.FinancialStaff.FinancialPaymentEditController;
import ui.viewcontroller.FinancialStaff.FinancialReceiptEditController;
import ui.viewcontroller.InventoryStaff.InventorySyncEditController;
import ui.viewcontroller.SalesStaff.SalesStaffPurchaseEditViewController;
import ui.viewcontroller.SalesStaff.SalesStaffReturnEditViewController;
import ui.viewcontroller.SalesStaff.SalesStaffSalesEditViewController;
import ui.viewcontroller.SalesStaff.SalesStaffSalesReturnEditViewController;
import util.BillType;
import util.Money;
import vo.AccountBillVO;
import vo.BillVO;
import vo.CashBillVO;
import vo.InventoryBillVO;
import vo.PurchaseVO;
import vo.SalesVO;

public class GeneralManagerExaminationCellController {

	BillVO bill;
	GeneralManagerExaminationViewController generalManagerExaminationViewController;
	SalesStaffSalesEditViewController salesStaffSalesEditViewController;
	SalesStaffSalesReturnEditViewController salesStaffSalesReturnEditViewController;
	SalesStaffPurchaseEditViewController salesStaffPurchaseEditViewController;
	SalesStaffReturnEditViewController salesStaffReturnEditViewController;
	FinancialCashBillEditController financialCashBillEditController;
	FinancialPaymentEditController financialPaymentEditController;
	FinancialReceiptEditController financialReceiptEditController;
	InventorySyncEditController inventorySyncEditController;
	
	@FXML
	Circle circle;
	
	@FXML
	Label billType;
	
	@FXML
	Label billIDIcon;
	
	@FXML
	Label billID;
	
	@FXML
	Label billDateIcon;
	
	@FXML
	Label billDate;
	
	@FXML
	Label billCreaterIcon;
	
	@FXML
	Label billCreater;
	
	@FXML
	Label billMoneyIcon;
	
	@FXML
	Label billMoney;
	
	@FXML
	Label DetailIcon;
	
	@FXML
	JFXCheckBox checkBox;
	
	@FXML
    public void initialize(){
		billIDIcon.setText("\ue67c");
		billDateIcon.setText("\ue60a");
		billCreaterIcon.setText("\ue609");
		billMoneyIcon.setText("\ue611");
        DetailIcon.setText("\ue89d");
    }
	
	public void setGeneralManagerExaminationViewController(GeneralManagerExaminationViewController generalManagerExaminationViewController){
		this.generalManagerExaminationViewController = generalManagerExaminationViewController;
	}
	
	public void setBill(BillVO bill){
		this.bill = bill;
		setBillType(bill);
		billID.setText(bill.ID);
		billDate.setText(String.valueOf(bill.date));
	}
	
	public void setBillType(BillVO bill){
		if(bill.type==BillType.CASH){
			CashBillVO cashBill = (CashBillVO) bill;
			circle.setStyle("-fx-Stroke:#0066FF");
			billType.setText("现");
			billType.setTextFill(Color.web("#0066FF"));
			billCreater.setText(cashBill.userName);
			billMoney.setText(Money.getMoneyString(cashBill.sum));
		}
		else if(bill.type==BillType.GIFT){
			InventoryBillVO inventoryBill = (InventoryBillVO) bill;
			circle.setStyle("-fx-Stroke:#FFCC00");
			billType.setText("赠");
			billType.setTextFill(Color.web("#FFCC00"));
			billCreater.setText(inventoryBill.user);
			billMoney.setText(Money.getMoneyString(0.0));
		}
		else if(bill.type==BillType.LOSS){
			InventoryBillVO inventoryBill = (InventoryBillVO) bill;
			circle.setStyle("-fx-Stroke:#99CCFF");
			billType.setText("损");
			billType.setTextFill(Color.web("#99CCFF"));
			billCreater.setText(inventoryBill.user);
			billMoneyIcon.setText("\ue61d");
			billMoney.setText(inventoryBill.inventory);
		}
		else if(bill.type==BillType.OVERFLOW){
			InventoryBillVO inventoryBill = (InventoryBillVO) bill;
			circle.setStyle("-fx-Stroke:#99CCFF");
			billType.setText("溢");
			billType.setTextFill(Color.web("#99CCFF"));
			billCreater.setText(inventoryBill.user);
			billMoneyIcon.setText("\ue61d");
			billMoney.setText(inventoryBill.inventory);
		}
		else if(bill.type==BillType.PAYMENT){
			AccountBillVO accountBill = (AccountBillVO) bill;
			circle.setStyle("-fx-Stroke:#00FF99");
			billType.setText("付");
			billType.setTextFill(Color.web("#00FF99"));
			billCreater.setText(accountBill.userName);
			billMoney.setText(Money.getMoneyString(accountBill.sum));
		}
		else if(bill.type==BillType.RECEIPT){
			AccountBillVO accountBill = (AccountBillVO) bill;
			circle.setStyle("-fx-Stroke:#00FF99");
			billType.setText("收");
			billType.setTextFill(Color.web("#00FF99"));
			billCreater.setText(accountBill.userName);
			billMoney.setText(Money.getMoneyString(accountBill.sum));
		}
		else if(bill.type==BillType.PURCHASE){
			PurchaseVO purchaseBill = (PurchaseVO) bill;
			circle.setStyle("-fx-Stroke:#FFCCFF");
			billType.setText("进");
			billType.setTextFill(Color.web("#FFCCFF"));
			billCreater.setText(purchaseBill.user);
			billMoney.setText(Money.getMoneyString(purchaseBill.sum));
		}
		else if(bill.type==BillType.RETURN){
			PurchaseVO purchaseBill = (PurchaseVO) bill;
			circle.setStyle("-fx-Stroke:#FFCCFF");
			billType.setText("退");
			billType.setTextFill(Color.web("#FFCCFF"));
			billCreater.setText(purchaseBill.user);
			billMoney.setText(Money.getMoneyString(purchaseBill.sum));
		}
		else if(bill.type==BillType.SALES){
			SalesVO salesBill = (SalesVO) bill;
			circle.setStyle("-fx-Stroke:#FF0033");
			billType.setText("销");
			billType.setTextFill(Color.web("#FF0033"));
			billCreater.setText(salesBill.user);
			billMoney.setText(Money.getMoneyString(salesBill.afterSum));
		}
		else if(bill.type==BillType.SALESRETURN){
			SalesVO salesBill = (SalesVO) bill;
			circle.setStyle("-fx-Stroke:#FF0033");
			billType.setText("退");
			billType.setTextFill(Color.web("#FF0033"));
			billCreater.setText(salesBill.user);
			billMoney.setText(Money.getMoneyString(salesBill.afterSum));
		}
	}
	
	public void clickReturnButton(){
		generalManagerExaminationViewController.clickReturnButton();
	}
	
	public void clickDetailButton(){
		Pane pane = null;
		if(bill.type==BillType.PURCHASE){
			pane = showPurchaseOrderBill();
		}
		else if(bill.type==BillType.SALES){
			pane = showSalesOrderBill();
		}
		else if(bill.type==BillType.RETURN){
			pane = showReturnOrderBill();
		}
		else if(bill.type==BillType.CASH){
			pane = showCashBill();
		}
		else if(bill.type==BillType.PAYMENT){
			pane = showPaymentBill();
		}
		else if(bill.type==BillType.RECEIPT){
			pane = showReceiptBill();
		}
		else if(bill.type==BillType.SALESRETURN){
			pane = showSalesReturnOrderBill();
		}
		else if(bill.type==BillType.OVERFLOW||bill.type==BillType.LOSS){
			pane = showInventoryBill();
		}
		generalManagerExaminationViewController.showBillDetail(pane);
	}
	
	public Pane showSalesOrderBill(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/SalesOrderEdit.fxml"));
            page = pageLoader.load();
            salesStaffSalesEditViewController = pageLoader.getController();
            salesStaffSalesEditViewController.setGeneralManagerExaminationCellController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
		salesStaffSalesEditViewController.isExamine();
		salesStaffSalesEditViewController.setForDetailView((SalesVO) bill);
		
		return page;
	}
	
	public Pane showSalesReturnOrderBill(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/SalesReturnOrderEdit.fxml"));
            page = pageLoader.load();
            salesStaffSalesReturnEditViewController = pageLoader.getController();
            salesStaffSalesReturnEditViewController.setGeneralManagerExaminationCellController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
		salesStaffSalesReturnEditViewController.isExamine();
		salesStaffSalesReturnEditViewController.setForDetailView((SalesVO) bill);
		return page;
	}
	
	public Pane showPurchaseOrderBill(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/PurchaseOrderEdit.fxml"));
            page = pageLoader.load();
            salesStaffPurchaseEditViewController = pageLoader.getController();
            salesStaffPurchaseEditViewController.setGeneralManagerExaminationCellViewController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
		salesStaffPurchaseEditViewController.isExamine();
		salesStaffPurchaseEditViewController.setForDetailView((PurchaseVO) bill);
		return page;
	}
	
	public Pane showReturnOrderBill(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/ReturnOrderEdit.fxml"));
            page = pageLoader.load();
            salesStaffReturnEditViewController = pageLoader.getController();
            salesStaffReturnEditViewController.setGeneralManagerExaminationCellViewController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
		salesStaffReturnEditViewController.isExamine();
		salesStaffReturnEditViewController.setForDetailView((PurchaseVO) bill);
		return page;
	}
	
	public Pane showCashBill(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/financialStaff/CashBillEdit.fxml"));
            page = pageLoader.load();
            financialCashBillEditController = pageLoader.getController();
            financialCashBillEditController.setGeneralManagerExaminationCellController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
		financialCashBillEditController.isExamine();
		financialCashBillEditController.setForDetailView((CashBillVO) bill);
		return page;
	}
	
	public Pane showPaymentBill(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/financialStaff/PaymentEdit.fxml"));
            page = pageLoader.load();
            financialPaymentEditController = pageLoader.getController();
            financialPaymentEditController.setGeneralManagerExaminationCellController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
		financialPaymentEditController.isExamine();
		financialPaymentEditController.setForDetailView((AccountBillVO) bill);
		return page;
	}
	
	public Pane showReceiptBill(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/financialStaff/ReceiptEdit.fxml"));
            page = pageLoader.load();
            financialReceiptEditController = pageLoader.getController();
            financialReceiptEditController.setGeneralManagerExaminationCellController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
		financialReceiptEditController.isExamine();
		financialReceiptEditController.setForDetailView((AccountBillVO) bill);
		return page;
	}
	
	public Pane showInventoryBill(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/inventory/SyncEdit.fxml"));
            page = pageLoader.load();
            inventorySyncEditController = pageLoader.getController();
            inventorySyncEditController.setGeneralManagerExaminationCellController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
		inventorySyncEditController.isExamine();
		inventorySyncEditController.setForDetailView((InventoryBillVO) bill);
		return page;
	}
}
