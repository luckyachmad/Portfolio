package inv.com.service;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import inv.com.dao.GenericDao;
import inv.com.service.PKSystLkup;
import inv.com.service.SystLkup;
import inv.com.util.Constraint;
import inv.com.util.OrderMap;
import inv.com.util.SearchResult;
import inv.com.util.Constant;

@Service("systConfService") @Transactional
public class SystConfServiceImpl implements SystConfService {
	
	@Autowired private SessionFactory sessionFactory;
	@Autowired private GenericDao genericDao;
	
// SystLkup
	public List getSystLkup(String tableType) {
		
		OrderMap orderMap = new OrderMap();
		orderMap.put("seq", Constant.ORDER_ASC);
		
		Constraint constraint = new Constraint();
		
		Map<String, Object> parameters = constraint.getParameters();
		parameters.put("tableType", tableType);
		
		constraint.setWhereClause("compId.tableType = :tableType and enabled = true");
		constraint.setParameters(parameters);
		
		return genericDao.searchHQL("systLkup", "SystLkup systLkup", constraint, 
				orderMap, false, -1, -1, true).getRecs();
	}
	
	public SearchResult searchSystLkup(Constraint constraint, OrderMap orderMap, 
			boolean isUsingPaging, int offset, int pageSize) {
		
		return genericDao.searchHQL("systLkup", "SystLkup systLkup", constraint, 
				orderMap, isUsingPaging, offset, pageSize);
	}
	
	public SystLkup getSystLkup(String tableType, String tableCode) {
		PKSystLkup pkSystLkup = new PKSystLkup(tableType, tableCode);
		
		return (SystLkup) sessionFactory.getCurrentSession().get(SystLkup.class, pkSystLkup);
	}
	
	public void save(SystLkup systLkup) {
		sessionFactory.getCurrentSession().saveOrUpdate(systLkup);
	}
	
}
