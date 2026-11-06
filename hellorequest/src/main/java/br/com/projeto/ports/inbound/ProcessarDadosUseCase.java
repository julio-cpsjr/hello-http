package br.com.projeto.ports.inbound;

import br.com.projeto.domain.LeituraSensor;
import br.com.projeto.ports.outbound.SensorRepositoryPort;

public class ProcessarDadosUseCase {
    private final SensorRepositoryPort tomadaSaida;

    public ProcessarDadosUseCase(SensorRepositoryPort tomadaSaida){
        this.tomadaSaida = tomadaSaida;
    }

    public void executar(LeituraSensor dados){
        System.out.println("Processando no Core....");
        tomadaSaida.salvar(dados);
    }
}
