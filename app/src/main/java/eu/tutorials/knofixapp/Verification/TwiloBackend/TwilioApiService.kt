//package eu.tutorials.knofixapp.Verification.TwiloBackend
//
//import retrofit2.Response
//import retrofit2.http.Field
//import retrofit2.http.FormUrlEncoded
//import retrofit2.http.Header
//import retrofit2.http.POST
//import retrofit2.http.Path
//
//interface TwilioService {
//    @FormUrlEncoded
//    @POST("v2/Services/{serviceSid}/Verifications")
//    suspend fun sendVerificationCode(
//        @Header("Authorization") auth: String,
//        @Path("serviceSid") serviceSid: String,
//        @Field("To") phoneNumber: String,
//        @Field("Channel") channel: String = "sms"
//    ): Response<VerificationResponse>
//
//    @FormUrlEncoded
//    @POST("v2/Services/{serviceSid}/VerificationCheck")
//    suspend fun verifyCode(
//        @Header("Authorization") auth: String,
//        @Path("serviceSid") serviceSid: String,
//        @Field("To") phoneNumber: String,
//        @Field("Code") code: String
//    ): Response<VerificationCheckResponse>
//}
//
//data class VerificationResponse(
//    val status: String,
//    val sid: String
//)
//
//data class VerificationCheckResponse(
//    val status: String,
//    val valid: Boolean
//)