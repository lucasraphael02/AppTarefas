package org.br.com.apptarefas.dominio.exceptions;

public class DominioException extends RuntimeException{
    public DominioException(String mensagem){
        super(mensagem);
    }
}
