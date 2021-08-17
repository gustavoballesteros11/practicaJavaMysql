/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertois;

import controladores.ControladorAvion;
import controladores.ControladorHangar;
import controladores.ControladorHangarAvion;

import controladores.ControladorPropietario;
import java.util.Scanner;
import modelos.Avion;
import modelos.Hangar;
import modelos.Propietario;
import vistas.VistaAvion;
import vistas.VistaPropietario;

/**
 *
 * @author Juan
 */
public class AeropuertoIS {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        VistaPropietario vistaPropietario = new VistaPropietario();
        Propietario propietario;
        ControladorPropietario controladorPropietario = new ControladorPropietario();
        ControladorHangar controladorHangar = new ControladorHangar();
        int opcion;

        do {

            System.out.println("Bienvenido al Aeropuerto");
            System.out.println("Por favor elija una opción");
            System.out.println("1 - Registrar propietario");
            System.out.println("2 - Registrar avión y propietario (opcional) ");
            System.out.println("3 - Consultar datos del hangar");
            System.out.println("0 - Salir");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    propietario = vistaPropietario.activarVista();
                    controladorPropietario.registrarPropietario(propietario);
                    break;

                case 2:
                    
                    Hangar hangar = controladorHangar.consultarInformacionHangar();

                    //1. Preguntar si tengo cupos disponibles en el hangar
                    if (hangar.getCuposDisponibles() > 0) {
                        int opcion2;
                        System.out.println("Si no desea registrar un propietario escriba 0");
                        opcion2 = teclado.nextInt();
                        if (opcion2 != 0) {
                            propietario = vistaPropietario.activarVista();
                            controladorPropietario.registrarPropietario(propietario);
                        }
                        System.out.println("El avión se registrará en el espacio numero " + (hangar.getCuposReservados() + 1));
                        VistaAvion vistaAvion = new VistaAvion();
                        Avion avion = vistaAvion.activarVista();
                        ControladorAvion controladorAvion = new ControladorAvion();
                        controladorAvion.registrarAvion(avion);
                        ControladorHangarAvion controladorHangarAvion = new ControladorHangarAvion();
                        controladorHangarAvion.matricularAvionEnHangar(avion.getMatricula());
                        controladorHangar.actualizarHangar(hangar);

                    } else {

                        System.out.println("No tenemos cupos disponibles");

                    }
                    break;
                    
                case 3:
                    
                    Hangar hangarConsulta = controladorHangar.consultarInformacionHangar();
                    System.out.println("El hangar numero " + hangarConsulta.getCodigoHangar() + " cuenta con las siguientes caracteristicas: ");
                    System.out.println("Numero de cupos reservados: " + hangarConsulta.getCuposReservados());
                    System.out.println("Numero de cupos disponibles: " + hangarConsulta.getCuposDisponibles());
                    System.out.println("Total de cupos: " + hangarConsulta.getCuposTotales());
                    break;

            }
            System.out.println("---------------------------------------------------------------------------");
        } while (opcion != 0);

    }

}
