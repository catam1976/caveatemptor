package ro.clovertech.backend.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.OverridesAttribute;
import java.util.Collection;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private String email;

    private boolean isAdmin;

    @OneToMany(mappedBy = "owner")
    private Collection<Comment> comments;

    @OneToMany(mappedBy = "soldBy")
    private Collection<Item> soldItems;

    @OneToMany(mappedBy = "placedBy")
    private Collection<Bid> bids;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="street", column=@Column(name="home_address_street")),
            @AttributeOverride(name="zipCode", column=@Column(name="home_address_zip_code")),
            @AttributeOverride(name="city", column=@Column(name="home_address_city"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="street", column=@Column(name="billing_address_street")),
            @AttributeOverride(name="zipCode", column=@Column(name="billing_address_zip_code")),
            @AttributeOverride(name="city", column=@Column(name="billing_address_city"))
    })
    private Address billingAddress;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="street", column=@Column(name="shipping_address_street")),
            @AttributeOverride(name="zipCode", column=@Column(name="shipping_address_zip_code")),
            @AttributeOverride(name="city", column=@Column(name="shipping_address_city"))
    })
    private Address shippingAddress;

    @OneToMany(mappedBy = "user")
    private Collection<PaymentDetails> paymentDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Collection<Item> getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(Collection<Item> soldItems) {
        this.soldItems = soldItems;
    }

    public Collection<Bid> getBids() {
        return bids;
    }

    public void setBids(Collection<Bid> bids) {
        this.bids = bids;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Collection<PaymentDetails> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Collection<PaymentDetails> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
