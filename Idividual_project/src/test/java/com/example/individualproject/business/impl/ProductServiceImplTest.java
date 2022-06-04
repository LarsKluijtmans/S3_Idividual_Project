package com.example.individualproject.business.impl;

import com.example.individualproject.business.exception.InvalidCredentialsException;
import com.example.individualproject.business.exception.UserNotFoundException;
import com.example.individualproject.dto.login.AccessTokenDTO;
import com.example.individualproject.dto.products.*;
import com.example.individualproject.dto.users.GetUserDTO;
import com.example.individualproject.dto.users.UpdateUserRequestDTO;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    //getAllProducts
    @Test
    void getAllProducts() {
        NormalUser user = new NormalUser(1l,"Lars","Lars","Lars","Lars","Lars","Lars");
        GetUserDTO userDTO = GetUserDTO.builder()
                .username(user.getUsername())
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .email(user.getEmail())
                .phoneNumber(user.getPhonenumber())
                .position("NORMAL")
                .build();

        Product PokemonDiamond = Product.builder()
                .id(1l)
                .title("Pokemon")
                .subTitle("diamond")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genre(new Genre(1l,"genre", null))
                .sold(false).
                productType("GAME")
                .images(Collections.emptyList())
                .seller(user)
                .build();

        Product PokemonPearl= Product.builder()
                .id(2l)
                .title("Pokemon")
                .subTitle("Pearl")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genre(new Genre(1l,"genre", null))
                .sold(false)
                .productType("GAME")
                .images(Collections.emptyList())
                .seller(user)
                .build();


        GetProductDTO PokemonDiamondDTO = GetProductDTO.builder()
                .id(PokemonDiamond.getId())
                .title(PokemonDiamond.getTitle())
                .subTitle(PokemonDiamond.getSubTitle())
                .series(PokemonDiamond.getSeries())
                .year(PokemonDiamond.getYear())
                .price(PokemonDiamond.getPrice())
                .condition(PokemonDiamond.getCondition())
                .description(PokemonDiamond.getDescription())
                .genre(PokemonDiamond.getGenre().getGenre())
                .productType(PokemonDiamond.getProductType())
                .images(Collections.emptyList())
                .seller(userDTO)
                .build();

        GetProductDTO PokemonPearlDTO = GetProductDTO.builder()
                .id(PokemonPearl.getId())
                .title(PokemonPearl.getTitle())
                .subTitle(PokemonPearl.getSubTitle())
                .series(PokemonPearl.getSeries())
                .year(PokemonPearl.getYear())
                .price(PokemonPearl.getPrice())
                .condition(PokemonPearl.getCondition())
                .description(PokemonPearl.getDescription())
                .genre(PokemonPearl.getGenre().getGenre())
                .productType(PokemonPearl.getProductType())
                .images(Collections.emptyList())
                .seller(userDTO)
                .build();

        when(productRepositoryMock.findAllBySold(false))
                .thenReturn(List.of(PokemonDiamond, PokemonPearl));

        List<GetProductDTO> actualResult = productServiceMock.getAllProducts();

        List<GetProductDTO> expectedResult = List.of(PokemonDiamondDTO, PokemonPearlDTO);

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).findAllBySold(false);
    }

    //getProducts
    @Test
    void getProducts() {
        NormalUser user = new NormalUser(1l,"Lars","Lars","Lars","Lars","Lars","Lars");
        GetUserDTO userDTO = GetUserDTO.builder()
                .username(user.getUsername())
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .email(user.getEmail())
                .phoneNumber(user.getPhonenumber())
                .position("NORMAL")
                .build();

        Product PokemonDiamond = Product.builder()
                .id(1l)
                .title("Pokemon")
                .subTitle("diamond")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genre(new Genre(1l,"genre", null))
                .sold(false).
                productType("GAME")
                .images(Collections.emptyList())
                .seller(user)
                .build();

        Product PokemonPearl= Product.builder()
                .id(2l)
                .title("Pokemon")
                .subTitle("Pearl")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genre(new Genre(1l,"genre", null))
                .sold(false)
                .productType("GAME")
                .images(Collections.emptyList())
                .seller(user)
                .build();


        GetProductDTO PokemonDiamondDTO = GetProductDTO.builder()
                .id(PokemonDiamond.getId())
                .title(PokemonDiamond.getTitle())
                .subTitle(PokemonDiamond.getSubTitle())
                .series(PokemonDiamond.getSeries())
                .year(PokemonDiamond.getYear())
                .price(PokemonDiamond.getPrice())
                .condition(PokemonDiamond.getCondition())
                .description(PokemonDiamond.getDescription())
                .genre(PokemonDiamond.getGenre().getGenre())
                .productType(PokemonDiamond.getProductType())
                .images(Collections.emptyList())
                .seller(userDTO)
                .build();

        GetProductDTO PokemonPearlDTO = GetProductDTO.builder()
                .id(PokemonPearl.getId())
                .title(PokemonPearl.getTitle())
                .subTitle(PokemonPearl.getSubTitle())
                .series(PokemonPearl.getSeries())
                .year(PokemonPearl.getYear())
                .price(PokemonPearl.getPrice())
                .condition(PokemonPearl.getCondition())
                .description(PokemonPearl.getDescription())
                .genre(PokemonPearl.getGenre().getGenre())
                .productType(PokemonPearl.getProductType())
                .images(Collections.emptyList())
                .seller(userDTO)
                .build();

        when(productRepositoryMock.findProductsByTitleLikeOrSubTitleIsLikeOrSeriesIsLikeOrConditionIsLikeOrGenre_GenreIsLikeAndSold("%Pokemon%","%Pokemon%","%Pokemon%","%Pokemon%","%Pokemon%", false))
                .thenReturn(List.of(PokemonDiamond, PokemonPearl));

        List<GetProductDTO> actualResult = productServiceMock.getProducts("Pokemon");

        List<GetProductDTO> expectedResult = List.of(PokemonDiamondDTO, PokemonPearlDTO);

        assertEquals(expectedResult, actualResult);

        verify(productRepositoryMock).findProductsByTitleLikeOrSubTitleIsLikeOrSeriesIsLikeOrConditionIsLikeOrGenre_GenreIsLikeAndSold("%Pokemon%","%Pokemon%","%Pokemon%","%Pokemon%","%Pokemon%", false);

        List<GetProductDTO> actualResultWithEmptyInput = productServiceMock.getProducts("");
        List<GetProductDTO> expectedResultWithEmptyInput  = Collections.emptyList();

        assertEquals(expectedResultWithEmptyInput, actualResultWithEmptyInput);
    }

    //getProduct
    @Test
    void getProduct() {

        NormalUser user = new NormalUser(1l,"Lars","Lars","Lars","Lars","Lars","Lars");
        GetUserDTO userDTO = GetUserDTO.builder()
                .username(user.getUsername())
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .email(user.getEmail())
                .phoneNumber(user.getPhonenumber())
                .position("NORMAL")
                .build();

        Product PokemonDiamond = Product.builder()
                .id(1l)
                .title("Pokemon")
                .subTitle("diamond")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genre(new Genre(1l,"genre", null))
                .sold(false).
                productType("GAME")
                .images(Collections.emptyList())
                .seller(user)
                .build();

        when(productRepositoryMock.findProductsByIdIsAndSold(1l, false))
                .thenReturn(PokemonDiamond);

        GetProductDTO actualResult = productServiceMock.getProduct(1l);

        GetProductDTO PokemonDiamondDTO = GetProductDTO.builder()
                .id(PokemonDiamond.getId())
                .title(PokemonDiamond.getTitle())
                .subTitle(PokemonDiamond.getSubTitle())
                .series(PokemonDiamond.getSeries())
                .year(PokemonDiamond.getYear())
                .price(PokemonDiamond.getPrice())
                .condition(PokemonDiamond.getCondition())
                .description(PokemonDiamond.getDescription())
                .genre(PokemonDiamond.getGenre().getGenre())
                .productType(PokemonDiamond.getProductType())
                .images(Collections.emptyList())
                .seller(userDTO)
                .build();

        assertEquals(PokemonDiamondDTO, actualResult);

        verify(productRepositoryMock).findProductsByIdIsAndSold(1l, false);

        GetProductDTO actualResultWithNullID = productServiceMock.getProduct( null);

        assertEquals(null, actualResultWithNullID);
    }

    //getAllOfAUsersProducts
    @Test
    void getAllOfAUsersProducts()  {
        Product PokemonPearl= Product.builder().id(1l).title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre(new Genre(1l,null, null)).sold(false).productType("GAME").images(Collections.emptyList()).seller(new NormalUser()).build();

        when(requestAccessToken.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(requestAccessToken.getUserId())
                .thenReturn(1l);
        when(productRepositoryMock.findAllBySeller_Id(1l))
                .thenReturn(List.of(PokemonPearl));
        when(normalUserRepositoryMock.findByUsername("Lars"))
                .thenReturn(new NormalUser(1l,"Lars","Lars","Lars","Lars","Lars","Lars"));

        List<GetProductDTO> actualResult = productServiceMock.getAllOfAUsersProductsNormalUser("Lars");
        List<GetProductDTO> expectedResult = List.of(new GetProductDTO(PokemonPearl));

        assertEquals(expectedResult, actualResult);

        verify(requestAccessToken).hasRole("NORMALUSER");
        verify(requestAccessToken).getUserId();
        verify(normalUserRepositoryMock).findByUsername("Lars");
        verify(productRepositoryMock).findAllBySeller_Id(1l);
    }
    @Test
    void getAllOfAUsersProducts_userNorFound()  {
        when(requestAccessToken.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(normalUserRepositoryMock.findByUsername("Lars"))
                .thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> productServiceMock.getAllOfAUsersProductsNormalUser("Lars"));

        verify(requestAccessToken).hasRole("NORMALUSER");
    }
    @Test
    void getAllOfAUsersProducts_isntNormalUser()  {
        when(requestAccessToken.hasRole("NORMALUSER"))
                .thenReturn(false);

          assertThrows(InvalidCredentialsException.class, () -> productServiceMock.getAllOfAUsersProductsNormalUser("Lars"));

        verify(requestAccessToken).hasRole("NORMALUSER");
    }
    @Test
    void getAllOfAUsersProducts_IDNotMatched()  {

        when(requestAccessToken.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(requestAccessToken.getUserId())
                .thenReturn(2l);
        when(normalUserRepositoryMock.findByUsername("Lars"))
                .thenReturn(new NormalUser(1l,"Lars","Lars","Lars","Lars","Lars","Lars"));

        assertThrows(InvalidCredentialsException.class, () -> productServiceMock.getAllOfAUsersProductsNormalUser("Lars"));

        verify(normalUserRepositoryMock).findByUsername("Lars");

        verify(requestAccessToken).hasRole("NORMALUSER");
        verify(requestAccessToken).getUserId();
    }

    //getAllOfAUsersProductsAdmin
    @Test
    void getAllOfAUsersProductsAdmin()  {
        Product PokemonPearl= Product.builder()
                .id(1l).title("Pokemon")
                .subTitle("Pearl")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genre(new Genre(1l,null, null))
                .sold(false)
                .productType("GAME")
                .images(Collections.emptyList())
                .seller(new NormalUser())
                .build();

        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(true);
        when(productRepositoryMock.findAllBySeller_Id(1l))
                .thenReturn(List.of(PokemonPearl));
        when(normalUserRepositoryMock.findByUsername("Lars"))
                .thenReturn(new NormalUser(1l,"Lars","Lars","Lars","Lars","Lars","Lars"));

        List<GetProductDTO> actualResult = productServiceMock.getAllOfAUsersProductsAdmin("Lars");
        List<GetProductDTO> expectedResult = List.of(new GetProductDTO(PokemonPearl));

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepositoryMock).findByUsername("Lars");
        verify(requestAccessToken).hasRole("ADMIN");
        verify(productRepositoryMock).findAllBySeller_Id(1l);
    }
    @Test
    void getAllOfAUsersProductsAdmin_UserNotFound()  {

        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(true);
        when(normalUserRepositoryMock.findByUsername("Lars"))
                .thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> productServiceMock.getAllOfAUsersProductsAdmin("Lars"));

        verify(normalUserRepositoryMock).findByUsername("Lars");
        verify(requestAccessToken).hasRole("ADMIN");
    }
    @Test
    void getAllOfAUsersProductsAdmin_isntAdmin()  {
        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(false);
      
        assertThrows(InvalidCredentialsException.class, () -> productServiceMock.getAllOfAUsersProductsAdmin("Lars"));

        verify(requestAccessToken).hasRole("ADMIN");
    }

    //deleteProductNormalUser
    @Test
    void deleteProductNormalUser() {
        when(requestAccessToken.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(requestAccessToken.getUserId())
                .thenReturn(1l);
        when(productRepositoryMock.findProductsByIdIs(1l))
                .thenReturn(Product.builder()
                        .seller(new NormalUser(1l,"lars","lars","lars","lars","lars","lars"))
                        .build());

        productServiceMock.deleteProductNormalUser(1l);

        verify(requestAccessToken).hasRole("NORMALUSER");
        verify(requestAccessToken).getUserId();
        verify(productRepositoryMock).findProductsByIdIs(1l);
        verify(productRepositoryMock).deleteById(1l);
    }
    @Test
    void deleteProductNormalUser_isntNormalUser() {
        when(requestAccessToken.hasRole("NORMALUSER"))
                .thenReturn(false);
        when(productRepositoryMock.findProductsByIdIs(1l))
                .thenReturn(Product.builder()
                        .seller(new NormalUser(1l,"lars","lars","lars","lars","lars","lars"))
                        .build());

        assertThrows(InvalidCredentialsException.class, () ->  productServiceMock.deleteProductNormalUser(1l));

        verify(requestAccessToken).hasRole("NORMALUSER");
        verify(productRepositoryMock).findProductsByIdIs(1l);
    }
    @Test
    void deleteProductNormalUser_IDNotMatched() {
        when(requestAccessToken.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(requestAccessToken.getUserId())
                .thenReturn(2l);
        when(productRepositoryMock.findProductsByIdIs(1l))
                .thenReturn(Product.builder()
                        .seller(new NormalUser(1l,"lars","lars","lars","lars","lars","lars"))
                        .build());

        assertThrows(InvalidCredentialsException.class, () ->  productServiceMock.deleteProductNormalUser(1l));

        verify(productRepositoryMock).findProductsByIdIs(1l);
        verify(requestAccessToken).hasRole("NORMALUSER");
        verify(requestAccessToken).getUserId();
    }

    //deleteProductAdmin
    @Test
    void deleteProductAdmin() {
        Product PokemonPearl = Product.builder()
                .id(1l)
                .title("Pokemon")
                .subTitle("Pearl")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genre(new Genre(1l,null, null))
                .sold(false)
                .productType("GAME")
                .images(Collections.emptyList())
                .seller(new NormalUser())
                .build();

        when(productRepositoryMock.findProductsByIdIs(1l))
                .thenReturn(PokemonPearl);
        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(true);

        productServiceMock.deleteProductAdmin(1l);

        verify(requestAccessToken).hasRole("ADMIN");
        verify(imageRepositoryMock).deleteByProductId(1l);
        verify(productRepositoryMock).deleteById(1l);
    }
    @Test
    void deleteProductAdmin_NotAdmin() {
        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> productServiceMock.deleteProductAdmin(1l));

        verify(requestAccessToken).hasRole("ADMIN");
    }

    //addProduct
    @Test
    void addProduct() {

        Product PokemonPearl= Product.builder()
                .title("Pokemon")
                .subTitle("Pearl")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genre(new Genre(1l,null, null))
                .sold(false)
                .productType("GAME")
                .images(List.of(new Image(null,"aa", null),new Image(null,"aaa", null)))
                .seller(null)
                .build();

        Product PokemonPearl2= Product.builder()
                .id(2l)
                .title("Pokemon")
                .subTitle("Pearl")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genre(new Genre(1l,null, null))
                .sold(false)
                .productType("GAME")
                .images(List.of(new Image(0l,"aa", null),new Image(0l,"aaa", null)))
                .build();

        when(genreRepositoryMock.getById(1l))
                .thenReturn(new Genre(1l,null, null));
        when(productRepositoryMock.save(PokemonPearl))
                .thenReturn(PokemonPearl2);

        CreateProductRequestDTO createProductRequestDTO = CreateProductRequestDTO.builder()
                .title("Pokemon")
                .subTitle("Pearl")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genreId(1l)
                .productType("GAME")
                .images(List.of("aa","aaa"))
                .seller("user")
                .build();

        CreateProductResponseDTO actualResult = productServiceMock.addProduct(createProductRequestDTO);

        CreateProductResponseDTO expectedResult = CreateProductResponseDTO.builder()
                .productId(2l)
                .build();

        assertEquals(expectedResult, actualResult);

        verify(genreRepositoryMock).getById(1l);
        verify(productRepositoryMock).save(PokemonPearl);
    }
}