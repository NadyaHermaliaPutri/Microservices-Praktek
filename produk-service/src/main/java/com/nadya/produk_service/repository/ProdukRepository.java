package com.nadya.produk_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nadya.produk_service.model.Produk;

public interface ProdukRepository extends JpaRepository<Produk,Long>{

}