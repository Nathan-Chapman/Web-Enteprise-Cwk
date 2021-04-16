/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nathanchapman.cwk3.pers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nathanchapman.cwk3.ent.Proposal;

/**
 *
 * @author natha
 */
@Stateless
public class ProposalFacade extends AbstractFacade<Proposal> {

    @PersistenceContext(unitName = "RelationsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProposalFacade() {
        super(Proposal.class);
    }
    
        public Proposal getProposalById(long id) { 
        return em.find(Proposal.class, id);
    }
}
