package br.com.biopark.cpa.service;

import java.util.Optional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biopark.cpa.models.Usuario;
import br.com.biopark.cpa.repository.UsuarioRepository;
import jakarta.persistence.Id;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario cadastrar(Usuario usuario) throws Exception {

        Usuario usuarioCadastrado = new Usuario();

        try {
            usuarioCadastrado = usuarioRepository.save(usuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return usuarioCadastrado;

    }

    public Iterable<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    public Usuario pegarUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent())
            return usuario.get();

        return null;
    }

    public Usuario atualizar(Long id, String senha) {
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.setSenha(senha);
        usuarioRepository.save(usuario);
        return usuario;
    }

    }

