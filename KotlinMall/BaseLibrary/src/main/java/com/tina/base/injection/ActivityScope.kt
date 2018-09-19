package com.tina.base.injection

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * @author yxc
 * @date 2018/9/16
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityScope