package com.wms.test;

import com.wms.model.Product;
import com.wms.repository.Repository;
import com.wms.repository.SQLiteProductRepository;
import com.wms.util.DatabaseConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {

    private static Repository<Product> repo;

    @BeforeAll
    public static void setup() {
        DatabaseConfig.initDatabase();
        repo = new SQLiteProductRepository();
    }

    @Test
    public void testSaveAndFindAll() {
        Product p = new Product(0, "Test Item", "Test Cat", 10, 99.99);
        repo.save(p);
        
        List<Product> list = repo.findAll();
        assertFalse(list.isEmpty(), "Daftar produk tidak boleh kosong setelah save");
        
        // Find the saved product
        boolean found = false;
        for (Product product : list) {
            if ("Test Item".equals(product.getName())) {
                found = true;
                break;
            }
        }
        assertTrue(found, "Produk yang ditambahkan tidak ditemukan");
    }
}
