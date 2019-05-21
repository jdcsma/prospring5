package jun.prospring5.ch5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Documentarist {

    private GrammyGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        System.out.println(">>>");
        guitarist.sing(new TaylorGuitar());
        System.out.println(">>>");
        guitarist.sing(new MartinGuitar());
        System.out.println(">>>");
        guitarist.talk();
    }

    @Autowired
    @Qualifier("johnMayer")
    public void setGuitarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}
