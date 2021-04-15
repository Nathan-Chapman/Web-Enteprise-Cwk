/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nathanchapman.cwk3.bus;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import nathanchapman.cwk3.ent.Proposal;
import nathanchapman.cwk3.pers.PersonFacade;
import nathanchapman.cwk3.pers.ProposalFacade;

/**
 *
 * @author natha
 */
@Stateless
public class ProposalService {
    
    @EJB
    private ProposalFacade propF;
    @EJB
    private PersonFacade pF;
    
    public Proposal createNewProposal(Proposal prop, long id) {
        prop.setPerson(pF.getPersonById(id));
        propF.create(prop);
        return prop;
    }

}
