package lab1.Ciphers;

public class GammingСipher {

    public static byte[] xor(String key, byte[] array){
        int key_point = 0;
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            if (key_point >= key.length()) key_point = 0;
            result[i] = (byte) (array[i] ^ key.charAt(key_point++));
        }
        return result;
    }

    public static byte[] _gamma_encrypt(String key, byte[] array) {
        int key_point = 0;
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            if (key_point >= key.length()) key_point = 0;
            //result[i] = (byte)(((array[i] + 128)  + (int)key.charAt(key_point++))  % 256);
            result[i] = (byte) ((array[i] + key.charAt(key_point++)) % 256);
        }
        return result;
    }

    public static byte[] _gamma_decrypt(String key, byte[] array) {
        int key_point = 0;
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            if (key_point >= key.length()) key_point = 0;
            //result[i] = (byte)(((array[i] + 128)  + 256 - (int)key.charAt(key_point++))  % 256);
            result[i] = (byte) ((array[i] + 256 - key.charAt(key_point++)) % 256);
        }
        return result;
    }
}
