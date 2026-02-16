1. Meaningful name
2. Fucntion Should Do One Thing
3. Comments
4. Single Responsibility Principle (SRP)

## Reflection 1

### Clean Code Principles Applied

Berikut adalah prinsip-prinsip **Clean Code** yang telah saya terapkan dalam tutorial ini:

1.  **Meaningful Names (Penamaan yang Jelas):**
    Saya menggunakan nama variabel, method, dan class yang deskriptif dan intuitif.
    * *Penerapan:* Penggunaan nama class `ProductRepository` untuk lapisan data, dan method seperti `deleteProduct(String productId)` yang secara eksplisit menjelaskan tujuannya.

2.  **Functions Should Do One Thing:**
    Setiap fungsi dirancang untuk melakukan satu tugas spesifik dengan baik. Fungsi yang fokus membuat kode lebih mudah dibaca dan diuji (*testable*).
    * *Penerapan:* Method `create(Product product)` pada Repository hanya berfokus pada penambahan objek ke dalam list, tanpa melakukan tugas tambahan lain.

3.  **Proper Comments (Komentar Seperlunya):**
    Kode ditulis sedemikian rupa agar dapat menjelaskan dirinya sendiri (*self-explanatory*).
    * *Penerapan:* Saya tidak memberikan komentar pada kode yang sudah jelas (seperti *setter/getter*).

4.  **Single Responsibility Principle (SRP):**
    Setiap class atau modul memiliki satu tanggung jawab utama. Hal ini menjaga agar kode tetap *maintainable*.
    * *Penerapan:* Saya memisahkan tanggung jawab antara `ProductController` (yang hanya menangani *request* HTTP dan *routing*) dengan `ProductRepository` (yang fokus pada manipulasi data).

5.  **Don't Repeat Yourself (DRY):**
    Saya menghindari duplikasi kode dengan membuat logika yang berulang ke dalam method atau fungsi terpisah.
    * *Penerapan:* Memastikan tidak ada blok kode yang sama persis (copy-paste) di dua tempat berbeda, terutama dalam logic validasi atau manipulasi ID.

6.  **Keep It Simple:**
    Saya mengutamakan kesederhanaan dalam implementasi.
    * *Penerapan:* Menggunakan struktur data `ArrayList` sederhana untuk penyimpanan data sementara, karena kebutuhan saat ini belum memerlukan kompleksitas database relasional penuh.

## Reflection 1

1. **After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?**

   Menurut saya, tidak ada angka yang pasti untuk menentukan jumlah unit test yang harus ada dalam satu class. Jumlahnya bergantung pada kompleksitas method yang kita buat. Lalu, untuk memastikan unit test cukup, kita bisa menggunakan Code Coverage sebagai parameter kita. Namun, code coverage yang tinggi belum cukup; kita juga harus memastikan untuk menguji:
    - Positive Scenarios,
    - Negative Scenarios,
    - Edge Cases, dan
    - memastikan logika yang bercabang (if-else atau switch) di mana setiap cabang harus dites.

   Selain itu, Code Coverage yang 100% tidak berarti kode kita sudah tidak ada bugs atau error. Code Coverage hanya berarti setiap baris kode telah dieksekusi setidaknya sekali selama testing; itu tidak menjamin kode sudah aman.

2. **Suppose that after writing the `CreateProductFunctionalTest.java` along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!**

   Jika saya membuat functional test baru dengan cara menyalin setup dan variable instance dari class sebelumnya, itu akan menurunkan kualitas kode. Walaupun kodenya berjalan, hal ini menciptakan duplikasi kode yang tidak perlu. Hal ini juga akan merusak kebersihan kode karena melanggar prinsip **DRY (Don't Repeat Yourself)**; kode akan sulit di-maintain karena jika ada perubahan, kita harus mengubah semua file test satu per satu secara manual.

   Solusi terbaik dari kondisi ini adalah menerapkan prinsip **Inheritance** atau membuat **Base Test Class** di mana semua variabel konfigurasi (seperti `serverPort`, `testBaseUrl`, `baseUrl`) dan method setup (`@BeforeEach`) dipindahkan ke dalam satu class induk ini. Lalu, class test lainnya (seperti `CreateProductFunctionalTest`, `HomePageFunctionalTest`, dll) cukup melakukan `extends BaseFunctionalTest`.


testing