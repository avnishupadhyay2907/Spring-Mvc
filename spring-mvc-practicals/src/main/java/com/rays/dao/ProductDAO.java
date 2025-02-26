package com.rays.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.rays.dto.ProductDTO;
@Repository
public class ProductDAO {
	
	
	@Autowired
	public SessionFactory sessionFactory;

	public long add(ProductDTO dto) throws DataAccessException {
		System.out.println("id is" + dto.getId());
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	public void update(ProductDTO dto) throws DataAccessException {
		sessionFactory.getCurrentSession().update(dto);
	}

	public ProductDTO delete(long id) throws DataAccessException {
		ProductDTO dto = findByPk(id);
		sessionFactory.getCurrentSession().delete(dto);
		return dto;
	}

	public ProductDTO findByPk(long pk) throws DataAccessException {
		ProductDTO dto = null;
		dto = (ProductDTO) sessionFactory.getCurrentSession().get(ProductDTO.class, pk);
		return dto;
	}

	
	public ProductDTO findByProductName(String productName) throws DataAccessException {
		ProductDTO dto = null;
		System.out.println("productName " + productName);
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ProductDTO.class);
		criteria.add(Restrictions.eq("productName", productName));
		List list = criteria.list();
		if (list.size() == 1) {
			dto = (ProductDTO) list.get(0);
		}
		return dto;
	}

	public List search(ProductDTO dto, int pageNo, int pageSize) {
		System.out.println("search 5");
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ProductDTO.class);
		if (dto != null) {
			if (dto.getId() != null && dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getProductName() != null && dto.getProductName().length() > 0) {
				criteria.add(Restrictions.like("productName", dto.getProductName() + "%"));
			}
			if (dto.getPurchaseDate() != null) {
				criteria.add(Restrictions.like("purchaseDate", dto.getPurchaseDate()));
			}
		}
		if (pageSize > 0) {
			System.out.println("search 6");
			pageNo = (pageNo - 1) * pageSize;
			criteria.setFirstResult(pageNo);
			criteria.setMaxResults(pageSize);
		}
		System.out.println("search 7");
		List list = criteria.list();
		return list;
	}
}
