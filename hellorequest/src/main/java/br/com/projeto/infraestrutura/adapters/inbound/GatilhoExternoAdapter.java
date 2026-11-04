package br.com.projeto.infraestrutura.adapters.inbound;

import br.com.projeto.core.ports.inbound.ProcessarDadosUseCase;

public class GatilhoExternoAdapter {
    private final ProcessarDadosUseCase useCase;

    public GatilhoExternoAdapter(ProcessarDadosUseCase useCase){
        this.useCase = useCase;
    }

    public void aoReceberEventoExterno(String mensagemBruta){
        // 1. Transforma a mensagem bruta no seu Record do Dominio
        // 2. Dispara a usecase
        // useCase.executar(novoObjeto);
    }
}
