package com.tina.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * @author yxc
 * @date 2018/9/16
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class PreComponentScope