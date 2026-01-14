package com.nadya.bukuservice.bukuservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadya.bukuservice.bukuservice.model.Buku;
import com.nadya.bukuservice.bukuservice.repository.BukuRepository;

@Service
public class BukuService {
    @Autowired
    private BukuRepository BukuRepository;

    public Buku save(Buku Buku) {
        return BukuRepository.save(Buku);
    }

    public Buku findById(Long id) {
        return BukuRepository.findById(id).orElse(null);
    }

    public List<Buku> findAll() {
        return BukuRepository.findAll();
    }
}
