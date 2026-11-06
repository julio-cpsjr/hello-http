package br.com.projeto.domain;

public class LeituraSensor{

    private String id;
    private double temperatura;
    private long timestamp;

    public LeituraSensor(String id, double temperatura, long timestamp){
        this.id =id;
        this.temperatura = temperatura;
        this.timestamp = timestamp;

    };

    public boolean IsTemperaturaCritica(){
        return this.temperatura > 80.0;
    }

}
