package lab1.Ciphers;

public class Permutation {

    public static byte[] _permutation_encrypt(String key, byte[] array) {
        if (check_valid_key(key, array.length)){
            int arr_length = array.length;
            if (array.length % key.length() != 0)
                arr_length = arr_length + (key.length() - arr_length % key.length());
            byte[] res = new byte[arr_length];
            for (int i = 0; i < array.length; i+=key.length()) {
                for (int j = 0; j < key.length(); j+=1) {
                    int position = Character.getNumericValue(key.charAt(j));
                    if (i + position < array.length) {
                        res[j + i] = array[i + position];
                    } else {
                        res[j + i] = 0;
                    }
                }
            }
            return res;
        } else {
            System.out.println("PERMUTATION: key is not valid");
            return null;
        }
    }


    private static boolean check_valid_key(String key, int array_len){
        if (key.length() > array_len){
            System.out.println("PERMUTATION: Длина ключа длиньше массива");
            return false;
        }
        return key.matches("[0-9]+");
    }


    public static byte[] _permutation_decrypt(String key, byte[] array) {
        if (check_valid_key(key, array.length)){
            byte[] res = new byte[array.length];
            for (int i = 0; i < array.length; i+=key.length()) {
                for (int j = 0; j < key.length(); j+=1){
                    if (i + j < array.length) {
                        int position = Character.getNumericValue(key.charAt(j));
                        if (i + position <= array.length)
                            res[i + position] = array[i + j];
                    }
                }
            }
            return res;
        } else {
            System.out.println("PERMUTATION: key is not valid");
            return null;
        }
    }

    public static void main(String[] args) {
//        String str = "приветмамка";
//        String key = "340125";
//        byte[] enc = str.getBytes();
//        byte[] res = _permutation_encrypt(key, enc);
//
//        System.out.println("Оригинал в байтах:");
//        for (int i = 0; i < enc.length; i++ ) {
//            if ( i % key.length() == 0) System.out.println();
//            System.out.print(enc[i] + "  ");
//        }
//
//        System.out.println();
//        System.out.println("Шифровка байтах:");
//        for (int i = 0; i < res.length; i++ ) {
//            if ( i % key.length() == 0) System.out.println();
//            System.out.print(res[i] + "  ");
//        }
//
//        System.out.println();
//        byte[] dec = _permutation_decrypt(key, res);
//        System.out.println("дешифровка в байтах");
//        for (int i = 0; i < res.length; i++ )
//            System.out.print(dec[i] + "  ");
//        String res1 = new String(dec);
//        System.out.println();
//        System.out.println("Дешифровка в String:");
//        System.out.println(res1);
    }
}
