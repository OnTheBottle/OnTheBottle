import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import сom.bottle.authorisation.repository.AuthorisationRepository;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorisationRepositoryTest {

    @Autowired
    private AuthorisationRepository authorisationRepository;

//    @Test
//    public void findByEmailWhenUserExistsShouldReturnIt() {
//        List<User> users = authorisationRepository.findByEmail("J%");
//        assertEquals(2, users.size());
//    }
//    public void findByNameWhenUserExistsShouldReturnIt() {
//        List<User> users = authorisationRepository.findByName("J%");
//        assertEquals(2, users.size());
//    }
//    public void findBySurnameWhenUserExistsShouldReturnIt() {
//        List<User> users = authorisationRepository.findBySurname("J%");
//        assertEquals(2, users.size());
//    }
//    public void findByAgeWgenUserExistsShouldReturnIt() {
//        List<User> users = authorisationRepository.findByAge(8);
//        assertEquals(8, users.size());
//    }
//    public void findByCountryWhenUserExistsShouldRetutnIt() {
//        List<User> users = authorisationRepository.findByCountry("J%");
//        assertEquals(2, users.size());
//    }
//    public void findByCityWhenUserExistsSgouldReturnIT() {
//        List<User> users = authorisationRepository.findByCity("J%");
//        assertEquals(2, users.size());
//    }
}
