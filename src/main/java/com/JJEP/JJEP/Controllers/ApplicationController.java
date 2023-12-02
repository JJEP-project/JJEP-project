package com.JJEP.JJEP.Controllers;

import com.JJEP.JJEP.application.*;
import com.JJEP.JJEP.application.client.ClientRequestDTO;
import com.JJEP.JJEP.application.client.ClientResponseDTO;
import com.JJEP.JJEP.application.client.ClientService;
import com.JJEP.JJEP.application.client.IClientService;
import com.JJEP.JJEP.application.client.child.Child;
import com.JJEP.JJEP.application.client.child.ChildRequestDTO;
import com.JJEP.JJEP.application.client.child.ChildResponseDTO;
import com.JJEP.JJEP.application.client.child.ChildService;
import com.JJEP.JJEP.user.UserResponseDTO;
import com.JJEP.JJEP.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@Controller
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;

    @Autowired
    ClientService clientService;

    @Autowired
    ChildService childService;

    @Autowired
    UserService userService;

    @GetMapping("/application")
    public String application(Model model, @RequestParam(name = "doubledClients", defaultValue = "false") String doubledClients,
                              @RequestParam(name = "client1children", defaultValue = "0") Integer client1children,
                              @RequestParam(name = "client2children", defaultValue = "0") Integer client2children) {

        ApplicationRequestDTO formApplication = new ApplicationRequestDTO();
        List<ClientRequestDTO> clients = new ArrayList<ClientRequestDTO>();
        clients.add(new ClientRequestDTO());

        if (client1children != 0) {
            List<ChildRequestDTO> children = new ArrayList<>();
            for (int i = 0; i < client1children; i++) {
                children.add(new ChildRequestDTO());
            }
            clients.get(0).setChildren(children);
        }


        if (doubledClients.equals("true")) {
            clients.add(new ClientRequestDTO());
            if (client2children != 0) {
                List<ChildRequestDTO> children = new ArrayList<>();
                for (int i = 0; i < client2children; i++) {
                    children.add(new ChildRequestDTO());
                }
                clients.get(1).setChildren(children);
            }
        }

        formApplication.setClients(clients);

        model.addAttribute("client1children", client1children);
        model.addAttribute("client2children", client2children);
        model.addAttribute("doubledClients", doubledClients);
        model.addAttribute("formApplication", formApplication);
        return "application";
    }

    @PostMapping("/application-handler")
    public String applicationHandler(@ModelAttribute("formApplication") ApplicationRequestDTO formApplication, Model model, RedirectAttributes redirectAttributes) {

        UserResponseDTO currentUser = userService.getAuthenticatedUser();

        try {
            formApplication.setUserId(currentUser.getId());
            applicationService.saveApplication(formApplication);
            redirectAttributes.addFlashAttribute("successMessage", "Application submitted successfully!");
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/application?error";
        }
    }

    @GetMapping("/applications")
    public String applications(Model model) {
        model.addAttribute("formApplication", new ApplicationBaseDTO());

        UserResponseDTO currentUser = userService.getAuthenticatedUser();
        ApplicationResponseDTO application = applicationService.findApplicationByUserId(currentUser.getId());

        model.addAttribute("app", application);


        return "applications";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }

    @GetMapping("/applications/{id}")
    public String applicationDetails(@PathVariable long id, Model model) {

        UserResponseDTO authUser = userService.getAuthenticatedUser();
        model.addAttribute("authUser", authUser);


        try {
            ApplicationResponseDTO form = applicationService.findApplicationByUserId(authUser.getId());
            model.addAttribute("form", form);
        } catch (ApplicationNotFoundException e) {

        }

        return "application-details";

    }

    @GetMapping("/applications/{id}/edit")
    public String editUserDetails(@PathVariable long id, Model model) {

        UserResponseDTO authUser = userService.getAuthenticatedUser();
        model.addAttribute("authUser", authUser);


        try {
            ApplicationResponseDTO form = applicationService.findApplicationByUserId(authUser.getId());
            model.addAttribute("form", form);
        } catch (ApplicationNotFoundException e) {

        }

        return "application-edit";

    }

    @PostMapping("/applications/{id}/update")
    public String updateApplication(@PathVariable long id, ApplicationResponseDTO form, ClientResponseDTO clientForm, ChildResponseDTO childForm) {


        try {
            applicationService.updateApplication(id, form);
            clientService.updateClient(clientForm.getId(), clientForm);
            childService.updateChild(childForm.getId(), childForm);
            return "redirect:/applications/{id}";
        } catch (Exception e) {
            return "redirect:/admin/users/{id}/edit?error";
        }


    }

}