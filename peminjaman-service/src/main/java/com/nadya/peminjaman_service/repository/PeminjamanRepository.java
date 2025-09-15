package com.nadya.peminjaman_service.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.nadya.peminjaman_service.model.Peminjaman;

public interface PeminjamanRepository extends JpaRepository<Peminjaman,Long>{

}