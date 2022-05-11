package nathanchapman.cwk3.pers;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public List<Proposal> getProposalsByPerson(long id) {
        Query query = em.createQuery("SELECT * FROM Proposal WHERE Person.ID =:id", Proposal.class);
        List<Proposal> proposals = query.setParameter("id", id).getResultList();
        return proposals;
    }
}
