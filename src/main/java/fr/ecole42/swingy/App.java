package fr.ecole42.swingy;

import fr.ecole42.swingy.config.SpringConfig;
import fr.ecole42.swingy.controller.GameController;
import fr.ecole42.swingy.docker.DockerPostgres;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    private static boolean isConsoleMode(String[] args) throws IllegalArgumentException {
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("console"))
                return true;
            if (args[1].equalsIgnoreCase("gui"))
                return false;
        }
        throw new IllegalArgumentException("Arguments can be \"console\" or \"gui\"");
    }

    public static void main( String[] args ) {
        boolean isConsole;
        args = new String[2];
        args[1] = "console";
        try {
            isConsole = isConsoleMode(args);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        DockerPostgres docker = new DockerPostgres();
        try {
            docker.waitContainer();
        } catch (InterruptedException e) {
            System.out.println("Can't launch database container");
            docker.closeContainer();
            System.exit(2);
        }


        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        GameController gameController = (GameController) context.getBean(GameController.class);
        gameController.addInit();
        gameController.show_all();
        //System.out.println(gameController.id(1));


        //System.out.println(isConsoleModeTest);

        /*
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            SessionFactory factory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();

            //Player p = new Player("name3");
            //p.addCharacter(CharacterDirector.buildWarrior());
            //p.addCharacter(CharacterDirector.buildMage());
            //session.save(p);
            List<Player> players = session.createQuery("FROM Player").getResultList();
            for (Player player: players)
                System.out.println(player);
            transaction.commit();

            session.close();
            factory.close();

        } catch (Exception ignored) {
        }

         */

        /*
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Player p = new Player("1231", CharacterDirector.buildWarrior());

        //Validate bean
        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(p);

        //Show errors
        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<Player> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
        } else {
            System.out.println("Valid Object");
        }
         */

        //docker.closeContainer();
    }
}
