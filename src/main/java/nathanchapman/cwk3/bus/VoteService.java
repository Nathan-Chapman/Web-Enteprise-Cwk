/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nathanchapman.cwk3.bus;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import nathanchapman.cwk3.ent.Vote;
import nathanchapman.cwk3.pers.PersonFacade;
import nathanchapman.cwk3.pers.ProposalFacade;
import nathanchapman.cwk3.pers.VoteFacade;


/**
 *
 * @author natha
 */
@Stateless
public class VoteService {

    @EJB 
    private VoteFacade vf;
    @EJB
    private ProposalFacade propF;
    @EJB
    private PersonFacade pf;
    
    public Vote createNewVote(Vote vote, long propId, long personId) {
        vote.setProposal(propF.getProposalById(propId));
        vote.setPerson(pf.getPersonById(personId));
        vf.create(vote);
        return vote;
    }
    
    public List<Vote> getAllVotes() {
        return vf.findAll();
        //return vf.getVotesByProposal(id);
   }
}
