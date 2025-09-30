package com.nadya.pengembalian_service.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pengembalian")
public class Pengembalian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bukuId;
    private Long anggotaId;
    private Long peminjamanId;
    private LocalDate tanggal_dikembalikan;
    private String terlambat;
    private Double denda;
}