package com.dz.common.dataimport3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author doggy
 *         Created on 2016-07-06.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ImportConfigure.class);
        ColdPlayer cp = (ColdPlayer) ac.getBean("cp");
        cp.main();
    }
}
