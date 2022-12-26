package fr.ecole42.swingy.docker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.io.IOException;

public class DockerPostgres {
	private ProcessBuilder processBuilder;
	private static final String postgresFolder = System.getProperty("user.dir") + "/src/postgres/";
	private static final String dockerComposePath = postgresFolder + "docker-compose.yml";
	private static final String launchCommand = "docker-compose -f " + dockerComposePath + " up -d";
	private static final String containerName = "postgres";
	private static final String stopCommand = "docker stop " + containerName;

	public DockerPostgres() {
		createDataFolder();
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

	private void createDataFolder() {
		String directoryName = postgresFolder.concat("data");

		File directory = new File(directoryName);
		if (!directory.exists()){
			if (!directory.mkdir()) {
				System.out.println("Can't create postgres data folder");
				System.exit(4);
			}
		}
	}

	public void waitContainer() throws InterruptedException {
		while (true) {
			System.out.println("Waiting container");

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
				System.out.println(ignored.getMessage());
			}
			Thread.sleep(5000);
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
