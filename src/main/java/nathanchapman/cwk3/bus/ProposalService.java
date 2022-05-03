/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nathanchapman.cwk3.bus;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import nathanchapman.cwk3.ent.Person;
import nathanchapman.cwk3.ent.Proposal;
import nathanchapman.cwk3.pers.PersonFacade;
import nathanchapman.cwk3.pers.ProposalFacade;
import nathanchapman.cwk3.pers.VoteFacade;

/**
 *
 * @author natha
 */
@Stateless
public class ProposalService implements Serializable {
    
    @EJB
    private ProposalFacade propf;
    @EJB
    private PersonFacade pF;
    @EJB
    private VoteFacade vF;
    
    public Proposal createProposal(Proposal prop, Person user) {
        prop.setPerson(user);
        propf.create(prop);
        return prop;
    }
    
    public Proposal changeProposal(Proposal prop, Person user) {
        prop.setPerson(user);
        propf.edit(prop);
        return prop;
    }
    
    public Proposal deleteProposal(Proposal prop, Person user) {
        prop.setPerson(user);
        propf.remove(prop);
        return prop;
    }
    
    public Proposal getPropById(long id) {
        List<Proposal> allProposal = propf.findAll();
        for (int i=0 ; i < allProposal.size() ; i++) {
            if (allProposal.get(i).getId() == id) {            
               return allProposal.get(i);
            }     
        }
        return null;
    }
    
    public List<Proposal> getAllProposals() {
        return propf.findAll();
    }
}
