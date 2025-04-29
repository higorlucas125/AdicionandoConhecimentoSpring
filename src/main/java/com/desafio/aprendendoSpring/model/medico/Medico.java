package com.desafio.aprendendoSpring.model.medico;


import com.desafio.aprendendoSpring.model.endereco.Endereco;
import com.desafio.aprendendoSpring.model.medico.dto.DadosAtualizarMedico;
import com.desafio.aprendendoSpring.model.medico.dto.DadosCadastroMedico;
import com.desafio.aprendendoSpring.model.enums.Especialidade;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="medicos")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@EqualsAndHashCode(of = "id")
public class Medico {

    public Medico (DadosCadastroMedico medico){
        this.status = true;
        this.nome = medico.nome();
        this.email = medico.email();
        this.telefone = medico.telefone();
        this.crm = medico.crm();
        this.especialidade = medico.especialidade();
        this.endereco = new Endereco(medico.endereco());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private boolean status;


    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    // Essa anotação fala que essa classe possui um atributo na mesma entidade que estou criando que é a medica
    @Embedded
    private Endereco endereco;

    public void atualizarInformacoes(DadosAtualizarMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluirLogico() {
        this.status = false;
    }
}
