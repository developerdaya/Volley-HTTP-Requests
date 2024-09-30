package com.developerdaya.volley_http_requests.network
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.Gson
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class GsonRequest<T>(
    method: Int,
    url: String,
    private val gson: Gson,
    private val clazz: Class<T>,
    listener: Response.Listener<T>,
    errorListener: Response.ErrorListener
) : Request<T>(method, url, errorListener) {

    private val listener: Response.Listener<T> = listener

    override fun parseNetworkResponse(response: NetworkResponse): Response<T> {
        return try {
            val jsonString = String(response.data, charset(HttpHeaderParser.parseCharset(response.headers)))
            val parsedObject = gson.fromJson(jsonString, clazz)
            Response.success(parsedObject, HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: JsonParseException) {
            Response.error(VolleyError(response))
        }
    }

    override fun deliverResponse(response: T) {
        listener.onResponse(response)
    }

    override fun deliverError(error: VolleyError) {
        super.deliverError(error)
    }
}
