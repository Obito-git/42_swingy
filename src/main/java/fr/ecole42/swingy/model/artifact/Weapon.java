package fr.ecole42.swingy.model.artifact;

import jakarta.persistence.*;

@Entity
@Table(name = "Weapons")
public class Weapon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private int stat;

	public Weapon(int stat) {
		this.stat = stat;
	}

	public Weapon() {
	}

	public int getStat() {
		return stat;
	}

	@Override
	public String toString() {
		return "Weapon: +" + stat + " attack";
	}
}
