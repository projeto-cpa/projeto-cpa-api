package br.com.biopark.cpa.controller.form;
import br.com.biopark.cpa.models.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TurmaForm {

    @NotBlank(message = "Nome não pode ser vazio")
    @NotNull(message = "Nome não pode ser vazio")
    private String nome;
    @NotBlank(message = "Descrição não pode ser vazia")
    @NotNull(message = "Descrição não pode ser vazia")
    private String descricao;
    @NotBlank(message = "Periodo não pode ser vazio")
    @NotNull(message = "Periodo não pode ser vazio")
    private String periodo;
    private Curso curso;
    private Boolean ativo;
    private Long idCurso;
}
