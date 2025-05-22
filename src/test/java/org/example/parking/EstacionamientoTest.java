package org.example.parking;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class EstacionamientoTest {


    @Test
    public void testRetirarVehiculo() throws Exception {
        //TODO test
        Estacionamiento estacionamiento = new Estacionamiento();
        Map<String, Ticket> vehiculosEstacionados = new HashMap<>();
        Vehiculo vehiculo = new Vehiculo("AH341KQ","Toyota", Vehiculo.Tipo.AUTO);
        Cliente cliente =new Cliente("44194983", "Marcos Morelli");
        Ticket ticket = new Ticket(cliente,vehiculo, LocalDateTime.now().minusMinutes(2),null);

        vehiculosEstacionados.put(vehiculo.getPatente(), ticket);
        Ticket ticketAutoRetirado=estacionamiento.retirarVehiculo(vehiculo.getPatente());
        assertEquals(vehiculo.getPatente(),ticketAutoRetirado.getVehiculo().getPatente());
    }

    @Test
    public void testCalcularPrecio() throws Exception {
        // TODO test
        Cliente cliente = new Cliente("44194983", "Marcos Morelli");
        Vehiculo vehiculo = new Vehiculo("AB187DJ", "Chevrolet", Vehiculo.Tipo.PICKUP);
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime salida = entrada.plusMinutes(90);
        Ticket ticket = new Ticket(cliente, vehiculo, entrada, salida);
        double precio = ticket.calcularPrecio();
        assertEquals(360.0, precio);

    }

}