package fr.ecole42.swingy.model.artifact;

import jakarta.persistence.*;

@Entity
public class Weapon extends Artifact {

	public Weapon(int stat) {
		this.stat = stat;
	}

	public Weapon() {
	}

	@Override
	public String toString() {
		return "Weapon: +" + stat + " attack";
	}
}
