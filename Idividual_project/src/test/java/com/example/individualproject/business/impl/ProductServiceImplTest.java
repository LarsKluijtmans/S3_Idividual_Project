package com.example.individualproject.business.impl;

import com.example.individualproject.business.ProductService;
import com.example.individualproject.dto.products.*;
import com.example.individualproject.repository.ImageRepository;
import com.example.individualproject.repository.ProductRepository;
import com.example.individualproject.repository.entity.Image;
import com.example.individualproject.repository.entity.Product;
import org.flywaydb.core.api.callback.BaseCallback;
import org.junit.jupiter.api.Test;

import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Test
    void getAllProducts() {
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        ImageRepository imageRepositoryMock = mock(ImageRepository.class);

        Product PokemonDiamond = Product.builder().id(1l).title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").sold(false).productType("GAME").images(Collections.emptyList()).build();

        Product PokemonPearl= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").sold(false).productType("GAME").images(Collections.emptyList()).build();

        when(productRepositoryMock.findAll())
                .thenReturn(List.of(PokemonDiamond, PokemonPearl));


        ProductService productServiceMock = new ProductServiceImpl(productRepositoryMock,imageRepositoryMock);

        List<GetProductDTO> actualResult = productServiceMock.getAllProducts();



        BasicProductInfo product1 = BasicProductInfo.builder().title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").productType("GAME").images(Collections.emptyList()).build();
        BasicProductInfo product2 = BasicProductInfo.builder().title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").productType("GAME").images(Collections.emptyList()).build();

        GetProductDTO PokemonDiamondDTO = new GetProductDTO(1l, product1);
        GetProductDTO PokemonPearlDTO = new GetProductDTO(2l, product2);

        List<GetProductDTO> expectedResult = List.of(PokemonDiamondDTO, PokemonPearlDTO);

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).findAll();

    }

    @Test
    void getProducts() {
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        ImageRepository imageRepositoryMock = mock(ImageRepository.class);

        Product PokemonDiamond = Product.builder().id(1l).title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").sold(false).productType("GAME").images(Collections.emptyList()).build();

        Product PokemonPearl= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").sold(false).productType("GAME").images(Collections.emptyList()).build();

        when(productRepositoryMock.findProductsByTitleLikeOrSubTitleIsLikeOrSeriesIsLikeOrConditionIsLikeOrGenreIsLike("%Pokemon%","%Pokemon%","%Pokemon%","%Pokemon%","%Pokemon%"))
                .thenReturn(List.of(PokemonDiamond, PokemonPearl));


        ProductService productServiceMock = new ProductServiceImpl(productRepositoryMock,imageRepositoryMock);

        List<GetProductDTO> actualResult = productServiceMock.getProducts("Pokemon");

        BasicProductInfo product1 = BasicProductInfo.builder().title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").productType("GAME").images(Collections.emptyList()).build();
        BasicProductInfo product2 = BasicProductInfo.builder().title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").productType("GAME").images(Collections.emptyList()).build();

        GetProductDTO PokemonDiamondDTO = new GetProductDTO(1l, product1);
        GetProductDTO PokemonPearlDTO = new GetProductDTO(2l, product2);

        List<GetProductDTO> expectedResult = List.of(PokemonDiamondDTO, PokemonPearlDTO);

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).findProductsByTitleLikeOrSubTitleIsLikeOrSeriesIsLikeOrConditionIsLikeOrGenreIsLike("%Pokemon%","%Pokemon%","%Pokemon%","%Pokemon%","%Pokemon%");



        List<GetProductDTO> actualResultWithEmptyInput = productServiceMock.getProducts("");
        List<GetProductDTO> expectedResultWithEmptyInput  = Collections.emptyList();

        assertEquals(expectedResultWithEmptyInput, actualResultWithEmptyInput);
    }

    @Test
    void getProduct() {
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        ImageRepository imageRepositoryMock = mock(ImageRepository.class);

        Product PokemonPearl= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").sold(false).productType("GAME").images(Collections.emptyList()).build();

        when(productRepositoryMock.findProductsByIdIs(2l))
                .thenReturn(PokemonPearl);

        ProductService productServiceMock = new ProductServiceImpl(productRepositoryMock,imageRepositoryMock);

        GetProductDTO actualResult = productServiceMock.getProduct(2l);



        BasicProductInfo product2 = BasicProductInfo.builder().title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").productType("GAME").images(Collections.emptyList()).build();

        GetProductDTO PokemonPearlDTO = new GetProductDTO(2l, product2);

        GetProductDTO expectedResult = PokemonPearlDTO;

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).findProductsByIdIs(2l);


        GetProductDTO actualResultWithNullID = productServiceMock.getProduct( null);

        assertEquals(null, actualResultWithNullID);
    }

    @Test
    void getAllOfAUsersProducts()  {
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        ImageRepository imageRepositoryMock = mock(ImageRepository.class);


        ProductService productServiceMock = new ProductServiceImpl(productRepositoryMock,imageRepositoryMock);

        List<GetProductDTO> actualResult = productServiceMock.getAllOfAUsersProducts(1l);
        List<GetProductDTO> expectedResult = Collections.emptyList();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteProduct() {
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        ImageRepository imageRepositoryMock = mock(ImageRepository.class);

        ProductService productServiceMock = new ProductServiceImpl(productRepositoryMock,imageRepositoryMock);

        productServiceMock.deleteProduct(1l);

        verify(productRepositoryMock).getById(1l);
        verify(productRepositoryMock).deleteById(1l);
    }

    @Test
    void addProduct() {
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        ImageRepository imageRepositoryMock = mock(ImageRepository.class);


        List<Image> images = new ArrayList<>();

        images.add(new Image(0,"aa", null));
        images.add(new Image(0,"aaa", null));

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("aa");
        imageUrls.add("aaa");


        Product PokemonPearl= Product.builder().title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").sold(false).productType("GAME").images(images).build();

        Product PokemonPearl2= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").sold(false).productType("GAME").images(images).build();

        when(productRepositoryMock.save(PokemonPearl))
                .thenReturn(PokemonPearl2);

        ProductService productServiceMock = new ProductServiceImpl(productRepositoryMock,imageRepositoryMock);


        CreateProductRequestDTO createProductRequestDTO = new CreateProductRequestDTO( new BasicProductInfo("Pokemon","Pearl","Pokemon",2022,10.01,"TRASH","Pokemon game","JRPG","GAME", imageUrls));
        CreateProductResponseDTO actualResult = productServiceMock.addProduct(createProductRequestDTO);

        CreateProductResponseDTO expectedResult = CreateProductResponseDTO.builder()
                .productId(2l)
                .build();

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).save(PokemonPearl);
    }

    @Test
    void updateProduct() {

        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        ImageRepository imageRepositoryMock = mock(ImageRepository.class);


        List<Image> images = new ArrayList<>();

        images.add(new Image(0,"aa", null));
        images.add(new Image(0,"aaa", null));

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("aa");
        imageUrls.add("aaa");


        Product PokemonPearl= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").sold(false).productType("GAME").images(images).build();

        Product PokemonPearl2= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").sold(false).productType("GAME").images(images).build();

        when(productRepositoryMock.save(PokemonPearl))
                .thenReturn(PokemonPearl2);

        ProductService productServiceMock = new ProductServiceImpl(productRepositoryMock,imageRepositoryMock);


        UpdateProductRequestDTO updateProductRequestDTO = new UpdateProductRequestDTO ( 2l,new BasicProductInfo("Pokemon","Pearl","Pokemon",2022,10.01,"TRASH","Pokemon game","JRPG","GAME", imageUrls));
        UpdateProductResponseDTO actualResult = productServiceMock.updateProduct(updateProductRequestDTO);

        UpdateProductResponseDTO expectedResult = UpdateProductResponseDTO.builder()
                .productId(2l)
                .build();

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).save(PokemonPearl);
    }
}