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
    public List<UsuarioCSV> importarCSVUsuario (List <Record> parseAllRecords) throws Exception {
        List<UsuarioCSV> usuarioCSV = new ArrayList<>();

        try {

            // for (int i = 0; i < parseAllRecords.size(); i++) {
            //     System.out.println("POSIÇÃOOOOOOOOOOOOO: " + parseAllRecords[i].getString());
            // }

            if (!parseAllRecords.equals("nome")) {
                throw new Exception("Erro ao importar lista de usuários verificação if");
            } 
            if (!parseAllRecords.get(0).equals("nome") || !parseAllRecords.get(1).equals("email")) {
                throw new Exception("Erro ao importar lista de usuários verificação else if");
            }

            for (Record record : parseAllRecords) { 

                System.out.println("POSIÇÃOOOOOOOOOOOOO: " + parseAllRecords.get(1));
                System.out.println("POSIÇÃOOOOOOOOOOOOO: " + parseAllRecords.get(1));
                System.out.println("POSIÇÃOOOOOOOOOOOOO: " + parseAllRecords.get(1));
                System.out.println("POSIÇÃOOOOOOOOOOOOO: " + parseAllRecords.get(1));
                UsuarioCSV usuario = new UsuarioCSV();
                usuario.setNome(record.getString("nome"));
                usuario.setEmail(record.getString("email"));
                usuarioCSV.add(usuario);
            }

        } catch (Exception e) {
            throw new Exception("Erro ao importar lista de usuários catch: " + e.getMessage());
        }
        return usuarioCSVRepository.saveAll(usuarioCSV);  
    }
}
