package com.chinmay.bloodglucoselogbook.di

import com.chinmay.bloodglucoselogbook.data.LogbookRepositoryImpl
import com.chinmay.bloodglucoselogbook.domain.LogbookRepository
import com.chinmay.bloodglucoselogbook.domain.LogbookUsecase
import com.chinmay.bloodglucoselogbook.ui.GlucoseMonitorViewmodel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factoryOf(::LogbookRepositoryImpl) { bind<LogbookRepository>() }
    factoryOf(::LogbookUsecase)
    viewModel { GlucoseMonitorViewmodel(get()) }
}
