package edu.udea.modelo;

import java.util.ArrayList;
import java.util.List;

public class CursoProfesor {

	private Integer id;
	
	private String nombre;
	
	private Integer cantidadCreditos;
	
	private Integer totalHorasSemestre;
	
	public List<Grupos> grupos = new ArrayList<Grupos>();
	
	public CursoProfesor(Integer id, String nombre, Integer cantidadCreditos, Integer totalHorasSemestre,
			List<Grupos> grupos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantidadCreditos = cantidadCreditos;
		this.totalHorasSemestre = totalHorasSemestre;
		this.grupos = grupos;
	}

	public CursoProfesor() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidadCreditos() {
		return cantidadCreditos;
	}

	public void setCantidadCreditos(Integer cantidadCreditos) {
		this.cantidadCreditos = cantidadCreditos;
	}

	public Integer getTotalHorasSemestre() {
		return totalHorasSemestre;
	}

	public void setTotalHorasSemestre(Integer totalHorasSemestre) {
		this.totalHorasSemestre = totalHorasSemestre;
	}

	public List<Grupos> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupos> grupos) {
		this.grupos = grupos;
	}

}
