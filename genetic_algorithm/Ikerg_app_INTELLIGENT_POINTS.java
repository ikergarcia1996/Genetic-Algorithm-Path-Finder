/*
----==============----
IKER GARCÍA FERRERO
    03/01/2017
 hardw360@gmail.com   
----==============----

«(C)» Copyright 2017 Iker García "Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0)" 

*/

package genetic_algorithm;


import java.util.ArrayList;

import app_interface.App_control_interface;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import variables.Variables;

public class Ikerg_app_INTELLIGENT_POINTS extends PApplet{
Variables var = new Variables();
    public static void main(String[] args) {
        PApplet.main("genetic_algorithm.Ikerg_app_INTELLIGENT_POINTS");

    }
    
    PFont f;
    
    float objetive_x= 100;
    float objetive_y= 190;
    
    int max_lifetime=var.max_lifetime;
    int max_lifetime_temp;
    int pop=var.pop;
    
    float spawn_x=0;
    float spawn_y=0;
    
    float wall_x=0;
    float wall_y=0;
    
   
    int actual_lifetime=0;
    int generation=0;     
    int midpoint;
    
    float max_score;
    
    boolean solved = false;
    
   

    class Vector
    {
     float x;
     float y;
     
     public Vector()
     {
      this.x=0;
      this.y=0;
     }
     public Vector(float d, float e)
     {
      this.x= d;
      this.y= e;
     }
     
     float force_intensity()
     {
     return abs(x+y);
     }
     
     void applyAcceleration(Vector acel)
     {
    	 if(var.calcule_acel)
    	 {
	       x+=(acel.x);
	       if (x>var.max_speed) x=var.max_speed;
	       else if (x<-var.max_speed) x=-var.max_speed;
	       
	       y+=(acel.y);
	       if (y>var.max_speed) y=var.max_speed;
	       else if (y<-var.max_speed) y=-var.max_speed;
    	 }
    	 
    	 else
    	 {
    		 x=acel.x;
    		 y=acel.y;
    	 }
    		 
     }
     
     void applySpeed(Vector speed)
     {
       
       x+=speed.x;
       y+=speed.y;
     }
     
     
    }
    
    class Obstacle
    {
    	Vector p1;
    	Vector p2;
    	
    	public Obstacle(float x1, float x2, float y1, float y2)
    	{
    		float ox1,ox2,oy1,oy2;
    		if (x1<x2) //Order coordinates makes the collision calculations easier. 
    		{
    			ox1=x1;
    			ox2=x2;
    		}
    		else 
    		{
    			ox1=x2;
    			ox2=x1;
    		}
    		
    		if (y1<y2)
    		{
    			oy1=y1;
    			oy2=y2;
    		}
    		else 
    		{
    			oy1=y2;
    			oy2=y1;
    		}
    		
    		this.p1=new Vector(ox1,oy1);
    		this.p2=new Vector(ox2,oy2);
    	}
    	
    	boolean collision(Vector pos)
    	{
    		if (pos.x>p1.x&&pos.x<p2.x&&pos.y>p1.y&&pos.y<p2.y)
    			return true;
    		else return false;
    	}
    	
    	void paint()
    	{
    		fill(66,134,244);
    		rectMode(CORNERS);
    		rect(p1.x,p1.y,p2.x,p2.y);
    	}
    	
    	
    	
    }
    
    class All_obstacles
    {
    	ArrayList<Obstacle> obstacles;
    	
    	public All_obstacles()
    	{
    		obstacles=new ArrayList<Obstacle>();
    	}
    	
    	void add_obstacle(float x1, float x2, float y1, float y2)
    	{
    		obstacles.add(new Obstacle(x1,x2,y1,y2));
    		System.out.println("MURO AÑADIDO EN EL " + x1 + " " + y1 + " " + x2 + " " +y2);
    	}
    	
    	void paint_obstacle()
    	{
	    	for (int i=0;i<obstacles.size();i++)
	    	{
	    		obstacles.get(i).paint();
	    	}
    	}
    	
    	boolean is_dead(Vector pos)
    	{	
    		boolean coll=false;
    		for (int i=0;i<obstacles.size();i++)
	    	{
	    		if(obstacles.get(i).collision(pos))
	    			coll=true;
	    	}
    		
    		return coll;
    	}
    }
    
