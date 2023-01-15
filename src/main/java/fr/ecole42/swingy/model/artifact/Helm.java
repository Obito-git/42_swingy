package fr.ecole42.swingy.model.artifact;

import jakarta.persistence.*;

@Entity
@Table(name = "Helm")
public class Helm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private int stat;

	public Helm(int stat) {
		this.stat = stat;
	}

	public Helm() {
	}

	public int getStat() {
		return stat;
	}

	@Override
	public String toString() {
		return "Helm: +" + stat + " hp";
	}
}
