package com.side.project.global.common.library

import org.springframework.expression.ExpressionParser
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext

class CustomSpringELParser {
    companion object {
        fun getDynamicValue(parameterNames: Array<String>, args: Array<Any>, key: String): Any? {
            val parser: ExpressionParser = SpelExpressionParser()
            val context: StandardEvaluationContext = StandardEvaluationContext()

            parameterNames.forEachIndexed { index, s -> context.setVariable(s, args[index]) }

            return parser.parseExpression(key).getValue(context)
        }
    }
}