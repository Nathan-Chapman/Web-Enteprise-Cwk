/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nathanchapman.cwk3.ctrl;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import nathanchapman.cwk3.bus.PersonService;
import nathanchapman.cwk3.ent.Person;

/**
 *
 * @author natha
 */
@Named(value = "personCtrl")
@RequestScoped
public class PersonCtrl {

    private Person p = new Person();

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }
    /**
     * Creates a new instance of PersonController
     */
    public PersonCtrl() {
    }
    
    @EJB
    private PersonService ps;
    public String doCreatePerson() {
        ps.createNewPerson(p);
        return "";
    }
    
}
