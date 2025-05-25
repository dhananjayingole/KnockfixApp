//package eu.tutorials.knofixapp.Verification.TwiloBackend
//
//import com.google.android.datatransport.runtime.dagger.Module
//import com.google.android.datatransport.runtime.dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Singleton
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//    @Provides
//    @Singleton
//    fun provideTwilioService(): TwilioService {
//        return Retrofit.Builder()
//            .baseUrl("https://verify.twilio.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(TwilioService::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideTwilioRepository(service: TwilioService): TwilioRepository {
//        return TwilioRepository(service)
//    }
//}