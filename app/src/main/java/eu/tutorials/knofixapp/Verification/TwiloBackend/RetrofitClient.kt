//package eu.tutorials.knofixapp.Verification.TwiloBackend
//
//import android.util.Base64
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object RetrofitClient {
//    private const val BASE_URL = "https://verify.twilio.com/"
//    private const val ACCOUNT_SID = "your_account_sid"
//    private const val AUTH_TOKEN = "your_auth_token"
//
//    private val auth = "Basic " +
//            Base64.encodeToString("$ACCOUNT_SID:$AUTH_TOKEN".toByteArray(), Base64.NO_WRAP)
//
//    private val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor { chain ->
//            val original = chain.request()
//            val requestBuilder = original.newBuilder()
//                .header("Authorization", auth)
//                .method(original.method, original.body)
//            val request = requestBuilder.build()
//            chain.proceed(request)
//        }
//        .build()
//
//    val instance: TwilioApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(TwilioApiService::class.java)
//    }
//}