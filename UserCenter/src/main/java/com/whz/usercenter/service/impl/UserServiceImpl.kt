package com.whz.usercenter.service.impl

import com.whz.base.ext.convert
import com.whz.usercenter.data.bean.LoginBean
import com.whz.usercenter.data.repository.UserRepository
import com.whz.usercenter.service.UserService
import io.reactivex.Observable
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-26
 */
class UserServiceImpl @Inject constructor() : UserService {
    @Inject
    lateinit var userRepository: UserRepository

    override fun loginService(mobile: String, password: String): Observable<LoginBean> {

        return userRepository.onLogin(mobile, password).convert()
    }
}