package Main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import Elementos.Ladrillo;
import Elementos.Nodo;
import Elementos.Volumen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
	public static LinkedList<Nodo> nodos;
	public static LinkedList<Volumen> volumenes;
	public static String[] colores;
	public static String vol;
	
	public static void main(String[] args) {
		volumenes=new LinkedList<Volumen>();
		nodos=new LinkedList<Nodo>();
		colores=new String[18];
		colores[0]="blue";colores[1]="red";colores[2]="green";colores[3]="chocolate";colores[4]="coral";colores[5]="brown";
		colores[6]="cyan";colores[7]="cadetblue";colores[8]="darkorange";colores[9]="cornsilk";colores[10]="crimson";
		colores[11]="chartreuse";colores[12]="darkviolet";colores[13]="darkseagreen";colores[14]="darkgoldenrod";
		colores[15]="firebrick";colores[16]="gray";colores[17]="greenyellow";
		nodos = new LinkedList<Nodo>();
		if(args.length == 0)
		{
			vol="all volumes";
		}
		else
		{
			vol=args[0];
		}

		//Cargando los volumenes
		cargarVolumenes();
		nodesSort();
		//Escribiendo out.dot		
		System.out.println("digraph glustergraph{\n\t graph [compound=true,label=\"Gluster Graph\"];");
		
		for(int j=0;j<nodos.size();j++)
		{
			nodos.get(j).sortMe();
			System.out.println(nodos.get(j).toDot());
		}
		        
        for(int j=0;j<volumenes.size();j++)
        {
        	if(volumenes.get(j).isShow())
        		System.out.println("vol"+j+"[label=\""+volumenes.get(j).getNombre()+"\",color="+volumenes.get(j).getColor()+",shape=folder]");
        	else
        		System.out.println("vol"+j+"[style=invis]");
        }

        for(int j=0;j<volumenes.size()-1;j++)
        {
        	System.out.println("vol"+j+" -> vol"+(j+1)+" [style=invis]");
        }
		
		System.out.println("}");
	}
	
	public static boolean nodeExists(String name)
	{
		if(nodos.size()==0) return false;
		for(int i=0;i<nodos.size();i++)
		{
			if(nodos.get(i).getNombre().equals(name))
				return true;
		}
		return false;
	}
	
	public static void nodesSort()
	{
		int j;
		boolean flag=true;
		Nodo n;
		while(flag)
		{
			flag= false; 
			for(j=0;j<nodos.size()-1;j++)
			{
				if(nodos.get(j).getNombre().compareTo(nodos.get(j+1).getNombre()) > 0 )
				{
					n=nodos.get(j);
					nodos.remove(j);
					nodos.add(j+1,n);
					flag=true;
				} 
			}	 
		}
	}

	public static void cargarVolumenes()
	{
		boolean brickFound=false;
		Volumen v=null;
		Ladrillo l=null;
		int factorReplica=0,codigo=0,contador=0,contadorVolumenes=0;
		String nombre="", tipo="", identificador="", estado="",cantidad="";
		int contador_nodos=0;

		//patrones de expresiones regulares
		String patronVolumeName, patronType, patronId, patronStatus,patronCantidad, patronLadrillo;
		patronVolumeName = "Volume Name: (\\S+)";
		patronType = "Type: (\\S+)";
		patronId = "Volume ID: (\\S+)";
		patronStatus = "Status: (\\S)";		
		patronCantidad = "Number of Bricks: (.*)";
		patronLadrillo = "Brick\\d+: (.*)";
		
		Pattern volumeName = Pattern.compile(patronVolumeName);
		Pattern type = Pattern.compile(patronType);
		Pattern id = Pattern.compile(patronId);
		Pattern status = Pattern.compile(patronStatus);
		Pattern numberBricks = Pattern.compile(patronCantidad);
		Pattern brick = Pattern.compile(patronLadrillo);

		Matcher m;

		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(System.in));
    	try {
    		String line;
    		line = br.readLine();
    		while(line != null)
    		{
	    		m=volumeName.matcher(line);
	    		if(m.find())
	    		{
	    			//System.out.println(m.group(1));
	    			nombre = m.group(1);
    				brickFound=true;
    				line = br.readLine();
		    		m=type.matcher(line);
		    		if(m.find())
		    		{
		    			//System.out.println(m.group(1));
		    			tipo=m.group(1);
		    		}
		    		
		    		line = br.readLine();
		    		m=id.matcher(line);
		    		if(m.find())
		    		{
		    			//System.out.println(m.group(1));
		    			identificador=m.group(1);
		    		}
		    		
		    		line = br.readLine();
		    		m=status.matcher(line);
		    		if(m.find())
		    		{
		    			//System.out.println(m.group(1));
		    			estado=m.group(1);
		    		}
		    		
		    		line = br.readLine();
		    		m=numberBricks.matcher(line);
		    		if(m.find())
		    		{
		    			//System.out.println(m.group(1));
		    			cantidad=m.group(1);
		    		}

		    		v=new Volumen(nombre, tipo,identificador, estado,cantidad);
		    		volumenes.add(v);
		    		v.setColor(colores[contadorVolumenes]);
		    		factorReplica=v.getFactorReplica();
		    		contador=0;
		    		codigo=0;
		    		contadorVolumenes++;
		    		if(nombre.equals(vol) || vol.equals("all volumes"))
		    		{
		    			v.setShow(true);
		    		}
	    		}	    		
	    		else
	    		{
	    			m=brick.matcher(line);
	    			if(m.find() && brickFound)
	    			{
	    				l = new Ladrillo(m.group(1));
	    				l.setColor(v.getColor());
    					l.setisShow(v.isShow());
    					if(!nodeExists(l.getNombreNodo()))
	    				{
    	    				nodos.add(new Nodo(l.getNombreNodo(),"127.0.0.1",contador_nodos));
	    					contador_nodos++;
	    				}
    					if(v.getTipo().equals("Distributed-Replicate"))
	    				{
	    					l.setCodigo(codigo);
	    					l.setReplicated(true);
	    					contador++;
	    					if(contador==factorReplica)
	    					{
	    						contador=0;
	    						codigo++;
	    					}	    						
	    				}
	    				v.agregarLadrillo(l);
	    				for(int i=0;i<nodos.size();i++)
	    				{
	    					if(nodos.get(i).getNombre().equals(l.getNombreNodo()))
	    					{
	    						nodos.get(i).agregarLadrillo(l);
	    					}
	    				}
	    			}
	    		}
	    		line = br.readLine();
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    		return;
    	} finally {
    		try {
    			br.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
	}
}