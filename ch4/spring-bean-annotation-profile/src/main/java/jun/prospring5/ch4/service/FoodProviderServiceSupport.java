package jun.prospring5.ch4.service;

import jun.prospring5.ch4.domain.Food;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class FoodProviderServiceSupport
        implements FoodProviderService, ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public List<Food> provideLunchSet() {

        List<String> lunchMenu = this.getLunchMenu();
        Objects.requireNonNull(lunchMenu);

        List<Food> lunchSet = new ArrayList<>();
        lunchMenu.stream().forEach(
                name -> lunchSet.add(this.newFood(name)));

        return lunchSet;
    }

    @Override
    public void setApplicationContext(
            ApplicationContext applicationContext)
            throws BeansException {
        this.context = applicationContext;
    }

    protected abstract List<String> getLunchMenu();

    protected ApplicationContext getContext() {
        return this.context;
    }

    protected Food newFood(String name) {
        Food food = this.getContext().getBean(Food.class);
        food.setName(name);
        return food;
    }
}
