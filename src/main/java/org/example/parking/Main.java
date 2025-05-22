package org.example.parking;

import java.util.*;
/* 405704 - MORELLI HIDALGO MARCOS GASPAR*/
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Estacionamiento estacionamiento = new Estacionamiento();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- MENÚ ESTACIONAMIENTO ---");
            System.out.println("1. Registrar entrada");
            System.out.println("2. Registrar salida");
            System.out.println("3. Listar vehículos estacionados");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> registrarEntrada();
                case "2" -> registrarSalida();
                case "3" -> listarVehiculos();
                case "4" -> {
                    System.out.println("Gracias por usar el sistema.");
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void registrarEntrada() {
        System.out.print("DNI cliente: ");
        String dni = scanner.nextLine();
        System.out.print("Nombre cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Patente del vehículo: ");
        String patente = scanner.nextLine();
        System.out.print("Modelo del vehículo: ");
        String modelo = scanner.nextLine();

        System.out.print("Tipo de vehículo (AUTO, SUV, PICKUP): ");
        Vehiculo.Tipo tipo;
        try {
            tipo = Vehiculo.Tipo.valueOf(scanner.nextLine().toUpperCase());
        } catch (Exception e) {
            System.out.println("Tipo inválido.");
            return;
        }

        Vehiculo vehiculo = new Vehiculo(patente, modelo, tipo);

        boolean ingresado = estacionamiento.ingresarVehiculo(dni, nombre, vehiculo);
        if (ingresado) {
            System.out.println("Vehículo ingresado correctamente.");
        } else {
            System.out.println("No se pudo ingresar el vehículo. Estacionamiento lleno o patente ya registrada.");
        }
    }

    private static void registrarSalida() {
        System.out.print("Ingrese la patente del vehículo: ");
        String patente = scanner.nextLine();

        try {
            Ticket ticket = estacionamiento.retirarVehiculo(patente);
            System.out.println("---- TICKET ----");
            System.out.println("Cliente: " + ticket.getCliente().getNombre());
            System.out.println("Patente: " + ticket.getVehiculo().getPatente());
            System.out.println("Modelo: " + ticket.getVehiculo().getModelo());
            System.out.println("Tipo: " + ticket.getVehiculo().getTipo());
            System.out.println("Hora entrada: " + ticket.getHoraEntrada());
            System.out.println("Hora salida: " + ticket.getHoraSalida());
            System.out.println("Total minutos: " + ticket.calcularMinutos());
            System.out.println("Total a pagar: $" + ticket.calcularPrecio());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarVehiculos() {
        List<Ticket> lista = estacionamiento.listarVehiculosEstacionados();
        if (lista.isEmpty()) {
            System.out.println("No hay vehículos actualmente estacionados.");
        } else {
            System.out.println("Vehículos estacionados:");
            for (Ticket t : lista) {
                System.out.println("- " + t.getVehiculo().getPatente() +
                        " (" + t.getCliente().getNombre() + " - " + t.getVehiculo().getTipo() + ")");
            }
        }
    }
}