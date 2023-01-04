package fr.ecole42.swingy;

import fr.ecole42.swingy.config.SpringConfig;
import fr.ecole42.swingy.controller.GameController;
import fr.ecole42.swingy.model.hero.Hero;
import fr.ecole42.swingy.model.hero.HeroDirector;
import fr.ecole42.swingy.view.ViewModes;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

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

    public static void main(String[] args) {
        boolean isConsole = false;
        args = new String[2];
        args[1] = "console";
        try {
            isConsole = isConsoleMode(args);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        GameController gameController = (GameController) context.getBean(GameController.class);
        gameController.setViewMode(isConsole ? ViewModes.CONSOLE : ViewModes.GUI);

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

        //docker.closeContainer();
    }
}
