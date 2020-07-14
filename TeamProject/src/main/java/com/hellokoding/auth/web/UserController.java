package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Appointments;
import com.hellokoding.auth.model.Students;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.AppointmentRepo;
import com.hellokoding.auth.service.AppointmentDao;
import com.hellokoding.auth.service.AppointmentDto;
import com.hellokoding.auth.service.UserDetailsServiceImpl;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.service.StudentDao;
import com.hellokoding.auth.service.UserService;
import com.hellokoding.auth.validator.UserValidator;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserDetailsServiceImpl userdetails;

    @Autowired
    private StudentDao studentdao;

    @Autowired
    private AppointmentDao appointmentdao;
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private StudentDao stdao;
    
    
    @GetMapping("/home")
    public String homepage(){
        return "home";
    }    
    
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }
    
      @GetMapping("/adminpage")
    public String goToAdminPage(Model m){
    List<User> u = userService.fetchAllUsers();
    m.addAttribute("u", u);
    return "/adminpage";
    }

    
    

    @GetMapping("/chat")
    public String givechat() {
        return "chat";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        
        return "redirect:/welcome";
    }

    @GetMapping("/loginpage")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "login";
    }
    
    
    
    
    

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
    
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }


    //----------------------------- Student's insert --------------------------------------
    //goes to insertstudentform
    @GetMapping(value = "/preInsertStudent")
    public String preInsertStudent(Model model) {
        model.addAttribute("studentFromForm", new Students());
        return "insertStudentForm";
    }

    @PostMapping("/insertStudent")
    public String submitForm(@ModelAttribute("newstudentname") Students newstudent) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User u = userService.findByUsername(currentPrincipalName);
        newstudent.setId(u);
        studentdao.insertStudent(newstudent);

        return "redirect:/viewstudents";
    }
    
            @GetMapping(value ="/preaddappointment")
    public String submitAppointmentsForm(Model model) {
            //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) 
            //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime)
        model.addAttribute("appointmentForm", new AppointmentDto());              
        return "addappointment";
    }
    
        @PostMapping("/addappointment")
    public String submitForm(@ModelAttribute("appointmentForm") AppointmentDto newappoint) {
          
        Appointments ap = new Appointments();
        ap.setTitle(newappoint.getTitle());
        ap.setAmount(newappoint.getAmount());
        
         SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateeventually =inputDateFormat.parse(newappoint.getDay());
            ap.setDay(dateeventually);
        } catch (ParseException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SimpleDateFormat startTime = new SimpleDateFormat("HH:mm");
        try {
            Date startTimeTventually =startTime.parse(newappoint.getStartTime());
            System.out.println(startTimeTventually);
            ap.setStartTime(startTimeTventually);
        } catch (ParseException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        SimpleDateFormat endTime = new SimpleDateFormat("HH:mm");
        try {
            Date endTimeTventually =endTime.parse(newappoint.getEndTime());
            ap.setEndTime(endTimeTventually);
        } catch (ParseException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        appointmentRepo.save(ap);
        return "redirect:/viewAppointments";
    }
    


    //----------------------------- View students --------------------------------------
    @RequestMapping("/viewstudents")
    public String viewstudents(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User u = userService.findByUsername(currentPrincipalName);
        long id = u.getId();
        List<Students> list = studentdao.fetchAllStudentsByTeachersId(id);
        model.addAttribute("list", list);
        return "viewstudents";
    }

    @RequestMapping(value = "/preUpdate/{studentId}", method = RequestMethod.GET)
    public String fetchStudentById(@PathVariable("studentId") Integer id, ModelMap mm) {
        Students result = studentdao.get(id);

        mm.addAttribute("student", result);
        return "updateStudentForm";     
    }

    //--------- Update student ---------- 
    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute("student") Students newstudent) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User u = userService.findByUsername(currentPrincipalName);
        newstudent.setId(u);
        studentdao.insertStudent(newstudent);

        return "redirect:/viewstudents";
    }


    
        @RequestMapping(value = "/viewmap/{studentId}", method = RequestMethod.GET)
    public String showStudentLocation(@PathVariable("studentId")int id, ModelMap model){
           
        Students st = stdao.get(id);
        model.addAttribute("st", st);
        return "/viewmap1";
    }
    

    @GetMapping(value = "/allgood")
    public String justGo() {

        return "allgood";
    }


        //----------------------------- View appointments --------------------------------------
    @RequestMapping("/viewAppointments")
    public String viewAppointments(Model model) {
        List<Appointments> list = appointmentdao.getAllAppointments();
        model.addAttribute("list", list);
        return "viewAppointments";
    }
    
    
    //-------------------------Delete student------------------------------------------------------------
    @RequestMapping(value = "/delete/{studentId}", method = RequestMethod.GET)
    public String doDelete(@PathVariable("studentId") int id, ModelMap mn) {

        studentdao.deleteStudentById(id);
        ArrayList<Students> result = (ArrayList<Students>) studentdao.getAllStudents();

        mn.addAttribute("list", result);

        return "redirect:/viewstudents";
    }
    //-------------------------Delete appointment------------------------------------------------------------
    @RequestMapping(value = "/deleteappo/{appointmentId}", method = RequestMethod.GET)
    public String doDeleteAppointment(@PathVariable("appointmentId") int id, ModelMap mn) {

        
        appointmentdao.deleteAppointmentById(id);
        ArrayList<Appointments> result = (ArrayList<Appointments>) appointmentdao.getAllAppointments();

        mn.addAttribute("list", result);

        return "redirect:/viewAppointments";
    }


    public void getDatesOfWeek() {
        Locale locale = null;
        final DayOfWeek firstDayOfWeek = WeekFields.of(locale).getFirstDayOfWeek();
        final DayOfWeek lastDayOfWeek = DayOfWeek.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);
        System.out.println(firstDayOfWeek);
        System.out.println(lastDayOfWeek);

    }
    
//    ==================================================
//----------delete appointment   
//    @RequestMapping(value = "/delete/{appointmentId}", method = RequestMethod.GET)
//    public String doDeleteAppoint(@PathVariable("appointmentId") Integer id, ModelMap mn) {
//
//        appointmentdao.deleteAppointment(id);
//        ArrayList<Appointments> result = (ArrayList<Appointments>) appointmentdao.findAppointment();
//
//        mn.addAttribute("list", result);
//
//        return "/viewappointments";
//    }




}
