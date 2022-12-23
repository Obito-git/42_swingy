package fr.ecole42.swingy.model.character;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Player")
public class Player {

	@Id
	@Column(name = "player_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "Name can't be empty")
	@Size(min = 2, max = 30, message = "Name should be from 2 to 30 characters")
	@Column(name = "name", length = 128, nullable = true, unique = true)
	private String name;

	@ElementCollection
	@Column (name = "characters")
	private List<Character> characters = new ArrayList<Character>();

	public Player(String name) {
		this.name = name;
	}

	public Player() {

	}

	public void addCharacter(Character character) {
		this.characters.add(character);
	}
}
