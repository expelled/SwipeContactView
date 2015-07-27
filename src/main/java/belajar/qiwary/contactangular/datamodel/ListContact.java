package belajar.qiwary.contactangular.datamodel;

/**
 * Created by hikmat on 26/07/15.
 */
public class ListContact {

    public String id;
    public String nourut;
    public String name;
    public String email;
    public String number;

    public ListContact() {

    }

    public ListContact(String id,String nourut, String name, String email, String number) {
        this.id = id;
        this.nourut = nourut;
        this.name = name;
        this.email = email;
        this.number = number;
    }
}
