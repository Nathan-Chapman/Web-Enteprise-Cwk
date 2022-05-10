package nathanchapman.cwk3.ctrl;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import nathanchapman.cwk3.bus.ProposalService;
import nathanchapman.cwk3.bus.VoteService;
import nathanchapman.cwk3.ent.Proposal;

@Named(value = "proposalCtrl")
@RequestScoped
public class ProrposalCtrl implements Serializable { //rename

    @EJB
    private ProposalService props;
    @EJB
    private VoteService votes;
    @Inject
    PersonCtrl personCtrl;

    //private Vote vote = new Vote();
    private Proposal prop = new Proposal(); //Proposal to get uiser input
    private List<Proposal> allProp = new ArrayList<>();
    private Long propId;

    public String createProposal() {
        props.createProposal(prop, personCtrl.getP());
        setProp(null);
        setProp(new Proposal());
        return "";
    }

    public String changeProposal() {
        props.changeProposal(prop, personCtrl.getP());
        return "";
    }

    public String deleteProposal() {
        props.changeProposal(prop, personCtrl.getP());
        return "";
    }

    public String viewProp() {
        displayProposalById(propId);
        return "viewproposal.xhtml";
    }
    
    String action;
    
    public void idListener(ActionEvent event) {
        action = "";
    }

    public String displayProposalById(Long id) {
        //Map<String, String> res = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        //setProp(props.getPropById(Long.valueOf(res.get("id"))));
        setProp(props.getPropById(id));
        //setProp(props.getPropById(Long.valueOfa)));
        
        return "";
    }

//    public String userVote() {
//            votes.createVote(vote, personCtrl.getP(), prop);
//            setVote(null);
//            setVote(new Vote());
//            return "";
//    }
    public ProrposalCtrl() {
    }

    public Long getPropId() {
        return propId;
    }

    public void setPropId(Long propId) {
        this.propId = propId;
    }

//    public Vote getVote() {
//        return vote;
//    }
//
//    public void setVote(Vote vote) {
//        this.vote = vote;
//    }
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
