package com.nadya.pengembalian_service.vo;

import java.time.LocalDate;

public class Peminjaman {
    private String id;
    private String bukuId;
    private Long anggotaId;
    private LocalDate tanggalPeminjaman;
    private LocalDate tanggalPengembalian;

    public Peminjaman(){

    }

    public Peminjaman(String id, String bukuId, Long anggotaId, LocalDate tanggalPeminjaman,
            LocalDate tanggalPengembalian) {
        this.id = id;
        this.bukuId = bukuId;
        this.anggotaId = anggotaId;
        this.tanggalPeminjaman = tanggalPeminjaman;
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBukuId() {
        return bukuId;
    }

    public void setBukuId(String bukuId) {
        this.bukuId = bukuId;
    }

    public Long getAnggotaId() {
        return anggotaId;
    }

    public void setAnggotaId(Long anggotaId) {
        this.anggotaId = anggotaId;
    }

    public LocalDate getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }

    public void setTanggalPeminjaman(LocalDate tanggalPeminjaman) {
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    public LocalDate getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public void setTanggalPengembalian(LocalDate tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}