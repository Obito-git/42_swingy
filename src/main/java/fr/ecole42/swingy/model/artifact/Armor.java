package fr.ecole42.swingy.model.artifact;

import jakarta.persistence.*;

@Entity
public class Armor extends Artifact {

	public Armor(int stat) {
		this.stat = stat;
	}

	public Armor() {

	}

	@Override
	public String toString() {
		return "Armor: +" + stat + " defence";
	}
}
