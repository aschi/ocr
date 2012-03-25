package ch.zhaw.ocr.NeuronalNetwork;

import java.util.Set;

public class Neuron <S extends Comparable<S>, T extends Comparable<T>> implements Comparable<Neuron<S, T>>{
	private S source;
	private T target;
	private double emphasis;
	
	//Hit count includes emphasis of hits
	private double hitCount;
	
	//used to recalculate emphasis
	private double emphasisFactor;
	
	
	/**
	 * Constructor for Prisi :-)
	 * @param source
	 * @param target
	 * @param emphasis
	 * @param hitCount
	 * @param emphasisFactor
	 */
	public Neuron(S source, T target, double emphasis, double hitCount,
			double emphasisFactor) {
		super();
		this.source = source;
		this.target = target;
		this.emphasis = emphasis;
		this.hitCount = hitCount;
		this.emphasisFactor = emphasisFactor;
	}

	public Neuron(S source, T target, double emphasis) {
		super();
		this.source = source;
		this.target = target;
		this.emphasis = emphasis;
	}
	
	public Neuron(S source, T target) {
		super();
		this.source = source;
		this.target = target;
	}
	
	public S getSource() {
		return source;
	}

	public void setSource(S source) {
		this.source = source;
	}

	public T getTarget() {
		return target;
	}

	public void setTarget(T target) {
		this.target = target;
	}
	

	public void addToNetwork(double emphasis){
		//recalculate hitCount + emphasisFactor
		hitCount+=emphasis;
		emphasisFactor = emphasisFactor == 0 ? emphasis : ((emphasisFactor*(hitCount-emphasis)+emphasis)/hitCount);
	}
	
	public void recalculateEmphasis(Set<Neuron<S, T>>subset){
		double hitSum = 0;
		
		//Sum up hit counts
		for(Neuron<S, T> n : subset){
			hitSum  += n.getHitCount();
		}
		
		emphasis = (hitCount / hitSum) * emphasisFactor;
	}
	
	public double getHitCount(){
		return hitCount;
	}
	
	public double getEmphasis() {
		return emphasis;
	}
	
	public double getEmphasisFactor() {
		return emphasisFactor;
	}

	public void setEmphasis(double emphasis) {
		this.emphasis = emphasis;
	}
	
	public boolean equals(Object o){
		if(o instanceof Neuron){
			//Compare word + language 
			return getSource().equals(((Neuron<S, T>) o).getSource()) && getTarget().equals(((Neuron<S, T>) o).getTarget());
		}else{
			return false;
		}
	}
	
	public int hashCode(){
		int hash = 7;
        hash = hash * 17 + target.hashCode();
        hash = hash * 17 + source.hashCode();
        return hash;
	}
	
	public String toString(){
		return source + "->" + target;
	}

	@Override
	public int compareTo(Neuron<S, T> o) {
		//sort by 1. target 2. source
		int sort = this.getTarget().compareTo(o.getTarget());
		if(sort == 0){
			return this.getSource().compareTo(o.getSource());
		}else{
			return sort;
		}
	}
	
}
