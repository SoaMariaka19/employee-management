package com.prog4.service;

import com.prog4.entity.Cnaps;
import com.prog4.repository.CnapsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CnapsService {
    private final CnapsRepository repository;

    public List<Cnaps> findAll(){
        return repository.findAll();
    }

   public Cnaps getById(Long id){
        Optional<Cnaps> cnaps = repository.findById(id);
        Cnaps cnaps1 = new Cnaps();
        cnaps.ifPresent(value -> cnaps1.setNbrCNAPS(value.getNbrCNAPS()));
        cnaps1.setId(id);
        return cnaps1;
   }
    public Cnaps getByNumber(String number){
        return repository.findByNbrCNAPS(number);
    }

    public Cnaps save(Cnaps cnaps){
        Cnaps cnaps1  = repository.save(cnaps);
        return this.getById(cnaps1.getId());
    }
}
