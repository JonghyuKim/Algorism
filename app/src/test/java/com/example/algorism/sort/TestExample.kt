package com.example.algorism.sort

import org.junit.Test

class TestExample{

    @Test
    fun test(){
        stringRevers("hello1")
        changeString("aaabbbccc", "bb", "kk")
    }

    fun stringRevers(arg : String){
        val chars = arg.toCharArray()
        val result = arg.toCharArray()
        val lastIndex = arg.lastIndex

        for(index in lastIndex downTo 0){
            result[lastIndex - index] = chars[index]
        }

        println(result)
    }

    fun changeString(target : String, find : String, change : String){
        val chars = target.toCharArray()

        val finds = find.toCharArray()

        var findValue = 0

        finds.forEach {
            findValue += (it - 'a')
        }

        val lastIndex = target.lastIndex - find.length

        var sumValue = 0

        for(value in find.indices){
            sumValue  += (target[value] - 'a')
        }

        for(index in 0 until lastIndex){
            if(findValue == sumValue){

            }
            else{
                sumValue = sumValue - target[index] + target[index + find.length]
            }
        }

        //타겟으로부터 맞는 문자열의 처음 인덱스와 마지막 인덱스를 찾음
        // - 문자열찾는 방법은 문자 -> 숫자로 변환 후 다 더한 후 숫자를 하나씩 더하고 빼는 순서대로 비교
        // - 숫자가 일치하면 한번더 문자열이 같은지 비교
        //찾은 문자열을 기준으로 문자열을 나눔
        //나눈 문자열 사이에 바꿀 문자열 삽입


        var result : String = target.replace(find,change)

        println(result)

        result = target.split(find).let {
            if(it.size > 1){
                StringBuilder(target)
                    .apply {
                        append(it[0])
                        append(change)
                        append(it[1])
                    }.toString()
            }
            else it[0]
        }

        println(result)

        result = target
            .indexOf(find)
            .let {indexStart -> indexStart.rangeTo(indexStart + find.length)}
            .let {range ->
                StringBuilder(target)
                    .apply {
                        delete(range.first, range.last)
                        insert(range.first, change)
                    }.toString()
            }

        println(result)
    }
}