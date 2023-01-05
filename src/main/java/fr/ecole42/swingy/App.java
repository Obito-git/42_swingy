package fr.ecole42.swingy;

import fr.ecole42.swingy.config.SpringConfig;
import fr.ecole42.swingy.view.MainVisualizer;
import fr.ecole42.swingy.view.ViewMode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    private static boolean isConsoleMode(String[] args) throws IllegalArgumentException {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("console"))
                return true;
            if (args[0].equalsIgnoreCase("gui"))
                return false;
        }
        throw new IllegalArgumentException("Arguments can be \"console\" or \"gui\"");
    }

    public static void main(String[] args) {
        boolean isConsole = false;
        try {
            isConsole = isConsoleMode(args);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        MainVisualizer mainVisualizer = context.getBean(MainVisualizer.class);
        mainVisualizer.setViewMode(isConsole ? ViewMode.CONSOLE : ViewMode.GUI);
        mainVisualizer.start();


        /*
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Hero c = HeroDirector.buildHunter("Zaloopich");



        //Validate bean
        Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(c);

        //Show errors
        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<Hero> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
        } else {
            System.out.println("Valid Object");
        }

        gameController.save(c);
        */
    }
}
