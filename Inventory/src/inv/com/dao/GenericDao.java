package inv.com.dao;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inv.com.util.Constant;
import inv.com.util.Constraint;
import inv.com.util.OrderMap;
import inv.com.util.PagingInfo;
import inv.com.util.SearchResult;

@Repository("genericDao")
public class GenericDao {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired protected SessionFactory sessionFactory;
	
	public List searchCriteria(Class domainClass, List<Criterion> restrictions, OrderMap orderMap) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(domainClass);
		
		if (restrictions != null) {
			for (Iterator iterator=restrictions.iterator(); iterator.hasNext();) {
				Criterion criterion = (Criterion) iterator.next();
				criteria.add(criterion);
			}
		}
		
		if (orderMap != null) {
			Map map = orderMap.getMap();
			List orderList = orderMap.getOrderList();
			
			for (Iterator iterator=orderList.iterator(); iterator.hasNext();) {
				String propertyName = (String) iterator.next();
				if (StringUtils.equals((String)map.get(propertyName), Constant.ORDER_ASC)) {
					criteria.addOrder(Order.asc(propertyName));
				} else if (StringUtils.equals((String)map.get(propertyName), Constant.ORDER_ASC)) {
					criteria.addOrder(Order.desc(propertyName));
				}
			}
		}
			
		return criteria.list();
	}
	
	public List runHQL(String hql, Map<String, Object> parameters) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		if (parameters != null) {
			for (String key : parameters.keySet()) {
				query.setParameter(key, parameters.get(key));
			}
		}
		
		return query.list();
	}
	
	public int executeUpdateHQL(String hql, Map<String, Object> parameters) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		if (parameters != null) {
			for (String key : parameters.keySet()) {
				query.setParameter(key, parameters.get(key));
			}
		}
		
		return query.executeUpdate();
	}
	
	public SearchResult searchHQL(String selectClause, String fromClause, Constraint constraint, 
			OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize, boolean isCacheQuery) {
		
		StringBuffer hql = new StringBuffer();
		
//		contoh = select customer from Customer custome
		hql.append(" select " + selectClause);
		hql.append(" from " + fromClause);
		
		Map<String, Object> parameters = null;
		
		if (constraint != null) {
//			contoh = select customer from Customer customer where ?
			if (StringUtils.isNotBlank(constraint.getWhereClause())) {
				
//										where customer.name  LIKE  :name
				hql.append(" where " + constraint.getWhereClause());
			}
//			menampung {name=%hel%}
			parameters = constraint.getParameters();
		}

		if (orderMap != null) {
			
//			contoh = select employee from Employee employee where ? order by ?
			hql.append(" order by " + orderMap.toString());
		}
		
//																	select customer from Customer customer where customer.name :name 
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		if (parameters != null) {
//								untuk mengeluarkan data yang di simpan di dalam map bedasarkan keynya
			for (String key : parameters.keySet()) {
				query.setParameter(key, parameters.get(key));
			}
		}
		
		
//		untuk membatasi paging misalnya max 10 untuk setiap page
		PagingInfo pagingInfo = null;
		
		if (isUsingPaging) {
			
			String hqlPaging = "select count(*) from " + fromClause;
			if (constraint != null) {
				if (StringUtils.isNotBlank(constraint.getWhereClause())) {
					hqlPaging = hqlPaging + " where " + constraint.getWhereClause();
				}
			}
			
			Query queryPaging = sessionFactory.getCurrentSession()
	 				.createQuery(hqlPaging);
			
			if (parameters != null) {
				for (String key : parameters.keySet()) {
					queryPaging.setParameter(key, parameters.get(key));
				}
			}
			
			int totalRows = ((Long) queryPaging.uniqueResult()).intValue();
			
			if (offset < 0 || offset >= totalRows) {
				offset = 0;
			}
			if (pageSize <= 0) {
				pageSize = Constant.DEFAULT_PAGE_SIZE;
			}
			
			query.setFirstResult(offset).setMaxResults(pageSize);
			
			
//			di input dengan data yang baru
			pagingInfo = new PagingInfo(offset, pageSize, totalRows);
		}
//											true
		List recs = query.setCacheable(isCacheQuery).list();
		 
//		di input yang ke SearchResult data yang baru 
		return new SearchResult(recs, pagingInfo);
	}
	
	public SearchResult searchHQL(String selectClause, String fromClause, Constraint constraint, 
			OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize) {
		
		return searchHQL(selectClause, fromClause, constraint, orderMap, isUsingPaging, offset, pageSize, false);
	}
	
	public SearchResult searchSQLQuery(String selectClause, String fromClause, Constraint constraint, 
			Map<String, Type> mapScalar, Map mapEntity,
			OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize) {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select " + selectClause);
		sql.append(" from " + fromClause);
		
		Map<String, Object> parameters = null;
		
		if (constraint != null) {
			if (StringUtils.isNotBlank(constraint.getWhereClause())) {
				sql.append(" where " + constraint.getWhereClause());
			}
			
			parameters = constraint.getParameters();
		}

		if (orderMap != null) {
			sql.append(" order by " + orderMap.toString());
		}
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		
		if (parameters != null) {
			for (String key : parameters.keySet()) {
				query.setParameter(key, parameters.get(key));
			}
		}
		
		if (mapScalar != null) {
			for (String key : mapScalar.keySet()) {
				query.addScalar(key, mapScalar.get(key));
			}
		}
		
		if (mapEntity != null) {
			
		}
		
		PagingInfo pagingInfo = null;
		
		if (isUsingPaging) {
			
			String sqlPaging = "select count(*) from " + fromClause;
			if (constraint != null) {
				if (StringUtils.isNotBlank(constraint.getWhereClause())) {
					sqlPaging = sqlPaging + " where " + constraint.getWhereClause();
				}
			}
			
			SQLQuery queryPaging = sessionFactory.getCurrentSession()
	 				.createSQLQuery(sqlPaging);
			
			if (parameters != null) {
				for (String key : parameters.keySet()) {
					queryPaging.setParameter(key, parameters.get(key));
				}
			}
			
			int totalRows = ((BigInteger) queryPaging.uniqueResult()).intValue();
			
			if (offset < 0 || offset >= totalRows) {
				offset = 0;
			}
			if (pageSize <= 0) {
				pageSize = Constant.DEFAULT_PAGE_SIZE;
			}
			
			query.setFirstResult(offset).setMaxResults(pageSize);
			
			pagingInfo = new PagingInfo(offset, pageSize, totalRows);
		}
		
		List recs = query.list();
		 
		return new SearchResult(recs, pagingInfo);
	}
	
	public List runSQLQuery(String sql, Map<String, Object> parameters, Map<String, Type> mapScalar) {
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		
		if (parameters != null) {
			for (String key : parameters.keySet()) {
				query.setParameter(key, parameters.get(key));
			}
		}
		
		if (mapScalar != null) {
			for (String key : mapScalar.keySet()) {
				query.addScalar(key, mapScalar.get(key));
			}
		}
		
		return query.list();
	}

}
