package Elementos;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Volumen {
	private String patronCantidad="(\\d+) x (\\d+) = (\\d+)";
	private String patronCantidad2="(\\d+)";
	private String nombre;
	private String tipo;
	private int tamanio;
	private String estado;
	private int cantLadrillos;
	private int factorReplica;
	private int numLadrillos;
	private String ID;
	private String color;
	private boolean show;
	
	private LinkedList<Ladrillo> ladrillos;
	
	public Volumen (String nombre, String tipo, String ID, String estado,String cantLadrillos)
	{
		System.err.println("New volume found: "+nombre);
		this.setNombre(nombre);
		this.setTipo(tipo);
		this.setID(ID);
		this.setEstado(estado);
		ladrillos = new LinkedList<Ladrillo>();
		
		if(this.tipo.equals("Distributed-Replicate"))
		{
			Pattern ladrillosPatron = Pattern.compile(patronCantidad);
			Matcher m;
			m=ladrillosPatron.matcher(cantLadrillos);
			m.find();
			this.numLadrillos=Integer.parseInt(m.group(1));
			this.factorReplica=Integer.parseInt(m.group(2));
			this.cantLadrillos=this.numLadrillos*this.factorReplica;
		}
		else
		{
			Pattern ladrillosPatron = Pattern.compile(patronCantidad2);
			Matcher m;
			m=ladrillosPatron.matcher(cantLadrillos);
			m.find();
			this.cantLadrillos=Integer.parseInt(m.group(1));
		}
	}
	
	public void agregarLadrillo(Ladrillo l) {
		ladrillos.add(l);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getTamanio() {
		return tamanio;
	}
	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int getCantLadrillos() {
		return cantLadrillos;
	}

	public void setCantLadrillos(int cantLadrillos) {
		this.cantLadrillos = cantLadrillos;
	}
	
	public String toString() {
		String s="Volumen "+nombre+" "+color;
		if(this.tipo.equals("Distributed-Replicate"))
		{
			s=s+" "+numLadrillos+"x"+factorReplica+"="+cantLadrillos+"\n";
		}
		else
		{
			s=s+" "+cantLadrillos+"\n";
		}
		for(int i=0;i<ladrillos.size();i++)
		{
			s=s+"\t"+ladrillos.get(i).toString()+"\n";
		}
		return s;
	}
	
	public int getFactorReplica() {
		return factorReplica;
	}

	public void setFactorReplica(int factorReplica) {
		this.factorReplica = factorReplica;
	}

	public int getNumLadrillos() {
		return numLadrillos;
	}

	public void setNumLadrillos(int numLadrillos) {
		this.numLadrillos = numLadrillos;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}
}
