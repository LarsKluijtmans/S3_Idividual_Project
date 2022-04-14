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
        return new GetProductDTO(productRepository.findProductsByIdIsLike(productID));
    }
    @Override
    public List<GetProductDTO> getAllOfAUsersProducts(Long userID) {
        return Collections.emptyList();
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
        for (String s : product.getImages()) {
            Image newimage = Image.builder()
                    .imageUrl(s)
                    .build();

            images.add(newimage);
        }

        Product newProduct = Product.builder()
                .title(product.getTitle())
                .subTitle(product.getSubTitle())
                .series(product.getSeries())
                .year(product.getYear())
                .price(product.getPrice())
                .condition(product.getCondition())
                .description(product.getDescription())
                .genre(product.getGenre())
                .sold(false)
                .images(images)
                .productType(product.getProductType())
                .build();

        Product savedProduct = productRepository.save(newProduct);


        CreateProductResponseDTO createProductResponseDTO = CreateProductResponseDTO.builder()
                .productId(savedProduct.getId())
                .build();

        savedProduct.setId(createProductResponseDTO.getProductId());

        SaveImages(savedProduct,product.getImages());

        return createProductResponseDTO;
    }

    //Update
    @Override
    public UpdateProductResponseDTO updateProduct(UpdateProductRequestDTO product) {

        List<Image> images = new ArrayList<>();
        for (String s : product.getImages()) {
            Image newimage = Image.builder()
                    .imageUrl(s)
                    .build();

            images.add(newimage);
        }

        Product updatedProduct = Product.builder()
                .id(product.getProductId())
                .title(product.getTitle())
                .subTitle(product.getSubTitle())
                .series(product.getSeries())
                .year(product.getYear())
                .price(product.getPrice())
                .condition(product.getCondition())
                .description(product.getDescription())
                .genre(product.getGenre())
                .sold(false)
                .productType(product.getProductType())
                .images(images)
                .build();

        Product savedProduct = productRepository.save(updatedProduct);

        UpdateProductResponseDTO updateProductResponseDTO = UpdateProductResponseDTO.builder()
                .productId(savedProduct.getId())
                .build();


        savedProduct.setId(updateProductResponseDTO.getProductId());

        imageRepository.deleteAllByProductIsLike(savedProduct);
        SaveImages(savedProduct,product.getImages());

        return  updateProductResponseDTO;
    }

    private Product save(Product product) {
        productRepository.save(product);
        return Product.builder().build();
    }

    //Images
    private void SaveImages(Product product, List<String> images){
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
