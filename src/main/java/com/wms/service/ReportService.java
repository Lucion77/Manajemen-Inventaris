package com.wms.service;

import com.wms.model.Product;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Service untuk menangani logika bisnis seperti pembuatan laporan.
 * Menunjukkan penggunaan Array, Percabangan, Pengulangan, dan File I/O.
 */
public class ReportService {

    /**
     * Mengekspor data produk ke file CSV (Media Penyimpan).
     */
    public boolean exportToCsv(List<Product> productList, String filePath) {
        // Konversi List ke Array (Syarat Tugas: Penggunaan Array)
        Product[] productsArray = productList.toArray(new Product[0]);
        
        // File I/O (Menyimpan data di media penyimpan)
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("ID,Name,Category,Quantity,Price\n");
            
            // Pengulangan (For loop) menggunakan Array
            for (int i = 0; i < productsArray.length; i++) {
                Product p = productsArray[i];
                
                // Percabangan (If) untuk validasi sederhana
                if (p != null) {
                    writer.write(p.getId() + "," +
                            p.getName() + "," +
                            p.getCategory() + "," +
                            p.getQuantity() + "," +
                            p.getPrice() + "\n");
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
