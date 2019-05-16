package jun.prospring5.ch4.service.highschool;

import jun.prospring5.ch4.service.FoodProviderServiceSupport;

import java.util.Arrays;
import java.util.List;

public class FoodProviderServiceImpl extends FoodProviderServiceSupport {

    private final static List<String> LUNCH_MENU;

    static {
        LUNCH_MENU = Arrays.asList(new String[]{"Coke", "Hamburger", "French Fries"});
    }

    @Override
    protected List<String> getLunchMenu() {
        return LUNCH_MENU;
    }
}
