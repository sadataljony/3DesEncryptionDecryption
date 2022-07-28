package com.sadataljony.app.android.tripledes.utils

import android.util.Log
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.Hex
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


class EncryptionDecryption {

    fun encryptUsing3DES(input: String, length: Int, encryptionKey: String): String? {
        try {
            val inputBytes = fillBytes(input, length)
            val key = fillBytes(encryptionKey, 24) // only support 16 or 24
            val cipher = Cipher.getInstance("DESede/ECB/NoPadding")
            cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(key, "DESede"))
            val encrypted = cipher.doFinal(inputBytes)
            val hexString: String = Hex.encodeHexString(encrypted)
            Log.e("Encrypt Result:", hexString.uppercase(Locale.getDefault()))
            return hexString.uppercase(Locale.getDefault())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun decryptUsing3DES(input: String, decryptionKey: String): String? {
        try {
            val key = fillBytes(decryptionKey, 24) // only support 16 or 24
            val cipher = Cipher.getInstance("DESede/ECB/NoPadding")
            cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(key, "DESede"))
            val encrypted = cipher.doFinal(Hex.decodeHex(input.toCharArray()))
            val hexString = String(encrypted)
            Log.e("Decrypt Result:", hexString)
            return hexString.replace("\u0000", "")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun fillBytes(key: String, length: Int): ByteArray {
        val keyBytes = ByteArray(length)
        var i = 0
        while (i < key.length && i < keyBytes.size) {
            keyBytes[i] = key[i].code.toByte()
            i++
        }
        return keyBytes
    }

}