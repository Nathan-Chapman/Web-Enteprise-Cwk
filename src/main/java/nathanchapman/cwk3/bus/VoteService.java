package nathanchapman.cwk3.bus;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import nathanchapman.cwk3.ent.Person;
import nathanchapman.cwk3.ent.Proposal;
import nathanchapman.cwk3.ent.Vote;
import nathanchapman.cwk3.pers.PersonFacade;
import nathanchapman.cwk3.pers.ProposalFacade;
import nathanchapman.cwk3.pers.VoteFacade;

@Stateless
public class VoteService {

    @EJB
    private VoteFacade vf;
    @EJB
    private ProposalFacade proposalF;
    @EJB
    private PersonFacade pf;

    public boolean userAlreadyVoted(long personId, long propId) {  //rewrite
        List<Vote> allVotesForProp = getVotesByProposalId(propId);
        for (int i = 0; i < allVotesForProp.size(); i++) {
            if (allVotesForProp.get(i).getPerson().getId() == personId) {
                return true;
            }
        }
        return false;
    }

    public Vote createVote(Vote vote, Person person, Proposal prop) {
        vote.setProposal(prop);
        vote.setPerson(person);
        vf.create(vote);
        return vote;
    }

    public Vote changeVote(Vote vote, Proposal prop, Person person) { // Not used
        vote.setProposal(prop);
        vote.setPerson(pf.getPersonById(person.getId()));
        vf.remove(getVote(person.getId(), prop.getId()));
        vf.create(vote);
        return vote;
    }

    public List<Vote> getAllVotes() { // Just included for testing purposes 
        return vf.findAll();
    }

    public List<Vote> getVotesByProposalId(long id) {
        try {
            return vf.getVotesByProposal(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Vote getVote(long personId, long propId) {
        List<Vote> temp = getVotesByProposalId(propId);
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getPerson().getId() == personId) {
                return temp.get(i);
            }
        }
        return new Vote();
    }
}
