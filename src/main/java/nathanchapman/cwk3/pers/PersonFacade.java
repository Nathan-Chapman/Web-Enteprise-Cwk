package nathanchapman.cwk3.pers;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import nathanchapman.cwk3.ent.Person;
import nathanchapman.cwk3.ent.Proposal;

@Stateless
public class PersonFacade extends AbstractFacade<Person> {

    @PersistenceContext(unitName = "RelationsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonFacade() {
        super(Person.class);
    }

    public Person getPersonById(long id) {
        return em.find(Person.class, id);
    }

    public Person getPersonByEmail(String email) { // WORKS
        Query query = em.createQuery("SELECT p FROM Person p WHERE p.email = :email");
        query.setParameter("email", email);
        List<Person> persons = query.getResultList();
        return persons.get(0);
    }
}
