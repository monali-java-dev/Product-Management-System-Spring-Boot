package com.aadiandjava.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aadiandjava.entity.Product;
import com.aadiandjava.repo.ProductRepository;
import com.aadiandjava.service.ProductService;
@Service

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

	@Override
	public Product getById(int id) {
		// TODO Auto-generated method stub
		Product product = null; 
	Optional<Product> op=productRepository.findById(id);
	if(op.isPresent())
	{
		 product = op.get();
	}
	return product;
	}
	
	@Override
	public void deleteById(int id) {
	    productRepository.deleteById(id);
	}
}