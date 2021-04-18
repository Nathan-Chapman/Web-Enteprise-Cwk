/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nathanchapman.cwk3.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import nathanchapman.cwk3.bus.ProposalService;
import nathanchapman.cwk3.ent.Proposal;

@Named(value = "proposalCtrl")
@RequestScoped
public class ProposalCtrl {
    
    private Proposal prop = new Proposal();
    private long id;
    List<Proposal> allProp = new ArrayList<>();

    public List<Proposal> getAllProp() {
        if (allProp.isEmpty()) {
            allProp = props.getAllProposals();
        }
        return allProp;
    }

    public void setAllProp(List<Proposal> allProp) {
        this.allProp = allProp;
    }
    
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
    
    public String displayProposalById() {
        Map<String, String>  res = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        setId(Long.valueOf(res.get("id")));
        setProp(props.getPropById(id));
        return "";
    }
}
