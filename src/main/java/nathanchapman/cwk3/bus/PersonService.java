package nathanchapman.cwk3.bus;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import nathanchapman.cwk3.ent.Person;
import nathanchapman.cwk3.ent.Proposal;
import nathanchapman.cwk3.pers.PersonFacade;
import nathanchapman.cwk3.pers.ProposalFacade;
import nathanchapman.cwk3.pers.VoteFacade;

@Stateless
public class PersonService {

    @EJB
    private PersonFacade pf;
    @EJB
    private ProposalFacade propf;
    @EJB
    private VoteFacade vf;

    public Person createNewPerson(Person p) {
        pf.create(p);
        return p;
    }

    public List<Proposal> getProposalByUser(long id) { // Not currently used
        return propf.getProposalsByPerson(id);
    }

    public Person logIn(String email, String password) throws BusinessException {
        Person p = null;
        if (email == null || password == null) { // Checks username and password were given
            throw new BusinessException("Incorrect username or password");
        }
        try { // Gets person with that username
            p = pf.getPersonByEmail(email);
        } catch (Exception e) { // If person doesnt exist throw and exception
            throw new BusinessException("Incorrect username or password");
        }
        if (p.getPassword().equals(password)) { // Checks password of user with that email against req password
            return p;
        }
        throw new BusinessException("Incorrect username or password");
    }
}
