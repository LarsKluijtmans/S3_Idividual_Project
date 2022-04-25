package com.example.individualproject.business.impl;

import com.example.individualproject.dto.products.*;
import com.example.individualproject.business.ProductService;
import com.example.individualproject.repository.ImageRepository;
import com.example.individualproject.repository.entity.Image;
import com.example.individualproject.repository.entity.Product;
import com.example.individualproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

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

        String searchName = "%" + name + "%";
        List<GetProductDTO> result = new ArrayList<>();

        GetProductDTO product;

        for (Product p :  productRepository.findProductsByTitleLikeOrSubTitleIsLikeOrSeriesIsLikeOrConditionIsLikeOrGenreIsLike(searchName,searchName,searchName,searchName,searchName)) {
            product = new GetProductDTO(p);
            result.add(product);
        }

        return result;
    }
    @Override
    public GetProductDTO getProduct(Long productID) {
        Product p = productRepository.findProductsByIdIs(productID);

        if(p == null) {
            return null;
        }

        return new GetProductDTO(p);
    }
    @Override
    public List<GetProductDTO> getAllOfAUsersProducts(Long userID) {

        List<GetProductDTO> result = new ArrayList<>();

        GetProductDTO product;

        for (Product p : productRepository.findAllBySeller_Id(userID)) {
            product = new GetProductDTO(p);
            result.add(product);
        }

        return result;
    }

    //Delete
    @Override
    public void deleteProduct(Long productID) {
        imageRepository.deleteAllByProductIsLike(productRepository.getById(productID));
        productRepository.deleteById(productID);
    }

    //Add
    @Override
    public CreateProductResponseDTO addProduct(CreateProductRequestDTO product) {

        List<Image> images = new ArrayList<>();
        for (String s : product.getProductInfo().getImages()) {
            Image newimage = Image.builder()
                    .imageUrl(s)
                    .build();

            images.add(newimage);
        }

        Product newProduct = Product.builder()
                .title(product.getProductInfo().getTitle())
                .subTitle(product.getProductInfo().getSubTitle())
                .series(product.getProductInfo().getSeries())
                .year(product.getProductInfo().getYear())
                .price(product.getProductInfo().getPrice())
                .condition(product.getProductInfo().getCondition())
                .description(product.getProductInfo().getDescription())
                .genre(product.getProductInfo().getGenre())
                .sold(false)
                .images(images)
                .productType(product.getProductInfo().getProductType())
                .build();

        Product savedProduct = productRepository.save(newProduct);


        CreateProductResponseDTO createProductResponseDTO = CreateProductResponseDTO.builder()
                .productId(savedProduct.getId())
                .build();

        savedProduct.setId(createProductResponseDTO.getProductId());

        saveImages(savedProduct,product.getProductInfo().getImages());

        return createProductResponseDTO;
    }

    //Update
    @Override
    public UpdateProductResponseDTO updateProduct(UpdateProductRequestDTO product) {

        List<Image> images = new ArrayList<>();
        for (String s : product.getProductInfo().getImages()) {
            Image newimage = Image.builder()
                    .imageUrl(s)
                    .build();

            images.add(newimage);
        }

        Product updatedProduct = Product.builder()
                .id(product.getProductId())
                .title(product.getProductInfo().getTitle())
                .subTitle(product.getProductInfo().getSubTitle())
                .series(product.getProductInfo().getSeries())
                .year(product.getProductInfo().getYear())
                .price(product.getProductInfo().getPrice())
                .condition(product.getProductInfo().getCondition())
                .description(product.getProductInfo().getDescription())
                .genre(product.getProductInfo().getGenre())
                .sold(false)
                .productType(product.getProductInfo().getProductType())
                .images(images)
                .build();

        Product savedProduct = productRepository.save(updatedProduct);

        UpdateProductResponseDTO updateProductResponseDTO = UpdateProductResponseDTO.builder()
                .productId(savedProduct.getId())
                .build();


        savedProduct.setId(updateProductResponseDTO.getProductId());

        imageRepository.deleteAllByProductIsLike(savedProduct);
        saveImages(savedProduct,product.getProductInfo().getImages());

        return  updateProductResponseDTO;
    }

    //Images
    private void saveImages(Product product, List<String> images){
       List<Image> imagesToSave = new ArrayList<>();

        for (String s : images) {
            Image newimage = Image.builder()
                    .product(product)
                    .imageUrl(s)
                    .build();

            imagesToSave.add(newimage);
        }

        imageRepository.saveAll(imagesToSave);
    }

}
