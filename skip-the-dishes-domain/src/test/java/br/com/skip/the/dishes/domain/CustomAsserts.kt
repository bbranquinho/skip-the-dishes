package br.com.skip.the.dishes.domain

import kotlin.test.assertTrue

fun <E : Throwable, B> B.assertException(expectedException: Class<E>, expectedMessage: String, block: B.() -> Any) =
        try {
            block()
        } catch (e: Throwable) {
            assertTrue(
                    "The expected exception is [${expectedException.canonicalName}], but throws [${e.javaClass.canonicalName}].",
                    { expectedException == e.javaClass }
            )
            assertTrue(
                    "The expected message is [${expectedMessage}], but throws the message [${e.message}].",
                    { expectedMessage == e.message }
            )
        }
