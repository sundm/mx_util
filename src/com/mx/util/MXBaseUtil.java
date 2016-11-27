package com.mx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class MXBaseUtil {
	private final static char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static final int ISO_PADDING_1 = 0;
	public static final int ISO_PADDING_2 = 1;

	public static String getVersion() {
		return "1.2.0316";
	}

	/**
	 * 大端表示的byte数组
	 * 
	 * @param
	 * @return
	 */
	public static byte[] intToBigBytes(int a) {
		return new byte[] { (byte) (0x000000ff & (a >>> 24)), (byte) (0x000000ff & (a >>> 16)),
				(byte) (0x000000ff & (a >>> 8)), (byte) (0x000000ff & (a)) };
	}

	public static String bytesToHexString(byte[] d, int s, int n) {

		if (d == null) {
			return null;
		}

		if (s < 0) {
			return null;
		}

		if (n < 1) {
			return null;
		}

		int len = d.length;
		if (len < (s + n)) {
			return null;
		}

		final char[] ret = new char[n * 2];
		final int e = s + n;
		int x = 0;

		for (int i = s; i < e; ++i) {

			final byte v = d[i];
			ret[x++] = HEX[0x0F & (v >> 4)];
			ret[x++] = HEX[0x0F & v];
		}

		return new String(ret);
	}

	public static byte[] byteToByteHex(byte[] data, short start, short length) {

		if (data == null) {
			return null;
		}

		if (start < 0) {
			return null;
		}

		if (length < 1) {
			return null;
		}

		int len = data.length;
		if (len < start + length) {
			return null;
		}

		int i;
		short temp;
		byte[] tempdata = new byte[length];

		for (i = 0; i < length; i++) {

			temp = data[start + i];
			if ((temp >= 0) && (temp <= 9)) {
				tempdata[i] = (byte) ((temp + 48) & 0xFF);
			} else if ((temp >= 10) && (temp <= 15)) {
				tempdata[i] = (byte) ((temp + 55) & 0xFF);
			} else {
				return null;
			}
		}

		return tempdata;
	}

	public static int parseInt(String txt, int radix, int def) {
		int ret;
		try {
			ret = Integer.valueOf(txt, radix);
		} catch (Exception e) {
			ret = def;
		}

		return ret;
	}

	public static String toHexString(byte[] d, int s, int n) {
		final char[] ret = new char[n * 2];
		final int e = s + n;

		int x = 0;
		for (int i = s; i < e; ++i) {
			final byte v = d[i];
			ret[x++] = HEX[0x0F & (v >> 4)];
			ret[x++] = HEX[0x0F & v];
		}
		return new String(ret);
	}

	public static String toHexStringR(byte[] d, int s, int n) {
		final char[] ret = new char[n * 2];

		int x = 0;
		for (int i = s + n - 1; i >= s; --i) {
			final byte v = d[i];
			ret[x++] = HEX[0x0F & (v >> 4)];
			ret[x++] = HEX[0x0F & v];
		}
		return new String(ret);
	}

	public static int toInt(byte[] b, int s, int n) {
		int ret = 0;

		final int e = s + n;
		for (int i = s; i < e; ++i) {
			ret <<= 8;
			ret |= b[i] & 0xFF;
		}
		return ret;
	}

	public static long toLong(byte[] b, int s, int n) {
		long ret = 0;
		final long e = s + n;
		for (int i = s; i < e; ++i) {
			ret <<= 8;
			ret |= b[i] & 0xFF;
		}
		return ret;
	}

	public static int toIntR(byte[] b, int s, int n) {
		int ret = 0;

		for (int i = s; (i >= 0 && n > 0); --i, --n) {
			ret <<= 8;
			ret |= b[i] & 0xFF;
		}
		return ret;
	}

	public static byte[] subBytes(byte[] byteSrc, int iStart, int iEnd) {
		byte[] byteRet = new byte[iEnd - iStart];
		for (int iPos = iStart; iPos < iEnd; iPos += 1) {
			byteRet[iPos - iStart] = byteSrc[iPos];
		}
		return byteRet;
	}

	public static String byte2hex(byte[] b, int len) {
		StringBuffer sb = new StringBuffer();
		String tmp = "";
		for (int i = 0; i < len; i++) {
			tmp = Integer.toHexString(b[i] & 0XFF);
			if (tmp.length() == 1) {
				sb.append("0" + tmp);
			} else {
				sb.append(tmp);
			}
		}
		String str = sb.toString();
		str = str.toUpperCase(Locale.CHINESE);
		return str;
	}

	public static String byte2hex(byte[] b) {
		return byte2hex(b, b.length);
	}

	public static byte[] hex2byte(String str) {
		if (str == null) {
			return null;
		}
		int len = str.length();

		if (len == 0) {
			return null;
		}
		if (len % 2 != 0) {
			throw new IllegalArgumentException("length error");
		}

		byte[] b = new byte[len / 2];
		for (int i = 0; i < str.length(); i += 2) {
			b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();
		}
		return b;
	}

	static public void arrayXOR(byte[] des, int desoffset, byte[] src, int srcoffset, int len) {
		int i;
		for (i = 0; i < len; i++) {
			des[desoffset + i] = (byte) (des[desoffset + i] ^ src[srcoffset + i]);
		}
	}

	static public byte[] arrayNOT(byte[] des, int len) {
		int i;
		byte[] b = new byte[len];
		for (i = 0; i < len; i++) {
			b[i] = (byte) (des[+i] ^ 0xFF);
		}

		return b;
	}

	static public int bytes2Int(byte[] bArray) {
		return bytes2Int(bArray, 16);
	}

	static public boolean convertIntToBoolean(int intValue) {
		return (intValue != 0);
	}

	static public int bytes2Int(byte[] bArray, int base) {
		return bytes2Int(bArray, bArray.length, base);
	}

	static public int bytes2Int(byte[] bArray, int iLen, int base) {
		String szBalance = byte2hex(bArray, iLen);
		int sum = Integer.valueOf(szBalance, base);
		return sum;
	}

	public static String tagTrace(short sTag, byte[] buffer, int iOffset, int iLen, String charsetName, boolean IsHex) {
		int iMove;
		int iTagValueLen;
		short sTempTag;

		while (iLen > 0) {
			sTempTag = sGetTag(buffer, iOffset);
			iMove = sGetTagLen(sTempTag);
			iOffset += iMove;

			if (sTempTag == (short) 0x70) {
				if (buffer[iOffset] == (byte) 0x81) {
					iLen = (short) (iLen - iMove - 2);
					iOffset += (short) 2;
				} else {
					iLen = (short) (iLen - iMove - 1);
					iOffset++;
				}
				continue;
			}

			byte byTemp = buffer[iOffset];
			if (byTemp == (byte) 0x81) {
				iTagValueLen = (short) (buffer[++iOffset] & 0x00ff);
				iOffset++;
			} else {
				iTagValueLen = (short) (byTemp & 0x00ff);
				iOffset++;
			}

			if (sTempTag == sTag) {

				try {
					String sztemp = null;
					if (IsHex) {
						sztemp = String.format("%02X", buffer[iOffset]);
						for (int iT = 1; iT < iTagValueLen; iT++) {
							sztemp = sztemp.concat(String.format("%02X", buffer[iOffset + iT]));

						}

					} else {
						sztemp = new String(copyOfRange(buffer, iOffset, iOffset + iTagValueLen), charsetName);
					}
					return sztemp;
				} catch (Exception e) {
					return null;
				}
			}

			iMove = (short) (iTagValueLen + 1 + iMove);
			iOffset = (short) (iTagValueLen + iOffset);
			if (iTagValueLen >= 128) {
				iMove++;
				iOffset++;
			}
			iLen = (short) (iLen - iMove);
		}

		return null;
	}

	static short sGetTag(byte[] buffer, int iOffset) {
		short sTag;
		if ((byte) (buffer[iOffset] & (byte) 0x1F) == (byte) 0x1F) {
			sTag = (short) toInt(buffer, iOffset, 2);
		} else {
			sTag = (short) buffer[iOffset];
		}
		return sTag;
	}

	static short sGetTagLen(short sTag) {
		if (((short) (sTag & (short) 0xff00)) != (short) 0)
			return (short) 2;
		return (short) 1;
	}

	public static int[] copyOf(int[] original, int newLength) {
		int[] copy = new int[newLength];
		System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
		return copy;
	}

	public static byte[] copyOfRange(byte[] original, int from, int to) {
		int newLength = to - from;
		if (newLength < 0)
			throw new IllegalArgumentException(from + " > " + to);
		byte[] copy = new byte[newLength];
		System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
		return copy;
	}

	/**
	 * @author gf
	 * @see 用于将string数组转化成对应的ascii码之后再转化成string，例如 入参“CMD”则输出为“434D44”
	 *      43是"C"的ascii表示
	 */
	public static String stringToAsciiString(String value) {
		StringBuffer sbu = new StringBuffer();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			sbu.append(Integer.toHexString(chars[i]));
		}
		return sbu.toString();
	}

	public static String stringMoneyTrans(String money, int base) {
		long nNum = Long.valueOf(money, base);
		if (nNum > 100000) {
			return "0.00元";
		}
		String szData;
		if (nNum >= 100) {
			szData = String.valueOf(nNum / 100) + '.';
			long nCent = nNum % 100;
			if (nCent >= 10) {
				szData = szData + String.valueOf(nCent);
			} else {
				szData = szData + '0' + String.valueOf(nCent);
			}

		} else if (nNum >= 10) {
			szData = "0." + String.valueOf(nNum);
		} else {
			szData = "0.0" + String.valueOf(nNum);
		}
		return szData + "元";
	}

	public static String toHexMoneyString(String money) {
		String szDataString = String.valueOf(Long.valueOf(money) * 100);

		for (int i = 0; i < 8 - szDataString.length(); i++) {
			szDataString = "0" + szDataString;
		}

		return szDataString;
	}

	public static String toMoneyString(String money, int bitLength) {
		String szDataString = String.valueOf(Long.valueOf(money) * 100);

		for (int i = 0; i < bitLength - szDataString.length(); i++) {
			szDataString = "0" + szDataString;
		}

		return szDataString;
	}

	public static String hex2Ascii(byte[] data) {
		StringBuilder szBuilder = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			char hexc = Character.valueOf((char) (data[i]));
			szBuilder.append(hexc);
		}
		return szBuilder.toString();
	}

	public static JSONObject map2Json(Map<String, Object> map) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			jsonObject.put(entry.getKey(), entry.getValue());
		}
		return jsonObject;
	}

	public static Map<String, Object> json2Map(String jsonText) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonText);
		Map<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Iterator<String> keys = jsonObject.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			map.put(key, jsonObject.get(key));
		}
		return map;
	}

	public static List<String> parseBerTLString(String tlString) throws Exception {
		return parseBerTLString(hex2byte(tlString));
	}

	public static List<String> parseBerTLString(byte[] tlString) throws Exception {
		List<String> list = new ArrayList<String>();
		while (tlString.length > 0) {
			int nOffset = 0;
			byte[] bTag = MXBaseUtil.subBytes(tlString, nOffset, nOffset + 1);
			nOffset++;
			if ((MXBaseUtil.bytes2Int(bTag) & 0x1F) == 0x1F) {
				nOffset++;
			}
			bTag = MXBaseUtil.subBytes(tlString, 0, nOffset);
			int nLen = MXBaseUtil.bytes2Int(MXBaseUtil.subBytes(tlString, nOffset, nOffset + 1));
			nOffset++;
			if (nLen > 128) {
				int nLenLen = nLen - 128;
				byte[] lendata = MXBaseUtil.subBytes(tlString, nOffset, nOffset + nLenLen);
				nLen = MXBaseUtil.bytes2Int(lendata);
				nOffset += nLenLen;
			}
			list.add(byte2hex(bTag));

			tlString = MXBaseUtil.subBytes(tlString, nOffset, tlString.length);
		}
		return list;
	}

	public static Map<String, String> parseBerTlv(String tlvData) throws Exception {
		return parseBerTlv(hex2byte(tlvData));
	}

	public static Map<String, String> parseBerTlv(byte[] tlvData) throws Exception {
		Map<String, String> mapResultMap = new HashMap<String, String>();

		while (tlvData.length > 0) {
			int nOffset = 0;
			byte[] bTag = MXBaseUtil.subBytes(tlvData, nOffset, nOffset + 1);
			nOffset++;
			boolean bRecursion = MXBaseUtil.convertIntToBoolean((MXBaseUtil.bytes2Int(bTag) & 0x20));
			if ((MXBaseUtil.bytes2Int(bTag) & 0x1F) == 0x1F) {
				nOffset++;
			}
			bTag = MXBaseUtil.subBytes(tlvData, 0, nOffset);
			int nLen = MXBaseUtil.bytes2Int(MXBaseUtil.subBytes(tlvData, nOffset, nOffset + 1));
			nOffset++;
			if (nLen > 128) {
				int nLenLen = nLen - 128;
				byte[] lendata = MXBaseUtil.subBytes(tlvData, nOffset, nOffset + nLenLen);
				nLen = MXBaseUtil.bytes2Int(lendata);
				nOffset += nLenLen;
			}
			byte[] bValue = MXBaseUtil.subBytes(tlvData, nOffset, nOffset + nLen);

			nOffset += nLen;

			if (bRecursion) {
				mapResultMap.putAll(parseBerTlv(bValue));
			} else {
				String strDicKey = MXBaseUtil.toHexString(bTag, 0, bTag.length);
				String strDicVal = MXBaseUtil.toHexString(bValue, 0, bValue.length);
				mapResultMap.put(strDicKey, strDicVal);
			}
			tlvData = MXBaseUtil.subBytes(tlvData, nOffset, tlvData.length);
		}
		return mapResultMap;
	}
}
