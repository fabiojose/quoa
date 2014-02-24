package net.sourceforge.quoa.example.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Artifact extends Lifeless {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "artifact")
	private Set<Unit> metrics;

	public Set<Unit> getMetrics() {
		return metrics;
	}
	public void setMetrics(Set<Unit> metrics) {
		this.metrics = metrics;
	}
	
}
