package fr.ecole42.swingy.model.artifact;

import jakarta.persistence.*;

@Entity
@Table(name = "Armors")
public class Armor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private int stat;

	public Armor(int stat) {
		this.stat = stat;
	}

	public Armor() {

	}

	public int getStat() {
		return stat;
	}

	@Override
	public String toString() {
		return "Armor: +" + stat + " defence";
	}
}
