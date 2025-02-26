package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.ProductDAO;
import com.rays.dto.ProductDTO;

@Service
@Transactional
public class ProductService {

	@Autowired
	public ProductDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(ProductDTO dto) {
		long i = dao.add(dto);
		return i;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(ProductDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long save(ProductDTO dto) {
		long id = dto.getId();
		if (dto.getId() != null && dto.getId() > 0) {
			update(dto);
		} else {
			id = add(dto);
		}
		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ProductDTO delete(long id) {
		ProductDTO deletedUser = dao.delete(id);
		return deletedUser;
	}

	@Transactional(readOnly = true)
	public ProductDTO findByPk(long pk) {
		ProductDTO dto = dao.findByPk(pk);
		return dto;
	}

	public ProductDTO findByProductName(String productName) {
		ProductDTO dto = dao.findByProductName(productName);
		return dto;
	}

	public List search(ProductDTO dto, int pageNo, int pageSize) {
		System.out.println("search 4");
		List list = dao.search(dto, pageNo, pageSize);
		return list;
	}
}
