package nathanchapman.cwk3.ctrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
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
    private Person loggedInUser = new Person();
    private boolean logedIn = false;
    private String loggedInResult;
    private String email;
    private String password; //Shouldnt store it in plain text
    
    private long id;
    List<Proposal> allProp = new ArrayList<>();

    private Vote vote= new Vote();
    private long propId;
    private long personId;
    
    private List<Vote> allVotes = new ArrayList<>();
    private List<Vote> votes = new ArrayList<>();
    private long pId;
    private boolean propOwner = false;
    
    //ui vars
    private String updateSignInLink = "SignIn";
    private String updateUserPage ="";
    private String home ="";
    private String hasVotedText ="Please log into vote";
    
    @EJB
    private PersonService ps;
    @EJB
    private ProposalService props;
     @EJB
    private VoteService vs;

    public String doCreatePerson() {
        ps.createNewPerson(p);
        return "";
    }

    public void logIn() {
        String res = ps.logIn(getEmail(), getPassword());
        if (res.equals("Successfully logged in")) {
            setLoggedInResult(res);
            setLogedIn(true);
            setLoggedInUser(ps.getUserByEmail(getEmail()));
            setHome("Click for home page");
            setLoggedInResult(res);
            setPersonId(getLoggedInUser().getId());
            updateHeaderLogIn();
            setHasVotedText("");
        } else {
            setLogedIn(false);
            setLoggedInResult(res);
        }
    }
    
    public void logOut() { // Need to update top right header when ran
        setLogedIn(false);
        setLoggedInUser(p);
        updateHeaderLogOut();
        setPersonId(0);
        setHasVotedText("Please log into vote");
    }
    
    public String doCreateVote() {
        //Map<String, String>  res = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        //setProp(props.getPropById(Long.valueOf(res.get("id"))));
        //propId = 201;
        if (vs.userAlreadyVoted(personId, getpId())) {
            changeVote();
             return"";
        }
         vs.createNewVote(vote, getpId(), personId);
         setVote(null);
         setVote(new Vote());
         return "";
}
    
    public String changeVote() {
        vs.changeVote(vote, getpId(), personId);
         setVote(null);
         setVote(new Vote());
        return"";
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
    
    public Person getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Person loggedInUser) {
        this.loggedInUser = loggedInUser;
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

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
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
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
