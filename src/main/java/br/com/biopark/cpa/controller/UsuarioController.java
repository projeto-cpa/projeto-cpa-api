package br.com.biopark.cpa.controller;

import java.net.URI;

import br.com.biopark.cpa.controller.dto.LoginDTO;
import br.com.biopark.cpa.controller.dto.TokenDTO;
import br.com.biopark.cpa.controller.dto.TurmaDTO;
import br.com.biopark.cpa.config.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.biopark.cpa.controller.dto.UsuarioDTO;
import br.com.biopark.cpa.controller.dto.alterar.AlterarSenhaDTO;
import br.com.biopark.cpa.controller.form.UsuarioForm;
import br.com.biopark.cpa.controller.form.alteracao.AlterarTurmaForm;
import br.com.biopark.cpa.controller.form.alteracao.AlterarUsuarioForm;
import br.com.biopark.cpa.controller.form.ativacao.AtivarUsuarioForm;
import br.com.biopark.cpa.controller.form.exclusao.excluirUsuarioForm;
import br.com.biopark.cpa.models.Cargo;
import br.com.biopark.cpa.models.Turma;
import br.com.biopark.cpa.models.Usuario;
import br.com.biopark.cpa.repository.CargoRepository;
import br.com.biopark.cpa.service.CargoService;
import br.com.biopark.cpa.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.common.record.Record;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    Date data = new Date();

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody @Valid UsuarioForm form,
        UriComponentsBuilder uriBuilder) throws Exception {
        Cargo cargo = cargoService.buscarCargo(form.getIdCargo());
        Usuario usuario = new Usuario(form.getNome(), form.getEmail(), form.getSenha(), cargo, form.getAtivo());
        usuario = usuarioService.cadastrar(usuario);
        URI uri = uriBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }
    
    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder)
            throws Exception {
        Usuario usuario = form.converter(cargoRepository);
        usuarioService.cadastrar(usuario);
        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO login, UriComponentsBuilder uriBuilder)
    throws Exception {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            login.email(), login.senha());
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            var usuario = (Usuario) authentication.getPrincipal();
            var tokenDTO = new TokenDTO(tokenService.gerarToken(usuario), true, usuario.getId());
            URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(tokenDTO);
        } catch (Exception err) {
            var usuario = usuarioService.buscarUsuarioPeloEmail(login.email());
            var tokenDTO = new TokenDTO(null, false, null);
            URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(tokenDTO);
        }

    }

    @PostMapping("/importar")
    public List<Usuario> importarUsuario(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            InputStream inputStream = file.getInputStream();
            CsvParserSettings csvParserSettings = new CsvParserSettings();
            csvParserSettings.setHeaderExtractionEnabled(true);
            CsvParser csvParser = new CsvParser(csvParserSettings);

            List<Record> parseAllRecords = csvParser.parseAllRecords(inputStream);
            List<Usuario> importar = usuarioService.importarUsuario(parseAllRecords);

            return importar;
        } catch (Exception e) {
            throw new Exception ("ERRO AO IMPORTAR USUARIO: " + e.getMessage());
        }
    }

    @GetMapping("/detalhar")
    @Transactional
    public ResponseEntity<UsuarioDTO> detalhar(@RequestBody @Valid @RequestParam Long id,
        UriComponentsBuilder uriBuilder) throws Exception {
        Usuario usuario = usuarioService.buscarPorId(id);
        URI uri = uriBuilder.path("usuario/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }

    @GetMapping
    @Transactional
    public Page<UsuarioDTO> listar(@RequestParam int pagina, @RequestParam int qtd) {
        Pageable paginacao = PageRequest.of(pagina, qtd);
        Page<Usuario> usuario = usuarioService.listarTodosUsuarios(paginacao);
        return UsuarioDTO.converter(usuario);
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody @Valid AlterarUsuarioForm form,UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioService.atualizarUsuario(form.getIdUsuario(), form.getNome(), form.getEmail(), form.getSenha(),  form.getAtivo());
        URI uri = uriBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }

    @PutMapping("/ativacao")
    public ResponseEntity<UsuarioDTO> ativarDesativarUsuario(@RequestBody @Valid AtivarUsuarioForm form, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioService.ativarDesativarUsuario(form.getIdUsuario());
        URI uri = uriBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }

    @DeleteMapping
    public ResponseEntity<UsuarioDTO> excluir(@RequestBody @Valid excluirUsuarioForm form,
            UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioService.excluirUsuario(form.getIdUsuario());
        URI uri = uriBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }
  
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable(name="id") long id, @RequestBody @Valid UsuarioForm form,
            UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioService.atualizar(id, form);
        URI uri = uriBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }
}
