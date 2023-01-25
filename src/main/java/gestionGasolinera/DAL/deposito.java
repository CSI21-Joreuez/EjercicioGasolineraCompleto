package gestionGasolinera.DAL;

import java.io.Serial;
import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="dlk_gga_deposito",schema="dlk_gga")
public class deposito {
	public deposito() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public int getLitros() {
		return litros;
	}

	public void setLitros(int litros) {
		this.litros = litros;
	}

	public int getTipo_combustible() {
		return tipo_combustible;
	}

	public void setTipo_combustible(int tipo_combustible) {
		this.tipo_combustible = tipo_combustible;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique= true, nullable=false)
	private int id;
	
	@Column(name="fecha")
	private Calendar fecha;
	
	@Column(name="litros")
	private int litros;

	@Column(name="importe",unique=false,nullable=false)
	private Integer importe;
	
	public int getImporte() {
		return importe;
	}

	public void setImporte(int importe) {
		this.importe = importe;
	}

	public deposito(Calendar fecha, int litros, int importe, int tipo_combustible) {
		super();
		this.fecha = fecha;
		this.litros = litros;
		this.importe = importe;
		this.tipo_combustible = tipo_combustible;
	}



	@Column(name="tipo_combustible")
	private int tipo_combustible;
	
	

}
