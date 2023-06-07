package br.com.biopark.cpa.service;

import java.util.ArrayList;
import java.util.List;

import com.univocity.parsers.common.record.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biopark.cpa.models.UsuarioCSV;
import br.com.biopark.cpa.repository.UsuarioCSVRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioCSVService {

    @Autowired
    private UsuarioCSVRepository usuarioCSVRepository;

    @Transactional
    public List<UsuarioCSV> importarCSVUsuario (List <Record> parseAllRecords) {
        List<UsuarioCSV> usuarioCSV = new ArrayList<>();
        
        for (Record record : parseAllRecords) {
            UsuarioCSV usuario = new UsuarioCSV();
            usuario.setNome(record.getString("nome"));
            usuario.setEmail(record.getString("email"));
            usuarioCSV.add(usuario);
        }

        return usuarioCSVRepository.saveAll(usuarioCSV);  
    }
}
