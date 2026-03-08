package com.wms.main;

import com.wms.model.Product;
import com.wms.repository.Repository;
import com.wms.repository.SQLiteProductRepository;
import com.wms.service.ReportService;
import com.wms.util.DatabaseConfig;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.util.List;

/**
 * Main Application Class.
 * Menginisialisasi Web Server menggunakan library eksternal Javalin.
 */
public class App {
    public static void main(String[] args) {
        // Inisialisasi Database
        DatabaseConfig.initDatabase();

        // Deklarasi Repository menggunakan tipe Interface (Polimorfisme)
        Repository<Product> repo = new SQLiteProductRepository();
        ReportService reportService = new ReportService();

        // Setup Javalin Web Server
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(8080);
        
        System.out.println("Server started at http://localhost:8080");

        // API GET semua produk
        app.get("/api/products", ctx -> {
            List<Product> products = repo.findAll();
            ctx.json(products);
        });

        // API POST tambah produk
        app.post("/api/products", ctx -> {
            Product p = ctx.bodyAsClass(Product.class);
            repo.save(p);
            ctx.status(201).result("Product added successfully");
        });

        // API DELETE produk
        app.delete("/api/products/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repo.delete(id);
            ctx.status(200).result("Product deleted successfully");
        });

        // API POST ekspor data ke CSV
        app.post("/api/export", ctx -> {
            List<Product> products = repo.findAll();
            boolean success = reportService.exportToCsv(products, "inventory_report.csv");
            if (success) {
                ctx.status(200).result("Exported to inventory_report.csv successfully!");
            } else {
                ctx.status(500).result("Failed to export report.");
            }
        });
    }
}
