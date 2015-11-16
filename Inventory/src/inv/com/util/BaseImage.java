package inv.com.util;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public class BaseImage {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String folder;
	private String fileName;
	private String type;
	private String contentType;
	private String remark;
	
	@Lob
	private byte [] contentBinary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public byte[] getContentBinary() {
		return contentBinary;
	}

	public void setContentBinary(byte[] contentBinary) {
		this.contentBinary = contentBinary;
	}
	
	
	
}
