package inv.com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import inv.com.service.DatabaseService;

import inv.com.util.Constraint;
import inv.com.filter.FlashMap;
import inv.com.filter.FlashMap.Message;
import inv.com.filter.FlashMap.MessageType;

public class BaseController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
//	masih belum di gunakan
//	@Autowired protected SystConfService systConfService;
	
	@Autowired protected DatabaseService databaseService;
	
//  untuk menambahkan query like %%,, object example mendapatkan data dari inputan 
	protected Constraint resolveSearch(Object example) {
		if(example == null) {
			return null;
		}
		
		Constraint constraint = new Constraint();
		
		StringBuilder buffer = new StringBuilder();
		Map<String, Object> parameters = constraint.getParameters();
		System.out.println(example+"exampleexample"+parameters+"parameters"+buffer+"bufferbuffer2222222222222222");
		// default
		String operator = " LIKE ";
		String prefix = "%";
		String suffix = "%";
		
//		buffer menampung yang sifatnya string, lalu sisanya di simpan di parameters
		specificSearch(example, buffer, parameters, operator, prefix, suffix);
		
		System.out.println(example+"exampleexample"+parameters+"parameters"+buffer+"bufferbuffer");
//		untuk mengambil nilai string "LIKE"
		constraint.setWhereClause(buffer.toString());
		constraint.setParameters(parameters);
		
		return constraint;
	}
	
	protected void specificSearch(Object object, StringBuilder buffer, Map<String, Object> parameters, 
			String operator, String prefix, String suffix) {
		// implemented at child class..
	}
	
	protected void addMessage(ModelMap modelMap, String message, MessageType type, boolean isRedirect) {
		if(isRedirect) {
			switch (type) {
				case info:
					FlashMap.setInfoMessage(message);
					break;
					
				case success:
					FlashMap.setSuccessMessage(message);
					break;
					
				case warning:
					FlashMap.setWarningMessage(message);
					break;
					
				case error:
					FlashMap.setErrorMessage(message);
					break;
			}
			
		} else {
			modelMap.put("message", new Message(type, message));
		}
	}
//	untuk mendapatkan nama local file
	protected String getOriginalFilename(MultipartFile multipartFile){
		if (multipartFile.isEmpty()){
			return null;
		}
		return multipartFile.getOriginalFilename();
	}
	
//	untuk upload to server
	protected void uploadFileToServer(MultipartFile multipartFile, String path) throws Exception{
		File file = new File(path);
		
		File directory = file.getParentFile();
		if (!directory.exists()){
			directory.mkdirs();
		}
		file.createNewFile();
		FileOutputStream ouStream = new FileOutputStream(file);
		
		ouStream.write(multipartFile.getBytes());
		ouStream.close();
	}
	
	protected String getRealPath(HttpSession session) {
		return session.getServletContext().getRealPath("/");
	}
	
	
	
//	masih belum digunakan 
//	public void putLookupMapIntoRequest(ModelMap modelMap, String key, String tableType) {
//		Map map = new HashMap();
//		
//		List systLkups = systConfService.getSystLkup(tableType);
//		for (Iterator<SystLkup> iterator=systLkups.iterator(); iterator.hasNext();) {
//			SystLkup systLkup = iterator.next();
//			
//			map.put(systLkup.getTableCode(), systLkup);
//		}
//		
//		modelMap.put(key, map);
//	}
}
