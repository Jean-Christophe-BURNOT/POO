package model.world;

import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import java.lang.Math;

import model.agents.Agent;
import model.agents.Etat;
//partie 2
//import model.agents.PointPositif;
import model.agents.Sexe;
import model.agents.Animal;
import model.agents.animaux.AbeilleDomestique;
import model.agents.animaux.AbeilleSolitaire;
import model.agents.animaux.FrelonAsiatique;
import model.agents.animaux.FrelonEuropeen;
import model.agents.animaux.Varroa;
import model.agents.vegetaux.Arbre;
import model.agents.vegetaux.Fleur;
import model.decor.Ruche;

public class Monde extends Time{
	//========================= Attributes =========================

	/**
	 * population d'agents dans le monde
	 */
	private Set<Agent>agents;
	/**
	 * map de probabilité pour trouver un agent
	 */
	private static final Map<Integer,Agent> proba= Monde.probaAgent();
	/**
	 * constante: largeur du monde
	 */
	private static int LARGEUR = 30;
	/**
	 * constante: longueur du monde
	 */
	private static int LONGUEUR = 20;
	
	private static int rayon = 10;
	
	protected static boolean isNight = false;
	
	/**
	 * 
	 * @param nbAgents
	 */
	
	//======================== Constructors ========================

	public Monde(int nbAgents) {
		agents=generateAgents(nbAgents);
	}
	
	/**
	 * Méthode utilitaire statistique pour produire la table de proba
	 * d'apparition d'un agent
	 * @return
	 */
	
	//====================== Getters / Setters =====================

	//========================== Overrides =========================

	@Override
	public String toString() {
		String ret="";
		ret+="******************************\n";
		ret+="Le monde contient "+agents.size()+" agents:\n";
		
		Set<Agent> coordSet = new TreeSet<Agent>(new CoordComparator());
		coordSet.addAll(agents);
		
		for(Agent a: coordSet) {
			ret+="\t"+a+"\n";
		}
		return ret;
	}
	
	//======================== Other methods =======================

	private static Map<Integer, Agent> probaAgent() {
		Ruche r1 =new Ruche(new Point(10,50));
		Ruche r2 =new Ruche(new Point(100,20));
		Map<Integer,Agent> myMap = new LinkedHashMap<Integer,Agent>();
	    myMap.put(20,new AbeilleDomestique(Sexe.Assexue,new Point(10,20),r1));
	    myMap.put(40,new AbeilleDomestique(Sexe.Assexue,new Point(5,10),r2));
	    myMap.put(50,new AbeilleSolitaire(Sexe.Assexue,new Point(10,10)));
	    myMap.put(55,new FrelonEuropeen(Sexe.Assexue,new Point(0,0)));
	    myMap.put(60,new FrelonAsiatique(Sexe.Assexue,new Point(0,0)));
	    myMap.put(70,new Varroa(Sexe.Assexue,new Point(0,0)));
	    myMap.put(80,new Arbre(new Point(0,0),1.0));
	    myMap.put(85,new Arbre(new Point(0,0),2.0));
	    myMap.put(100,new Fleur(new Point(0,0)));
	    
	    System.out.println("-".repeat(30));
	    System.out.println(r1);
	    System.out.println(r2);
	    
	    return myMap;
	}
	
	/**
	 * fabrication aléatoire d'un Agent par tirage dans la Map
	 * et positionnement aléatoire
	 * @param alea
	 * @return
	 */
	private static Agent tirage(int alea) {
		/*
		 * NE PAS TOUCHER!
		 */
		Agent agent=null;
		if(alea<100 && alea>=0) {
			boolean trouve = false;
			Iterator<Integer> it = proba.keySet().iterator();
			while(!trouve && it.hasNext()) {
				Integer clef = it.next();
				if(clef>=alea) {
					agent = proba.get(clef);
					trouve=true;
				}
			}
		}
		else {
			agent = new Fleur(new Point(0,0));
		}
		//positionnement aléatoire entre Longueur et Largeur
		int aleaX = (int)(Math.random()*LONGUEUR);
		int aleaY = (int)(Math.random()*LONGUEUR);
		agent.setCoord(aleaX, aleaY);
		return agent;
	}

	private TreeSet<Agent> generateAgents(int nbAgents) {
			/*
			 * NE PAS TOUCHER!
			 */
		TreeSet<Agent> ts = new TreeSet<Agent>();
		for(int i=0;i<nbAgents;i++) {
			int alea = (int)(Math.random()*100);
			//partie 2
			//ts.add((Agent)tirage(alea).clone());
			//partie 1
			ts.add(this.copieAgent(tirage(alea)));
		}
		return ts;
	}
	/**
	 * à supprimer dès la partie deux lorsqu'il devient possible de
	 * cloner les agents
	 * place toutes les abeilles dans la même ruche
	 * les arbres ont tous la même taille
	 * ne respecte pas les bonnes pratiques (switch case sur le type) 
	 * @param tirage
	 * @return
	 */
	private Agent copieAgent(Agent tirage) {
		// TODO Auto-generated method stub
		Ruche ruche =new Ruche(new Point(10,50));
		Agent ret = null;
		String classeAgentName = tirage.getClass().getSimpleName();
		switch(classeAgentName) {
			case "AbeilleDomestique":
				AbeilleDomestique ad = (AbeilleDomestique) tirage;
				ret = new AbeilleDomestique(ad.getSexe(),ad.getCoord(),ruche);
				break;
			case "AbeilleSolitaire":
				AbeilleSolitaire as = (AbeilleSolitaire) tirage;
				ret = new AbeilleSolitaire(as.getSexe(),as.getCoord());
				break;
			case "FrelonEuropeen":
				FrelonEuropeen fe = (FrelonEuropeen) tirage;
				ret = new FrelonEuropeen(fe.getSexe(),fe.getCoord());
				break;
			case "FrelonAsiatique":
				FrelonAsiatique fa = (FrelonAsiatique) tirage;
				ret = new FrelonAsiatique(fa.getSexe(),fa.getCoord());
				break;
			case "Varroa":
				Varroa v = (Varroa) tirage;
				ret = new Varroa(v.getSexe(),v.getCoord());
				break;
			case "Arbre":
				Arbre a = (Arbre) tirage;
				ret = new Arbre(a.getCoord(),1.0);
				break;
			default:
				Fleur f = (Fleur) tirage;
				ret = new Fleur(f.getCoord());
				break;
		}
		return ret;
	}

	/**
	 * génère un cycle de vie dans le monde
	 */
	public void cycle() {
		System.out.println("----------------------Cycle---------------------");
		for (Agent a : agents) {
			gererRencontres();
			a.cycle();
			a.maj();
			
			if (a instanceof Animal) {
				if (((Animal) a).getNiveauSante() == Etat.Mourant) {
					agents.remove(a);
				}
			}
		}
	}
	
	
	public void gererRencontres() {
		
		HashSet<Agent> neighboors = new HashSet<Agent>();
		for (Agent a : agents) {
			for (Agent e : agents) {
				
				if ( Math.abs(
						a.getCoord().getX() 
						- e.getCoord().getX()) 
					<= rayon
					|| Math.abs(
						a.getCoord().getY() 
						- e.getCoord().getY()) 
					<= rayon) {
						neighboors.add(e);
						
					if (a instanceof Animal) {
						//System.out.println(a.getClass().getSimpleName() + " rencontre " + e.getClass().getSimpleName());
						((Animal)a).rencontrer(e);
					}
				}
			}
		}
	}
}
