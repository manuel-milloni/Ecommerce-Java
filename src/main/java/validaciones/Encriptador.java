
package validaciones;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class Encriptador {
    
    
    private static final String AES_ALGORITHM = "AES";
    private static final String ENCRYPTION_KEY = "nax3QSuveekMhA46ak4Tsg=="; 

    public static String encrypt(String data) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedValue = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedValue = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedValue);
    }

    private static Key generateKey() throws Exception {
        byte[] keyValue = ENCRYPTION_KEY.getBytes("UTF-8");
        return new SecretKeySpec(keyValue, AES_ALGORITHM);
    }
    
}
