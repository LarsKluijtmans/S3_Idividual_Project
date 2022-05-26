package com.example.individualproject.business.impl;

import com.example.individualproject.dto.login.AccessTokenDTO;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepositoryMock;
    @Mock
    private GenreRepository genreRepositoryMock ;
    @Mock
    private NormalUserRepository normalUserRepositoryMock;
    @Mock
    private  ImageRepository imageRepositoryMock;
    @Mock
    private AccessTokenDTO requestAccessToken;
    @InjectMocks
    private ProductServiceImpl productServiceMock;

    @Test
    void getAllProducts() {
        NormalUser user = new NormalUser(1l,"Lars","Lars","Lars","Lars","Lars","Lars");
        GetUserDTO userDTO = new GetUserDTO(user);

        Product PokemonDiamond = Product.builder().id(1l).title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(Collections.emptyList()).seller(user).build();
        Product PokemonPearl= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(Collections.emptyList()).seller(user).build();

        when(productRepositoryMock.findAll())
                .thenReturn(List.of(PokemonDiamond, PokemonPearl));

        List<GetProductDTO> actualResult = productServiceMock.getAllProducts();

        BasicProductInfo product1 = BasicProductInfo.builder().title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genreId(1l).productType("GAME").images(Collections.emptyList()).build();
        BasicProductInfo product2 = BasicProductInfo.builder().title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genreId(1l).productType("GAME").images(Collections.emptyList()).build();

        List<GetProductDTO> expectedResult = List.of(new GetProductDTO(1l, product1, userDTO),new GetProductDTO(2l, product2, userDTO));

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).findAll();

    }

    @Test
    void getProducts() {
        NormalUser user = new NormalUser(1l,"Lars","Lars","Lars","Lars","Lars","Lars");
        GetUserDTO userDTO = new GetUserDTO(user);

        Product PokemonDiamond = Product.builder().id(1l).title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(Collections.emptyList()).seller(user).build();
        Product PokemonPearl= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(Collections.emptyList()).seller(user).build();

        when(productRepositoryMock.findProductsByTitleLikeOrSubTitleIsLikeOrSeriesIsLikeOrConditionIsLikeOrGenre_GenreIsLike("%Pokemon%","%Pokemon%","%Pokemon%","%Pokemon%","%Pokemon%"))
                .thenReturn(List.of(PokemonDiamond, PokemonPearl));

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

        NormalUser user = new NormalUser(1l,"Lars","Lars","Lars","Lars","Lars","Lars");
        GetUserDTO userDTO = new GetUserDTO(user);

        Product PokemonPearl= Product.builder().id(2l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(Collections.emptyList()).seller(user).build();

        when(productRepositoryMock.findProductsByIdIs(2l))
                .thenReturn(PokemonPearl);

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


        Product PokemonPearl= Product.builder().id(1l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(Collections.emptyList()).seller(new NormalUser()).build();

        when(requestAccessToken.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(requestAccessToken.getUserId())
                .thenReturn(1l);

        when(productRepositoryMock.findAllBySeller_Id(1l))
                .thenReturn(List.of(PokemonPearl));

        List<GetProductDTO> actualResult = productServiceMock.getAllOfAUsersProducts(1l);
        List<GetProductDTO> expectedResult = List.of(new GetProductDTO(PokemonPearl));

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).findAllBySeller_Id(1l);
    }

    @Test
    void deleteProduct() {
       when(requestAccessToken.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(requestAccessToken.getUserId())
                .thenReturn(1l);
        when(productRepositoryMock.findProductsByIdIs(1l))
                .thenReturn(Product.builder()
                        .seller(new NormalUser(1l,"lars","lars","lars","lars","lars","lars"))
                        .build());

        productServiceMock.deleteProduct(1l);

        verify(productRepositoryMock).findProductsByIdIs(1l);
        verify(productRepositoryMock).deleteById(1l);
    }

    @Test
    void addProduct() {
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

        CreateProductRequestDTO createProductRequestDTO = new CreateProductRequestDTO( new BasicProductInfo("Pokemon","Pearl","Pokemon",2022,10.01,"TRASH","Pokemon game",1l,"GAME", imageUrls), 1l);
        CreateProductResponseDTO actualResult = productServiceMock.addProduct(createProductRequestDTO);

        CreateProductResponseDTO expectedResult = CreateProductResponseDTO.builder()
                .productId(2l)
                .build();

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).save(PokemonPearl);
    }
}