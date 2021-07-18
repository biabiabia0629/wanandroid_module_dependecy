package com.biabiabia.wanandroid_dependency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.biabiabia.wanandroid_compose.dependency.Dependencies
import com.biabiabia.wanandroid_dependency.ui.theme.DependencyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DependencyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello ${Dependencies.androidx_core_core_ktx}!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DependencyTheme {
        Greeting("Android")
    }
}