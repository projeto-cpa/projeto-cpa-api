package br.com.biopark.cpa.controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import br.com.biopark.cpa.models.Curso;
import br.com.biopark.cpa.models.Disciplina;
import br.com.biopark.cpa.repository.DisciplinaRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoForm {

    @NotBlank(message = "Nome não pode ser vazio")
    @NotNull(message = "Nome não pode ser vazio")
    private String nome;
    private Boolean ativo;
    private String descricao;
    private List<Long> disciplinas;

    public Curso converter(DisciplinaRepository disciplinaRepository) {
        
        List<Disciplina> disciplinasList = new ArrayList<Disciplina>();
        for (Long id : this.disciplinas) {
            Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
            if (disciplina.isPresent()) {
                disciplinasList.add(disciplina.get());
            }
        }
        return new Curso(this.ativo, this.nome, this.descricao, disciplinasList);
    }

}
