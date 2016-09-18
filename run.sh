#!/bin/bash
#
#Pavlik Salles Juan Jose 02/08/2014
#
#Version 0.1: 
# - Works with Distribute and Distributed-Replicate volumes, the number of bricks doesn't matter. At least in theory xD.
# - Colours are kind of hardcoded for a number of 18 volumes (TOCHANGE)
# - 
#
#Comments to jjpavlik@gmail.com or https://viviendolared.blogspot.com :D
#
#Requieres:
# apt-get install graphviz
#
#TIPS:
# 1-Build gluster_info file by running:
# gluster vol info > gluster_info
# 2-Compile by running create_jar.sh
# 3-Run script ./run.sh
# 5-A PNG file called out.png should be generated
# --Bricks that belong to the same volume will have the same colour.
# --Replicated bricks can be identified by their REPX code.

if [ ! -e gluster_info ]; then
 echo "Couldn't fine gluster_info file. You can build it running \"gluster vol info > gluster_info\"."
 exit
fi

cat gluster_info | java -jar glustergraph.jar > out.dot
dot -Tpng out.dot > out.png
