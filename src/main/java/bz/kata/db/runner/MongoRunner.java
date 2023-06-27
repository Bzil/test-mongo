package bz.kata.db.runner;


import bz.kata.db.migration.OfferCreator;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MongoRunner implements CommandLineRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        OfferCreator creator = applicationContext.getBean(OfferCreator.class);
        String action = args[0];
        if (action == null) {
            throw new RuntimeException("Provide any action");
        }


        switch (action) {
            case "init" -> creator.init();
            case "clean" -> creator.cleanup();
            case "recreate" -> {
                creator.cleanup();
                creator.init();
            }
            default -> throw new RuntimeException("Unknown action");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}