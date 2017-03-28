/*
----==============----
IKER GARCÍA FERRERO
    03/01/2017
 hardw360@gmail.com   
----==============----

«(C)» Copyright 2017 Iker García "Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0)" 

*/
package variables;

public class Variables {
	
	public int mode = 1; //
	public float max_speed=5; //
	public float max_acel=2; //
	public int onlybest=0; //
	public   int pop=25; //
	public int max_lifetime = 900;
	public 	boolean fastmode=false; //
	public 	float mutation_rate=(float) 0.01; //
	public float crossover_rate=(float) 0.90;
	public int loops_fastMode=1000; //
	public boolean fast_ultil_solved=false; //
	public int frammerate=200; //
	public float factor_tamaño=1;//
	public int best_mutations=2; //
	public boolean calcule_acel=true;
	public boolean reset=false;
	public boolean resetWalls=false;
}
