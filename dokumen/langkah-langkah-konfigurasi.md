# Tahapan Konfigurasi dan Instalasi Sistem

Dokumen ini menjelaskan tahapan konfigurasi, pemantauan, serta otomasi Sistem Informasi Perpustakaan yang dibangun menggunakan arsitektur Microservices berbasis Spring Boot**.  
Sistem dijalankan di atas Kubernetes Cluster dan mencakup penerapan CQRS, Centralized Logging, Monitoring, serta CI/CD Pipeline**.

---

## Alur Konfigurasi Sistem

### 1. Setup Infrastruktur dan Microservices (Kubernetes)

Tahapan awal dilakukan untuk menyiapkan fondasi sistem agar seluruh service dapat saling terhubung dengan baik.  
Pastikan terminal berada pada folder **kubernetes** sebelum menjalankan perintah.

#### a. Namespace (Isolasi Resource)

Seluruh resource aplikasi ditempatkan pada namespace perpustakaan-ns yang telah didefinisikan di file namespace.yaml.

```bash
kubectl apply -f namespace.yaml
```

#### b. Deployment Database dan Middleware
Database dan message broker dijalankan lebih awal untuk menghindari kegagalan koneksi service.

- Setup Secret dan Storage
```bash
kubectl apply -f mongodb-secret.yaml
kubectl apply -f mongodb-pvc.yaml
kubectl apply -f mail-secret.yaml
```

- Deploy Database dan Broker:
```bash
kubectl apply -f mongodb-deployment.yaml
kubectl apply -f rabbitmq-deployment.yaml
```

#### c. Deployment Service (Terapkan konfigurasi deployment untuk semua service)
Jalankan perintah berikut ini di terminal:

```bash
kubectl apply -f eureka-deployment.yaml
kubectl apply -f api-gateway-deployment.yaml
kubectl apply -f buku-service-deployment.yaml
kubectl apply -f anggota-service-deployment.yaml
kubectl apply -f notification-service-deployment.yaml
kubectl apply -f peminjaman-service-deployment.yaml
kubectl apply -f peminjaman-query-service-deployment.yaml
kubectl apply -f pengembalian-service-deployment.yaml
```

### d. Verifikasi Status Pod
Pastikan seluruh pod berjalan normal dengan status Running.
Jalankan perintah berikut ini di terminal:
```bash
kubectl get pods -n perpustakaan-ns
```

### 2. Monitoring (Prometheus dan Grafana)
Monitoring digunakan untuk memantau performa sistem dan microservices. Sistem monitoring menggunakan Prometheus untuk pengambilan (scraping) metrik, sedangkan Grafana digunakan untuk visualisasi dashboard. Sebelum melakukan konfigurasi, pastikan telah berada di folder monitoring yang berada didalam folder kubernetes. File konfigurasinya berada di dalam folder kubernetes/monitoring.
Langkah – Langkah konfigurasi:

## a. Namespace Monitoring
```bash
kubectl apply -f monitoring-namespace.yaml
```
### b. Pengaturan Hak Akses (RBAC)
Prometheus memerlukan akses untuk membaca data cluster. 
Jalankan perintah berikut ini di terminal:
```bash
kubectl apply -f prometheus-rbac.yaml
```
### c. Deploy Prometheus dan Konfigurasi
ConfigMap digunakan untuk mengatur target dan interval scraping.
Jalankan perintah berikut ini di terminal:
```bash
kubectl apply -f prometheus-configmap.yaml 
kubectl apply -f prometheus-deployment.yaml
```
## d. Deploy Exporter
Digunakan untuk memonitor MongoDB dan RabbitMQ.
Jalankan perintah berikut ini di terminal:
```bash
kubectl apply -f monitoring-mongodb-secret.yaml
kubectl apply -f mongodb-exporter.yaml
kubectl apply -f rabbitmq-exporter.yaml
```
### e. Deploy Grafana
```bash
kubectl apply -f grafana-deployment.yaml
```
### e. Akses Dashboard
**Prometheus**: http://localhost:30090 (Username: admin, Password: admin)
**Grafana**: http://localhost:30003

### 3. Logging (ELK Stack)
Sistem logging menggunakan Elasticsearch, Logstash, Kibana (ELK), dan  Filebeat untuk mengambil log container di setiap node. Sebelum melakukan konfigurasi pastikan berada di folder logging. 
Langkah – Langkah konfigurasi:
### - Tahap Instalasi
### a. Namespace Logging
Jalankan perintah berikut ini di terminal:
```bash
kubectl apply -f logging-namespace.yaml
```
### b. Deploy Komponen ELK
Sebelum itu pastikan sudah masuk ke folder kubernetes/logging.
Jalankan perintah berikut ini di terminal:
```bash
kubectl apply -f elasticsearch.yaml
kubectl apply -f kibana.yaml
kubectl apply -f logstash.yaml
kubectl apply -f filebeat.yaml
```
### - Konfigurasi Kibana	
    1. Akses Kibana di http://localhost:30601
    2. Masuk ke Stack Management > Index Patterns
    3. Buat index dengan nama microservices-log-*
    4. Buka menu Discover untuk melihat log aplikasi

### 4. Jenkins 
Jenkins digunakan untuk mengotomatisasi proses CI/CD mulai dari build hingga deployment ke Kubernetes. Sebelum melakukan konfigurasi, pastikan berada di folder jenkins yang berada di dalam folder kubernetes.
### - Setup Jenkins di Kubernetes
Untuk menjaga data job dan plugin, digunakan Persistent Volume.
### a. Deploy Jenkins Masuk ke folder kubernetes/Jenkins.
Jalankan perintah berikut ini di terminal:
```bash
kubectl apply -f jenkins-setup.yaml
kubectl apply -f jenkins-pvc.yaml
kubectl apply -f jenkins-deployment.yaml
```
### b. Akses Jenkins
Dashboard Jenkins dapat diakses melalui:http://localhost:32000

### Konfigurasi Pipeline
Setiap microservice memiliki file Jenkinsfile tersendiri yang digunakan untuk mendefinisikan alur kerja pipeline CI/CD. Tahapan pipeline sebagai berikut:

    1. **Initialize**: Pada tahap ini, Jenkins melakukan persiapan lingkungan kerja dengan memastikan bahwa Docker dan kubectl telah tersedia dan dapat digunakan oleh agent Jenkins.

    2. **Build Maven**: Jenkins menjalankan perintah mvn clean package -DskipTests untuk 
    mengompilasi source code aplikasi menjadi file JAR.

    3. **Build Docker Image**: File JAR yang dihasilkan kemudian dibungkus ke dalam sebuah Docker image. Image tersebut diberi tag versi tertentu.

    4. **Push Image ke Docker Registry**: Setelah image berhasil dibuat, Jenkins mengunggah image tersebut ke Local Docker Registry yang berjalan pada localhost:5000. Registry ini digunakan sebagai penyimpanan image sebelum dideploy ke Kubernetes.

    5. **Deployment dan Rolling Update ke Kubernetes**: Pada tahap akhir, Jenkins memperbarui konfigurasi deployment di Kubernetes agar menggunakan image terbaru. Proses rolling update dilakukan untuk memastikan aplikasi tetap berjalan tanpa downtime, sekaligus memantau status rollout hingga deployment berhasil.

### Daftar Pipeline
*   `pipeline-anggotaservice`
*   `pipeline-bukuservice`
*   `pipeline-notificationservice`
*   `pipeline-peminjamanqueryservice`
*   `pipeline-peminjamancommandservice`
*   `pipeline-pengembalianservice`
