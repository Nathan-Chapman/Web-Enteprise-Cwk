package nathanchapman.cwk3.ctrl;

import java.io.Serializable;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import nathanchapman.cwk3.bus.ProposalService;
import nathanchapman.cwk3.bus.VoteService;
import nathanchapman.cwk3.ent.Proposal;
import nathanchapman.cwk3.ent.Vote;

@Named(value = "voteCtrl")
@RequestScoped
public class VoteCtrl implements Serializable {

    private Vote vote = new Vote();
    //private Proposal proposal = new Proposal();

    @EJB
    private VoteService vs;
    @EJB
    private ProposalService props; 
    @Inject
    PersonCtrl personCtrl;

    public String userVote() {
        //Map<String, String> res = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        //Proposal prop = props.getPropById(Long.valueOf(res.get("id")));
        //vote.setVoteValue("a");
        //vs.createVote(vote, personCtrl.getP());
        Proposal prop = props.getPropById(151L);
        vs.createVote(vote, personCtrl.getP(), prop);
        setVote(null);
        setVote(new Vote());
        return "";
    }

//    public String changeVote() {
//        vs.changeVote(vote, getProposal(), personCtrl.getP());
//        setVote(null);
//        setVote(new Vote());
//        return "";
//    }
    public VoteCtrl() {
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }
}
