package org.example.parking;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

@Data
@AllArgsConstructor
public class Ticket {
    private final Cliente cliente;
    private final Vehiculo vehiculo;
    private final LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;

    public Ticket(Cliente cliente, Vehiculo vehiculo) {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.horaEntrada = LocalDateTime.now();
    }

    public void marcarSalida() {
        Random random = new Random();
        this.horaSalida = LocalDateTime.now().plusMinutes(random.nextInt(200)+1);
    }

    public long calcularMinutos() {
        return Duration.between(horaEntrada, horaSalida).toMinutes();
    }

    public double calcularPrecio() {
        // TO DO implementar el metodo para calcular el importe a abonar segun el tipo de vehiculo
        // AUTO -> 100, SUV -> 130, PICKUP -> 180
        long minutos = calcularMinutos(), horas;
        double precioHora =0, tiempoEstacionado;
        switch (vehiculo.getTipo()) {
            case AUTO: precioHora=100;
                break;
            case SUV: precioHora=130;
                break;
            case PICKUP: precioHora=180;
                break;
            default: precioHora=100;
        }
        // el importe es por hora redondeando el tiempo hacia arriba,
        tiempoEstacionado= (double)minutos/60;
        horas = (long)Math.ceil(tiempoEstacionado);
        // por ejemplo si estuvo 45 minutos se le tarifa por 60, si estuvo 80 minutos se le tarifa por 120 minutos, etc...
        // retornar el importe final
        return horas*precioHora;
    }

}