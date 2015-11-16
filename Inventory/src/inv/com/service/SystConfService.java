package inv.com.service;

import java.util.List;

import inv.com.service.SystLkup;
import inv.com.util.Constraint;
import inv.com.util.OrderMap;
import inv.com.util.SearchResult;

public interface SystConfService {

	public List getSystLkup(String tableTyle);
	public SearchResult searchSystLkup(Constraint constraint, OrderMap orderMap, 
			boolean isUsingPaging, int offset, int pageSize);
	public SystLkup getSystLkup(String tableType, String tableCode);
	public void save(SystLkup systLkup);
	
}
