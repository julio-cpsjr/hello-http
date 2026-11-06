package br.com.projeto.adapters.outbound;

import br.com.projeto.domain.LeituraSensor;
import br.com.projeto.ports.outbound.SensorRepositoryPort;

public class DatabaseSensorAdapter implements SensorRepositoryPort {
    @Override
    public void salvar(LeituraSensor dados){
        System.out.println("Dados salvos fisicamente ");
    }
}
