/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nathanchapman.cwk3.bus;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import nathanchapman.cwk3.ent.Person;
import nathanchapman.cwk3.ent.Proposal;
import nathanchapman.cwk3.ent.Vote;
import nathanchapman.cwk3.pers.PersonFacade;
import nathanchapman.cwk3.pers.ProposalFacade;
import nathanchapman.cwk3.pers.VoteFacade;

/**
 *
 * @author natha
 */
@Stateless
public class PersonService {

    @EJB
    private PersonFacade pf;
    @EJB
    private ProposalFacade propf;
    @EJB
    private VoteFacade vf;

    public Person createNewPerson(Person p) {
        p.setAdminStatus(false);
        pf.create(p);
        return p;
    }

    public List<Proposal> getProposalByUser(long id) {
        List<Proposal> allProposal = propf.findAll();
        List<Proposal> temp = new ArrayList<>();
        for (int i = 0; i < allProposal.size(); i++) {
            if (allProposal.get(i).getPerson().getId() == id) {
                temp.add(allProposal.get(i));
            }
        }
        return temp;
    }

    public List<Vote> getVotesByPersonId(long id) {
        List<Vote> allVotes = vf.findAll();
        List<Vote> temp = new ArrayList<>();
        for (int i = 0; i < allVotes.size(); i++) {
            if (allVotes.get(i).getPerson().getId() == id) {
                temp.add(allVotes.get(i));
            }
        }
        return temp;
    }

    public Person logIn(String email, String password) throws BusinessException {
        Person temp;
        List<Person> allPerson = pf.findAll();
        for (int i = 0; i < allPerson.size(); i++) {
            if (allPerson.get(i).getEmail().equals(email)) {
                temp = allPerson.get(i);
                if (temp.getPassword().equals(password)) {
                    return temp;
                } else {
                    throw new BusinessException("Username or password incorrect");
                }
            } else {
                throw new BusinessException("Username or password incorrect");
            }
        }
        return null;
    }

    public Person getUserByEmail(String email) {
        Person temp;
        List<Person> allPerson = pf.findAll();
        for (int i = 0; i < allPerson.size(); i++) {
            if (allPerson.get(i).getEmail().equals(email)) {
                return allPerson.get(i);
            }
        }
        return null;
    }
}
