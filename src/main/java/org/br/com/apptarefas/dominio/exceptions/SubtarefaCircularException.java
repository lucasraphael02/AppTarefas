package org.br.com.apptarefas.dominio.exceptions;

public class SubtarefaCircularException extends RegraNegocioException {
    public SubtarefaCircularException(String msg) {
        super(msg);
    }
}
