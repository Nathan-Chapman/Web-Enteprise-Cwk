package nathanchapman.cwk3.pers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nathanchapman.cwk3.ent.Proposal;

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
