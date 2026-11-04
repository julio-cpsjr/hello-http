package br.com.projeto.core.ports.inbound;

import br.com.projeto.core.domain.EntidadePrincipal;
import br.com.projeto.core.ports.outbound.SaidaPort;

public class ProcessarDadosUseCase {
    private final SaidaPort tomadaSaida;

    public ProcessarDadosUseCase(SaidaPort tomadaSaida){
        this.tomadaSaida = tomadaSaida;
    }

    public void executar(EntidadePrincipal dados){
        System.out.println("Processando no Core....");
        tomadaSaida.salvar(dados);
    }
}
