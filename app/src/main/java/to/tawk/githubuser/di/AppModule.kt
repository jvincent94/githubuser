package to.tawk.githubuser.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import to.tawk.githubuser.data.local.AppDatabase
import to.tawk.githubuser.data.local.UserDetailsDao
import to.tawk.githubuser.data.local.UsersDao
import to.tawk.githubuser.data.remote.UserRemoteDataSource
import to.tawk.githubuser.data.remote.UserService
import to.tawk.githubuser.data.repository.UserRepository
import javax.inject.Singleton

/**
 * Module used for dependencies
 */

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    //instance of retrofit that will be used across the entire app
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    // Gson instance
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    // UserService instance
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

    // RemoteDataSource instance
    @Singleton
    @Provides
    fun provideUserRemoteDataSource(userService: UserService) = UserRemoteDataSource(userService)

    // Room database instance
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    // DAO User instance
    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    // DAO User Details instance
    @Singleton
    @Provides
    fun provideUserDetailsDao(db: AppDatabase) = db.userDetailsDao()

    // Repository instance
    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: UserRemoteDataSource,
                          localDataSource: UsersDao,
                          localUserDetailsSource: UserDetailsDao) =
            UserRepository(remoteDataSource, localDataSource, localUserDetailsSource)
}