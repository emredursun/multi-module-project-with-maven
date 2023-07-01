package nl.sogyo.bank.domain.client;

import nl.sogyo.bank.domain.account.IAccountPortfolio;

public interface IClient {

    /**
     * Returns the name of the client.
     *
     * @return Name of the client.
     */
    String getName();

    /**
     * Sets the name of the client.
     *
     * @param name Name of the client.
     */
    void setName(String name);

    /**
     * Returns the adress of the client.
     *
     * @return Adress of the client.
     */
    String getAddress();

    /**
     * Sets the address of the client.
     *
     * @param address Address of the client.
     */
    void setAddress(String address);

    /**
     * Returns the zipcode for the adress of the client.
     *
     * @return Zipcode for the adress of the client.
     */
    String getZipCode();

    /**
     * Sets the zipcode for the adress of the client.
     *
     * @param zipCode Zipcode for the adress of the client.
     */
    void setZipCode(String zipCode);

    /**
     * Returns the place of residence of the client.
     *
     * @return Place of residence of the client.
     */
    String getCity();

    /**
     * Sets the place of residence of the client.
     *
     * @param city Place of residence of the client.
     */
    void setCity(String city);

    /**
     * Returns the accountportfolio of this client.
     *
     * @return The accountportfolio of this client.
     */
    IAccountPortfolio getAccountPortfolio();

    /**
     * Connects the appropriate accountportfolio to this client.
     *
     * @param accountPortfolio Accountportfolio for this client.
     */
    void setAccountPortfolio(IAccountPortfolio accountPortfolio);

}