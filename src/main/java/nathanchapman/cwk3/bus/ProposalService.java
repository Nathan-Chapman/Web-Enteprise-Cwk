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
public class ProposalService {
    
    @EJB
    private ProposalFacade propf;
    @EJB
    private PersonFacade pF;
    @EJB
    private VoteFacade vF;
    
    public Proposal createNewProposal(Proposal prop, long id) {
        prop.setPerson(pF.getPersonById(id));
        propf.create(prop);
        return prop;
    }
    
    public Proposal changeProposal(Proposal prop, long id) {
        prop.setPerson(pF.getPersonById(id));
        propf.edit(prop);
        return prop;
    }
    
    public Proposal deleteProposal(Proposal prop, long id) {
        prop.setPerson(pF.getPersonById(id));
        propf.remove(prop);
        return prop;
    }
    
    //THIS METHOD DOESNT WORK IDK WHY BUT IT ALWAYS RETURNS NULL
    public Proposal getPropById(long id) {
        List<Proposal> allProposal = propf.findAll();
        for (int i=0 ; i < allProposal.size() ; i++) {
            if (allProposal.get(i).getId() == id) {            
               return allProposal.get(i);
            }     
        }
        return null;
    }
}
