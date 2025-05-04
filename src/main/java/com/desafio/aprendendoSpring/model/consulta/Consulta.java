package com.desafio.aprendendoSpring.model.consulta;


import com.desafio.aprendendoSpring.model.consulta.dto.DadosAgendamentoConsulta;
import com.desafio.aprendendoSpring.model.enums.MotivoCancelamento;
import com.desafio.aprendendoSpring.model.medico.Medico;
import com.desafio.aprendendoSpring.model.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="consultas")
@Entity(name="Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Column(name = "data")
    private LocalDateTime dateTime;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    public void cancelar(MotivoCancelamento motivo) {

        this.motivoCancelamento = motivo;
    }
}
