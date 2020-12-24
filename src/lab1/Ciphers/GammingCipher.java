package lab1.Ciphers;

public class GammingCipher {

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

    public static void main(String[] args) {
//        String s = "приве";
//        String key = "key";
//        byte[] to_enc = s.getBytes();
//        to_enc = XOR.xor_cbc(to_enc, CBC.init);
//        byte[] enc = _gamma_encrypt(key, to_enc);
//        byte[] dec = _gamma_decrypt(key, enc);
//        dec = XOR.xor_cbc(dec, CBC.init);
//        System.out.println(new String(dec));
    }
}
