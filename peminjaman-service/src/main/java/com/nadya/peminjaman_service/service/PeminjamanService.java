package com.nadya.peminjaman_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nadya.peminjaman_service.model.Peminjaman;
import com.nadya.peminjaman_service.repository.PeminjamanRepository;
import com.nadya.peminjaman_service.vo.Anggota;
import com.nadya.peminjaman_service.vo.Buku;
import com.nadya.peminjaman_service.vo.ResponseTemplate;


@Service
public class PeminjamanService {
    @Autowired
    private PeminjamanRepository PeminjamanRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    public List<Peminjaman> getAllPeminjamans(){
    return PeminjamanRepository.findAll();
    }

    public Peminjaman getPeminjamanById(Long id) {
    return PeminjamanRepository.findById(id).orElse(null);
    }

    public Peminjaman createPeminjaman(Peminjaman peminjaman){
    return PeminjamanRepository.save(peminjaman);
    }

    public List<ResponseTemplate> getPeminjamanWithBukuById(Long id){
        List<ResponseTemplate> responseList = new ArrayList<>();
        Peminjaman peminjaman = getPeminjamanById(id);
        ServiceInstance serviceInstance = discoveryClient.getInstances("buku-service").get(0);
        Buku buku = restTemplate.getForObject(serviceInstance.getUri() + "/api/buku/"
                + peminjaman.getBukuId(), Buku.class);
                serviceInstance = discoveryClient.getInstances("anggota-service").get(0);
        Anggota anggota = restTemplate.getForObject(serviceInstance.getUri() + "/api/anggota/"
                + peminjaman.getAnggotaId(), Anggota.class);
        ResponseTemplate vo = new ResponseTemplate();
        vo.setPeminjaman(peminjaman);
        vo.setBuku(buku);
        vo.setAnggota(anggota);
        responseList.add(vo);
        return responseList;
    }

    public void deletePeminjaman (Long id){
    PeminjamanRepository.deleteById(id);
    }
}