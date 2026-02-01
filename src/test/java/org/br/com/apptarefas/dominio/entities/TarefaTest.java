package org.br.com.apptarefas.dominio.entities;

import org.br.com.apptarefas.dominio.exceptions.RegraNegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {

    private Tarefa tarefa;

        @BeforeEach
        void setUp(){
            tarefa = Tarefa.criar(
                    "Estudar Java",
                    LocalDate.of(2026, 5, 1),
                    LocalDate.of(2026, 5, 20)
            );
        }


        @Test
        void naoDevePermitirDataVencimentoAntesDaDataInicioTarefas() {
            assertThrows(RegraNegocioException.class, () -> {
                tarefa.alterarDataVencimento(LocalDate.of(2024, 4, 30));
            });
        }

        @Test
        void naoDevePermitirDataVencimentoAntesDaDataInicioSubTarefas() {
            assertThrows(RegraNegocioException.class, () -> {
                tarefa.alterarDataVencimento(LocalDate.of(2024, 4, 30));
            });
        }

        @Test
        void naoDevePermitirTarefaEstaDisponivelAntesDaDataInicio() {
            assertThrows(RegraNegocioException.class, () -> {
               tarefa.estaDisponivel();
            });
        }

        @Test
        void naoDeveAdicionarSubtarefa() {
            Tarefa pai = Tarefa.criar(
                    "Programação Java",
                    LocalDate.of(2025, 5, 1),
                    LocalDate.of(2025, 5, 20)
            );
            Tarefa filha = Tarefa.criar(
                    "Fazer Teste",
                    LocalDate.of(2025, 5, 1),
                    LocalDate.of(2025, 5, 20)
            );

            pai.adicionarSubtarefa(filha);
            assertThrows(RegraNegocioException.class, () -> {
                filha.adicionarSubtarefa(pai);
            });
        }

    }
