package com.example.iqbal.karcis.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.iqbal.karcis.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private var mAuthTask: UserLoginTask? = null
    private lateinit var mSharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_signup.setOnClickListener { attemptLogin() }
    }

    private fun attemptLogin() {
        if (mAuthTask != null) {
            return
        }

        // Reset errors.
        input_name.error = null
        input_email.error = null
        input_password.error = null

        // Store values at the time of the login attempt.
        val nameStr = input_name.text.toString()
        val emailStr = input_email.text.toString()
        val passwordStr = input_password.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            input_password.error = getString(R.string.error_invalid_password)
            focusView = input_password
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailStr)) {
            input_email.error = getString(R.string.error_field_required)
            focusView = input_email
            cancel = true
        } else if (!isEmailValid(emailStr)) {
            input_email.error = getString(R.string.error_invalid_email)
            focusView = input_email
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            mAuthTask = UserLoginTask(emailStr, passwordStr)
            mAuthTask!!.execute(null as Void?)
        }
    }

    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        register_form.visibility = if (show) View.GONE else View.VISIBLE
        register_form.animate()
            .setDuration(shortAnimTime)
            .alpha((if (show) 0 else 1).toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    register_form.visibility = if (show) View.GONE else View.VISIBLE
                }
            })

        register_progress.visibility = if (show) View.VISIBLE else View.GONE
        register_progress.animate()
            .setDuration(shortAnimTime)
            .alpha((if (show) 1 else 0).toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    register_progress.visibility = if (show) View.VISIBLE else View.GONE
                }
            })
    }

    private fun isEmailValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 4
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    inner class UserLoginTask internal constructor(private val mEmail: String, private val mPassword: String) :
        AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                return false
            }

            return DUMMY_CREDENTIALS
                .map { it.split(":") }
                .firstOrNull { it[0] == mEmail }
                ?.let {
                    // Account exists, return true if the password matches.
                    it[1] == mPassword
                }
                ?: true
        }

        override fun onPostExecute(success: Boolean?) {
            mAuthTask = null
            showProgress(false)

            if (success!!) {
                mSharedPref = this@RegisterActivity.getPreferences(Context.MODE_PRIVATE)
                with (mSharedPref.edit()) {
                    putBoolean(getString(com.example.iqbal.karcis.R.string.login_state), true)
                    putString(getString(R.string.user_email), input_email.text.toString())
                    putString(getString(R.string.user_name), input_name.text.toString())
                    apply()
                }
                goToMain()
            } else {
                input_password.error = getString(R.string.error_incorrect_password)
                input_password.requestFocus()
            }
        }

        override fun onCancelled() {
            mAuthTask = null
            showProgress(false)
        }
    }

    private fun goToMain() {
        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        /**
         * A dummy authentication store containing known user names and passwords.
         * TODO: remove after connecting to a real authentication system.
         */
        private val DUMMY_CREDENTIALS = arrayOf("foo@example.com:hello", "bar@example.com:world")
    }
}
