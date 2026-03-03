# 🛍️ Eshop Advance Programming

**👤 Name:** Tsaniya Fini Ardiyanti  
**📚 Class:** Advanced Programming B  
**🆔 Student Number:** 2406437893
---
## 🚀 Live Demo
🔗 **[Click This!](https://superb-luelle-tugas-advprog-eeb06ec5.koyeb.app/)** to see the deployed application.

<details>
<summary><b>📝 Refleksi Module 1: Coding Standards</b></summary>

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

</details>

<details>
<summary><b>📝 Refleksi Module 2: CI/CD & DevOps</b></summary>

## Reflection 2
### 📌 Soal 1: Code Quality Issues
List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
### 📝 Jawaban
> Selama mengerjakan exercise, saya memperbaiki beberapa isu terkait code quality. Salah satunya adalah *"Modifiers should be declared in the correct order"*, yang dalam praktiknya saya terapkan dengan menghapus modifier `public` yang tidak diperlukan pada test classes.
> **Strategi** yang saya lakukan adalah meniadakan access modifier `public` dari test classes dan test methods JUnit 5, seperti `ProductControllerTest` dan `ProductServiceImplTest`. Hal ini karena JUnit 5 tidak lagi mewajibkan test classes dan methods bersifat `public`. Menjadikan test classes sebagai *package-private* merupakan pendekatan yang lebih direkomendasikan agar kode lebih bersih dan ruang lingkupnya lebih terbatas (*strictly scoped*).

> Selain itu, saya juga menangani isu berupa *unused imports* di beberapa kelas Java. **Strategi** yang saya terapkan adalah meninjau ulang laporan dari SonarCloud untuk mengetahui secara tepat file mana saja yang memiliki isu tersebut, lalu menghapus *import* yang tidak digunakan. Langkah ini membantu menjaga kebersihan *codebase*, mengurangi potensi kebingungan, serta memastikan tidak ada dependensi yang tidak diperlukan yang ikut dimuat dalam file.

---

### 📌 Soal 2: CI/CD Implementation
Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous  Deployment? Explain the reasons (minimum 3 sentences)!
### 📝 Jawaban
> Ya, saya yakin implementasi yang saya terapkan sudah sepenuhnya memenuhi definisi dari *Continuous Integration* (CI) dan *Continuous Deployment* (CD).
> Untuk **Continuous Integration**, setiap kali saya melakukan *push* kode baru atau membuka *Pull Request*, GitHub Actions secara otomatis memicu *workflow* untuk menjalankan *automated test suites*, sementara SonarCloud menampilkan hasil *static code analysis*. Hal ini memastikan bahwa setiap integrasi kode baru langsung diverifikasi kebenarannya serta kualitasnya sebelum digabungkan ke dalam *main codebase*.
> Sementara untuk **Continuous Deployment**, saya menggunakan pendekatan *pull-based* dengan Koyeb. Ketika terdapat perubahan kode yang berhasil di-*merge* ke branch `master`, Koyeb secara otomatis mendeteksi perubahan tersebut, membangun *Docker image* berdasarkan `Dockerfile` yang telah disediakan, dan langsung men-*deploy* aplikasi yang telah diperbarui ke *server* produksi tanpa memerlukan intervensi manual. Dengan begitu, tercipta sebuah *pipeline* yang mulus dan menyeluruh, mulai dari menulis kode hingga kode tersebut dapat diakses oleh pengguna.

</details>


<details>
<summary><b>📝 Refleksi Module 3: Maintainability & OO Principles </b></summary>

## Reflection 3
### **1) Explain what principles you apply to your project!**

### **a. Single Responsibility Principle (SRP)**
Saya menerapkan prinsip ini dengan memisahkan `CarController` dari file `ProductController.java`. Sebelumnya, `CarController` didefinisikan sebagai *inner class* (atau dalam file yang sama) dengan `ProductController`, yang menyebabkan file tersebut memiliki banyak tanggung jawab (menangani *request* Product dan Car sekaligus). Kini, `CarController` memiliki file tersendiri, memastikan bahwa setiap kelas hanya memiliki satu alasan untuk berubah.

---

### **b. Open-Closed Principle (OCP)**
Saya menerapkan prinsip ini dengan menggunakan *interface* pada *layer service* dan *repository* (contoh: `CarService` dan `CarRepository`). `CarController` sekarang bergantung pada *interface* `CarService`, bukan pada implementasi konkrit `CarServiceImpl`. Hal ini membuat sistem terbuka untuk pengembangan (*open for extension*), kita bisa menambahkan implementasi *service* baru seperti `CarServiceDatabaseImpl` tanpa mengubah *controller*, tetapi tertutup untuk modifikasi (*closed for modification*) karena kode *controller* tidak perlu diubah saat implementasinya diganti.

---

### **c. Liskov Substitution Principle (LSP)**
Saya menerapkan prinsip ini dengan menghapus hubungan *inheritance* di mana `CarController` sebelumnya *extends* `ProductController`. Sebelumnya, `CarController extends ProductController` melanggar LSP karena "Car Controller" secara perilaku tidak dapat menggantikan posisi "Product Controller". Dengan menghapus *extends* dan membiarkan `CarController` berdiri sendiri, saya memastikan bahwa pewarisan tidak disalahgunakan hanya untuk *code reuse*.

---

### **d. Interface Segregation Principle (ISP)**
Saya menerapkan prinsip ini dengan membuat *interface* terpisah untuk setiap entitas yang berbeda, yaitu `CarService` dan `ProductService`. Alih-alih membuat *interface* generik berukuran besar (misalnya `GeneralService`) yang menangani segalanya, saya memisahkannya. Ini memastikan bahwa `CarController` hanya bergantung pada metode-metode yang relevan dengan mobil yang didefinisikan di `CarService`, dan tidak dipaksa bergantung pada metode-metode yang berkaitan dengan *product*.

---

### **e. Dependency Inversion Principle (DIP)**
Saya menerapkan prinsip ini dengan memastikan bahwa *module* tingkat tinggi (`CarController`) bergantung pada abstraksi (*interface*), bukan pada *module* tingkat rendah (*concrete classes*).

**Sebelumnya:** *Controller* mungkin bergantung pada `CarServiceImpl` atau *service* bergantung langsung pada kelas `CarRepository`.

**Sesudahnya:** Saya mengubah *dependency injection* untuk menggunakan *interface* `CarService` (`private CarService carService`). Ini memisahkan (*decouple*) *controller* dari detail implementasi spesifik dari *service*.

---
### 2) Explain the advantages of applying SOLID principles to your project with examples.

---

**a. Maintainability yang Lebih Baik (SRP)**

Dengan menerapkan **Single Responsibility Principle (SRP)**, *codebase* menjadi jauh lebih bersih dan mudah dirawat. Sebelumnya, `ProductController` menangani logika Product dan Car sekaligus. Jika saya ingin memperbaiki *bug* pada fitur pembuatan Car, saya berisiko merusak fitur Product karena mereka berada dalam file yang sama.

* **Contoh:** Setelah memisahkan `CarController` ke dalam file sendiri, saya sekarang dapat memodifikasi, *debug*, atau merombak ulang logika terkait Car tanpa menyentuh atau khawatir akan `ProductController`.

---

**b. Fleksibilitas dalam Pergantian Implementasi (DIP)**

Menerapkan **Dependency Inversion Principle (DIP)** membuat sistem menjadi sangat fleksibel dan memiliki *loose coupling* (keterikatan yang longgar). Dengan bergantung pada *interface*, kita dapat beralih di antara berbagai implementasi yang berbeda tanpa harus memodifikasi kode yang bergantung padanya.

**Contoh:** `CarServiceImpl` bergantung pada *interface* `CarRepository`, bukan pada kelas implementasi spesifik `CarRepositoryImpl`. Jika kita memutuskan untuk mengubah penyimpanan data dari `List` sementara (*in-memory*) ke *database* sungguhan seperti PostgreSQL, kita hanya perlu membuat kelas *repository* baru yang mengimplementasikan `CarRepository`. Kode `CarServiceImpl` akan tetap sama persis karena ia bergantung pada kontrak *interface*, bukan pada detail implementasinya.

---

**c. Skalabilitas dan Ekstensibilitas yang Ditingkatkan (OCP)**

**Open-Closed Principle (OCP)** membuat sistem lebih mudah diperluas tanpa harus memodifikasi kode yang sudah ada.

* **Contoh:** Saat ini, `CarServiceImpl` menggunakan penyimpanan data sederhana di memori (`List`). Jika di masa depan kita ingin beralih ke *database* sungguhan (misalnya PostgreSQL), kita cukup membuat kelas baru `CarServiceDatabaseImpl` yang mengimplementasikan *interface* `CarService`. Kode `CarController` tidak perlu diubah sama sekali karena ia hanya peduli pada *interface*-nya, bukan pada detail implementasinya.

---

**d. Menghindari Efek Samping dari Pewarisan (LSP)**

Dengan mematuhi **Liskov Substitution Principle (LSP)**, kita menghindari perilaku tidak terduga yang disebabkan oleh penyalahgunaan pewarisan (*inheritance*).

* **Contoh:** Saya menghapus `CarController extends ProductController`. Sebelumnya, `CarController` mewarisi metode-metode dari `ProductController` yang tidak diperlukan atau bahkan tidak seharusnya digunakan. Dengan menghapus hubungan ini, `CarController` berdiri sendiri, mencegah penggunaan tidak sengaja terhadap logika spesifik Product di dalam konteks Car.

---
### 3) Explain the disadvantages of not applying SOLID principles to your project with examples.

---

**a. Tingginya Ketergantungan Kode (Pelanggaran SRP)**

Tanpa menerapkan **Single Responsibility Principle (SRP)**, *codebase* akan menjadi "Spaghetti Code" di mana banyak tanggung jawab bercampur aduk dalam satu kelas.

* **Contoh:** Sebelum *refactoring*, `ProductController.java` menangani *request* Product dan Car sekaligus. Jika saya perlu mengubah logika *routing* untuk Car, saya harus mengedit file yang sama yang digunakan untuk Product. Ini meningkatkan risiko tidak sengaja merusak fitur Product saat sedang mengerjakan fitur Car, membuat sistem menjadi rapuh dan sulit dirawat.

---

**b. Pewarisan yang Menyesatkan dan Efek Samping (Pelanggaran LSP)**

Tanpa menerapkan **Liskov Substitution Principle (LSP)**, kita mungkin menyalahgunakan pewarisan (*inheritance*) demi *code reuse*, yang mengarah pada hierarki yang membingungkan.

* **Contoh:** Di kode awal, `CarController` *extends* `ProductController`. Ini mengimplikasikan bahwa `CarController` *adalah* `ProductController`, yang secara logika tidak benar. Ia mewarisi metode-metode (seperti membuat *product*) yang tidak dibutuhkannya. Jika seseorang memanggil metode dari kelas induk pada objek `CarController`, hal ini dapat menyebabkan perilaku tak terduga atau *bug* karena *subclass* tidak dirancang untuk menangani perilaku induk tersebut.

---

**c. Kesulitan dalam Pengujian (Pelanggaran DIP)**

Tanpa menerapkan **Dependency Inversion Principle (DIP)**, *module* tingkat tinggi akan terikat erat dengan implementasi tingkat rendah, membuat pengujian unit (*unit testing*) menjadi sangat sulit.

* **Contoh:** Jika `CarController` bergantung langsung pada kelas konkrit `CarServiceImpl` (alih-alih pada *interface* `CarService`), kita tidak dapat dengan mudah mengganti *service* tersebut dengan objek *Mock* selama pengujian. Ini akan memaksa kita menjalankan *integration test* (menggunakan *service* dan *repository* sungguhan) hanya untuk menguji logika *controller*, yang lebih lambat dan lebih rentan terhadap *error* terkait lingkungan.



</details>