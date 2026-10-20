package com.yktunnel.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {

    private val navy = Color.rgb(7, 16, 35)
    private val panel = Color.rgb(15, 29, 56)
    private val blue = Color.rgb(37, 132, 255)
    private val pale = Color.rgb(225, 235, 250)
    private val muted = Color.rgb(151, 171, 202)

    private lateinit var serverInput: EditText
    private lateinit var portInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var statusText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = navy
        window.navigationBarColor = navy
        window.decorView.systemUiVisibility = 0
        buildScreen()
    }

    private fun buildScreen() {
        val scroll = ScrollView(this).apply {
            setBackgroundColor(navy)
            isFillViewport = true
        }
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(dp(24), dp(28), dp(24), dp(24))
        }
        scroll.addView(root)
        setContentView(scroll)

        val logo = TextView(this).apply {
            text = "YK"
            textSize = 38f
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
            setBackgroundColor(blue)
            elevation = dp(5).toFloat()
        }
        root.addView(logo, LinearLayout.LayoutParams(dp(88), dp(88)).apply {
            bottomMargin = dp(14)
        })

        root.addView(TextView(this).apply {
            text = "YK TUNNEL"
            textSize = 28f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }, matchWrap().apply { bottomMargin = dp(6) })

        root.addView(TextView(this).apply {
            text = "Votre tunnel. Votre connexion."
            textSize = 14f
            setTextColor(muted)
            gravity = Gravity.CENTER
        }, matchWrap().apply { bottomMargin = dp(28) })

        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(18), dp(20), dp(18), dp(18))
            setBackgroundColor(panel)
        }
        root.addView(card, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ))

        addLabel(card, "ADRESSE DU SERVEUR")
        serverInput = addInput(card, "ex. vpn.exemple.com")
        addLabel(card, "PORT UDP")
        portInput = addInput(card, "5667").apply {
            setText("5667")
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
        }
        addLabel(card, "MOT DE PASSE")
        passwordInput = addInput(card, "Mot de passe", true)

        val connect = Button(this).apply {
            text = "CONNECTER"
            textSize = 15f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.WHITE)
            setBackgroundColor(blue)
            isAllCaps = false
            setOnClickListener { onConnectPressed() }
        }
        card.addView(connect, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, dp(52)
        ).apply { topMargin = dp(20) })

        val disconnect = Button(this).apply {
            text = "DÉCONNECTER"
            textSize = 14f
            setTextColor(pale)
            setBackgroundColor(Color.rgb(36, 51, 78))
            isAllCaps = false
            setOnClickListener {
                statusText.text = "Déconnecté"
                statusText.setTextColor(muted)
            }
        }
        card.addView(disconnect, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, dp(48)
        ).apply { topMargin = dp(10) })

        statusText = TextView(this).apply {
            text = "Interface prête — moteur ZIVPN à intégrer"
            textSize = 13f
            setTextColor(muted)
            gravity = Gravity.CENTER
        }
        root.addView(statusText, matchWrap().apply {
            topMargin = dp(22)
            bottomMargin = dp(8)
        })

        root.addView(TextView(this).apply {
            text = "Version de départ • Android"
            textSize = 12f
            setTextColor(Color.rgb(105, 127, 160))
            gravity = Gravity.CENTER
        }, matchWrap().apply { topMargin = dp(12) })
    }

    private fun onConnectPressed() {
        val host = serverInput.text.toString().trim()
        val portText = portInput.text.toString().trim()
        val password = passwordInput.text.toString()

        when {
            host.isEmpty() -> showStatus("Saisis l'adresse du serveur.", true)
            portText.isEmpty() -> showStatus("Saisis le port UDP.", true)
            portText.toIntOrNull() !in 1..65535 ->
                showStatus("Le port doit être compris entre 1 et 65535.", true)
            password.isBlank() -> showStatus("Saisis le mot de passe.", true)
            else -> showStatus(
                "Paramètres saisis. Le moteur client ZIVPN n'est pas encore intégré.",
                true
            )
        }
    }

    private fun showStatus(message: String, warning: Boolean) {
        statusText.text = message
        statusText.setTextColor(if (warning) Color.rgb(255, 190, 90) else muted)
    }

    private fun addLabel(parent: LinearLayout, label: String) {
        parent.addView(TextView(this).apply {
            text = label
            textSize = 11f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(muted)
        }, matchWrap().apply {
            topMargin = dp(8)
            bottomMargin = dp(7)
        })
    }

    private fun addInput(parent: LinearLayout, hintText: String, password: Boolean = false): EditText {
        val input = EditText(this).apply {
            hint = hintText
            textSize = 15f
            setTextColor(Color.WHITE)
            setHintTextColor(Color.rgb(117, 139, 171))
            setSingleLine(true)
            setPadding(dp(12), 0, dp(12), 0)
            background = android.graphics.drawable.GradientDrawable().apply {
                setColor(Color.rgb(8, 20, 41))
                setStroke(dp(1), Color.rgb(47, 70, 107))
                cornerRadius = dp(10).toFloat()
            }
            if (password) {
                inputType = android.text.InputType.TYPE_CLASS_TEXT or
                    android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                inputType = android.text.InputType.TYPE_CLASS_TEXT or
                    android.text.InputType.TYPE_TEXT_VARIATION_URI
            }
        }
        parent.addView(input, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, dp(50)
        ).apply { bottomMargin = dp(8) })
        return input
    }

    private fun matchWrap() = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
    )

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()
}
