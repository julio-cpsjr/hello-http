package br.com.projeto.adapters.inbound;

import br.com.projeto.domain.LeituraSensor;
import br.com.projeto.ports.inbound.SalvarLeituraUseCase;

public class MqttSensorAdapter {
    private final SalvarLeituraUseCase useCase;

    public MqttSensorAdapter(SalvarLeituraUseCase useCase){
        this.useCase = useCase;
    }

    public void onMessageReceived(String jsonPayload){
        LeituraSensor leitura = new LeituraSensor("SENSOR-01",85.3, System.currentTimeMillis());
        useCase.executar(leitura);
    }
}
