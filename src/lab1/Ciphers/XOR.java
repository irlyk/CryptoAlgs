package lab1.Ciphers;

public class XOR {

    public static byte[] _xor(String key, byte[] array){
        int key_point = 0;
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            if (key_point >= key.length()) key_point = 0;
            result[i] = (byte) (array[i] ^ key.charAt(key_point++));
        }
        return result;
    }

    public static byte[] xor_cbc(byte[] arr1, byte[] arr2){
        byte[] result = new byte[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = (byte) (arr1[i] ^ arr2[i]);
        }
        return result;
    }

    public static void main(String[] args) {
//        String s = "привет";
//        String key = "qwertyuiasdfghjklzxcvbnm";
//        System.out.println("Шифруем: " + '\'' + s + '\'');
//        System.out.println("Ключ:" + '\'' + key + '\'');
//        System.out.println();
//
//        byte[] to_enc = s.getBytes();
//        System.out.println("Оригинал в байтах:");
//        for (int i = 0; i < to_enc.length; i++ ) {
//            if ( i != 0 && i % key.length() == 0) System.out.println();
//            System.out.print(to_enc[i] + "  ");
//        }
//        System.out.println("\n-------------\n");
//
//        byte[] enc = _xor(key, to_enc);
//        System.out.println("Шифровка байтах:");
//        for (int i = 0; i < enc.length; i++ ) {
//            if ( i != 0 && i % key.length() == 0) System.out.println();
//            System.out.print(enc[i] + "  ");
//        }
//        System.out.println("\n-------------\n");
//
//        byte[] dec = _xor(key, enc);
//        System.out.println("дешифровка в байтах");
//        for (int i = 0; i < dec.length; i++ ) {
//            if ( i != 0 && i % key.length() == 0) System.out.println();
//            System.out.print(dec[i] + "  ");
//        }
//        System.out.println("\n-------------\n");
//
//        System.out.println("Расшифровка текст:");
//        System.out.println('\'' + new String(dec) + '\'');
//
//
//        System.out.println("CBC TEST:");
//        byte[] cbc1 = "thisisteststringforxorf".getBytes();
//        byte[] cbc2 = "keykeykeykeykeykeykeyke".getBytes();
//
//        byte[] cbc = xor_cbc(cbc1, cbc2);
//        System.out.println(new String(cbc));
//        byte[] dec_cbc = xor_cbc(cbc, cbc2);
//        System.out.println(new String(dec_cbc));
    }
}
