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

    public Person logIn(String email, String password) throws BusinessException {  //Very inefficent and cases fatal eroror if user is not found
        List<Person> allPerson = pf.findAll();
        try {
            for (int i = 0; i < allPerson.size(); i++) {
                if (allPerson.get(i).getEmail().equals(email)) {
                    if (allPerson.get(i).getPassword().equals(password)) {
                        return allPerson.get(i);
                    } else {
                        //throw new BusinessException("Incorrect username or password");
                    }
                } else {
                    //throw new BusinessException("Incorrect username or password");
                }
            }
        } catch (Exception e) {
            throw new BusinessException("Incorrect username or password");
        }
        return null;
    }
}
