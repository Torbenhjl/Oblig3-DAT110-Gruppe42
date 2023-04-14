package no.hvl.dat110.util;

/**
 * exercise/demo purpose in dat110
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

	public static BigInteger hashOf(String entity) {

		BigInteger hashint = null;

		// Task: Hash a given string using MD5 and return the result as a BigInteger.

		try {
			// create an instance of the MD5 message digest algorithm
			MessageDigest md = MessageDigest.getInstance("MD5");

			// compute the hash of the input 'entity'
			byte[] hash = md.digest(entity.getBytes());

			// convert the hash into hex format
			String hex = String.format("%032x", new BigInteger(1, hash));

			// convert the hex into BigInteger
			hashint = new BigInteger(hex, 16);
		} catch (NoSuchAlgorithmException e) {
			// handle exception if MD5 algorithm is not available
			e.printStackTrace();
		}

		return hashint;
	}

	public static BigInteger addressSize() {
		// Create an instance of the MD5 message digest algorithm
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		// Compute the hash value of an empty byte array
		byte[] emptyData = new byte[0];
		byte[] hashValue = md.digest(emptyData);

		// Compute the number of bits in the hash value
		int numBits = hashValue.length * 8;

		// Compute the address size = 2 ^ number of bits
		BigInteger addressSize = BigInteger.valueOf(2).pow(numBits);

		// Return the address size
		return addressSize;
	}

	public static int bitSize() {

		int digestlen = 0;

		// find the digest length
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			digestlen = md.getDigestLength();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return digestlen * 8;
	}

	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for (byte b : digest) {
			strbuilder.append(String.format("%02x", b & 0xff));
		}
		return strbuilder.toString();
	}

}
