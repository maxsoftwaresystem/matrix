package matrix.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MyAES {

	private final String ALGORITIMO = "AES/CTR/NoPadding";
	private final String CHAVE = "140b41b22a29beb4061bda66b6747e14";
	private final String IV = "1234567890123456";

	private Key chaveAES;
	private IvParameterSpec ivps;

	public MyAES() {

		byte[] ivArray = Conversores.converteASCIIParaByteArray(this.IV, false);
		ivps = new IvParameterSpec(ivArray);

		byte[] chaveArray = Conversores.converteHexStringToByteArray(this.CHAVE);
		chaveAES = new SecretKeySpec(chaveArray, "AES");
	}

	public String getValorHexdecimal(String valor) {

		return Conversores.converteByteArrayToHexString(Conversores
				.converteASCIIParaByteArray(valor, true));

	}

	public String encriptar(String texto) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException {

		Cipher c = Cipher.getInstance(ALGORITIMO);
		c.init(Cipher.ENCRYPT_MODE, chaveAES, ivps);
		byte[] textoArray = Conversores.converteHexStringToByteArray(texto);
		byte[] mensagem = c.doFinal(textoArray);
		return Conversores.converteByteArrayToHexString(mensagem);
	}

	public String desencriptar(String texto) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException {

		Cipher c = Cipher.getInstance(ALGORITIMO);
		c.init(Cipher.DECRYPT_MODE, chaveAES, ivps);
		byte[] textoArray = Conversores.converteHexStringToByteArray(texto);
		byte[] mensagem = c.doFinal(textoArray);
		return Conversores.converteByteArrayToHexString(mensagem);
	}

}
