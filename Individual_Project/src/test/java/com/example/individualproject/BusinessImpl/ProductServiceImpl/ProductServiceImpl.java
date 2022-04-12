package com.example.individualproject.BusinessImpl.ProductServiceImpl;

public class ProductServiceImpl {

   /* @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void test_GetAllProducts() {

        List<GetProductDTO> result = productService.getAllProducts();

        assertEquals(0, result.size());
    }

    @Test
    void test_GetProductsByName() {

        CreateProductRequestDTO createProductRequestDTO = new CreateProductRequestDTO("lars","lars","lars",2000,10.10,"GOOD","lars","JRPG","GAME" );

        productService.addProduct(createProductRequestDTO);

        List<GetProductDTO> result = productService.getProducts("lars");
        assertEquals(1, result.size());

        result = productService.getProducts("jon");
        assertEquals(0, result.size());
    }

    @Test
    void test_GetProductsById() {

        CreateProductRequestDTO createProductRequestDTO = new CreateProductRequestDTO("lars","lars","lars",2000,10.10,"GOOD","lars","JRPG","GAME" );

        productService.addProduct(createProductRequestDTO);

        GetProductDTO result = productService.getProduct(1l);
        assertEquals("lars", result.getTitle());
        assertEquals("lars", result.getSub_title());
        assertEquals("lars", result.getSeries());
        assertEquals(2000, result.getYear());
        assertEquals(10.10, result.getPrice());
        assertEquals("lars", result.getCondition_());
        assertEquals("lars", result.getDescription());
        assertEquals("JRPG", result.getGenre());
        assertEquals("GAME", result.getProduct_type());
    }

    @Test
    void test_GetAllOfAUsersProducts() {
        //ToDo make this test
    }

    @Test
    void test_DeleteProduct() {

        CreateProductRequestDTO createProductRequestDTO = new CreateProductRequestDTO("lars","lars","lars",2000,10.10,"GOOD","lars","JRPG","GAME" );

        productService.addProduct(createProductRequestDTO);

        List<GetProductDTO> result = productService.getAllProducts();

        assertEquals(1, result.size());

        productService.deleteProduct(1l);

        result = productService.getAllProducts();

        assertEquals(0, result.size());
    }

    @Test
    void test_AddProduct() {

        CreateProductRequestDTO createProductRequestDTO = new CreateProductRequestDTO("lars","lars","lars",2000,10.10,"GOOD","lars","JRPG","GAME" );

        productService.addProduct(createProductRequestDTO);

        List<GetProductDTO> result = productService.getAllProducts();

        assertEquals(1, result.size());
    }

    @Test
    void test_UpdateProduct() {

        CreateProductRequestDTO createProductRequestDTO = new CreateProductRequestDTO("lars","lars","lars",2000,10.10,"GOOD","lars","JRPG","GAME" );

        productService.addProduct(createProductRequestDTO);

        GetProductDTO result = productService.getProduct(1l);

        assertEquals("lars", result.getTitle());
        assertEquals("lars", result.getSub_title());
        assertEquals("lars", result.getSeries());
        assertEquals(2000, result.getYear());
        assertEquals(10.10, result.getPrice());
        assertEquals("lars", result.getCondition_());
        assertEquals("lars", result.getDescription());
        assertEquals("JRPG", result.getGenre());
        assertEquals("GAME", result.getProduct_type());

        UpdateProductRequestDTO updateProductRequestDTO = new UpdateProductRequestDTO(1l,"lars2","lars2","lars2",2002,10.12,"GOOD","lars2","JRPG","GAME" );

        productService.updateProduct(updateProductRequestDTO);

        result = productService.getProduct(1l);

        assertEquals("lars2", result.getTitle());
        assertEquals("lars2", result.getSub_title());
        assertEquals("lars2", result.getSeries());
        assertEquals(2002, result.getYear());
        assertEquals(10.12, result.getPrice());
        assertEquals("lars2", result.getCondition_());
        assertEquals("lars2", result.getDescription());
        assertEquals("JRPG", result.getGenre());
        assertEquals("GAME", result.getProduct_type());
    }*/
}
