/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Bruger
 */
@Entity
@NamedQuery(name = "Customer.deleteAllRows", query = "DELETE FROM Customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @ElementCollection
    private List<String> hobbies = new ArrayList();
    
        private Address address;
//    
//    @OneToMany
        //    @OneToOne (cascade=CascadeType.PERSIST)
//    @OneToOne
    
//    @OneToMany(mappedBy = "customer")
    @ManyToMany
    private List<Address> addresses = new ArrayList();


    public List<Address> getAddresses() {
        return addresses;
    }


    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    
     public Customer() {
    }

    public Customer(String firstname, String lastname, List hobbies) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.hobbies = hobbies;

    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addHobby(String s){
        hobbies.add(s);
    }
    
    public String getHobbies(){
        return String.join(",", hobbies);
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Customer)) {
//            return false;
//        }
//        Customer other = (Customer) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "entities.Customer[ id=" + id + " ]";
//    }
    
}
