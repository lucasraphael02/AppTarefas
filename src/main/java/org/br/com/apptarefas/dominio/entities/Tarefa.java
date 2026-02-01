package org.br.com.apptarefas.dominio.entities;

import org.br.com.apptarefas.dominio.exceptions.RegraNegocioException;
import org.br.com.apptarefas.dominio.exceptions.SubtarefaCircularException;
import org.br.com.apptarefas.dominio.exceptions.ViolacaoDeDataException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tarefa {
    private int  id;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataInicio;
    private LocalDate dataVencimento;
    private boolean concluido;
    private Tarefa tarefaPai;
    private List<Tarefa> subTarefas = new ArrayList<Tarefa>();
    private List<Tarefa> tarefasDependentes = new ArrayList<Tarefa>();

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public Tarefa getTarefaPai() {
        return tarefaPai;
    }

    public List<Tarefa> getSubTarefas() {
        return subTarefas;
    }

    public List<Tarefa> getTarefasDependentes() {
        return tarefasDependentes;
    }

    private Tarefa(String descricao, LocalDate dataInicio, LocalDate dataVencimento) {
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataVencimento = dataVencimento;
    }

    public static Tarefa criar(String descricao, LocalDate dataInicio, LocalDate dataVencimento) {
        return new Tarefa(descricao, dataInicio, dataVencimento);
    }

    public void alterarDataVencimento(LocalDate dataVencimento){
        if (!this.dataInicio.isBefore(dataVencimento)) {
            throw new ViolacaoDeDataException("Data de Vencimento não pode anteceder data de Início");
        }

        this.dataVencimento = dataVencimento;
    }

    public void estaDisponivel(){
        if (!this.dataInicio.isBefore(LocalDate.now())) {
            throw new ViolacaoDeDataException("Tarefa não disponível, data de hoje antecede data de Início");
        }
    }

    public void adicionarSubtarefa(Tarefa subtarefa){
        if (this.subTarefas.contains(subtarefa)) {
            throw new RegraNegocioException("A Tarefa " + subtarefa.getDescricao() + "já é subtarefa de " + getDescricao());
        }

        for (Tarefa pai = getTarefaPai(); pai != null; pai = pai.getTarefaPai() ) {
            if (pai.equals(subtarefa)) {
                throw new SubtarefaCircularException("Não pode haver subtarefas circulares");

            }
        }

        this.subTarefas.add(subtarefa);
        subtarefa.adicionarTarefaPai(this);

    }

    public void adicionarTarefaPai(Tarefa tarefa){
        // TODO Desenvolver regra de negócio
        this.tarefaPai = tarefa;
    }




}
