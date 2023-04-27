package br.com.biopark.cpa.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.biopark.cpa.models.Eixo;
import br.com.biopark.cpa.repository.EixoRepository;

@Service
public class EixoService {

    @Autowired
    private EixoRepository eixoRepository;

    @Transactional
    public Eixo cadastrar(Eixo eixo) throws Exception{

        Eixo eixoCadastrado = new Eixo();
        
        try {
            eixoCadastrado = eixoRepository.save(eixo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return eixoCadastrado;
    }

    public Page<Eixo> listar(Pageable pageable) {
        return eixoRepository.findAll(pageable);
    }

    public Page<Eixo> buscarPorNome(String nomeEixo, Pageable pageable) {
        return eixoRepository.findByNome(nomeEixo, pageable);
    }
}
