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