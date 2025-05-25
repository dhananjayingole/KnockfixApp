//package eu.tutorials.knofixapp.Verification.TwiloBackend
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class VerificationViewModel @Inject constructor(
//    private val repository: TwilioRepository
//) : ViewModel() {
//    private val _uiState = MutableStateFlow(VerificationUiState())
//    val uiState: StateFlow<VerificationUiState> = _uiState.asStateFlow()
//
//    fun sendVerificationCode(phoneNumber: String) {
//        viewModelScope.launch {
//            _uiState.value = _uiState.value.copy(
//                isLoading = true,
//                phoneNumber = phoneNumber,
//                error = null
//            )
//
//            repository.sendVerificationCode(phoneNumber).fold(
//                onSuccess = {
//                    _uiState.value = _uiState.value.copy(
//                        isLoading = false,
//                        otpSent = true
//                    )
//                },
//                onFailure = { e ->
//                    _uiState.value = _uiState.value.copy(
//                        isLoading = false,
//                        error = e.message ?: "Failed to send OTP"
//                    )
//                }
//            )
//        }
//    }
//
//    fun verifyCode(code: String) {
//        viewModelScope.launch {
//            _uiState.value = _uiState.value.copy(
//                isLoading = true,
//                error = null
//            )
//
//            repository.verifyCode(_uiState.value.phoneNumber, code).fold(
//                onSuccess = { response ->
//                    _uiState.value = _uiState.value.copy(
//                        isLoading = false,
//                        isVerified = response.valid,
//                        error = if (!response.valid) "Invalid verification code" else null
//                    )
//                },
//                onFailure = { e ->
//                    _uiState.value = _uiState.value.copy(
//                        isLoading = false,
//                        error = e.message ?: "Verification failed"
//                    )
//                }
//            )
//        }
//    }
//
//    fun resendVerificationCode() {
//        sendVerificationCode(_uiState.value.phoneNumber)
//    }
//}
//
//data class VerificationUiState(
//    val phoneNumber: String = "",
//    val isLoading: Boolean = false,
//    val otpSent: Boolean = false,
//    val isVerified: Boolean = false,
//    val error: String? = null
//)