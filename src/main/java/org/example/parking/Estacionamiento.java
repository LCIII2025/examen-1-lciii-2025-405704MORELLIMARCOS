package org.example.parking;

import java.util.*;

public class Estacionamiento {
    private final int capacidadMaxima = 50;
    private final Map<String, Ticket> vehiculosEstacionados = new HashMap<>();
    private final Map<String, Cliente> clientesRegistrados = new HashMap<>();

    public boolean ingresarVehiculo(String dni, String nombre, Vehiculo vehiculo) {
        // TO DO implementar la logica para registrar el ingreso de un nuevo vehiculo en el parking
        // la capacidad maxima del estacionamiento es de 50 vehiculos, si supera esta capacidad retornar FALSE
        if(vehiculosEstacionados.size() >= capacidadMaxima) {
            return false;
        }
        // validar que no exista otro vehiculo con la misma patente, es un caso de error, retornar FALSE
        if(vehiculosEstacionados.containsKey(vehiculo.getPatente())) {
            return false;
        }
        // validar si existe el cliente registrado, agregar el nuevo vehiculo en la lista del cliente existente, caso contrario crear un nuevo registro
        Cliente cliente = clientesRegistrados.get(dni);
        if(cliente == null) {
            cliente = new Cliente(dni, nombre);
            clientesRegistrados.put(dni, cliente);
        }

        if(cliente.buscarVehiculoPorPatente(vehiculo.getPatente()) != null) {
            return false;
        }

        cliente.agregarVehiculo(vehiculo);
        // si el proceso es exitoso retornar TRUE
        Ticket ticket = new Ticket(cliente,vehiculo);
        vehiculosEstacionados.put(vehiculo.getPatente(), ticket);
        return true;



    }

    public Ticket retirarVehiculo(String patente) throws Exception {
        // TO DO implementar la lógica para retirar un vehiculo del parking
        // validar que exista la patente, caso contrario arrojar la exception "Vehiculo no encontrado"
        if(!vehiculosEstacionados.containsKey(patente)) {
            throw new Exception("Vehiculo no encontrado");
        }
        // calcular y retornar el ticket del vehiculoEstacionado (ver Ticket.marcarSalida())
        Ticket ticket = vehiculosEstacionados.get(patente);
        ticket.marcarSalida();
        vehiculosEstacionados.remove(patente);

        return ticket;
    }

    public List<Ticket> listarVehiculosEstacionados() {
        return new ArrayList<>(vehiculosEstacionados.values());
    }
}
