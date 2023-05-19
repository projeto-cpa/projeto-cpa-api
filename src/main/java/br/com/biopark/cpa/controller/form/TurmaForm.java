package br.com.biopark.cpa.controller.form;
import br.com.biopark.cpa.models.Turma;
import br.com.biopark.cpa.service.CursoService;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaForm {

    private String nome;
    private String descricao;
    private long cursoId;

    public Turma converter(CursoService cursoService) {
        return new Turma(this.nome, this.descricao, cursoService.buscarCurso(cursoId));
    }
}
