/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nathanchapman.cwk3.ctrl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
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
    
    private Proposal prop = new Proposal();
    private long id;
    List<Proposal> allProp = new ArrayList<>();

    private Vote vote= new Vote();
    private long propId = 2;
    private long personId;
    
    private List<Vote> allVotes = new ArrayList<>();
    private List<Vote> votes = new ArrayList<>();
    private long pId = 2;
    
    //ui vars
    private String updateSignInLink = "SignIn";
    private String updateUserPage ="";
    private String home ="";
    
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
        setProp(props.getPropById(getPropId()));
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
        }
        setLogedIn(false);
        setLoggedInResult(res);
    }
    
    public void logOut() { // Need to update top right header when ran
        setLogedIn(false);
        setLoggedInUser(p);
        updateHeaderLogOut();
        setPersonId(0);
    }
    
    public String doCreateVote() {
        vs.createNewVote(vote, propId, personId);
        return"";
    }
    
    public String changeVote() {
        vs.changeVote(vote, propId, personId);
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
}
