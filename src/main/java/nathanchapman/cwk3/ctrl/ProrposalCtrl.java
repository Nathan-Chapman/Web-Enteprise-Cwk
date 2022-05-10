package nathanchapman.cwk3.ctrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import nathanchapman.cwk3.bus.ProposalService;
import nathanchapman.cwk3.bus.VoteService;
import nathanchapman.cwk3.ent.Proposal;
import nathanchapman.cwk3.ent.Vote;

@Named(value = "proposalCtrl")
@RequestScoped
public class ProrposalCtrl implements Serializable {

    @EJB
    private ProposalService props;
    @EJB
    private VoteService votes;
    @Inject
    PersonCtrl personCtrl;

    //private Vote vote = new Vote();
    private Proposal prop = new Proposal();
    private Vote vote = new Vote();
    private List<Proposal> allProp = new ArrayList<>();
    private Long propId;
    private String testString = "aa";

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
        setProp(props.getPropById(propId));
        personCtrl.setpId(propId);
        return "viewproposal.xhtml";
    }

    public String userVote() {
        setProp(props.getPropById(personCtrl.getpId()));
        setTestString(vote.getVoteValue());
        votes.createVote(vote, personCtrl.getP(), prop);
        setVote(null);
        setVote(new Vote());
        return "";
    }

    public ProrposalCtrl() {
    }

    public Long getPropId() {
        return propId;
    }

    public void setPropId(Long propId) {
        this.propId = propId;
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

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

}
