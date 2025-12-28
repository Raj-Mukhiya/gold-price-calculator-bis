package com.goldcalculator.bis

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val df = DecimalFormat("#,##0.00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rate24 = findViewById<EditText>(R.id.etRate)
        val carat = findViewById<EditText>(R.id.etCarat)
        val weight = findViewById<EditText>(R.id.etWeight)
        val making = findViewById<EditText>(R.id.etMaking)
        val gst = findViewById<EditText>(R.id.etGST)
        val result = findViewById<TextView>(R.id.tvResult)

        findViewById<Button>(R.id.btnCalculate).setOnClickListener {

            val rate24k = rate24.text.toString().toDouble()
            val jewelleryCarat = carat.text.toString().toDouble()
            val grams = weight.text.toString().toDouble()
            val makingPct = making.text.toString().toDouble()
            val gstPct = gst.text.toString().toDouble()

            val ratePer10g = (rate24k / 24) * jewelleryCarat
            val ratePerGram = ratePer10g / 10
            val goldValue = ratePerGram * grams
            val makingCharge = goldValue * makingPct / 100
            val subTotal = goldValue + makingCharge
            val gstAmount = subTotal * gstPct / 100
            val totalWithGST = subTotal + gstAmount

            result.text = """
Gold Value: ₹${df.format(goldValue)}
Making Charges (${makingPct}%): ₹${df.format(makingCharge)}
Subtotal: ₹${df.format(subTotal)}
GST (${gstPct}%): ₹${df.format(gstAmount)}

Total Payable with GST: ₹${df.format(totalWithGST)}
Total Payable without GST: ₹${df.format(subTotal)}
            """.trimIndent()
        }
    }
}
