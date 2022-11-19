package at.tectrain.cicd.app.controller;

import at.tectrain.cicd.app.service.CalculatorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public int add(@RequestParam int a, @RequestParam int b) {
        return calculatorService.add(a, b);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/subtract")
    public int subtract(@RequestParam int a, @RequestParam int b) {
        return calculatorService.subtract(a, b);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/multiply")
    public int multiply(@RequestParam int a, @RequestParam int b) {
        return calculatorService.multiply(a, b);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/divide")
    public int divide(@RequestParam int a, @RequestParam int b) {
        return calculatorService.divide(a, b);
    }

}
