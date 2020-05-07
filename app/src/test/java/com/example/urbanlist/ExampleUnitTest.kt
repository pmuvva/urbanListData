package com.example.urbanlist

import com.google.common.truth.Truth.assertThat
import org.junit.Test

import org.junit.Assert.*


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun retrofit_check(){
      val res = ApiClient.getClient.getList("test app")
        val response = res.execute()
        assertThat(response.code()).isEqualTo(200)
    }


}
