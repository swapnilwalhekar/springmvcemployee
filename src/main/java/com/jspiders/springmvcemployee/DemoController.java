package com.jspiders.springmvcemployee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DemoController {
    List<Employee> empList = new ArrayList<>();
    {
        empList.add(new Employee(1,"Swapnil",30000));
        empList.add(new Employee(2,"Tushar",35000));
        empList.add(new Employee(3,"Sachin",40000));
    }
    //fetching a employee data in landing page
    @GetMapping("/")
    public String getEmployee(Model model){
        model.addAttribute("empdata",empList);
        return "landing";
    }
    // fetch emp details in add employee form
    @GetMapping("/addnewemployee")
    public String getEmpForm(Model model){
        model.addAttribute("employee",new Employee());
        return "addnewemployee";
    }
    // add new emp
    @PostMapping("/saveemployee")
    public String insertEmployee(Employee employee){
        empList.add(employee);
        return "redirect:/";
    }
    // get the perticular emp data for update the details
    @GetMapping("/updateemployee/{id}")
    public String showUpdateForm(@PathVariable (value = "id") int id,Model model){
        Employee e = empList.get(id-1);
        model.addAttribute("employee",e);
        return "empupdateform";
    }
    // update the emp details when clicking on update button
    @GetMapping("/modifyemployee")
    public String changeEmp(Employee e){
        empList.set(e.getEmpId()-1,e);
        return "redirect:/";
    }
    // delete a perticular emp
    @GetMapping("/deleteemployee/{id}")
    public String deleteEmp(@PathVariable(value = "id")int id,Model model){
        Employee e = empList.get(id-1);
        empList.remove(e);
        return "redirect:/";
    }
}
