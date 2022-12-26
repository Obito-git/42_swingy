package fr.ecole42.swingy.dao;

import fr.ecole42.swingy.model.character.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PlayerDAO {
	private final SessionFactory sessionFactory;

	@Autowired
	public PlayerDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(readOnly = true)
	public List<Player> index() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Player", Player.class).getResultList();
	}

	@Transactional(readOnly = true)
	public Player id(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Player.class, id);
	}

	@Transactional(readOnly = false)
	public void save(Player player) {
		Session session = sessionFactory.getCurrentSession();
		session.save(player);
	}
}