    All_obstacles obs=new All_obstacles();
    
    class DNA
    {
      
      float max_force= var.max_acel;
      Vector[] genes;
      
      
      public DNA()
      {
        iniDNA();
      }
      
      public DNA(DNA tocopy) //Clone a DNA
      {
    	  genes=new Vector[max_lifetime];
    	  int ind = max_lifetime;
    	  if (ind>max_lifetime_temp)
    		  ind=max_lifetime_temp;
    	  
          for (int i=0;i<ind;i++)
          {
          	genes[i]=new Vector();
          	genes[i].x=tocopy.genes[i].x;
          	genes[i].y=tocopy.genes[i].y;
          }
          
          for (int i=ind;i<max_lifetime;i++)
          {
        	  genes[i]=new Vector();
        	  genes[i].x=  (float) (Math.random()*(max_force*2))-max_force;
              genes[i].y=  (float) (Math.random()*(max_force*2))-max_force;
          }
    		 
      }
      
      void iniDNA()
      {
        genes=new Vector[max_lifetime];
        for (int i=0;i<max_lifetime;i++)
        {
        	genes[i]=new Vector();
        }
        
        
        
        for (int i=0; i<max_lifetime;i++)
        {
         genes[i].x=  (float) (Math.random()*(max_force*2))-max_force;
         genes[i].y=  (float) (Math.random()*(max_force*2))-max_force;
          
         
        }
        
      }
      
      /*NOT USED
     int force_intensity() 
      {
       float intensity= genes[actual_lifetime].force_intensity();
        if (intensity==0) return 0;
        else if (intensity<6) return 1;
        else return 2;
      }
      */
      
      Vector actual_gen()
      {
       return genes[actual_lifetime];
      }
      
    DNA crossover(DNA partner2,int midpoint)
      {
       
        DNA child = new DNA();
        
        int ind = child.genes.length;
        if (ind>max_lifetime_temp)
        	ind = max_lifetime_temp;
        
        for (int i=0;i<ind;i++)
        {
          if (i<midpoint)
          {
            child.genes[i]=this.genes[i];
          }
          
          else 
          {
            child.genes[i]=partner2.genes[i];
          }
        }
        
        return child;
      }
        
      
      
      void mutate()
      {
       for (int i=0;i<max_lifetime;i++)
       {
         if (Math.random()<var.mutation_rate)
         {
           int tempx,tempy;
            tempx= Math.round( (float) (Math.random()*(max_force*2))-max_force );
            tempy= Math.round( (float) (Math.random()*(max_force*2))-max_force );
            genes[i].x= tempx;
            genes[i].y= tempy;
         }
       }
      }
      
    }

    class Fitness
    {
     float dis;
     float time_alive;
     float total_score;
     
     public Fitness()
     {
       this.dis=Float.MAX_VALUE;
       this.time_alive=0;
       this.total_score=0;
     }
     
     boolean update(Vector pos) 
     {
       dis=(float) (Math.pow(pos.x-objetive_x,2)+ Math.pow(pos.y-objetive_y,2)); 
       time_alive++;  
       total_score=(float) (1.0/(1.0+dis+time_alive*time_alive)); //we dont do the sqrt of the distance, because sqrt is too slow, do time_alive*time_alive is much faster. 
       																//"1 +" to avoid 1/0
     
       if (dis<3) return true;
       else return false;
     }
     
     void dead()
     {
      total_score-=1000; 
     }
     
     
     float get_score()
     {
       return total_score;
     }
      
    }

    class Rocket
    {
      Vector pos;
      Vector speed;
      DNA dna;
      
      Fitness fitness;
      boolean dead;
      boolean finish;
      boolean best;
      
      public Rocket()
      {
       this.pos= new Vector(spawn_x,spawn_y);
       this.speed = new Vector(0,0);
       this.dna = new DNA(); 
       this.fitness= new Fitness();
       this.dead=false;
       this.finish=false;
       this.best=false;
       
      }
      void tick()
      {
      speed.applyAcceleration(dna.actual_gen());
      pos.applySpeed(dna.actual_gen());
      finish=fitness.update(pos);
      if (finish){
    	  fitness.total_score*=4;
    	  solved=true;
    	  if(!var.fastmode)
    		  System.out.println("Un individuo ha llegado al objetivo");
    	  
      }
    	
     
      dead=obs.is_dead(pos);
      if (dead)
    	  fitness.total_score-=100;
      }
      
      
      
