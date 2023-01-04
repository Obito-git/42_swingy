package fr.ecole42.swingy.dao;

import fr.ecole42.swingy.model.character.Character;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CharacterDAO {
	private final SessionFactory sessionFactory;

	@Autowired
	public CharacterDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(readOnly = true)
	public List<Character> index() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Character", Character.class).getResultList();
	}

	@Transactional(readOnly = true)
	public Character id(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Character.class, id);
	}

	@Transactional(readOnly = false)
	public void save(Character player) {
		Session session = sessionFactory.getCurrentSession();
		session.save(player);
	}
}
