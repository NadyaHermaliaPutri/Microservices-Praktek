package com.nadya.anggotaservice.anggotaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nadya.anggotaservice.anggotaservice.model.Anggota;

@Repository
public interface AnggotaRepository extends JpaRepository<Anggota, Long> {
}