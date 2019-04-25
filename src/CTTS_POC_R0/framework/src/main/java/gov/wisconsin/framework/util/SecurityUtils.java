package gov.wisconsin.framework.util;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils extends FwBaseClass {

	@Value("${EncryptionKeyValueLocation}")
    public String keyValuePath;
	
	@Value("${WI_XML_TRANSFER}")
	public String id;
	    
	@Value("${KEY_LENGTH}")
	public int keyByteSize;
	    
	@Value("${ALGORITM}")
	public String algo;
	
	private Cipher cipher;
    private SecretKeySpec secretKey;
    
    /**
     * initializes a CipherInfo instance and sets up the Cipher and SecretKeySpec
     * instances. 
     */
    @PostConstruct
    public void initCipherInfo() {
    	String transf = null;
    	InputStream is = null;
    	byte[] secretKeyBytes = null;
    	
    	try {		
    		apiLogger.debug("CipherInfo constructor: Path to the Key = " + keyValuePath);
		 
		    Resource resource = applicationContext.getResource(keyValuePath);
		    is = resource.getInputStream();
		    secretKeyBytes = new byte[keyByteSize];
		    
		    is.read(secretKeyBytes);
		} catch (Exception e) {			
			apiLogger.debug("Exception reading key from file = " + keyValuePath);
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_SECURITY, "Exception reading key from file = " + keyValuePath);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					FwExceptionManager.handleException(this.getClass(), e);
				}
			}
		}
    	
    	try {
			// Create key object from byte array
		  	this.secretKey = new SecretKeySpec(secretKeyBytes, algo);
			transf = algo + "/" + FwConstants.MODE_ECB + "/" + FwConstants.PADDING_PKCS5;	
			this.cipher = Cipher.getInstance(transf, FwConstants.PROVIDER_IBMJCE);
		} catch(java.security.NoSuchAlgorithmException nalg) {		
			apiLogger.debug("Couldn't find the Algorithm: = " + algo + "exception is :" + nalg);
		} catch(java.security.NoSuchProviderException nprvd) {		
			apiLogger.debug("Couldn't find the Provider: = " + FwConstants.PROVIDER_IBMJCE + "exception is :" + nprvd);
		} catch(javax.crypto.NoSuchPaddingException npadd) {		
			apiLogger.debug("Couldn't find the Padding: = " + transf + "exception is :" + npadd);
		} catch(SecurityException se) {		
			apiLogger.debug("CipherInfo constructor : Security Exception while creating the Cipher :" + se);
		} catch(Exception e) {		
			apiLogger.debug("exception is :" + e);			
		}
    }
	
	/**
     * <code>encrypt</code> encrypts the String to be encrypted.
     *
     * @param input a <code>String</code> the input to be encrypted.
     * @return a <code>byte[]</code>, the encrypted data in bytes 
     */
    public byte[] encrypt(String input) throws Exception {
    	if(input == null) {
    		return null;
    	}
    	
		try {
			return encrypt(input.getBytes(FwConstants.ENCODING_UTF8));
		} catch(Exception e) {
			apiLogger.debug("CipherInfo.encrypt: Exception: " + e);	
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_SECURITY);
		}
		return null;
    } // encrypt

	/**
     * <code>encrypt</code> encrypts the input data.
     *
     * @param input a <code>byte[]</code> to be encrypted.
     * @return a <code>byte[]</code> the encrypted data in bytes.
     */
    public byte[] encrypt(byte[] input) throws Exception {
		if(input == null) {
		    return null;
		}
		
		try {
			this.cipher.init(Cipher.ENCRYPT_MODE,this.secretKey);
			return this.cipher.doFinal(input);
		} catch(Exception e) {
			apiLogger.debug("CipherInfo.encrypt: Exception: " + e);
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_SECURITY);
		}
		return null;
    } // encrypt

    /**
     * <code>decrypt</code> decrypts the encrypted input data.
     *
     * @param cryptBytes a <code>byte[]</code> the value to be decrypted.
     * @return <code>byte[]</code> the decrypted data.
     */
    public byte[] decrypt(byte[] cryptBytes) throws Exception {
    	if(cryptBytes == null) {
    		return null;
    	}

		this.cipher.init(Cipher.DECRYPT_MODE,this.secretKey);
		byte[] decrypt =  this.cipher.doFinal(cryptBytes);
		return new String(decrypt, FwConstants.ENCODING_UTF8).getBytes();
    } // decrypt
    
    /**
     * Add 'Z' to the input string for proper LDAP syntax. 
     * Creation date: (07/16/2001 11:03:45 AM)
     * @return java.lang.String
     * @param time java.lang.String
     */
    public String getTimeSyntax(String time)  {
    	String strLDAPTimeSyntx = null;
    	
    	if(time != null) {
    		strLDAPTimeSyntx = time + "Z";
    	}

    	return strLDAPTimeSyntx;
    }
   
    public String parseCNFromDN(String strDN) {
    	String strCN = null;
    	
    	try {
    		if(strDN != null) {
        		int intParsrPostn = strDN.indexOf(',');
        		strCN = strDN.substring(0, intParsrPostn);
        	}
    	} catch(Exception e) {
			 FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_SECURITY);
    	}

    	return strCN;
    }
    
	 public void generateKeys() {
		 try {
			 int i= -1;
			 SecretKey skey = null;
			 String algo = FwConstants.ALGO_BLOWFISH;
			 int byteSize = FwConstants.KEY_SIZE_56;

			 while(++i < 10) {
				 skey = generateKey(algo,byteSize);
				 String path = "/web/webdir0d/wimap/gov/wi/wimap/doa/security/property/testBlowfishKey" + i;
				 byte[] myKeyBytes = skey.getEncoded();
				 FileOutputStream o = new FileOutputStream(path);
				 o.write(myKeyBytes);
				 o.close();
			 }
		 } catch(Exception e) {
			 FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_SECURITY);
		 }
	}
	 
	/**
	 *  <code>generateKey</code> generates a secret key based on algorithm.
	 *
	 * @param algo a <code>String</code> dnoting the algorithm e.g. Blowish
	 * @param byteSize an <code>int</code> the number of bytes in the key
	 * @return a <code>SecretKey</code> 
	 * @exception Exception if an error occurs
	 */
	public SecretKey generateKey(String algo, int byteSize) throws Exception {
		SecretKey skey = null;
		
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(algo);
			kgen.init(byteSize * 8);
			skey = kgen.generateKey();
    	} catch(Exception e) {
			 FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_SECURITY);
    	}
		
		return skey;
	}
    
    public String getKeyValuePath() {
		return keyValuePath;
	}

	public void setKeyValuePath(String keyValuePath) {
		this.keyValuePath = keyValuePath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getKeyByteSize() {
		return keyByteSize;
	}

	public void setKeyByteSize(int keyByteSize) {
		this.keyByteSize = keyByteSize;
	}

	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}

	public SecretKeySpec getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(SecretKeySpec secretKey) {
		this.secretKey = secretKey;
	}

	public Cipher getCipher() {
		return cipher;
	}

	public void setCipher(Cipher cipher) {
		this.cipher = cipher;
	}
}
