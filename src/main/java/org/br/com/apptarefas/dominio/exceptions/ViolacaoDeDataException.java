package org.br.com.apptarefas.dominio.exceptions;

public class ViolacaoDeDataException extends RegraNegocioException{
    public ViolacaoDeDataException(String mensagem){
        super(mensagem);
    }
}
