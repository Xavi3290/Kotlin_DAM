package com.example.activitat10

import java.util.logging.Logger
import java.security.NoSuchAlgorithmException
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.logging.Level


class Sha {
    companion object {
        private fun bytesToHex(hash: ByteArray?): String {
            val hexString = StringBuilder(2 * hash!!.size)
            for (i in hash.indices) {
                val hex = Integer.toHexString(0xff and hash[i].toInt())
                if (hex.length == 1) {
                    hexString.append('0')
                }
                hexString.append(hex)
            }
            return hexString.toString()
        }

        fun calculateSHA(originalString: String): String {
            var encodedhash: ByteArray? = null
            try {
                val digest = MessageDigest.getInstance("SHA-256")
                encodedhash = digest.digest(
                    originalString.toByteArray(StandardCharsets.UTF_8)
                )
            } catch (ex: NoSuchAlgorithmException) {
                Logger.getLogger(Sha::class.java.name).log(Level.SEVERE, null, ex)
            }
            return bytesToHex(encodedhash)
        }
    }
}
