package lab1.Ciphers;

import java.util.ArrayList;

public class CBC {

    private static byte[] init = {-15,  -87,  -63,  -93,  -98,  -79,  -91,  -101,  -66,  -80};

    private static byte[] encrypt_with_chosen_cipher(boolean flag, String key, byte[] array, int cipher){
        byte[] result = null;
        if (cipher % 2 == 0) {
            if (flag) return  GammingCipher._gamma_encrypt(key, array);
            else return GammingCipher._gamma_decrypt(key, array);
        }
        switch (cipher){
            case 1:
                if (flag) result = Permutation._permutation_encrypt("9685703124", array);
                else result = Permutation._permutation_decrypt("9685703124", array);
                break;
            case 3:
                if (flag) result = Substitution._substitution_encrypt("3", array);
                else result = Substitution._substitution_decrypt("3", array);
                break;
            case 5:
                if (flag) result = ROL._rol_encrypt("8", array);
                else result = ROL._rol_decrypt("8", array);
                break;
        }
        return result;
    }

    public static byte[] encrypt_v2 (String key, byte[] array, int block_size) {
        ArrayList<Byte> list = new ArrayList<>();
        int cipher = 0;
        byte[] Ei = new byte[block_size];
        for (int i = 0; i < array.length; i+=block_size) {
            if (cipher > 5) cipher = 0;
            // Берём срезку в 10 символов из array
            byte[] to_enc = new byte[block_size];
            for (int j = 0; j < to_enc.length; j++) {
                if (i + j < array.length)
                    to_enc[j] = array[i + j];
                else
                    to_enc[j] = 0;
            }
            // Если это первый блок, то XOR c init
            if (i == 0) {
                to_enc = XOR.xor_cbc(to_enc, init);
            } else {
                to_enc = XOR.xor_cbc(to_enc, Ei);
            }
            Ei = encrypt_with_chosen_cipher(true, key, to_enc, cipher);
            cipher += 1;
            for (int j = 0; j < Ei.length; j++)
                list.add(Ei[j]);
        }

        byte[] result = new byte[list.size()];
        for (int i = 0; i < result.length; i+=1)
            result[i] = list.get(i);
        return result;
    }


    public static byte[] decrypt_v2 (String key, byte[] array, int block_size) {
        ArrayList<Byte> list = new ArrayList<>();
        byte[] Ci;
        byte[] Ci_1 = new byte[block_size];
        // Инициализируем Ci-1 первым блоком
        for (int i = 0; i < block_size; i++) {
            Ci_1[i] = array[i];
        }
        int cipher = 0;
        for (int i = 0; i < array.length; i+=block_size) {
            if (cipher > 5) cipher = 0;
            // Берём блок в 10 символов
            Ci = new byte[block_size];
            for (int j = 0; j < Ci.length; j++) {
                Ci[j] = array[i + j];
            }
            byte[] dec = encrypt_with_chosen_cipher(false, key, Ci, cipher);
            byte[] result;
            // Если это первый блок, то XOR c init
            if (i == 0) {
                result = XOR.xor_cbc(dec, init);
            }
            // Иначе XOR спредыдущим блоком
            else {
                result = XOR.xor_cbc(dec, Ci_1);
            }
            Ci_1 = Ci;
            cipher += 1;
            for (int j = 0; j < result.length; j++)
                list.add(result[j]);
        }
        byte[] result = new byte[list.size()];
        for (int i = 0; i < result.length; i+=1)
            result[i] = list.get(i);
        return result;
    }

    public static void main(String[] args) {
        String test = "привет";
        byte[] t = test.getBytes();
        String key = "key";

        byte[] enc = encrypt_v2(key, t, 10);
        byte[] dec = decrypt_v2(key, enc, 10);

        System.out.println();
        System.out.println(new String(dec));
////        String test = "привет";
////        byte[] bytes = test.getBytes();
////        splitter(bytes, 10);
////        byte[] result = concatenate();
////        System.out.println(new String(result));
//
//        String s = "привет";
//        String key = "key";
//        System.out.println("Шифруем: " + '\'' + s + '\'');
//        System.out.println("Ключ:" + '\'' + key + '\'');
//        System.out.println();
//
//        byte[] ch = s.getBytes();
//        System.out.println("Оригинал в байтах:");
//        for (int i = 0; i < ch.length; i++ ) {
//            System.out.print(ch[i] + "  ");
//        }
//        System.out.println("\n-------------\n");
//
//        byte[] enc = encrypt(key, ch);
//        System.out.println("Шифровка байтах:");
//        for (int i = 0; i < enc.length; i++ ) {
//            System.out.print(enc[i] + "  ");
//        }
//        System.out.println("\n-------------\n");
//
//        byte[] dec = decrypt(key, enc);
//        System.out.println("дешифровка в байтах");
//        for (int i = 0; i < dec.length; i++ ) {
//            System.out.print(dec[i] + "  ");
//        }
//        System.out.println("\n-------------\n");
//
//        System.out.println("Расшифровка текст:");
//        System.out.println(new String(dec));
    }
}
