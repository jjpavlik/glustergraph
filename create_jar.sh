#!/bin/bash

cd src/

javac Elementos/*.java Main/*.java

jar cfm ../glustergraph.jar Manifest.txt Elementos/*class Main/*class
