package com.example.edittextforcurrency

import com.example.edittextforcurrency.Utils.dataCheck
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Before
    fun setUp() {
        clearAllMocks()
        unmockkAll()
    }

    @Test
    fun testCheckData() {
        val mock:checkModel = mockk()
        val result = dataCheck(mock)
        every { mock } returns checkModel("","")
        assertEquals(result,false)
        every { mock } returns checkModel("seenu","")
        assertEquals(result,false)
        every { mock } returns checkModel("seenu","kakakak")
        assertEquals(result,false)
    }
}