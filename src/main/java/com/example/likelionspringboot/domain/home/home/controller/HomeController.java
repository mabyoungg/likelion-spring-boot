package com.example.likelionspringboot.domain.home.home.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private int num = 0;

    @GetMapping("/")
    @ResponseBody
    String showMain() {
        return "main";
    }

    @GetMapping("/about")
    @ResponseBody
    String shwoAbout() {
        return "about";
    }

    @GetMapping("/calc")
    @ResponseBody
    String shwoCalc(int a, int b) {
        return "a + b: %d".formatted(a + b);
    }

    @GetMapping("/calc2")
    @ResponseBody
    String shwoCalc2(Integer a, Integer b) {
        return "a: " + a + ", b: " + b;
    }

    @GetMapping("/calc3")
    @ResponseBody
    String shwoCalc3(
            @RequestParam(defaultValue = "0") int a,
            @RequestParam(defaultValue = "0") int b
    ) {
        return "a + b: %d".formatted(a + b);
    }

    @GetMapping("/calc4")
    @ResponseBody
    String shwoCalc4(
            @RequestParam(defaultValue = "0") double a,
            @RequestParam(defaultValue = "0") double b
    ) {
        return "a + b: %f".formatted(a + b);
    }

    @GetMapping("/calc5")
    @ResponseBody
    String shwoCalc5(
            @RequestParam(defaultValue = "0") String a,
            @RequestParam(defaultValue = "0") String b
    ) {
        return "a + b: %s".formatted(a + b);
    }

    @GetMapping("/calc6")
    @ResponseBody
    int shwoCalc6(
            int a,
            int b
    ) {
        return a + b;
    }

    @GetMapping("/calc7")
    @ResponseBody
    boolean shwoCalc7(
            int a,
            int b
    ) {
        return a > b;
    }

    @GetMapping("/calc8")
    @ResponseBody
    Person shwoCalc8(
            String name,
            int age
    ) {
        return new Person(name, age);
    }

    @GetMapping("/calc9")
    @ResponseBody
    Map<String, Object> shwoCalc9(
            String name,
            int age
    ) {
        Map<String, Object> personMap = Map.of(
                "name", name,
                "age", age
        );

        return personMap;
    }

    @GetMapping("/calc10")
    @ResponseBody
    List<Person> shwoCalc10(
            String name,
            int age
    ) {
        List<Person> perosnList = new ArrayList<>(){{
            add(new Person(name, age));
            add(new Person(name + "2", age + 1));
            add(new Person(name + "3", age + 2));
        }};

        return perosnList;
    }

    @GetMapping("/calc11")
    String showCalc11() {
        return "calc11";
    }

    @GetMapping("/calc12")
    String showCalc12(Model model) {
        model.addAttribute("value1","값1");
        model.addAttribute("value2","값2");

        return "calc12";
    }

    @GetMapping("/calc13")
    @ResponseBody
    int showCalc13() {
        num++;

        return num;
    }

}

@Getter
@AllArgsConstructor
class Person {
    private String name;
    private int age;
}