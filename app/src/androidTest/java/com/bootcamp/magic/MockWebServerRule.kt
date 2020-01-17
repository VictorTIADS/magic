package com.bootcamp.magic

import com.bootcamp.magic.network.RetrofitConfig.Companion.BASE_URL
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule : TestWatcher(){

    val mockWebServer : MockWebServer by lazy {
        MockWebServer()
    }

    override fun starting(description: Description?) {
        super.starting(description)
        mockWebServer.start()
        BASE_URL = mockWebServer.url("/v1/").toString()
    }

    override fun finished(description: Description?) {
        super.finished(description)
        mockWebServer.shutdown()
    }
}