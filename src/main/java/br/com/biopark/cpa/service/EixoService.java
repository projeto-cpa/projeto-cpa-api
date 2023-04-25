package br.com.biopark.cpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biopark.cpa.models.Eixo;
import br.com.biopark.cpa.repository.EixoRepository;

@Service
public class EixoService {

    @Autowired
    private EixoRepository eixoRepository;

    public Eixo cadastrar(Eixo eixo) throws Exception{

        Eixo eixoCadastrado = new Eixo();
        
        try {
            eixoCadastrado = eixoRepository.save(eixo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return eixoCadastrado;
    }
    
}
