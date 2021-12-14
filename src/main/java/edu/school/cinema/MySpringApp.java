package edu.school.cinema;

import edu.school.cinema.config.JavaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MySpringApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(JavaConfig.class);
        ctx.refresh();

        // MyBean mb1 = ctx.getBean(MyBean.class);

        // MyBean mb2 = ctx.getBean(MyBean.class);

        ctx.close();
    }

}
