package jun.prospring5.ch4;

import java.security.MessageDigest;

public class MessageDigester {

    private MessageDigest digest1;
    private MessageDigest digest2;

    public void setDigest1(MessageDigest digest1) {
        this.digest1 = digest1;
    }

    public void setDigest2(MessageDigest digest2) {
        this.digest2 = digest2;
    }

    public void digest(String msg) {
        System.out.println("Using digest1:");
        this.digest(msg, this.digest1);

        System.out.println("Using digest2:");
        this.digest(msg, this.digest2);
    }

    public static String byte2Hex(byte[] bytes) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {

            String t = Integer.toHexString(bytes[i] & 0XFF);

            if (t.length() == 1) {
                sb.append("0" + t);
            }
            else {
                sb.append(t);
            }

            if (i < bytes.length - 1) {
                sb.append(":");
            }
        }

        return sb.toString().toUpperCase();
    }

    private void digest(String msg, MessageDigest digest) {
        System.out.println("Using algorithm:" + digest.getAlgorithm());
        digest.reset();
        byte[] bytes = msg.getBytes();
        byte[] result = digest.digest(bytes);
        System.out.println(byte2Hex(result));
    }
}
