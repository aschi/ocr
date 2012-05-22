package ch.zhaw.ocr.NeuronalNetwork;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class NeuronalNetwork<S extends Comparable<S>, T extends Comparable<T>> {
	private Set<Neuron<S, T>> neuronSet;

	public NeuronalNetwork() {
		neuronSet = new TreeSet<Neuron<S, T>>();
	}

	public NeuronalNetwork(Set<Neuron<S, T>> neuronSet) {
		this.neuronSet = neuronSet;
	}

	/**
	 * Add neuron
	 * 
	 * @param source
	 * @param target
	 */
	public void addNeuron(S source, T target) {
		addNeuron(source, target, 1);
	}

	/**
	 * Add neuron
	 * 
	 * @param source
	 * @param target
	 * @param emphasis
	 */
	public void addNeuron(S source, T target, double emphasis) {
		// Calculate default emphasis
		// int numberOfSourceConnections = getTargetList(source).size();
		// emphasis = (numberOfSourceConnections == 0 ? 1
		// : 1 / numberOfSourceConnections) * emphasis;

		// Generate new neuron
		Neuron<S, T> n = new Neuron<S, T>(source, target, emphasis);

		// add neuron to the set if its a new one
		
		if(searchNeuron(n) != null){
			searchNeuron(n).addToNetwork(emphasis);
		}else{
			neuronSet.add(n);
			n.addToNetwork(emphasis);
		}
		
		// Recalculate emphasis on all related neurons
		Set<Neuron<S, T>> subset = getNeuronsFromSource(source);
		for (Neuron<S, T> o : subset) {
			o.recalculateEmphasis(subset);
		}

	}

	/**
	 * Get a set of sources for a given target
	 * 
	 * @param target
	 * @return
	 */
	public Set<S> getSourceList(T target) {
		Set<S> rv = new TreeSet<S>();

		for (Neuron<S, T> n : neuronSet) {
			if (n.getTarget().equals(target)) {
				rv.add(n.getSource());
			}
		}
		return rv;
	}

	/**
	 * Get a set of targets related to a given source
	 * 
	 * @param source
	 * @return
	 */
	public Set<T> getTargetList(S source) {
		Set<T> rv = new TreeSet<T>();

		for (Neuron<S, T> n : neuronSet) {
			if (n.getSource().equals(source)) {
				rv.add(n.getTarget());
			}
		}
		return rv;
	}

	/**
	 * Get a set of all targets
	 * 
	 * @return
	 */
	public Set<T> getTargetList() {
		Set<T> rv = new TreeSet<T>();

		for (Neuron<S, T> n : neuronSet) {
			rv.add(n.getTarget());
		}
		return rv;
	}

	/**
	 * Get a set of all sources
	 * 
	 * @return
	 */
	public Set<S> getSourceList() {
		Set<S> rv = new TreeSet<S>();

		for (Neuron<S, T> n : neuronSet) {
			rv.add(n.getSource());
		}
		return rv;
	}

	/**
	 * Get a set of neurons related to a given source
	 * 
	 * @param source
	 * @return
	 */
	public Set<Neuron<S, T>> getNeuronsFromSource(S source) {
		Set<Neuron<S, T>> rv = new TreeSet<Neuron<S, T>>();
		for (Neuron<S, T> n : neuronSet) {
			if (n.getSource().equals(source)) {
				rv.add(n);
			}
		}
		return rv;
	}

	/**
	 * Get a set of neurons related to a given target
	 * 
	 * @param target
	 * @return
	 */
	public Set<Neuron<S, T>> getNeuronsFromTarget(T target) {
		Set<Neuron<S, T>> rv = new TreeSet<Neuron<S, T>>();
		for (Neuron<S, T> n : neuronSet) {
			if (n.getTarget().equals(target)) {
				rv.add(n);
			}
		}
		return rv;
	}

	/**
	 * Geter for neuronSet
	 * 
	 * @return
	 */
	public Set<Neuron<S, T>> getNeuronSet() {
		return neuronSet;
	}

	/**
	 * Get reference to a neuron in our network.
	 * 
	 * @param comp
	 *            neuron with same source & target
	 * @return
	 */
	public Neuron<S, T> searchNeuron(Neuron<S, T> comp) {
		for (Neuron<S, T> n : neuronSet) {
			if (n.equals(comp)) {
				return n;
			}
		}
		return null;
	}

	public void printNetwork() {
		System.out.println("----------------------------------");
		for (Neuron<S, T> n : neuronSet) {
			System.out.println(n + ": " + n.getEmphasis());
		}
		System.out.println("----------------------------------");
	}

	/**
	 * Remove irrelevan (emphasis < threshold)
	 * 
	 * @param threshold
	 */
	public void removeIrrelevantNeurons(double threshold) {
		ArrayList<Neuron<S, T>> removeList = new ArrayList<Neuron<S, T>>();
		for (Neuron<S, T> n : neuronSet) {
			if (n.getEmphasis() < threshold) {
				removeList.add(n);

			}
		}

		for (Neuron<S, T> n : removeList) {
			neuronSet.remove(n);
		}
		if (removeList.size() > 0) {
			System.out.println(removeList.size()
					+ " neutrons removed (emphasis < " + threshold + ")");
		}
	}

}
