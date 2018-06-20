package fr.epita.quiz.datamodel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public class UserLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uid;
	
	private String userName;
	private String hashedPassword;
	
	/**
	 *
	 */
	public UserLogin() {

	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the hashed password
	 */
	public String getHashedPassword() {
		return hashedPassword;
	}

	/**
	 * hashes the password before storing
	 * 
	 * @param clearTextPassword
	 */
	public void setPassword(String clearTextPassword) {
		this.hashedPassword = md5Hash(clearTextPassword);
	}

	/**
	 * store the hashedpassword without performing hash procedure 
	 * @param hashedPassword
	 */
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	
	/**
	 * @param passwordToHash
	 * @return MD5 Hash of the password
	 */
	private String md5Hash(String passwordToHash) {
		if (passwordToHash == null)
			return null;
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(passwordToHash.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO: LOGGER.error("Error occured while hashing the password of the user:" + this, e);
		}
		return generatedPassword;
	}

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}
}