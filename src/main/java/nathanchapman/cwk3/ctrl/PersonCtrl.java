package nathanchapman.cwk3.ctrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import nathanchapman.cwk3.bus.BusinessException;
import nathanchapman.cwk3.bus.PersonService;
import nathanchapman.cwk3.bus.ProposalService;
import nathanchapman.cwk3.bus.VoteService;
import nathanchapman.cwk3.ent.Person;
import nathanchapman.cwk3.ent.Proposal;
import nathanchapman.cwk3.ent.Vote;

@Named(value = "personCtrl")
@SessionScoped
public class PersonCtrl implements Serializable {

    private Person p = new Person();
    private boolean logedIn = false;
    private String loggedInResult;
    List<Proposal> allProp = new ArrayList<>();

    private Vote vote = new Vote();
    private long propId;

    private List<Vote> allVotes = new ArrayList<>();
    private List<Vote> votes = new ArrayList<>();
    private long pId;
    private boolean propOwner = false;

    //ui vars
    private String updateSignInLink = "SignIn";
    private String updateUserPage = "";
    private String home = "";
    private String hasVotedText = "Please log into vote";

    @EJB
    private PersonService ps;
    @EJB
    private ProposalService props;
    @EJB
    private VoteService vs;

    public String doCreatePerson() { // add error handling
        ps.createNewPerson(p);
        setP(null);
        setP(new Person());
        return "signin.xhtml";
    }

    public String logIn() { //eror handling
        try {
            Person res = ps.logIn(p.getEmail(), p.getPassword());
            if (res != null) {
                setLogedIn(true);
                setHome("Click for home page");
                setP(res);
                setLoggedInResult("LOGGED IN");
                updateHeaderLogIn();
                setHasVotedText("");
                return "home.xhtml";
            }
        } catch (BusinessException e) {
            FacesMessage msg = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage("", msg);
            setLogedIn(false);
        }
        return "";
    }

    public void logOut() { // Need to update top right header when ran
        setLogedIn(false);
        setP(null);
        setP(new Person());
        updateHeaderLogOut();
        setHasVotedText("Please log into vote");
    }

    public String updateHeaderLogIn() {
        setUpdateSignInLink("");
        setUpdateUserPage("User page here // log out on this page");
        return "";
    }

    public String updateHeaderLogOut() {
        setUpdateSignInLink("SignIn");
        setUpdateUserPage("");
        return "";
    }

    //GETTER AND SETTERS
    public String getHasVotedText() {
        return hasVotedText;
    }

    public void setHasVotedText(String hasVotedText) {
        this.hasVotedText = hasVotedText;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getUpdateSignInLink() {
        return updateSignInLink;
    }

    public void setUpdateSignInLink(String updateSignInLink) {
        this.updateSignInLink = updateSignInLink;
    }

    public String getUpdateUserPage() {
        return updateUserPage;
    }

    public void setUpdateUserPage(String updateUserPage) {
        this.updateUserPage = updateUserPage;
    }

    public String getLoggedInResult() {
        return loggedInResult;
    }

    public void setLoggedInResult(String loggedInResult) {
        this.loggedInResult = loggedInResult;
    }

    public boolean isLogedIn() {
        return logedIn;
    }

    public void setLogedIn(boolean logedIn) {
        this.logedIn = logedIn;
    }

    public List<Vote> getVotes() {
        if (votes.isEmpty()) {
            votes = vs.getVotesByProposalId(pId);
        }
        votes = vs.getVotesByProposalId(pId);
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public long getqId() {
        return pId;
    }

    public void setqId(long pId) {
        this.pId = pId;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public long getPropId() {
        return propId;
    }

    public void setPropId(long propId) {
        this.propId = propId;
    }

    public List getAllVotes() {
        if (allVotes.isEmpty()) {
            allVotes = vs.getAllVotes();
        }
        return allVotes;
    }

    public void setAllVotes(List<Vote> allVotes) {
        this.allVotes = allVotes;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }

    public PersonCtrl() {
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

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public boolean isPropOwner() {
        return propOwner;
    }

    public void setPropOwner(boolean propOwner) {
        this.propOwner = propOwner;
    }
}
