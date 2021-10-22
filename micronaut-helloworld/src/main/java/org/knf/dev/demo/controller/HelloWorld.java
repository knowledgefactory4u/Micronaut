package org.knf.dev.demo.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/echo")
public class HelloWorld {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public String index() {
        return "Hello World";
    }
}
