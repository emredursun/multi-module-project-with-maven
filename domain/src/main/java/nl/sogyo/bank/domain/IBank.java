package nl.sogyo.bank.domain;

import nl.sogyo.bank.domain.account.IBankAccount;
import nl.sogyo.bank.domain.client.IClient;
import nl.sogyo.bank.domain.exception.AccountNotFoundException;
import nl.sogyo.bank.domain.exception.ClientNotFoundException;
import nl.sogyo.bank.domain.exception.InitializationParameterException;

import java.util.Collection;

public interface IBank {

	/**
	 * Creates a new client for the bank.
	 * 
	 * @param name
	 *            Name of the client.
	 * @param adress
	 *            Adress of the client.
	 * @param zipCode
	 *            Zipcode for the adress of the client.
	 * @return Client object initialised with input parameters.
	 */
	IClient createNewClient(String name, String adress, String zipCode)
			throws InitializationParameterException;

	/**
	 * Creates a new client for the bank.
	 * 
	 * @param name
	 *            Name of the client.
	 * @param adress
	 *            Adress of the client.
	 * @param zipCode
	 *            Zipcode for the adress of the client.
	 * @param city
	 *            Place of residence of the client.
	 * @return Client object initialised with input parameters.
	 */
	IClient createNewClient(String name, String adress, String zipCode,
			String city) throws InitializationParameterException;

	/**
	 * Creates a new checkingaccount for a client.
	 * 
	 * @param owner
	 *            Client who owns the account.
	 * @return CheckingAccount initialised with input parameters
	 */
	IBankAccount createNewCheckingAccount(IClient owner)
			throws InitializationParameterException;

	/**
	 * Creates a new savingsaccount for a client.
	 * 
	 * @param owner
	 *            Client who owns the account.
	 * @return SavingsAccount initialised with input parameters
	 */
	IBankAccount createNewSavingsAccount(IClient owner)
			throws InitializationParameterException;

	/**
	 * Creates a new brokerageaccount for a client.
	 * 
	 * @param owner
	 *            Client who owns the account.
	 * @return BrokerageAccount initialised with input parameters
	 */
	IBankAccount createNewBrokerageAccount(IClient owner)
			throws InitializationParameterException;

	/**
	 * Returns all the clients of the bank.
	 * 
	 * @return All the clients of the bank.
	 */
	Collection<IClient> getAllClients();

	/**
	 * Returns a specific client that conforms to the search criteria.
	 * 
	 * @param name
	 *            Name of the client.
	 * @param adress
	 *            Adress of the client.
	 * @param zipCode
	 *            Zipcode for the adress of the client.
	 * @return Client that conforms to the search criteria.
	 */
	IClient getClient(String name, String adress, String zipCode)
			throws ClientNotFoundException;

	/**
	 * Returns all the accounts of the bank.
	 * 
	 * @return All the accounts of the bank.
	 */
	Collection<IBankAccount> getAllAccounts();

	/**
	 * Returns the account that belongs to the specified accountNumber.
	 * 
	 * @param accountNumber
	 *            Number of the account that is being searched for.
	 * @return The account that belongs to the specified accountNumber.
	 */
	IBankAccount getBankAccount(String accountNumber)
			throws AccountNotFoundException;

}