      float get_fitness()
      {
        return fitness.get_score();
      }
      
      /*NOT USED
      int get_intensity()
      {
        return dna.force_intensity();
      }*/
      
      float get_x()
      {
        return pos.x;
      }
      
       float get_y()
      {
        return pos.y;
      }

    }

      

    class Population
    {
      
      Rocket[] population; 
      
      
      public Population()
      {
    	  population = new Rocket[pop];
    	  
    	  for (int i=0;i<pop;i++)
    	  {
    	  population[i]=new Rocket();
    	  }
    	  
    	  
      }
      
      

      boolean finish_it()
      {
    	  for (int i=0;i<pop;i++)
    	  {
    		  if(!population[i].dead&&!population[i].finish)
    			  return false;
    	  }
    	  return true;
      }
      
      void tick()
      {
        for (int i=0;i<pop;i++)
        {
          if (!population[i].dead&&!population[i].finish)
          {
            population[i].tick();
          }
            if(population[i].best||var.onlybest==0)
	            {
	            
	       
	            if(!var.fastmode)
	            {
		            if (population[i].dead)
		            {
		              fill(247,4,4);
		              ellipse(population[i].get_x(),population[i].get_y(),var.factor_tamaño*5,var.factor_tamaño*5);
		            }
		            
		            else if (population[i].finish)
		            {
		              fill(78,232,78);
		              ellipse(population[i].get_x(),population[i].get_y(),var.factor_tamaño*5,var.factor_tamaño*5);        
		            }
		            
		            else
		            {
		              fill(242,94,205);
		              ellipse(population[i].get_x(),population[i].get_y(),var.factor_tamaño*5,var.factor_tamaño*5);      
		            }
	            }
            }
         
       }
      }
      
      void new_generation()
      {
    	 
        //CHILD GENERATION
        ArrayList<Integer> fortuneWheel = new ArrayList<Integer>();
        int n,j;
        float score_max=population[0].get_fitness();
        DNA best = new DNA(population[0].dna);
        for (int i = 0; i<pop;i++)
        {
          n=(int) population[i].get_fitness()*1000000+1;
          
          if (n<1) n=1;
          
          for (j=0; j<n;j++)
          {
            fortuneWheel.add(i);
          }
          
          if (population[i].get_fitness()>score_max)
          {
            best = new DNA(population[i].dna);
            max_score=population[i].get_fitness();
            
          }
        }
        if(!var.fastmode)
        {
        //System.out.println("Mejor: "+ score_max);
       
        }
        
        //UPDATE VARIABLES POR THE NEXT GENERATION
        max_lifetime_temp=max_lifetime; //Need to calculate midpoint
        max_lifetime=var.max_lifetime;
        pop=var.pop;
        
        Rocket[] population2= new Rocket[pop];
        for (int i=0;i<pop;i++)
  	   {
     	   population2[i]=new Rocket();
     	 
  	   }
       for (int i = 0; i<pop;i++)
       {
    	   if (Math.random()<=var.crossover_rate)
    	   {
		       int elm1=Math.round( (float) (Math.random()*(fortuneWheel.size()-1)));
		       int elm2=Math.round( (float) (Math.random()*(fortuneWheel.size()-1)));
		       
		       
		       
		       DNA parent1 = population[fortuneWheel.get(elm1)].dna;
		       DNA parent2 = population[fortuneWheel.get(elm2)].dna;
		     
		       midpoint=Math.round( (float) (Math.random()*(max_lifetime_temp-(max_lifetime_temp/2)))+(max_lifetime_temp/2));
		       
		       DNA child = parent1.crossover(parent2,midpoint);
		     
		       child.mutate();
		       population2[i].dna=child;
    	   }
       }
       //NEW POPULATION
    
       population=population2;
       
       //RESCUE BEST
       
       population[0].dna=new DNA(best);
       population[0].best=true;
       
       //RESCUE SOME MUTATIONS FROM THE BEST (THIS HELPS TO ACCELERATE THE OPTIMIZATION OF THE SOLUTION,
       //AND SOME TIMES HELPS TO FIND THE SOLUTION)
       if(var.best_mutations>population.length-1)
    	   var.best_mutations=population.length-1;
       
       for (int i = 1; i<var.best_mutations;i++)
       {
    	   population[i].dna= new DNA(best);
           population[i].dna.mutate();
       }
     
       generation++;
       actual_lifetime=0;
       
       
      }
       
    }
    
