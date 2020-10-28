package lab1.Objects;

public class ByteText {

    private byte[] byte_text;

    public ByteText (){ }

    public byte[] getByte_text() {
        return byte_text;
    }

    public void setByte_text(byte[] byte_text) {
        this.byte_text = byte_text;
    }

    @Override
    public String toString(){
        return new String(byte_text);
    }
}
