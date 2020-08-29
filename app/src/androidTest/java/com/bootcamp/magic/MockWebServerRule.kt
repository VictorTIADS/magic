package com.bootcamp.magic

import com.bootcamp.magic.network.RetrofitConfig.Companion.BASE_URL
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule : TestWatcher(){

    val mock : MockWebServer by lazy {
        MockWebServer()
    }

    override fun starting(description: Description?) {
        super.starting(description)
        mock.start()
        BASE_URL = mock.url("/v1/").toString()
    }

    override fun finished(description: Description?) {
        super.finished(description)
        mock.shutdown()
    }
}