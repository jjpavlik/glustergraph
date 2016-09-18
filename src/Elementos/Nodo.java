package Elementos;

import java.util.LinkedList;

public class Nodo {
	
	private String nombre;
	private int id;
	private String IP;
	private LinkedList<Ladrillo> ladrillos;

	public Nodo(String nombre, String IP,int id)
	{    					
		System.err.println("New node found: "+nombre);
		this.setNombre(nombre);
		this.setIP(IP);
		ladrillos = new LinkedList<Ladrillo>();
		this.id=id;
	}
	
	/**
	 * Bubble sort, keep the nodes tide.
	 */
	public void sortMe()
	{
		int j;
		boolean flag=true;
		Ladrillo l;
		//System.err.println("Sorting "+this.nombre+"\n");
		while(flag)
		{
			flag= false; 
			for(j=0;j<ladrillos.size()-1;j++)
			{
				//System.err.println("Comparing "+ladrillos.get(j).getPuntoMontaje()+" with "+ladrillos.get(j+1).getPuntoMontaje()+"\n");
				if(ladrillos.get(j).getPuntoMontaje().compareTo(ladrillos.get(j+1).getPuntoMontaje()) > 0 )
				{
					l=ladrillos.get(j);
					ladrillos.remove(j);
					ladrillos.add(j+1,l);
					flag=true;
				} 
			}	 
		}
	}
		
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getIP() {
		return IP;
	}
	
	public void setIP(String iP) {
		IP = iP;
	}
	
	public void agregarLadrillo(Ladrillo l) {
		ladrillos.add(l);
	}
	
	public String toString()
	{
		String s="Nodo "+nombre+"\n";
		for(int i=0;i<ladrillos.size();i++)
		{
			s=s+"\t"+ladrillos.get(i).toString()+"\n";
		}
		return s;
	}
	
	public String toDot()
	{
		String s;
		s="subgraph cluster_nodo"+id+" {\n";
		s=s+"\t label=\""+nombre+"\";\n";
		for(int i=0;i<ladrillos.size();i++)
		{
			if(ladrillos.get(i).isReplicated())
			{	
				if(ladrillos.get(i).isShow())
					s=s+"\t nodo"+id+"_ladrillo"+i+" [label=\""+ladrillos.get(i).getPuntoMontaje()+" REP"+ladrillos.get(i).getCodigo()+"\",color="+ladrillos.get(i).getColor()+",shape=box3d];\n";
				else
					s=s+"\t nodo"+id+"_ladrillo"+i+" [style=invis];\n";
			}
			else
			{
				if(ladrillos.get(i).isShow())
					s=s+"\t nodo"+id+"_ladrillo"+i+" [label=\""+ladrillos.get(i).getPuntoMontaje()+"\",color="+ladrillos.get(i).getColor()+",shape=box3d];\n";
				else
					s=s+"\t nodo"+id+"_ladrillo"+i+" [style=invis];\n";
			}
		}
		for(int i=0;i<ladrillos.size()-1;i++)
		{
			s=s+"\t nodo"+id+"_ladrillo"+i+" -> "+"nodo"+id+"_ladrillo"+(i+1)+" [style=\"invis\"];\n";
		}
		s=s+"\n};";
		return s;
	}
}
