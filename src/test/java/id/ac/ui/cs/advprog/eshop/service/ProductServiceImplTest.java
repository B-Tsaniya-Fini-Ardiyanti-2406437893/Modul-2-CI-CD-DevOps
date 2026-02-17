package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl service;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }

    @Test
    void testCreateAndFind() {
        when(productRepository.create(product)).thenReturn(product);

        service.create(product);

        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        Iterator<Product> iterator = List.of(product).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = service.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(product, result.getFirst());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testCreateProductWhenIdIsNull() {
        Product productWithoutId = new Product();
        productWithoutId.setProductName("Barang Tanpa ID");
        productWithoutId.setProductQuantity(5);

        when(productRepository.create(productWithoutId)).thenReturn(productWithoutId);

        Product result = service.create(productWithoutId);

        assertNotNull(result.getProductId());
        assertEquals("Barang Tanpa ID", result.getProductName());
        verify(productRepository, times(1)).create(productWithoutId);
    }

    @Test
    void testFindById() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        when(productRepository.findById(productId)).thenReturn(product);

        Product result = service.findById(productId);

        assertEquals(product, result);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testEditProduct() {
        when(productRepository.edit(product)).thenReturn(product);

        Product result = service.edit(product);

        assertEquals(product, result);
        verify(productRepository, times(1)).edit(product);
    }

    @Test
    void testDeleteProduct() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        service.delete(productId);

        verify(productRepository, times(1)).delete(productId);
    }
}