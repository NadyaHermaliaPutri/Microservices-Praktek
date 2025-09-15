package com.nadya.buku_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadya.buku_service.model.Buku;

public interface BukuRepository extends JpaRepository<Buku,Long>{

}
