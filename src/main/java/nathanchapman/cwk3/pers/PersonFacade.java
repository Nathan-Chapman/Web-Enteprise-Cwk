package nathanchapman.cwk3.pers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nathanchapman.cwk3.ent.Person;

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
}
