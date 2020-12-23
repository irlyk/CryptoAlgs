package lab1.Ciphers;

public class ROL {

    private static boolean check_valid_key(String key){
        try {
            int i = Character.getNumericValue(key.charAt(0));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static byte right(byte a, byte s) {
        int x = a & 0xFF; // Побитовое И с маской 11111111 для устранения расширения знака при приведении отрицательных значений
        int y = s % 8; // Не надо закомментировать взятие остатка от деления на 8 — оно здесь необходимо.
        return  (byte)((x >> y) | (x << (8 - y)));
    }

    private static byte left(byte a, byte s) {
        int x = a & 0xFF;
        int y = s % 8;
        return  (byte)((x << y) | (x >> (8 - y)));
    }

    public static byte[] _rol_encrypt(String key, byte[] array) {
        if (check_valid_key(key)) {
            byte shift = (byte) Character.getNumericValue(key.charAt(0));
            byte[] result = new byte[array.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = right(array[i], shift);
            }
            return result;
        } else {
            System.out.println("ROL: key is not valid");
            return null;
        }
    }


    public static byte[] _rol_decrypt(String key, byte[] array) {
        if (check_valid_key(key)) {
            byte shift = (byte) Character.getNumericValue(key.charAt(0));
            byte[] result = new byte[array.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = left(array[i], shift);
            }
            return result;
        } else {
            System.out.println("ROL: key is not valid");
            return null;
        }
    }
    public static void main(String[] args) {
//        String s = "привет";
//        byte shift = 00000001;
//        byte[] chars = s.getBytes();
//        byte[] enc = new byte[chars.length];
//
//        for(int i= 0 ; i < chars.length; i++) {
//            System.out.println(chars[i]);
//            System.out.println("vpravo");
//            chars [i] = right(chars[i],shift);
//            System.out.println(chars[i]);
//            System.out.println("vlevo");
//            chars [i] = left(chars[i],shift);
//            System.out.println(chars[i]);
//        }
//
//        System.out.println("Оригинал в байтах:");
//        for (int i = 0; i < chars.length; i++ ) {
//            System.out.print(chars[i] + "  ");
//        }
//
//        enc = _rol_encrypt("1", chars);
////        for (int i = 0; i < chars.length; i++) {
////            enc[i] = right(chars[i], shift);
////        }
//
//        System.out.println();
//        System.out.println("Шифровка байтах:");
//        for (int i = 0; i < enc.length; i++ ) {
//            System.out.print(enc[i] + "  ");
//        }
//
////        byte[] dec = new byte[enc.length];
////        for (int i = 0; i < chars.length; i++) {
////            dec[i] = left(enc[i], shift);
////        }
//
//        byte[] dec = _rol_decrypt("1", enc);
//        System.out.println();
//        System.out.println("Дешифровка байтах:");
//        for (int i = 0; i < dec.length; i++ ) {
//            System.out.print(dec[i] + "  ");
//        }
//
//        System.out.println("\nДешифрованное сообщение");
//        System.out.println(new String(dec));
    }

}
