package Elementos;

import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Ladrillo 
{
	private String patronNodo="(\\S+):(\\S+)";
	private String puntoMontaje;//lugar en el arbol de directorios donde esta montado el ladrillo
	private String nombre;//nombre del ladrillo formato nombreNodo:puntoMontaje
	private String nombreNodo;//nombre del nodo al que pertenece el ladrillo
	private String dispositivo;//dispositivo donde se encuentra el ladrillo
	private int codigo;//ladrillos replicados tienen el mismo codigo
	private int tamanio;
	private String color;
	private boolean isReplicated;
	private boolean isShow;
	
	private LinkedList<Ladrillo> replicas;//posiblemente no lo precise.
	
	public Ladrillo(String nombre)
	{
		System.err.println("New brick found: "+nombre);
		this.nombre=nombre;
		Pattern nombreNodoPatron = Pattern.compile(patronNodo);
		Matcher m;
		m=nombreNodoPatron.matcher(nombre);
		m.find();
		this.nombreNodo=m.group(1);
		this.puntoMontaje=m.group(2);
	}

	public boolean isShow()
	{
		return this.isShow;
	}
	
	public void setisShow(boolean i)
	{
		this.isShow=i;
	}
	
	public String getPuntoMontaje() {
		return puntoMontaje;
	}
	
	public void setPuntoMontaje(String puntoMontaje) {
		this.puntoMontaje = puntoMontaje;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDispositivo() {
		return dispositivo;
	}
	
	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}
	
	public int getTamanio() {
		return tamanio;
	}
	
	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}
	
	public String getNombreNodo() {
		return nombreNodo;
	}

	public void setNombreNodo(String nombreNodo) {
		this.nombreNodo = nombreNodo;
	}
	
	public int getCodigo()
	{
		return codigo;
	}
	
	public void setCodigo(int c)
	{
		this.codigo=c;
	}
	
	public String toString()
	{
		return "Ladrillo: "+nombre+" ("+codigo+")";
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isReplicated() {
		return isReplicated;
	}

	public void setReplicated(boolean isReplicated) {
		this.isReplicated = isReplicated;
	}
}
