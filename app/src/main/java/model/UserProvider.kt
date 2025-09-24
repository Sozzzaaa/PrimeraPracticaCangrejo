package model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.security.MessageDigest

class UserProvider {
    companion object {
        lateinit var listU: List<UserModel>

        // Load users from JSON
        fun readUsersFromAssets(context: Context) {
            try {
                val json = context.assets.open("users.json")
                    .bufferedReader()
                    .use { it.readText() }
                val gson = Gson()
                val userType = object : TypeToken<List<UserModel>>() {}.type
                listU = gson.fromJson(json, userType)
            } catch (e: Exception) {
                e.printStackTrace()
                listU = emptyList() // prevent uninitialized crash
            }
        }

        // SHA-256 hash function
        fun sha256(input: String): String {
            val digest = MessageDigest.getInstance("SHA-256")
            val hashBytes = digest.digest(input.toByteArray(Charsets.UTF_8))
            return hashBytes.joinToString("") { "%02x".format(it) }
        }

        // Validate login
        fun validateEmailPw(email: String, pw: String): Boolean {
            val pwHash = sha256(pw)  // hash only the input
            listU.forEach { user ->
                if (user.email == email && user.passwd == pwHash) {
                    return true
                }
            }
            return false
        }
    }
}
