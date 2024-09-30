# Volley-HTTP-Requests
To make a simple GET request using Volley in Android with Kotlin, follow these steps:

### Step 1: Add Volley Dependency

First, you need to add the Volley library dependency to your `build.gradle` file:

```groovy
implementation 'com.android.volley:volley:1.2.1'
```

### Step 2: Create Data Model

Create a data model class to represent the employee:

```kotlin
data class Employee(val name: String, val profile: String)

data class EmployeeResponse(val message: String, val employees: List<Employee>)
```

### Step 3: Create a Function to Make the GET Request

Now, create a function that makes a GET request to the provided API and retrieves the response.

```kotlin
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.GsonRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Call the function to make the API request
        fetchEmployees()
    }

    private fun fetchEmployees() {
        val url = "https://mocki.io/v1/1a44a28a-7c86-4738-8a03-1eafeffe38c8"

        // Create a request queue
        val requestQueue = Volley.newRequestQueue(this)

        // Create a Gson instance
        val gson: Gson = GsonBuilder().create()

        // Create a GsonRequest
        val gsonRequest = GsonRequest<EmployeeResponse>(
            Request.Method.GET,
            url,
            null,
            Response.Listener { response ->
                // Handle the response
                val employees = response.employees
                // For demonstration, let's print the employee names
                employees.forEach { employee ->
                    Toast.makeText(this, "Name: ${employee.name}, Profile: ${employee.profile}", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                // Handle the error
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            },
            EmployeeResponse::class.java
        )

        // Add the request to the queue
        requestQueue.add(gsonRequest)
    }
}
```

### Step 4: Create the Activity Layout

Here is a simple layout for your activity (`activity_main.xml`):

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Employee List"
        android:textSize="20sp"
        android:textStyle="bold"/>
</LinearLayout>
```

### Step 5: Add Internet Permission

Don’t forget to add the Internet permission in your `AndroidManifest.xml` file:

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

### Summary

In this example, the app will fetch the employee data from the provided API using Volley. When the response is received, it displays the employee names in a Toast message. You can modify this to display the data in a RecyclerView or any other UI component as needed. 

This setup gives you a basic implementation of making a GET request with Volley in Android using Kotlin.
