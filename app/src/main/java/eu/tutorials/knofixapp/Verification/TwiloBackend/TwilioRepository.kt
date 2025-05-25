//package eu.tutorials.knofixapp.Verification.TwiloBackend
//
//import android.util.Base64
//import javax.inject.Inject
//
//class TwilioRepository @Inject constructor(private val twilioService: TwilioService) {
//    private val accountSid = "YOUR_ACCOUNT_SID"
//    private val authToken = "YOUR_AUTH_TOKEN"
//    private val serviceSid = "YOUR_VERIFY_SERVICE_SID"
//
//    private val authHeader = "Basic " +
//            Base64.encodeToString("$accountSid:$authToken".toByteArray(), Base64.NO_WRAP)
//
//    suspend fun sendVerificationCode(phoneNumber: String): Result<VerificationResponse> {
//        return try {
//            val response = twilioService.sendVerificationCode(authHeader, serviceSid, phoneNumber)
//            if (response.isSuccessful && response.body() != null) {
//                Result.success(response.body()!!)
//            } else {
//                Result.failure(Exception(response.errorBody()?.string()))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    suspend fun verifyCode(phoneNumber: String, code: String): Result<VerificationCheckResponse> {
//        return try {
//            val response = twilioService.verifyCode(authHeader, serviceSid, phoneNumber, code)
//            if (response.isSuccessful && response.body() != null) {
//                Result.success(response.body()!!)
//            } else {
//                Result.failure(Exception(response.errorBody()?.string()))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//}