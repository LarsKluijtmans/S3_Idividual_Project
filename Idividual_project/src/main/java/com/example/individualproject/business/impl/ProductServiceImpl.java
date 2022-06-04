package com.example.individualproject.business.impl;

import com.example.individualproject.business.exception.BuyingYourOwnProductException;
import com.example.individualproject.business.exception.InvalidCredentialsException;
import com.example.individualproject.business.exception.ProductNotFoundException;
import com.example.individualproject.business.exception.UserNotFoundException;
import com.example.individualproject.dto.login.AccessTokenDTO;
import com.example.individualproject.dto.products.*;
import com.example.individualproject.business.ProductService;
import com.example.individualproject.repository.GenreRepository;
import com.example.individualproject.repository.ImageRepository;
import com.example.individualproject.repository.NormalUserRepository;
import com.example.individualproject.repository.entity.Image;
import com.example.individualproject.repository.entity.NormalUser;
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
    private final GenreRepository genreRepository;
    private final NormalUserRepository normalUserRepository;
    private final ImageRepository imageRepository;
    private final AccessTokenDTO requestAccessToken;

    @Override
    public List<GetProductDTO> getAllProducts() {
        List<GetProductDTO> result = new ArrayList<>();

        GetProductDTO product;

        for (Product p : productRepository.findAllBySold(false)) {

            product = new GetProductDTO(p);
            result.add(product);
        }

        return result;
    }
    @Override
    public List<GetProductDTO> getProducts(String name) {
        //TODO get only product not yet sold
        if(name.equals("")) {
            return Collections.emptyList();
        }

        String searchName = "%" + name + "%";
        List<GetProductDTO> result = new ArrayList<>();

        GetProductDTO product;

        for (Product p :  productRepository.findProductsByTitleLikeOrSubTitleIsLikeOrSeriesIsLikeOrConditionIsLikeOrGenre_GenreIsLikeAndSold(searchName,searchName,searchName,searchName,searchName, false)) {
            product = new GetProductDTO(p);
            result.add(product);
        }

        return result;
    }
    @Override
    public GetProductDTO getProduct(Long productID) {
        Product p = productRepository.findProductsByIdIsAndSold(productID, false);

        if(p == null) {
            return null;
        }

        return new GetProductDTO(p);
    }

    @Override
    public List<GetProductDTO> getAllOfAUsersProductsNormalUser(String username) {

        isNormalUser();

        NormalUser user = normalUserRepository.findByUsername(username);

        if (user == null){
            throw new UserNotFoundException();
        }


        if (!requestAccessToken.getUserId().equals(user.getId())){
            throw new InvalidCredentialsException();
        }

        List<GetProductDTO> result = new ArrayList<>();

        GetProductDTO product;

        for (Product p : productRepository.findAllBySeller_Id(user.getId())) {
            product = new GetProductDTO(p);
            result.add(product);
        }

        return result;
    }

    @Override
    public void buyProduct(Long productID) {
      Product p = productRepository.findById(productID).orElse(null);

      if(p == null) {
          throw new ProductNotFoundException();
      }

      if(p.getSeller().getId() == requestAccessToken.getUserId()){
          throw new BuyingYourOwnProductException();
      }
      p.setSold(true);

      productRepository.save(p);
    }

    @Override
    public List<GetProductDTO> getAllOfAUsersProductsAdmin(String username) {

        if (!requestAccessToken.hasRole("ADMIN")){
            throw new InvalidCredentialsException();
        }

        NormalUser user = normalUserRepository.findByUsername(username);

        if (user == null){
            throw new UserNotFoundException();
        }

        List<GetProductDTO> result = new ArrayList<>();
        GetProductDTO product;

        for (Product p : productRepository.findAllBySeller_Id(user.getId())) {
            product = new GetProductDTO(p);
            result.add(product);
        }

        return result;
    }

    //Delete

    @Override
    public void deleteProductAdmin(Long productID) {
        Product p = productRepository.findProductsByIdIs(productID);

        if (!requestAccessToken.hasRole("ADMIN")){
            throw new InvalidCredentialsException();
        }

        imageRepository.deleteByProductId(p.getId());
        productRepository.deleteById(productID);
    }
    @Override
    public void deleteProductNormalUser(Long productID) {

        Product p = productRepository.findProductsByIdIs(productID);

        isNormalUser();

        if (!requestAccessToken.getUserId().equals(p.getSeller().getId())){
            throw new InvalidCredentialsException();
        }

        imageRepository.deleteByProductId(p.getId());
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
                .genre(genreRepository.getById(product.getGenreId()))
                .sold(false)
                .images(images)
                .productType(product.getProductType())
                .seller(normalUserRepository.findByUsername(product.getSeller()))
                .build();

        Product savedProduct = productRepository.save(newProduct);

        CreateProductResponseDTO createProductResponseDTO = CreateProductResponseDTO.builder()
                .productId(savedProduct.getId())
                .build();

        savedProduct.setId(createProductResponseDTO.getProductId());

        saveImages(savedProduct,product.getImages());

        return createProductResponseDTO;
    }

    //Update
    @Override
    public UpdateProductResponseDTO updateProduct(UpdateProductRequestDTO product) {

        Product p = productRepository.findProductsByIdIs(product.getProductId());

        isNormalUser();

        if ( !requestAccessToken.getUserId().equals(p.getSeller().getId())){
            throw new InvalidCredentialsException();
        }

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
                .genre(genreRepository.getById(product.getGenreId()))
                .sold(false)
                .productType(product.getProductType())
                .images(images)
                .seller(p.getSeller())
                .build();

        Product savedProduct = productRepository.save(updatedProduct);

        UpdateProductResponseDTO updateProductResponseDTO = UpdateProductResponseDTO.builder()
                .productId(savedProduct.getId())
                .build();


        savedProduct.setId(updateProductResponseDTO.getProductId());


        imageRepository.deleteByProductId(savedProduct.getId());
        saveImages(savedProduct,product.getImages());

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

    private void isNormalUser(){
        if (!requestAccessToken.hasRole("NORMALUSER")){
            throw new InvalidCredentialsException();
        }
    }

}
