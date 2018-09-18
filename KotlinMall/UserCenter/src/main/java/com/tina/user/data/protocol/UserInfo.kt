package com.tina.user.data.protocol

/**
 * @author yxc
 * @date 2018/9/16
 * 用户实体类
 *
 */
data class UserInfo(val id: String,
                    val userInfo: String,
                    val userName: String,
                    val userGender:String,
                    val userMobile:String,
                    val userSign:String)