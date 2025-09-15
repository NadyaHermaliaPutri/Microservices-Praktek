package com.nadya.pengembalian_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadya.pengembalian_service.model.Pengembalian;
import com.nadya.pengembalian_service.repository.PengembalianRepository;

@Service
public class PengembalianService {
    @Autowired
    private PengembalianRepository pengembalianRepository;

    public List<Pengembalian> getAllPengembalians(){
    return pengembalianRepository.findAll();
    }

    public Pengembalian getPengembalianById(Long id) {
    return pengembalianRepository.findById(id).orElse(null);
    }

    public  Pengembalian createPengembalian(Pengembalian pengembalian){
    return pengembalianRepository.save(pengembalian);
    }

    public void deletePengembalian (Long id){
    pengembalianRepository.deleteById(id);
    }
}
