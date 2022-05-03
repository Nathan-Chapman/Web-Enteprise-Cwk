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
import javax.inject.Inject;
import nathanchapman.cwk3.bus.ProposalService;
import nathanchapman.cwk3.ent.Proposal;

@Named(value = "proposalCtrl")
@RequestScoped
public class ProrposalCtrl { //rename
    
    @EJB
    private ProposalService props;
    @Inject PersonCtrl personCtrl;
    
    private Proposal prop = new Proposal(); //Proposal to get uiser input
    private List<Proposal> allProp = new ArrayList<>();
   
    
    public String createProposal() {
        props.createProposal(prop,  personCtrl.getP() );
        setProp(null);
        setProp(new Proposal());
        return"";
    }    
        
    public String changeProposal() {
        props.changeProposal(prop, personCtrl.getP());
        return"";
    }
    
    public String deleteProposal() {
        props.changeProposal(prop, personCtrl.getP());
        return"";
    }   
    
        public String displayProposalById() {
        Map<String, String>  res = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        setProp(props.getPropById(Long.valueOf(res.get("id"))));
//        if (logedIn == false) {
//            setHasVotedText("Please log into vote");
//        }
       // setHasVotedText("");
        return "";
    }  
    
    public ProrposalCtrl() {
    }
    
    public List<Proposal> getAllProp() {
        if (allProp.isEmpty()) {
            allProp = props.getAllProposals();
        }
        return allProp;
    }

    public void setAllProp(List<Proposal> allProp) {
        this.allProp = allProp;
    }
    
    public void updateAllProp() {
        this.allProp = props.getAllProposals();
    }

    public Proposal getProp() {
        return prop;
    }

    public void setProp(Proposal prop) {
        this.prop = prop;
    }

    
   
}
