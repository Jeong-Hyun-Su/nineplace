package com.side.project.common.annotation.lock

import com.side.project.common.aop.AopTransaction
import com.side.project.common.library.CustomSpringELParser
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Aspect
@Component
class DistributedLockAop(
    private val redissonClient: RedissonClient,
    private val aopTransaction: AopTransaction
) {
    @Around("@annotation(com.side.project.common.annotation.lock.DistributedLock)")
    fun distributedLock(joinPoint: ProceedingJoinPoint): Any {
        val signature: MethodSignature = joinPoint.signature as MethodSignature
        val method: Method = signature.method
        val distributedLock: DistributedLock = method.getAnnotation(DistributedLock::class.java)

        val key: String = "LOCK:${CustomSpringELParser.getDynamicValue(signature.parameterNames, joinPoint.args, distributedLock.key)}"
        val rLock: RLock = redissonClient.getLock(key)

        try {
            val available: Boolean = rLock.tryLock(distributedLock.waitTime, distributedLock.leaseTime, distributedLock.timeUnit);
            if(!available) return false

            return aopTransaction.proceed(joinPoint)
        } catch (e: InterruptedException) {
            throw InterruptedException();
        } finally {
            try {
                rLock.unlock();   // (4)
            } catch (e: IllegalMonitorStateException) {
            }
        }
    }


}