    void draw_text()
    {	
    	clear();
    	background(255);  
    	noStroke();
        fill(161, 0, 255);
        text("Generation: " + generation + " Best: " + max_score  ,1,height-1);
    }
    
    void iterations()
    {	
    	
    	while(true)
    	{	
	    	population.tick();
	    	 actual_lifetime++;
	         if (actual_lifetime==max_lifetime)
	         {
	          actual_lifetime=0;
	          population.new_generation();
	          break;
	         }
    	}
    }

    //DRAWING
    public void settings()
    {
    	size(720,480);
    	smooth(2);
    }
    
    public void stop()
    {
    	System.exit(0);
    }
    
    Population population;
    
    public void setup()
    {
    PImage titlebaricon = loadImage("icon.PNG");
    surface.setIcon(titlebaricon);
     surface.setResizable(true);
     surface.setTitle("SimpleAI - Intelligent Points");
     background(255);  
     noStroke();
     population=new Population();
     App_control_interface.main(var, 0, 0);
     f = createFont ("Serif",25);
     textFont(f);
     
    }
    
    public void draw()
    {
    	

   
    frameRate(var.frammerate);
    
   //print in the screen
   
    
    draw_text();
    

    
    
    if (var.fastmode)
    {	
    	for (int i=0; i<var.loops_fastMode;i++)
    	{
	    	iterations();
	    	if(!var.fastmode||var.reset||var.resetWalls) break;
    	}
    	var.fastmode=false;
    	solved=false;
    }
    
    else if(var.fast_ultil_solved)
    {
    	var.fastmode=true;
    	while(!solved&&var.fast_ultil_solved&&!var.reset&&!var.resetWalls)
    	{
    		iterations();	
    	}
    	var.fastmode=false;
    	var.fast_ultil_solved=false;
    	solved=false;
    }
    	
    if (var.reset)
    {
    	population=new Population();
    	obs=new All_obstacles();
    	var.reset=false;
    }
    
    if(var.resetWalls)
    {
    	obs=new All_obstacles();
    	var.resetWalls=false;
    }
    
    else
    {
      
      
      population.tick();
      
      //PAINT OBJETIVE
      if(solved)
    	  fill(109, 255, 107);
      else
    	  fill(204,0,204);
      
      ellipse(objetive_x,objetive_y,var.factor_tamaño*15,var.factor_tamaño*15);
      fill(255,255,255);
      ellipse(objetive_x,objetive_y,var.factor_tamaño*5,var.factor_tamaño*5);
      //PAINT SPAWN
      fill(247,215,128);
      ellipse(spawn_x,spawn_y,var.factor_tamaño*15,var.factor_tamaño*15);
      fill(255,255,255);
      ellipse(spawn_x,spawn_y,var.factor_tamaño*5,var.factor_tamaño*5);
      //PAINT WALLS
      obs.paint_obstacle();
      
      actual_lifetime++;
      if (actual_lifetime==max_lifetime||population.finish_it())
      {
      
       actual_lifetime=0;
       population.new_generation();    
       System.out.println("Generación: "+ generation);
       solved=false;
     } 
      
      }
      
      
      
    }

    
    
    public void mouseClicked()
    {	
    	if (var.mode==1)
    	{
    	objetive_x=mouseX;
    	objetive_y=mouseY;
    	}
    	
    	else if (var.mode==2)
    	{
    	spawn_x=mouseX;
    	spawn_y=mouseY;
    	}
    	
    	else if (var.mode == 3)
    	{
    		if (wall_x==0&&wall_y==0)
    		{
    			wall_x=mouseX;
    			wall_y=mouseY;
    		}
    		
    		else
    		{
    			obs.add_obstacle(wall_x, mouseX, wall_y, mouseY);
    			wall_x=0;
    			wall_y=0;
    		}
    			
    		
    		
    	}
    	
    	
    
    }
    
    
    

}