package сom.bottle.authorisation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import сom.bottle.authorisation.entity.User;
import сom.bottle.authorisation.repository.AuthorisationRepository;
import сom.bottle.authorisation.request.LoginRequest;
import сom.bottle.authorisation.request.RegistrationRequest;
import сom.bottle.authorisation.response.LoginResponse;
import сom.bottle.authorisation.response.RegistrationResponse;



@Controller
public class AuthorisationController {
    private AuthorisationRepository authorisationRepository;

@Autowired
    public AuthorisationController(AuthorisationRepository authorisationRepository) {
        this.authorisationRepository = authorisationRepository;
    }

    @PostMapping(path = "/login")
    @ResponseBody
    public LoginResponse login(LoginRequest request) {
    LoginResponse response = new LoginResponse();
        User userByLogin = authorisationRepository.findByLogin(request.getLogin());
        if(userByLogin != null){
            if(userByLogin.getPassword() == request.getPassword()){
                response.setUuid(userByLogin.getId());
            }
        }
        return response;
    }

    @PostMapping(path = "/registration")
    @ResponseBody
    public RegistrationResponse registration(RegistrationRequest request) {
        RegistrationResponse response = new RegistrationResponse();
        User userByEmail = authorisationRepository.findByEmail(request.getEmail());
        User userByLogin = authorisationRepository.findByLogin(request.getLogin());
        if(userByEmail == null && userByLogin == null){
            User newUser = new User(request.getLogin(), request.getPassword(), request.getEmail());
            authorisationRepository.addUser(newUser);
            response.setSuccessful(true);
        } else {
            response.setSuccessful(false);
        }
        return response;
    }
}
