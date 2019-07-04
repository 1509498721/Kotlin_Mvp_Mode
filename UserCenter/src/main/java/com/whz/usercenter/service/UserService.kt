package com.whz.usercenter.service
import com.whz.usercenter.data.bean.LoginBean
import io.reactivex.Observable
/**
 *Created by whz  on 2019-06-26
 */
interface UserService {
    fun loginService(mobile: String, password: String): Observable<LoginBean>
}