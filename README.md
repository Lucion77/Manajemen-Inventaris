# Warehouse Management System

Proyek ini adalah implementasi sistem manajemen inventaris barang berbasis Web menggunakan Java 17, Javalin framework, dan database SQLite (melalui JDBC).

## Persyaratan Pengerjaan yang Telah Dipenuhi
1. **Perancangan/Pemodelan**: Terdapat pada dokumentasi awal (SDLC).
2. **Bahasa & Web App**: Aplikasi ditulis dalam Java dan mengakses web via `localhost:8080`.
3. **Database Eksternal**: Menggunakan SQLite (`sqlite-jdbc`).
4. **Desain UI (Input/Output)**: Terdapat `index.html` dengan desain responsif (CSS) dan interaktif (JavaScript `fetch`).
5. **Struktur Kontrol**: Menerapkan blok `if-else` (di `SQLiteProductRepository`), pengulangan `for`/`while` (di `ReportService` & `SQLiteProductRepository`).
6. **Fungsi/Method & Array**: Method `exportToCsv` membuat array dari list obyek `Product`.
7. **Pewarisan & Polimorfisme**: Class `Product` mewariskan class abstrak `BaseEntity` beserta overriding method `getDetails()`. Terdapat juga overloading method di `Repository`.
8. **File I/O**: Aplikasi dapat mengekspor data SQLite ke dalam file CSV (`inventory_report.csv`).
9. **Namespaces**: Disusun rapi ke dalam beberapa package (e.g. `com.wms.main`, `com.wms.model`, `com.wms.repository`, dsb).
10. **Dokumentasi**: Kode dilengkapi standar **Javadoc**.
11. **Unit Testing**: Diuji menggunakan framework eksternal JUnit 5 (lihat file di `src/test/java`).

## Cara Menjalankan Aplikasi
1. Buka terminal/CMD/PowerShell lalu navigasikan ke folder proyek ini.
2. Jalankan perintah untuk Test: 
   ```bash
   mvn clean test
   ```
3. Jalankan perintah untuk Build/Compile: 
   ```bash
   mvn clean package
   ```
4. Jalankan aplikasi:
   ```bash
   java -jar target/warehouse-management-system-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```
5. Buka Browser dan akses: `http://localhost:8080`
