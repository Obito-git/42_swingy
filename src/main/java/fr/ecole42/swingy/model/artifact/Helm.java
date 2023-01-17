package fr.ecole42.swingy.model.artifact;

import jakarta.persistence.*;

@Entity
public class Helm extends Artifact {
	public Helm(int stat) {
		this.stat = stat;
	}

	public Helm() {
	}

	@Override
	public String toString() {
		return "Helm: +" + stat + " hp";
	}
}
