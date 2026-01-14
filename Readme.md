# Perpustakaan Microservices System

Proyek ini adalah implementasi sistem Perpustakaan menggunakan arsitektur Microservices berbasis Spring Boot. Sistem dijalankan di atas Kubernetes Cluster. Sistem ini mencakup implementasi pola CQRS, Centralized Logging, Monitoring, dan CI/CD Pipeline.

---

## Arsitektur dan Komponen Sistem:

1. **Service Utama**: Buku, Anggota, Peminjaman (Command & Query), Pengembalian, Notifikasi.
2. **Infrastruktur Pendukung**: API Gateway, Eureka Discovery Server, RabbitMQ, MongoDB, serta H2 Database.
3. **Orchestration**: Kubernetes (K8s).
4. **Monitoring Sistem**: Prometheus dan Grafana.
5. **Logging**: ELK Stack (Elasticsearch, Logstash, Kibana, Filebeat). 
6. **CI/CD**: Jenkins.

---

## Panduan Instalasi & Deployment

Langkah-langkah untuk menjalankan sistem secara berurutan:

1. **Penerapan Layanan utama dan Database:**
    Melakukan instalasi MongoDB dan RabbitMQ, kemudian menjalankan seluruh layanan utama seperti Buku, Anggota, dan service terkait lainnya ke dalam Kubernetes cluster.

2. **Konfigurasi Monitoring:**
    Menyiapkan Prometheus sebagai alat pengumpulan metrik serta mengonfigurasi Grafana untuk menampilkan dashboard pemantauan kondisi dan performa aplikasi.

3. **Konfigurasi Logging:**
    Mengatur Filebeat dan ELK Stack agar seluruh log dari container microservices dapat dikumpulkan, disimpan, dan dianalisis secara terpusat.

4. **Otomatisasi CI/CD Pipeline:**
    Menjalankan Jenkins Pipeline untuk mengotomatisasi proses build aplikasi, pembuatan image, hingga deployment ke lingkungan Kubernetes.

Selengkapnya: **[Tahapan Konfigurasi dan Instalasi Sistem](dokumen/langkah-langkah-konfigurasi.md)**

---

## Prasyarat

- **Docker Desktop** (dengan Kubernetes Enabled)
- **Git**
- **Java JDK 17**
- **Maven**

