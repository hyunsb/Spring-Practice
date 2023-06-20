package com.hyunsb.springbootprac;

import com.hyunsb.springbootprac.config.Config;
import com.hyunsb.springbootprac.service.SortService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class SpringBootPracticeApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        SortService sortService = context.getBean(SortService.class);

        System.out.println("[result]: " + sortService.doSort(Arrays.asList(args)));
    }
}
