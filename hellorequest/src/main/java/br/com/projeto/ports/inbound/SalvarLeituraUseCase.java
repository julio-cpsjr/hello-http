package br.com.projeto.ports.inbound;

import br.com.projeto.domain.LeituraSensor;

public interface SalvarLeituraUseCase {
    void executar(LeituraSensor leitura);
}
