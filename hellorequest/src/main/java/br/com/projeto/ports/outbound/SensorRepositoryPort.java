package br.com.projeto.ports.outbound;

import br.com.projeto.domain.LeituraSensor;

public interface SensorRepositoryPort {
    void salvar(LeituraSensor leitura);
}
