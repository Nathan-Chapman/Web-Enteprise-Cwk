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

@Named(value = "personCtrl")
@RequestScoped
public class PersonCtrl {

    private Person p = new Person();
    private String email;
    private String password; //Shouldnt store it in plain text

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }
    
    public PersonCtrl() {
    }
    
    @EJB
    private PersonService ps;

    public String doCreatePerson() {
        ps.createNewPerson(p);
        return "";
    }
    
    public void logIn() {
        setLogInResult(ps.logIn(getEmail(), getPassword()));
    }
    private String logInResult;

    public String getLogInResult() {
        return logInResult;
    }

    public void setLogInResult(String logInResult) {
        this.logInResult = logInResult;
    }
    
}
