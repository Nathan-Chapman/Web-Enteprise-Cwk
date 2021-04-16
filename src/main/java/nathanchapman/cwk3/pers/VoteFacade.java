/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nathanchapman.cwk3.pers;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import nathanchapman.cwk3.ent.Vote;


/**
 *
 * @author natha
 */
@Stateless
public class VoteFacade extends AbstractFacade<Vote> {

    @PersistenceContext(unitName = "RelationsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }   
            
    public VoteFacade() {
        super(Vote.class);
    }
    
    public List<Vote> getVotesByProposal(long id) { //Might need to check for empty list idk
        Query query = em.createQuery("SELECT * FROM Vote WHERE Vote.ID =:id", Vote.class);
        List<Vote> votes = query.setParameter("id", id).getResultList();
        return votes;
    }
}
