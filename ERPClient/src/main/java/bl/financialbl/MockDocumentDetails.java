package bl.financialbl;

import blservice.financeblservice.DocumentDetailsInput;
import vo.BillVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/7.
 */
public class MockDocumentDetails extends DocumentDetails {
    @Override
    public ArrayList<BillVO> getDocumentDetails(DocumentDetailsInput input) {
        ArrayList<BillVO> billVOS = new ArrayList<BillVO>();
        billVOS.add(new MockFinance().findByID("SKD-20171022-00001"));
        return billVOS;
    }
}
