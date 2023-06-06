package br.com.biopark.cpa.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.biopark.cpa.controller.form.UsuarioForm;
import br.com.biopark.cpa.models.Usuario;
import br.com.biopark.cpa.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario cadastrar(Usuario usuario) throws Exception{
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

    public Usuario buscarPorId(Long usuarioRespondenteId) {
        Optional<Usuario> usuario = usuarioRepository.findById((usuarioRespondenteId));

            if (usuario.isPresent())
                return usuario.get();
            else
                throw new EntityNotFoundException("Usuário não encontrado");

    }

    public Usuario atualizar(Long id, UsuarioForm usuarioForm) {
        Usuario usuario = usuarioRepository.findById(id).get();

        if (usuarioForm.getSenha() != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            usuario.setSenha(bCryptPasswordEncoder.encode(usuarioForm.getSenha()));
        }
        
        if(usuarioForm.getImagem() != null){
            usuario.setImagem(usuarioForm.getImagem());
        }
       
        return usuarioRepository.save(usuario);

    }

}