/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nathanchapman.cwk3.bus;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import nathanchapman.cwk3.ent.Person;
import nathanchapman.cwk3.pers.PersonFacade;

/**
 *
 * @author natha
 */
@Stateless
public class PersonService {

    @EJB
    private PersonFacade pf;

    public Person createNewPerson(Person p) {
        p.setAdminStatus(false);
        pf.create(p);
        return p;
    }
}
