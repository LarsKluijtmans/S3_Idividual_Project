package com.example.individualproject.business.impl;

import com.example.individualproject.DTO.Products.*;
import com.example.individualproject.business.ProductService;
import com.example.individualproject.repository.entity.Product;
import com.example.individualproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<GetProductDTO> getAllProducts() {

        List<GetProductDTO> result = new ArrayList<>();

        GetProductDTO product;

        for (Product p : productRepository.findAll()) {
            product = new GetProductDTO(p);
            result.add(product);
        }

        return result;
    }
    @Override
    public List<GetProductDTO> getProducts(String name) {
        if(name.equals("")) {
            return Collections.emptyList();
        }

        String Name = "%" + name + "%";
        List<GetProductDTO> result = new ArrayList<>();

        GetProductDTO product;

        for (Product p :  productRepository.findProductsByTitleLikeOrSubTitleIsLikeOrSeriesIsLikeOrConditionIsLikeOrGenreIsLike(Name,Name,Name,Name,Name)) {
            product = new GetProductDTO(p);
            result.add(product);
        }

        return result;
    }
    @Override
    public GetProductDTO getProduct(Long productID) {
        return new GetProductDTO(productRepository.findProductsByIdIsLike(productID));
    }
    @Override
    public List<GetProductDTO> getAllOfAUsersProducts(Long userID) {
      //ToDo make this
        return Collections.emptyList();
    }

    //Delete
    @Override
    public void deleteProduct(Long productID) {
        productRepository.deleteById(productID);
    }

    //Add
    @Override
    public CreateProductResponseDTO addProduct(CreateProductRequestDTO product) {

        Product newProduct = Product.builder()
                .title(product.getTitle())
                .subTitle(product.getSub_title())
                .series(product.getSeries())
                .year(product.getYear())
                .price(product.getPrice())
                .condition(product.getCondition_())
                .description(product.getDescription())
                .genre(product.getGenre())
                .sold(false)
                .product_type(product.getProduct_type())
                .build();

        Product savedProduct= save(newProduct);

        return CreateProductResponseDTO.builder()
                .productId(savedProduct.getId())
                .build();
    }
    private Product save(Product product) {
        productRepository.save(product);
        return Product.builder().build();
    }

    //Update
    @Override
    public UpdateProductResponseDTO updateProduct(UpdateProductRequestDTO product) {
        Product updatedProduct = Product.builder()
                .id(product.getProductId())
                .title(product.getTitle())
                .subTitle(product.getSub_title())
                .series(product.getSeries())
                .year(product.getYear())
                .price(product.getPrice())
                .condition(product.getCondition_())
                .description(product.getDescription())
                .genre(product.getGenre())
                .sold(false)
                .product_type(product.getProduct_type())
                .build();

        Product savedProduct= update(updatedProduct);

        return UpdateProductResponseDTO.builder()
                .productId(savedProduct.getId())
                .build();
    }
    private Product update(Product product) {
        productRepository.save(product);
        return Product.builder().build();
    }
}
