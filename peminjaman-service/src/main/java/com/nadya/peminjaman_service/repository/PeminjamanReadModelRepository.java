package com.nadya.peminjaman_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nadya.peminjaman_service.model.PeminjamanReadModel;

@Repository
public interface PeminjamanReadModelRepository extends MongoRepository<PeminjamanReadModel, String> {
    Optional<PeminjamanReadModel> findByPeminjamanIdSql(Long peminjamanIdSql);
    List<PeminjamanReadModel> findByAnggotaId(Long anggotaId);
    List<PeminjamanReadModel> findByBukuId(Long bukuId);
}
