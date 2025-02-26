package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rays.dto.ProductDTO;
import com.rays.dto.ProductDTO;
import com.rays.form.ProductForm;
import com.rays.form.ProductForm;
import com.rays.service.ProductService;
import com.rays.service.UserService;
import com.rays.util.DataUtility;
@Controller
@RequestMapping(value = "Product")
public class ProductCtl {

	@Autowired
	public ProductService service;

	@ModelAttribute("form")
	public void preload(Model model) {
		List list = service.search(null, 0, 0);
		model.addAttribute("productList", list);
	}

	@GetMapping
	public String display(@ModelAttribute("form") ProductForm form, @RequestParam(required = false) Long id) {

		if (id != null && id > 0) {
			ProductDTO dto = service.findByPk(id);
			form.setId(dto.getId());
			form.setProductName(dto.getProductName());
			form.setProductAmmount(dto.getProductAmmount());
			form.setPurchaseDate(DataUtility.dateToString(dto.getPurchaseDate()));
			form.setProductCategory(dto.getProductCategory());
		}
		return "ProductView";
	}

	@PostMapping
	public String submit(@ModelAttribute("form") ProductForm form) {

		ProductDTO dto = new ProductDTO();
		dto.setId(form.getId());
		dto.setProductName(form.getProductName());
		dto.setProductAmmount(form.getProductAmmount());
		dto.setPurchaseDate(DataUtility.stringToDate(form.getPurchaseDate()));
		dto.setProductCategory(form.getProductCategory());

		if (form.getId() > 0) {
			service.update(dto);
		} else {
			service.add(dto);
		}
		return "ProductView";
	}

	@GetMapping("search")
	public String display(@ModelAttribute("form") ProductForm form, Model model) {

		int pageNo = 1;
		int pageSize = 5;

		List list = service.search(null, pageNo, pageSize);

		model.addAttribute("list", list);

		form.setPageNo(pageNo);

		return "ProductListView";
	}

	@PostMapping("search")
	public String search(@ModelAttribute("form") ProductForm form, @RequestParam(required = false) String operation,
			Model model) {

		ProductDTO dto = null;

		int pageNo = 1;
		int pageSize = 5;

		if (operation.equals("next")) {
			pageNo = form.getPageNo();
			pageNo++;
		}

		if (operation.equals("previous")) {
			pageNo = form.getPageNo();
			pageNo--;
		}

		if (operation.equals("search")) {
			dto = new ProductDTO();
			dto.setId(form.getId());
			dto.setProductName(form.getProductName());

		}

		if (operation.equals("delete")) {
			if (form.getIds() != null && form.getIds().length > 0) {
				for (long id : form.getIds()) {
					service.delete(id);
				}
			}
		}

		List list = service.search(dto, pageNo, pageSize);

		form.setPageNo(pageNo);

		model.addAttribute("list", list);

		return "ProductListView";
	}
}
