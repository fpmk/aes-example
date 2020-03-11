import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

class AESenc {

    private static final String ALGO = "AES";

    /**
     * Encrypt a string with AES algorithm.
     *
     * @param data is a string
     * @return the encrypted string
     */
    public static String encrypt(String data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encVal);
    }

    /**
     * Decrypt a string with AES algorithm.
     *
     * @param encryptedData is a string
     * @return the decrypted string
     */
    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = encryptedData.getBytes();
        byte[] decValue = c.doFinal(Base64.getDecoder().decode(decordedValue));
        return new String(decValue);
    }

    /**
     * Generate a new encryption key.
     */
    private static Key generateKey() {
        return new SecretKeySpec("secretkey16lengt".getBytes(StandardCharsets.UTF_8), ALGO);
    }
}

class Scratch {


    public static void main(String[] args) {
        String str = "my secret string";
        try {
            String encrypt = null;
            encrypt = AESenc.encrypt(str);
            System.out.println(encrypt);
            System.out.println(AESenc.decrypt(encrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
