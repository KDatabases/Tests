package com.sxtanna.database.tests

import com.sxtanna.database.base.Database
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

abstract class DatabaseTest<D : Database<*, *, *>> {

	lateinit var database : D


	/**
	 * After create is called, the function [Database.enable] is called
	 */
	@BeforeEach
	internal fun setUp() {
		val time = measureTimeMillis {
			database = create().apply { enable() }
		}
		println("Enabling database took $time ms")
	}

	@Test
	internal fun run() {
		val time = measureTimeMillis {
			runTest()
		}
		println("Running test took $time ms")
	}

	/**
	 * After the test runs, the function [Database.disable] is called
	 */
	@AfterEach
	internal fun tearDown() {
		val time = measureTimeMillis {
			database.disable()
		}
		println("Disabling database took $time ms")
	}


	abstract fun create() : D

	abstract fun runTest()

}