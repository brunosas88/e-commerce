package br.com.letscode.comprasvalidator.metricas;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;



@Component
public class Metricas {

    private final Counter contadorComprasProcessadas;
    private final Counter contadorComprasValidadas;

    public Metricas(MeterRegistry metricaCompraProcessada, MeterRegistry metricaCompraValidada) {
        this.contadorComprasProcessadas = metricaCompraProcessada.counter("metrica_compras_processadas");
        this.contadorComprasValidadas = metricaCompraValidada.counter("metrica_compras_validadas");
    }

    public void incrementarContadorCP() {
        contadorComprasProcessadas.increment();
    }

    public void incrementarContadorCV() {
        contadorComprasValidadas.increment();
    }

}
