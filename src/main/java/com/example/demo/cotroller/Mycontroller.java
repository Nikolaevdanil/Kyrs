package com.example.demo.cotroller;

import com.example.demo.models.Manufacture;
import com.example.demo.models.Worker;
import com.example.demo.service.ManufactureService;
import com.example.demo.service.UserService;
import com.example.demo.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class Mycontroller {

    private ManufactureService manufactureService;
    private WorkerService workerService;
    private UserService userService;
    @Autowired
    public Mycontroller(ManufactureService manufactureService, WorkerService workerService, UserService userService) {
        this.manufactureService = manufactureService;
        this.workerService = workerService;
        this.userService = userService;
    }



    @GetMapping()
    public String index(Model model1,Model model2,Model model3) {
        model1.addAttribute("worker", workerService.getAllWorker());
        model2.addAttribute("manufacture",manufactureService.getAllManufacture());
        model3.addAttribute("user",userService.getAllUser());
        return "home";
    }
    @PostMapping("/addManufacture")
    public String addManufacture(@RequestParam String name,
                                 @RequestParam String address){
        Manufacture manufacture = new Manufacture();
        manufacture.setName(name);
        manufacture.setAddress(address);
        manufactureService.addManufacture(manufacture);
        return "redirect:/people";
    }
    @PostMapping("/addWorker")
    public String addWorker(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String middleName,
                            String name){
        Worker worker=new Worker();
        worker.setFirstName(firstName);
        worker.setLastName(lastName);
        worker.setMiddleName(middleName);
        worker.manufacture = manufactureService.getManufactureById(manufactureService.getManufactureId(name));
        workerService.addWorker(worker);
        return "redirect:/people";
    }
    @PostMapping("/id/{id}")
    public String delete1(@PathVariable("id") Long id) {
        manufactureService.deleteManufactureById(id);

        return "redirect:/people";
    }
    @PostMapping("/{id}")
    public String delete2(@PathVariable("id") Long id) {

        workerService.deleteWorkerById(id);
        return "redirect:/people";
    }
    @PostMapping("/id1/{id}")
    public String delete3(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/people";
    }
    @PostMapping("/show")
    public @ResponseBody String getWorkerManufacture(@RequestParam Long id){
        return "name:" + workerService.getManufactureByWorker(id).getName() + "<br/> address:" + workerService.getManufactureByWorker(id).getAddress() + "<br/> id:" + workerService.getManufactureByWorker(id).getId();
    }

    @PostMapping("/getManufactureByName")
    public @ResponseBody
    String getManufacturesByName(){
        return manufactureService.printManufactures(manufactureService.filterByName());

    }
    @PostMapping("/getManufactureByManId")
    public @ResponseBody
    String getManufacturesByManId(){
        return manufactureService.printManufactures(manufactureService.filterByManId());
    }
    @PostMapping("/getManufactureByAddress")
    public @ResponseBody
    String getManufacturesByAddress(){
        return manufactureService.printManufactures(manufactureService.filterByAddress());
    }
    @PostMapping("/getWorkerByWorId")
    public @ResponseBody
    String getWorkersByWorId(){
        return workerService.printWorkers(workerService.filterByWorId());
    }
    @PostMapping("/getWorkerByFirstName")
    public @ResponseBody
    String getWorkersByFirstName(){
        return workerService.printWorkers(workerService.filterByFirstName());
    }
    @PostMapping("/getWorkerByLastName")
    public @ResponseBody
    String getWorkersByLastName(){
        return workerService.printWorkers(workerService.filterByLastName());
    }
    @PostMapping("/getWorkerByMiddleName")
    public @ResponseBody
    String getWorkersByMiddleName(){
        return workerService.printWorkers(workerService.filterByMiddleName());
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/action")
    public String action() {
        return "action";
    }
    @GetMapping("/money")
    public String money() {
        return "money";
    }
    @GetMapping("/cabinet")
    public String cabinet() {
        return "cabinet";
    }
    @GetMapping("/pay")
    public String pay() {
        return "pay";
    }

    @GetMapping("/help")
    public String help(Model model3) {
        model3.addAttribute("user",userService.getAllUser());
        return "help";
    }
    @PostMapping("/help/getUserId")
    public @ResponseBody
    String getUserId(){
        return userService.printUser(userService.filterByWorId());

    }
    @PostMapping("/help/getUserName")
    public @ResponseBody
    String getUserName(){
        return userService.printUser(userService.filterByName());
    }
    @PostMapping("/help/getUserSurname")
    public @ResponseBody
    String getUserSurname(){
        return userService.printUser(userService.filterBySurname());
    }
    @GetMapping("/turn")
    public String getTurn(){
        return "turn";
    }
    @GetMapping("/turn/del")
    public @ResponseBody
    String getTurn1(){
        return "Стиральная машинка выключена";
    }
}
