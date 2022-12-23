package fr.ecole42.swingy.docker;

import fr.ecole42.swingy.model.character.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class DockerPostgres {
	private ProcessBuilder processBuilder;
	private static final String dockerComposePath = System.getProperty("user.dir") + "/src/postgres/docker-compose.yml";
	private static final String launchCommand = "docker-compose -f " + dockerComposePath + " up -d";
	private static final String containerName = "postgres";
	private static final String stopCommand = "docker stop " + containerName;

	public DockerPostgres() {
		processBuilder = new ProcessBuilder();
		processBuilder.command("bash", "-c", launchCommand);

		try {
			Process process = processBuilder.start();
			int exitVal = process.waitFor();
			if (exitVal != 0) {
				System.out.println("Can't launch database container");
				System.exit(1);
			}
		} catch (IOException | InterruptedException e) {
			System.out.println("Can't launch database container");
			System.exit(1);
		}
	}

	public void waitContainer() throws InterruptedException {
		while (true) {
			Thread.sleep(5000);
			final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
					.configure() // configures settings from hibernate.cfg.xml
					.build();
			try {
				SessionFactory factory = new MetadataSources(registry)
						.buildMetadata().buildSessionFactory();
				Session session = factory.openSession();
				Transaction transaction = session.beginTransaction();

				transaction.commit();

				session.close();
				factory.close();
				break;

			} catch (Exception ignored) {
			}
		}

		System.out.println("Container ok, connection ok");
	}

	public void closeContainer() {
		processBuilder.command("bash", "-c", stopCommand);

		try {
			Process process = processBuilder.start();
			int exitVal = process.waitFor();
			if (exitVal != 0) {
				System.out.println("Can't stop " + containerName + " container");
				System.exit(1);
			}
		} catch (IOException | InterruptedException e) {
			System.out.println("Can't stop " + containerName + "database container");
			System.exit(1);
		}
	}
}
