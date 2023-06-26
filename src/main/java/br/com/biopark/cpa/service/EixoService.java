package br.com.biopark.cpa.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;
import br.com.biopark.cpa.models.Eixo;
import br.com.biopark.cpa.repository.EixoRepository;

import java.util.Optional;

@Service
public class EixoService {

    @Autowired
    private EixoRepository eixoRepository;

    @Transactional
    public Eixo cadastrar(Eixo eixo) throws Exception {

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

    public Eixo buscarPorId(long id) {

        Optional<Eixo> eixo = eixoRepository.findById(id);

        if (eixo.isPresent())
            return eixo.get();
        else
            return null;
    }

    @Transactional
    public Eixo atualizar(Eixo eixo) throws Exception {
        if (eixo == null) {
            throw new Exception("Eixo não encontrado para atualização");
        }

        try {
            eixo = eixoRepository.save(eixo);
        } catch (Exception e) {
            throw new Exception(e.getCause());
        }

        return eixo;
    }

    public Eixo ativarDesativarEixo(Long id) {
        Eixo eixo = eixoRepository.findById(id).get();
        Boolean ativo = eixo.getAtivo().equals(true) ? false : true;
        eixo.setAtivo(ativo);
        eixoRepository.save(eixo);
        return eixo;
    }

    public Eixo excluirEixo(Long id) {
        Eixo eixo = eixoRepository.findById(id).get();
        eixoRepository.delete(eixo);
        return eixo;
    }

    // implemente o metodo para atualizar o cargo
    public Eixo atualizarTemp(Long id, String nome, String descricao, Boolean ativo) {
        Eixo eixo = eixoRepository.findById(id).get();
        eixo.setDataAtualizacao(new Date());
        eixo.setNome(nome);
        eixo.setDescricao(descricao);
        eixo.setAtivo(ativo);
        eixoRepository.save(eixo);
        return eixo;
    }
}
