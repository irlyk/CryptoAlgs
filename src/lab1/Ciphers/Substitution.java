package lab1.Ciphers;

public class Substitution {

    private static boolean check_valid_key(String key, int array_len){
        try {
            int i = Character.getNumericValue(key.charAt(0));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static byte[] _substitution_encrypt(String key, byte[] array) {
        if (check_valid_key(key, array.length)) {
            // делаю unsigned byte в виде int массива
            int [] new_array = new int[array.length];
            for (int i = 0; i < array.length; i++) {
                new_array[i] = array[i] & 0xff;
            }

            int t = Character.getNumericValue(key.charAt(0));
            byte[] res = new byte[array.length];
            for (int i = 0; i < array.length; i++) {
                res[i] = (byte) ((new_array[i] + t) % 256);
            }
            return res;
        } else {
            System.out.println("SUBSTITUTION: key is not valid");
            return null;
        }
    }
   public static byte[] _substitution_decrypt(String key, byte[] array) {
       if (check_valid_key(key, array.length)) {
           // делаю unsigned byte в виде int массива
           int [] new_array = new int[array.length];
           for (int i = 0; i < array.length; i++) {
               new_array[i] = array[i] & 0xff;
           }

           int t = Character.getNumericValue(key.charAt(0));
           byte[] res = new byte[array.length];
           for (int i = 0; i < array.length; i++) {
               res[i] = (byte) ((new_array[i] - t) % 256);
           }
           return res;
       } else {
           System.out.println("SUBSTITUTION: key is not valid");
           return null;
       }
   }

//    public static void main(String[] args) {
//        String t = "Привет";
//        String key = "3";
//
//        byte[] r = t.getBytes();
//        System.out.println("Оригинал в байтах:");
//        for (int i = 0; i < r.length; i++ ) {
//            System.out.print(r[i] + "  ");
//        }
//
//        byte[] enc = _substitution_encrypt(key, r);
//
//        System.out.println();
//        System.out.println("Шифровка байтах:");
//        for (int i = 0; i < enc.length; i++ ) {
//            System.out.print(enc[i] + "  ");
//        }
//
//        byte[] dec = _substitution_decrypt(key, enc);
//        System.out.println();
//        System.out.println("дешифровка в байтах");
//        for (int i = 0; i < dec.length; i++ )
//            System.out.print(dec[i] + "  ");
//
//        System.out.println();
//        System.out.println(new String(dec));
//    }
}
