package eu.appcom.rubhackathon.annotations

import java.lang.annotation.Inherited

/**
 * author nilsdruyen
 * date 2018-12-14
 */

@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Inherited
@Retention(AnnotationRetention.RUNTIME)
annotation class Title(val value: Int)