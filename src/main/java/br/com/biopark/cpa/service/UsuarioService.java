package br.com.biopark.cpa.service;

import br.com.biopark.cpa.config.email.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.biopark.cpa.controller.form.RecuperarSenhaForm;
import br.com.biopark.cpa.models.Cargo;
import br.com.biopark.cpa.models.Turma;
import br.com.biopark.cpa.controller.form.UsuarioForm;
import br.com.biopark.cpa.controller.form.recuperacao.RecuperarAcessoForm;
import br.com.biopark.cpa.models.Usuario;
import br.com.biopark.cpa.repository.UsuarioRepository;
import java.util.Base64;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.univocity.parsers.common.record.Record;
import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private EmailSender emailSender;

    public Usuario cadastrar(Usuario usuario) throws Exception {
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new Exception("Erro ao cadastrar usuario " + e.getCause());
        }
    }

    public Usuario buscarUsuario(String login) throws Exception {
        try {
            return usuarioRepository.findByEmail(login);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar usuário");
        }
    }

    public Usuario buscarUsuarioPeloEmail(String email) throws Exception {
        try {
            System.out.println(email + "teste log");
            return usuarioRepository.findByEmail(email);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar usuário");
        }
    }

    public Iterable<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    public Page<Usuario> listarTodosUsuarios(Pageable usuario) {
        return usuarioRepository.findAll(usuario);
    }

    public Usuario buscarPorId(Long usuarioRespondenteId) {
        Optional<Usuario> usuario = usuarioRepository.findById((usuarioRespondenteId));
        if (usuario.isPresent())
            return usuario.get();
        else
            throw new EntityNotFoundException("Usuário não encontrado");
    }

    public Usuario atualizar(Long id, UsuarioForm usuarioForm) {
        Usuario usuario = usuarioRepository.findById(id).get();

        if (usuarioForm.getSenha() != null && usuarioForm.getSenhaAtual() != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String novaSenha = bCryptPasswordEncoder.encode(usuarioForm.getSenha());
            if (bCryptPasswordEncoder.matches(usuarioForm.getSenhaAtual(), usuario.getSenha())) {
                if (usuarioForm.getSenha() != null) {
                    usuario.setSenha(novaSenha);
                }

            } else {
                throw new IllegalArgumentException("Senha atual incorreta");
            }
        }

        if (usuarioForm.getImagem() != null) {
            usuario.setImagem(usuarioForm.getImagem());
        }

        return usuarioRepository.save(usuario);

    }

    public String gerarCodigoAleatorio(Long tamanho, Boolean letras) {
        String codigo = "";

        String[] caracteres = letras
                ? new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" }
                : new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };

        for (int i = 0; i < tamanho; i++) {
            int posicao = (int) (Math.random() * caracteres.length);
            codigo += caracteres[posicao];
        }

        return codigo;
    }

    public String gerarCodigoRecuperacao(String email) {
        String originalInput = this.gerarCodigoAleatorio((long) 32, true).concat(",").concat(email);
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        // encodedString = encodedString.replace("+/=", "._-");
        return encodedString;
    }

    public Usuario recuperar(RecuperarAcessoForm form) throws MessagingException {
        Usuario usuario = usuarioRepository.findByEmail(form.getEmail());
        String codigoRecuperacao = this.gerarCodigoRecuperacao(form.getEmail());
        emailSender.enviarCodigoRecuperacao(form.getEmail(), codigoRecuperacao);
        usuario.setCodigoRecuperacao(codigoRecuperacao);
        return usuarioRepository.save(usuario);
    }

    public Usuario recuperarAcesso(RecuperarSenhaForm form) {
        Usuario usuario = usuarioRepository.findByEmail(form.getEmail());
        if (form.getCodigo().equals(usuario.getCodigoRecuperacao())) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String novaSenha = bCryptPasswordEncoder.encode(form.getSenha());
            usuario.setCodigoRecuperacao(null);
            usuario.setSenha(novaSenha);
        } else {
            throw new RuntimeException("Código de Recuperação inválido!");
        }
        return usuarioRepository.save(usuario);
    }
  
    public Usuario excluirUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        usuarioRepository.delete(usuario);
        return usuario;
    }

    public Usuario ativarDesativarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        Boolean ativo = usuario.getAtivo().equals(true) ? false : true;
        usuario.setAtivo(ativo);
        usuarioRepository.save(usuario);
        return usuario;
    }

    public Usuario atualizarUsuario(Long id, String nome, String email, String senha, Boolean ativo) {
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setAtivo(ativo);
        usuarioRepository.save(usuario);
        return usuario;
    }

    @Transactional
    public List<Usuario> importarUsuario(List<Record> parseAllRecords) throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            for (Record record : parseAllRecords) {
                Usuario usuario = new Usuario();

                usuario.setNome(record.getString("nome"));
                usuario.setEmail(record.getString("email"));
                usuario.setSenha(record.getString("senha"));

                int idCargo = record.getInt("idCargo");
                Cargo cargo = new Cargo("", "", true);
                cargo.setId(idCargo);
                usuario.setCargo(cargo);

                usuarios.add(usuario);

                usuarioRepository.save(usuario);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao importar lista de usuários: " + e.getMessage());
        }
        return usuarios;
    }

}