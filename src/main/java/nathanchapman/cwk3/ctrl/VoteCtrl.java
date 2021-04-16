/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nathanchapman.cwk3.ctrl;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import nathanchapman.cwk3.bus.VoteService;
import nathanchapman.cwk3.ent.Vote;

/**
 *
 * @author natha
 */
@Named(value = "voteCtrl")
@RequestScoped
public class VoteCtrl {

    private Vote vote= new Vote();
    private long propId;
    private long personId;

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

    

    /**
     * Creates a new instance of VoteCtrl
     */
    public VoteCtrl() {
    }
    
    @EJB
    private VoteService vs;
    public String doCreateVote() {
        vs.createNewVote(vote, propId, personId);
        return"";
    }
}
