package ru.sicampus.bootcamp2026.domain.usecases.auth

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import ru.sicampus.bootcamp2026.domain.entities.User
import ru.sicampus.bootcamp2026.domain.entities.auth.UserAuth
import ru.sicampus.bootcamp2026.domain.usecases.auth.AuthByCredentialsUseCase
import ru.sicampus.bootcamp2026.testdoubles.FakeAuthRepository

class AuthByCredentialsUseCaseTest {
    fun fakeUserAuth(
        id: Long = 1L,
        email: String = "test@example.com",
        firstName: String = "John",
        lastName: String = "Doe"
    ): UserAuth {
        return UserAuth(
            user = User(
                id = id,
                email = email,
                firstName = firstName,
                lastName = lastName
            )
        )
    }

    @Test
    fun `returns success if repo success`() = runTest {
        val repo = FakeAuthRepository().apply {
            checkAndAuthResult = Result.success(fakeUserAuth())
        }
        val useCase = AuthByCredentialsUseCase(repo)
        val result = useCase("login", "passwd")
        assertTrue(result.isSuccess)
    }
}