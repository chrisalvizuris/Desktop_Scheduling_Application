package Model;

public class Contacts {
    private int contactId;
    private String contactName;
    private String contactEmail;

    /**
     * This method is the constructor for the contact object.
     * @param contactName The name of the contact.
     * @param contactEmail The email of the contact.
     */
    public Contacts(String contactName, String contactEmail) {
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return contactName;
    }
}
