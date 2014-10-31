package matrix.util;

public class Conversores {

	public static byte[] converteASCIIParaByteArray(String s, boolean padding) {

		byte[] b = new byte[s.length()];

		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) s.charAt(i);
		}
		return b;

	}

	public static String converteByteArrayToHexString(byte[] b) {

		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;

	}

	public static byte[] converteHexStringToByteArray(String s) {

		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

}
