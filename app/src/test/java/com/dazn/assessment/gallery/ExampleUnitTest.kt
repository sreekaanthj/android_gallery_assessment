package com.dazn.assessment.gallery

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun dateSorting() {
        val testData = listOf(
            "2022-01-02",
            "2022-01-04",
            "2022-01-07",
            "2022-01-06",
            "2022-01-01",
            "2022-01-03"
        )

        val sortedData = testData.sortedDescending()

        assertEquals("2022-01-07", sortedData[0])
    }
}