package com.bottle;

import com.bottle.findPerson.FindPerson;
import com.bottle.findPerson.User;
import com.bottle.findPerson.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/person_search")
public class FindPersonController {

    private final UserRepository userRepository;

    public FindPersonController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/find")
    public List<User> getListOfPersons(Model model) {

        return new FindPerson().findFromDB(userRepository, model);
    }


}
