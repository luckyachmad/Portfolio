package inv.com.util;

public class PagingInfo {

	private String searchMethod;
	
	private int offset;
	private int pageSize;
	private int totalRows;
	
	public PagingInfo() {}
	
	public PagingInfo(int offset, int pageSize, int totalRows) {
		this.offset = offset;
		this.pageSize = pageSize;
		this.totalRows = totalRows;
	}
	
	public String getSearchMethod() {
		return searchMethod;
	}
	public void setSearchMethod(String searchMethod) {
		this.searchMethod = searchMethod;
	}
	
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
}
