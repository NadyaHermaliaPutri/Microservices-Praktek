package com.nadya.peminjamanservice.peminjamanservice.vo;

import com.nadya.peminjamanservice.peminjamanservice.model.Peminjaman;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateVO {
    private Peminjaman peminjaman;
    private Anggota anggota;
    private Buku buku;
}