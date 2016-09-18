#!/bin/bash
#
#Pavlik salles Juan Jose 02/08/2014
#
#Version 0.1: 
# - Admite volumenes Distribute y Distributed-Replicate sin importar el numero de ladrillos, ni de nodos, ni de replicas. Al menos en teoria.
# - Debido a que los colores estan hardcoded el numero maximo de volumenes es 18 (TOCHANGE)
# - 
#
#Comentarios a jjpavlik@gmail.com o en https://viviendolared.blogspot.com
#
#Requiere:
# apt-get install graphviz
#
#Funcionamiento:
# 1-Armar el archivos hosts con los hosts que conforman el cluster de gluster, el archivo tiene el formato hostname:IP
# 2-Armar el archivo de configuracion de gluster corriendo "gluster vol info > gluster_info"
# 3-Ubicar ambos archivos en el mismo directorio donde se encuentra este script
# 4-Lanzar este script ./lanzar
# 5-La imagen sera out.png
# --Los ladrillos de un mismo volumen tendran el mismo color
# --Los ladrillos con replicas podran ser identificados por su codigo. Por ejemplo si el ladrillo 1 del nodo 2 es replica del ladrillo 2 del nodo 1, ambos tendran el mismo codigo

if [ ! -e gluster_info ]; then
 echo "Couldn't fine gluster_info file. You can build it running \"gluster vol info > gluster_info\"."
 exit
fi

cat gluster_info | java -jar glustergraph.jar > out.dot
dot -Tpng out.dot > out.png
