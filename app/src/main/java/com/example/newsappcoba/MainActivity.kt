package com.example.newsappcoba

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.newsappcoba.domain.usecase.AppEntryUseCase
import com.example.newsappcoba.presentation.onboarding.OnBoardingScreen
import com.example.newsappcoba.presentation.onboarding.OnBoardingViewModel
import com.example.newsappcoba.ui.theme.NewsAppCobaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appEntryUseCase: AppEntryUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        lifecycleScope.launch {
            appEntryUseCase.readAppEntry().collect(){
                Log.d("test",it.toString())
            }
        }
        setContent {
            NewsAppCobaTheme(
                dynamicColor = false
            ) {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)){
                    val viewModel :OnBoardingViewModel = hiltViewModel()
                    OnBoardingScreen(
                        event = viewModel::onEvent
                    )
                }
            }
        }
    }
}

