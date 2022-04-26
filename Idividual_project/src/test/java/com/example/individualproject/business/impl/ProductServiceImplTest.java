package com.example.individualproject.business.impl;

import com.example.individualproject.business.ProductService;
import com.example.individualproject.dto.products.*;
import com.example.individualproject.dto.users.GetUserDTO;
import com.example.individualproject.repository.GenreRepository;
import com.example.individualproject.repository.ImageRepository;
import com.example.individualproject.repository.NormalUserRepository;
import com.example.individualproject.repository.ProductRepository;
import com.example.individualproject.repository.entity.Genre;
import com.example.individualproject.repository.entity.Image;
import com.example.individualproject.repository.entity.NormalUser;
import com.example.individualproject.repository.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Test
    void getAllProducts() {
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        GenreRepository genreRepositoryMock = mock(GenreRepository.class);
        NormalUserRepository normalUserRepositoryMock = mock(NormalUserRepository.class);
        ImageRepository imageRepositoryMock = mock(ImageRepository.class);

        NormalUser user = new NormalUser(1l,"Lars","Lars","Lars","Lars","Lars","Lars");
        GetUserDTO userDTO = new GetUserDTO(user);

        Product PokemonDiamond = Product.builder().id(1l).title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(Collections.emptyList()).seller(user).build();
        Product PokemonPearl= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(Collections.emptyList()).seller(user).build();

        when(genreRepositoryMock.getById(1l))
                .thenReturn(new Genre(1l,null, null));
        when(productRepositoryMock.findAll())
                .thenReturn(List.of(PokemonDiamond, PokemonPearl));


        ProductService productServiceMock = new ProductServiceImpl(productRepositoryMock, genreRepositoryMock, normalUserRepositoryMock, imageRepositoryMock);

        List<GetProductDTO> actualResult = productServiceMock.getAllProducts();



        BasicProductInfo product1 = BasicProductInfo.builder().title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genreId(1l).productType("GAME").images(Collections.emptyList()).build();
        BasicProductInfo product2 = BasicProductInfo.builder().title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genreId(1l).productType("GAME").images(Collections.emptyList()).build();

        GetProductDTO PokemonDiamondDTO = new GetProductDTO(1l, product1, userDTO);
        GetProductDTO PokemonPearlDTO = new GetProductDTO(2l, product2, userDTO);

        List<GetProductDTO> expectedResult = List.of(PokemonDiamondDTO, PokemonPearlDTO);

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).findAll();

    }

    @Test
    void getProducts() {
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        GenreRepository genreRepositoryMock = mock(GenreRepository.class);
        NormalUserRepository normalUserRepositoryMock = mock(NormalUserRepository.class);
        ImageRepository imageRepositoryMock = mock(ImageRepository.class);

        NormalUser user = new NormalUser(1l,"Lars","Lars","Lars","Lars","Lars","Lars");
        GetUserDTO userDTO = new GetUserDTO(user);

        Product PokemonDiamond = Product.builder().id(1l).title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(Collections.emptyList()).seller(user).build();
        Product PokemonPearl= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(Collections.emptyList()).seller(user).build();

        when(genreRepositoryMock.getById(1l))
                .thenReturn(new Genre(1l,null, null));
        when(productRepositoryMock.findProductsByTitleLikeOrSubTitleIsLikeOrSeriesIsLikeOrConditionIsLikeOrGenre_GenreIsLike("%Pokemon%","%Pokemon%","%Pokemon%","%Pokemon%","%Pokemon%"))
                .thenReturn(List.of(PokemonDiamond, PokemonPearl));


        ProductService productServiceMock = new ProductServiceImpl(productRepositoryMock, genreRepositoryMock, normalUserRepositoryMock, imageRepositoryMock);

        List<GetProductDTO> actualResult = productServiceMock.getProducts("Pokemon");

        BasicProductInfo product1 = BasicProductInfo.builder().title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genreId(1l).productType("GAME").images(Collections.emptyList()).build();
        BasicProductInfo product2 = BasicProductInfo.builder().title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genreId(1l).productType("GAME").images(Collections.emptyList()).build();

        GetProductDTO PokemonDiamondDTO = new GetProductDTO(1l, product1, userDTO);
        GetProductDTO PokemonPearlDTO = new GetProductDTO(2l, product2, userDTO);

        List<GetProductDTO> expectedResult = List.of(PokemonDiamondDTO, PokemonPearlDTO);

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).findProductsByTitleLikeOrSubTitleIsLikeOrSeriesIsLikeOrConditionIsLikeOrGenre_GenreIsLike("%Pokemon%","%Pokemon%","%Pokemon%","%Pokemon%","%Pokemon%");



        List<GetProductDTO> actualResultWithEmptyInput = productServiceMock.getProducts("");
        List<GetProductDTO> expectedResultWithEmptyInput  = Collections.emptyList();

        assertEquals(expectedResultWithEmptyInput, actualResultWithEmptyInput);
    }

    @Test
    void getProduct() {
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        GenreRepository genreRepositoryMock = mock(GenreRepository.class);
        NormalUserRepository normalUserRepositoryMock = mock(NormalUserRepository.class);
        ImageRepository imageRepositoryMock = mock(ImageRepository.class);

        NormalUser user = new NormalUser(1l,"Lars","Lars","Lars","Lars","Lars","Lars");
        GetUserDTO userDTO = new GetUserDTO(user);

        Product PokemonPearl= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(Collections.emptyList()).seller(user).build();

        when(genreRepositoryMock.getById(1l))
                .thenReturn(new Genre(1l,null, null));
        when(productRepositoryMock.findProductsByIdIs(2l))
                .thenReturn(PokemonPearl);

        ProductService productServiceMock = new ProductServiceImpl(productRepositoryMock, genreRepositoryMock, normalUserRepositoryMock, imageRepositoryMock);

        GetProductDTO actualResult = productServiceMock.getProduct(2l);

        BasicProductInfo product2 = BasicProductInfo.builder().title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genreId(1l).productType("GAME").images(Collections.emptyList()).build();

        GetProductDTO PokemonPearlDTO = new GetProductDTO(2l, product2, userDTO);

        GetProductDTO expectedResult = PokemonPearlDTO;

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).findProductsByIdIs(2l);

        GetProductDTO actualResultWithNullID = productServiceMock.getProduct( null);

        assertEquals(null, actualResultWithNullID);
    }

    @Test
    void getAllOfAUsersProducts()  {
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        GenreRepository genreRepositoryMock = mock(GenreRepository.class);
        NormalUserRepository normalUserRepositoryMock = mock(NormalUserRepository.class);
        ImageRepository imageRepositoryMock = mock(ImageRepository.class);

        Product PokemonPearl= Product.builder().id(1l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(Collections.emptyList()).seller(new NormalUser()).build();

        when(productRepositoryMock.findAllBySeller_Id(1l))
                .thenReturn(List.of(PokemonPearl));

        ProductService productServiceMock = new ProductServiceImpl(productRepositoryMock, genreRepositoryMock, normalUserRepositoryMock, imageRepositoryMock);

        List<GetProductDTO> actualResult = productServiceMock.getAllOfAUsersProducts(1l);
        List<GetProductDTO> expectedResult = List.of(new GetProductDTO(PokemonPearl));

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).findAllBySeller_Id(1l);
    }

    @Test
    void deleteProduct() {
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        GenreRepository genreRepositoryMock = mock(GenreRepository.class);
        NormalUserRepository normalUserRepositoryMock = mock(NormalUserRepository.class);
        ImageRepository imageRepositoryMock = mock(ImageRepository.class);

        ProductService productServiceMock = new ProductServiceImpl(productRepositoryMock, genreRepositoryMock, normalUserRepositoryMock, imageRepositoryMock);

        productServiceMock.deleteProduct(1l);

        verify(productRepositoryMock).getById(1l);
        verify(productRepositoryMock).deleteById(1l);
    }

    @Test
    void addProduct() {
        ProductRepository productRepositoryMock = mock(ProductRepository.class);
        GenreRepository genreRepositoryMock = mock(GenreRepository.class);
        NormalUserRepository normalUserRepositoryMock = mock(NormalUserRepository.class);
        ImageRepository imageRepositoryMock = mock(ImageRepository.class);

        List<Image> images = new ArrayList<>();

        images.add(new Image(0,"aa", null));
        images.add(new Image(0,"aaa", null));

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("aa");
        imageUrls.add("aaa");


        Product PokemonPearl= Product.builder().title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(images).build();

        Product PokemonPearl2= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(images).build();

        when(genreRepositoryMock.getById(1l))
                .thenReturn(new Genre(1l,null, null));
        when(productRepositoryMock.save(PokemonPearl))
                .thenReturn(PokemonPearl2);

        ProductService productServiceMock = new ProductServiceImpl(productRepositoryMock, genreRepositoryMock, normalUserRepositoryMock, imageRepositoryMock);


        CreateProductRequestDTO createProductRequestDTO = new CreateProductRequestDTO( new BasicProductInfo("Pokemon","Pearl","Pokemon",2022,10.01,"TRASH","Pokemon game",1l,"GAME", imageUrls), 1l);
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
        GenreRepository genreRepositoryMock = mock(GenreRepository.class);
        NormalUserRepository normalUserRepositoryMock = mock(NormalUserRepository.class);
        ImageRepository imageRepositoryMock = mock(ImageRepository.class);


        List<Image> images = new ArrayList<>();

        images.add(new Image(0,"aa", null));
        images.add(new Image(0,"aaa", null));

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("aa");
        imageUrls.add("aaa");


        Product PokemonPearl= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(images).build();

        Product PokemonPearl2= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(images).build();

        when(genreRepositoryMock.getById(1l))
                .thenReturn(new Genre(1l,null, null));
        when(productRepositoryMock.save(PokemonPearl))
                .thenReturn(PokemonPearl2);

        ProductService productServiceMock = new ProductServiceImpl(productRepositoryMock, genreRepositoryMock, normalUserRepositoryMock, imageRepositoryMock);


        UpdateProductRequestDTO updateProductRequestDTO = new UpdateProductRequestDTO ( 2l,new BasicProductInfo("Pokemon","Pearl","Pokemon",2022,10.01,"TRASH","Pokemon game",1l,"GAME", imageUrls));

        UpdateProductResponseDTO actualResult = productServiceMock.updateProduct(updateProductRequestDTO);

        UpdateProductResponseDTO expectedResult = UpdateProductResponseDTO.builder()
                .productId(2l)
                .build();

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).save(PokemonPearl);
    }
}