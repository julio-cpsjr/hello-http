package br.com.projeto.service;

import br.com.projeto.domain.LeituraSensor;
import br.com.projeto.ports.inbound.SalvarLeituraUseCase;
import br.com.projeto.ports.outbound.SensorRepositoryPort;

public class SalvarLeituraService implements SalvarLeituraUseCase {

    private final SensorRepositoryPort repositoryPort;

    public SalvarLeituraService(SensorRepositoryPort repositoryPort){
        this.repositoryPort =repositoryPort;
    }

    @Override
    public void executar(LeituraSensor leitura){
        if(leitura.IsTemperaturaCritica()){
            System.out.println("ALERTA: Temperatura crítica detectada!");
        }
        repositoryPort.salvar(leitura);
    }
}
