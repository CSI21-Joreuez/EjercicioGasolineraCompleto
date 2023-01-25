package gestionGasolinera.controller;

import java.util.Calendar;
 import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import gestionGasolinera.DAL.combustible;
import gestionGasolinera.DAL.deposito;
import gestionGasolinera.DAL.vehiculos;
import impl.Consultas;

@Controller
public class App {

	public static void main(String[] args) {
			
		ApplicationContext context = new ClassPathXmlApplicationContext("contexto.xml"); 
		Consultas consulta = (Consultas) context.getBean(Consultas.class);
		Scanner reader = new Scanner(System.in);
		int numero = 0;
		
		int litros;
		String dni;
		String matricula;
		Calendar fecha = Calendar.getInstance();
		int importe;
		int tipo_combustible;
		int opcion=1;
		List<combustible> listaC = consulta.selectCombustibles();
				List<deposito> listadepo = consulta.selectDepositos();
		do {
			System.out.println("1) Repostaje normal.");
			System.out.println("2) Repostaje factura.");
			System.out.println("3) Ver todos los repostajes.");
			System.out.println("4) Importe total combustible vendido.");
			System.out.println("5) Llenado de deposito.");
			System.out.println("6) Eliminar último llenado de depósito.");
			System.out.println("7) Ver todos los llenados de depósito.");
			System.out.println("Elige un numero para el registro de la gasolinera");
			numero = reader.nextInt();

			
			switch (numero) {
			case 1:
				
				;
				System.out.println("Inserta el Importe del Repostaje");
				importe = reader.nextInt();
				for (combustible cm : listaC) {
					System.out.println(cm.getId()+")" + cm.getNombre());
				}
				System.out.println("Tipo de Combustible repostado");
				tipo_combustible = reader.nextInt();
				
				consulta.insterarVehiculo(new vehiculos( fecha, null, null, importe, tipo_combustible));
				System.out.println("Repostaje Completado Con Exito");
				break;

			case 2:
				listaC = consulta.selectCombustibles();
		        System.out.println("Inserta la Matricula del Vehiculo");
		        matricula = reader.next();
		        
		        System.out.println("Inserta DNI del Cliente");
		        dni = reader.next();
		        
				System.out.println("Inserta el Importe del Repostaje");
				importe = reader.nextInt();
				
				for (combustible cm : listaC) {
					System.out.println(cm.getId()+")" + cm.getNombre());
				}
				System.out.println("Tipo de Combustible repostado");
				tipo_combustible = reader.nextInt();
				consulta.insterarVehiculo(new vehiculos( fecha, dni, matricula, importe, tipo_combustible));
				System.out.println("la factura se ha completado con exito");
				break;
			case 3:
				List<vehiculos> listav = consulta.selectVehiculos();
				for (vehiculos vh : listav) {
					System.out.println(vh.getId()+") DNI: " + vh.getDni() +" Matricula: " + vh.getMatricula() +" Combustible: "+ vh.getId_combustible() +" " + vh.getImporte()+ "Euros");

				}
				break;

			case 4:
				 listav = consulta.selectVehiculos();
				 listaC = consulta.selectCombustibles();
				 System.out.println(" --- *** Cantidad de Combustible vendida de cada tipo *** ---");
				 for (combustible cb : listaC) {
						importe = 0;
					 System.out.print(cb.getId()+") Combustible: "+ cb.getNombre() +"--> ");
					 for (vehiculos vh : listav) {
							if(vh.getId_combustible() == cb.getId() || vh.getId_combustible() == cb.getId() || vh.getId_combustible() == cb.getId() )
							importe += vh.getImporte();
						}
						System.out.println( importe+ " Euros");
				}
				break;
			case 5:
				importe =0;
				System.out.println("Inserta Litros:");
				litros = reader.nextInt();
				System.out.println("Inserta Importe:");
				importe = reader.nextInt();
				for (combustible cm : listaC) {
					System.out.println(cm.getId()+")" + cm.getNombre());
				}
				System.out.println("Tipo de Combustible:");
				tipo_combustible = reader.nextInt();
				consulta.llenarDeposito(new deposito( fecha,litros, importe, tipo_combustible));
				break;

			case 6:
				importe =0;
				for ( deposito depo : listadepo) {
				for (int i = 1; i < listadepo.size(); i++) {
					if(i>importe)
						importe = i;
				}
				consulta.borrarUltimoLLenado(listadepo.get(importe));
				}
				
				break;
			case 7:
				for (deposito depo : listadepo) {
					System.out.println("ID"+depo.getId()+" ) " +"Tipo de Combustible: "+depo.getTipo_combustible() + " Litros: " +depo.getLitros()+ " Importe: " +depo.getImporte()+" Euros");
				}
				break;
			default:
				System.out.println("No has asignado un numero dentro del Rango");
				break;
			}
			System.out.println("Pulsa 0 para salir");
			opcion = reader.nextInt();
			
		
		} while (opcion !=0);	
	}	
}
