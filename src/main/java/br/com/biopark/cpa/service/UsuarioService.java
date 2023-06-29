package br.com.biopark.cpa.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.biopark.cpa.models.Cargo;
import br.com.biopark.cpa.models.Turma;
import br.com.biopark.cpa.models.Usuario;
import br.com.biopark.cpa.repository.UsuarioRepository;
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

    public Usuario atualizar(Long idUsuario, String senha) {
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        usuario.setSenha(bCryptPasswordEncoder.encode(senha));
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