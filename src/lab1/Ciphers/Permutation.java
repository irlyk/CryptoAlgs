package lab1.Ciphers;

public class Permutation {


    private static byte[] make_array_from_matrix(Byte[][] matrix){
        byte[] result = new byte[matrix.length * matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null) result[i * matrix[i].length + j] = matrix[i][j];
                else result[i * matrix[i].length + j] = (byte)'_';
            }
        }
        return result;
    }

    public static byte[] _permutation_encrypt(String key, byte[] array) {
        if (check_valid_key(key, array.length)){
            int col = key.length(), row = (int)Math.ceil((float)array.length/key.length());
            Byte[][] res_ = new Byte[row][col];
            int row_num = -1, column_num = 0;
            for (int i = 0; i < array.length; i++){
                if ((i % key.length()) == 0) {
                    row_num++;
                    column_num = 0;
                }
                res_[row_num][Character.getNumericValue(key.charAt(column_num++))] = array[i];
            }
            return make_array_from_matrix(res_);
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
            byte[] res_ = new byte[array.length];
            int key_position = 0, row = 0;
            for (int i = 0; i < array.length; i++) {
                if (key_position >= key.length()){
                    row += key.length();
                    key_position = 0;
                }
                res_[i] = array[Character.getNumericValue(key.charAt(key_position++)) + row];
            }
            return res_;
        } else {
            System.out.println("PERMUTATION: key is not valid");
            return null;
        }
    }
}
