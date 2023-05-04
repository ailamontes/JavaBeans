/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.*;

public class Security {

//	 	0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41, 0x53, 
//                0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79
//                 // thisisasecretkey

    private static String cpher = Database.Cipherstr;
    private static String keyString = Database.keystr;
    private static byte[] key = keyString.getBytes();

    

    public static String encrypt(String strToEncrypt) {
        String encryptedString = null;
        try {
            Cipher cipher = Cipher.getInstance(cpher);
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return encryptedString;
    }

    public static String decrypt(String codeDecrypt) {
        String decryptedString = null;
        try {
            Cipher cipher = Cipher.getInstance(cpher);
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedString = new String(cipher.doFinal(Base64.decodeBase64(codeDecrypt)));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return decryptedString;
    }
}
