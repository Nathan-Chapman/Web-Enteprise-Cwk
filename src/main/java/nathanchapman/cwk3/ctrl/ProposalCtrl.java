/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nathanchapman.cwk3.ctrl;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import nathanchapman.cwk3.bus.ProposalService;
import nathanchapman.cwk3.ent.Proposal;

/**
 *
 * @author natha
 */
@Named(value = "proposalCtrl")
@RequestScoped
public class ProposalCtrl {
    
    private Proposal prop = new Proposal();
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Proposal getProp() {
        return prop;
    }
    
    public void setProp(Proposal prop) {
        this.prop = prop;
    }

    /**
     * Creates a new instance of ProposalCtrl
     */
    public ProposalCtrl() {
    }
   
    @EJB
    private ProposalService props;
    
    public String doCreateProposal() {
        props.createNewProposal(prop, id);
        return"";
    }
    
    public String changeProposal() {
        props.changeProposal(prop, id);
        return"";
    }
    
    public String deleteProposal() {
        props.changeProposal(prop, id);
        return"";
    }
}
