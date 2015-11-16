package inv.com.view;

import inv.com.entity.Product;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ReportToExel extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		HSSFCellStyle blackStyle = workbook.createCellStyle();
		blackStyle.setFillForegroundColor(HSSFColor.BLACK.index);
		blackStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		blackStyle.setWrapText(true);
		
//		Map<String, PurchaseOrder> mapPo = (Map) model.get("test");
//		untuk start penomoran
		int i = 0;
		List recs = (List) model.get("recs");
		for (Iterator<Product> iterator = recs.iterator(); iterator.hasNext(); i++){
			
			Product product = iterator.next();
//			untuk penomoran
			getCell(sheet, i, 0).setCellValue(new HSSFRichTextString(String.valueOf(i-1)));
			getCell(sheet, i, 1).setCellValue(new HSSFRichTextString(product.getId()));
			
			
		}
		response.setHeader("test","test.xls");
	}

}
