package br.com.biopark.cpa.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.common.record.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.biopark.cpa.models.UsuarioCSV;
import br.com.biopark.cpa.repository.UsuarioCSVRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/importar")
@Transactional
public class UsuarioCSVController {

    @Autowired
    private UsuarioCSVRepository usuarioCSVRepository;

    @PostMapping(value = "/importarUsuario")
    public List<UsuarioCSV> importarUsuarioCSV(@RequestParam("file") MultipartFile file) throws Exception {
        List<UsuarioCSV> usuarioCSV = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings csvParserSettings = new CsvParserSettings();

        csvParserSettings.setHeaderExtractionEnabled(true);
        CsvParser csvParser = new CsvParser(csvParserSettings);

        List<Record> parseAllRecords = csvParser.parseAllRecords(inputStream);

        parseAllRecords.forEach(record -> {
            UsuarioCSV usuario = new UsuarioCSV();
            usuario.setNome(record.getString("nome"));
            usuario.setEmail(record.getString("email"));
            usuarioCSV.add(usuario);
        });

        return usuarioCSVRepository.saveAll(usuarioCSV);

    }

}