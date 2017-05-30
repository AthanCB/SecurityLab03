/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classLibrary;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;
import securitylab03.MainPage;
import securitylab03.PasswordManagerView;

/**
 *
 * @author athan
 */
public class CryptoGen {
    String P;
    String S;
    String sKey;
    String authHash;
    
    public CryptoGen(String P, String S)
    {
        this.P = P;
        this.S = S;
        
    }
    
    public String authHashGen() {
        String algorithm = "PBKDF2WithHmacSHA1";
        
        int c = 1000;
        int dkLen = 16;

        KeySpec spec = new PBEKeySpec(sKey.toCharArray(), P.getBytes(), c, dkLen);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
            byte[] key = skf.generateSecret(spec).getEncoded();
            return authHash = toHex(key);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordManagerView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String sKeyGen() throws InvalidKeySpecException {
        int c = 2000;
        int dkLen = 16;
        try {
            KeySpec spec = new PBEKeySpec(P.toCharArray(), S.getBytes(), c, dkLen);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = skf.generateSecret(spec).getEncoded();
            return sKey = toHex(key);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordManagerView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {

        }
        return sKeyGen();
    }

    private String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }
}
