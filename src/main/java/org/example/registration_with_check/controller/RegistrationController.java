package org.example.registration_with_check.controller;

import org.example.registration_with_check.model.User;
import org.example.registration_with_check.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * Получение формы для заполнения пользователем
     *
     * @param model
     * @return view с формой
     */
    @GetMapping("/registration")
    public String registrationGet(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    /**
     * Обработка данных присланных вместе с формой пользователем
     *
     * @param user
     * @param model
     * @return view с ответом об ошибке или успехе отправки
     * @throws Exception
     */
    @PostMapping("/registration")
    public String registrationSubmit(@ModelAttribute User user, Model model) throws NoSuchAlgorithmException {
        model.addAttribute("user", user);
        registrationService.registration(user);
        return "result";
    }

    /**
     * Метод для получения списка всех пользователей отправивших форму
     *
     * @param model
     * @return view со списком всех пользователей
     */
    @GetMapping("/get-all-users")
    public String registrationSubmit(Model model) {
        List<String> messages = registrationService.getAllUsers();
        model.addAttribute("messages", messages);
        return "all_users";
    }
}
