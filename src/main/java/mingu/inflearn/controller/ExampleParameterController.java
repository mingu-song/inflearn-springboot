package mingu.inflearn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/example/parameter")
public class ExampleParameterController {

    @GetMapping("/example1")
    public void example1(@RequestParam String id, @RequestParam String code, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("code", code);
    }

    @GetMapping("/example2")
    public void example2(@RequestParam Map<String, Object> paramMap, Model model) {
        model.addAttribute("paramMap", paramMap);
    }

    @GetMapping("/example3")
    public void example3(ExampleParameter parameter, Model model) {
        model.addAttribute("parameter", parameter);
    }

    @GetMapping("/example4/{id}")
    public String example4(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "/example/parameter/example4";
    }

    @GetMapping("/example5")
    public void example5(@RequestParam String[] ids, Model model) {
        model.addAttribute("ids", ids);
    }

    // json
    @GetMapping("/example6/form")
    public void form() {}

    @PostMapping("/example6/saveData")
    @ResponseBody
//    public Map<String, Object> example6(@RequestBody Map<String, Object> requestBody) {
    public Map<String, Object> example6(@RequestBody ExampleRequestBodyUser requestBody) {
        log.info("requestBody : {}", requestBody);
        Map<String, Object> result = new HashMap<>();
        result.put("result", true);
        return result;
    }
}
