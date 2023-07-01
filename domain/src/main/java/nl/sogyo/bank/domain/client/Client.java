package nl.sogyo.bank.domain.client;

import nl.sogyo.bank.domain.account.IAccountPortfolio;
import nl.sogyo.bank.domain.exception.InitializationParameterException;

import java.util.Objects;

public class Client implements IClient {

    private String name;

    private String address;

    private String zipCode;

    private String city;

    private IAccountPortfolio accountPortfolio;

    /**
     * Constructor.
     *
     * @param name Name of the client.
     */
    public Client(String name)
            throws InitializationParameterException {
        this(name, null, null, null);
    }

    /**
     * Constructor.
     *
     * @param name    Name of the client.
     * @param adress  Adress of the client.
     * @param zipCode Zipcode for the adress of the client.
     */
    public Client(String name, String adress, String zipCode)
            throws InitializationParameterException {
        this(name, adress, zipCode, null);
    }

    /**
     * Constructor.
     *
     * @param name    Name of the client.
     * @param address  Adress of the client.
     * @param zipCode Zipcode for the adress of the client.
     * @param city    Place of residence of the client.
     */
    public Client(String name, String address, String zipCode, String city)
            throws InitializationParameterException {
        if (name == null || "".equals(name)) {
            throw new InitializationParameterException(this.getClass()
                    .getName()
                    + ": name");
        }

        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
    }

    /**
     * Returns the name of the client.
     *
     * @return Name of the client.
     * @see IClient#getName()
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the client.
     *
     * @param name Name of the client
     * @see IClient#setName(String)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retuns the adress of the client.
     *
     * @return Adress of the client.
     * @see IClient#getAddress()
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets the adress of the client.
     *
     * @param address Adress of the client
     * @see IClient#setAddress(String)
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the zipcode for the adress of the client.
     *
     * @return Zipcode for the adress of the client.
     * @see IClient#getZipCode()
     */
    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * Sets the zipcode for the adress of the client.
     *
     * @param zipCode Zipcode for the adress of the client.
     * @see IClient#setZipCode(String)
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Returns the place of residence of the client.
     *
     * @return Place of residence of the client.
     * @see IClient#getCity()
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Sets the place of residence of the client.
     *
     * @param city Place of residence of the client.
     * @see IClient#setCity(String)
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Checks to see if two objects are equal to eachother.
     *
     * @return True if objects are equal to eachother.<br />
     * False if objects are NOT equal to eachother.
     * @see Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Client client = (Client) object;
        return Objects.equals(name, client.name) && Objects.equals(address, client.address) &&
                Objects.equals(zipCode, client.zipCode) && Objects.equals(city, client.city) &&
                Objects.equals(accountPortfolio, client.accountPortfolio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, zipCode, city, accountPortfolio);
    }

    /**
     * @see IClient#getAccountPortfolio()
     */
    public IAccountPortfolio getAccountPortfolio() {
        return accountPortfolio;
    }

    /**
     * @see IClient#setAccountPortfolio(IAccountPortfolio)
     */
    public void setAccountPortfolio(IAccountPortfolio accountPortfolio) {
        this.accountPortfolio = accountPortfolio;
    }

}