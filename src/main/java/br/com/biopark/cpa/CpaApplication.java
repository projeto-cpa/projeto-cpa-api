package br.com.biopark.cpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.biopark.cpa.entidades.Funcionario;
import br.com.biopark.cpa.entidades.cargos.Aluno;

@SpringBootApplication
public class CpaApplication {

	public static void main(String[] args) {
		Funcionario funcionario = new Funcionario();
		Aluno aluno = new Aluno();
		System.out.println("Aqui:");
		System.out.println(aluno);
		SpringApplication.run(CpaApplication.class, args);
	}

}
