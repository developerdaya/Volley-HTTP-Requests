package com.developerdaya.volley_http_requests.ui
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.developerdaya.volley_http_requests.R
import com.developerdaya.volley_http_requests.model.EmployeeResponse
import com.developerdaya.volley_http_requests.network.GsonRequest
import com.google.gson.Gson
import com.google.gson.GsonBuilder


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchEmployees()
    }

    private fun fetchEmployees() {
        val url = "https://mocki.io/v1/1a44a28a-7c86-4738-8a03-1eafeffe38c8"
        val requestQueue = Volley.newRequestQueue(this)
        val gson: Gson = GsonBuilder().create()
        val gsonRequest = GsonRequest(
            Request.Method.GET,
            url,
            gson,
            EmployeeResponse::class.java,
            { response ->
                val employees = response.employees
                var textView = findViewById<TextView>(R.id.textView)
                textView.text = "${employees}"
                employees.forEach { employee ->
                    println("Name: ${employee.name}, Profile: ${employee.profile}")
                }
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(gsonRequest)
    }
}
