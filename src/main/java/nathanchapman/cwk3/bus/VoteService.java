package nathanchapman.cwk3.bus;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import nathanchapman.cwk3.ent.Vote;
import nathanchapman.cwk3.pers.PersonFacade;
import nathanchapman.cwk3.pers.ProposalFacade;
import nathanchapman.cwk3.pers.VoteFacade;

@Stateless
public class VoteService {

    @EJB 
    private VoteFacade vf;
    @EJB
    private ProposalFacade propF;
    @EJB
    private PersonFacade pf;
    
    public boolean userAlreadyVoted(Long personId, Long propId) {
        List<Vote> allVotesForProp = getVotesByProposalId(propId);
        for (int i = 0 ;  i < allVotesForProp.size() ; i++) {
            if (allVotesForProp.get(i).getPerson().getId() == personId ) {
                return true;
            }
        }
         return false;
    }
    
    public Vote createNewVote(Vote vote, long propId, long personId) {
        vote.setProposal(propF.getProposalById(propId));
        vote.setPerson(pf.getPersonById(personId));
        vf.create(vote);
        return vote;
    }
    
    public Vote changeVote(Vote vote, long propId, long personId) {
        vote.setProposal(propF.getProposalById(propId));
        vote.setPerson(pf.getPersonById(personId));
        vf.remove(getVote(personId, propId));
        vf.create(vote);
        return vote;
    }
    
    public List<Vote> getAllVotes() {
        return vf.findAll();
        //return vf.getVotesByProposal(id);
   }
    
    public List<Vote> getVotesByProposalId(long id) {
        List<Vote> allVote = vf.findAll();
        List<Vote> temp = new ArrayList<>();
        for (int i=0 ; i < allVote.size() ; i++) {
            if (allVote.get(i).getProposal().getId() == id) {            
               temp.add(allVote.get(i));
            }     
        }
        return temp;
    }
    
    public Vote getVote(long personId,long propId) {
        List<Vote> temp = getVotesByProposalId(propId);
        for (int i = 0 ;  i < temp.size() ; i++) {
            if (temp.get(i).getPerson().getId() == personId ) {
                return temp.get(i);
            }
        }
        return new Vote();
    }
}
