package com.example.algorism

import org.junit.Test

import org.junit.Assert.*
import java.util.regex.Pattern
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

    }

//    @Test
//    fun aaa(){
//
//
//        val a = "aello"
//        val b = a.toMutableList()
//
//        var c = b[0].toDouble()
//        c +=
//
//        println('a'.toInt() - b[0].toInt())
//        println('a' - b[0])
//        println(b.joinToString (""))
//
//        for ( a in aaa )
//    }

    @Test
    fun bbb(){
        val text = "GRPID=6557cd61-ef3e-42b1-9f01-6635c765517f;PGRPID=29993a3e-7e9a-4f7a-b4d2-617799bd222a;GRPNAME=sda;AGENT_COUNT=0;AGENT_ONLINE_COUNT=0;AGENT_GROUP_COUNT=0;"

        val GRPNAME = "aa"
        val agentGroupInfo = AgentGroupInfo(text)
        println(agentGroupInfo.pgrpId)
    }

}

data class AgentGroup(
    val name: String,
    val declaredType: String,
    val scope: String,
    val value: String,
    val nil: Boolean,
    val globalScope: Boolean,
    val typeSubstituted: Boolean,
    val agentGroupInfo: AgentGroupInfo = AgentGroupInfo(value)
)

class AgentGroupInfo(private val dataString : String){
    private val KEY_GRPID = "GRPID"
    private val PGRPID = "PGRPID"
    private val GRPNAME = "GRPNAME"
    private val AGENT_COUNT = "AGENT_COUNT"
    private val AGENT_ONLINE_COUNT = "AGENT_ONLINE_COUNT"

    val grpId : String = findValue(KEY_GRPID)
    val pgrpId : String = findValue(PGRPID)
    val grpName : String = findValue(GRPNAME)
    val agentCount : String = findValue(AGENT_COUNT)
    val agentOnlineCount : String = findValue(AGENT_ONLINE_COUNT)

    private fun findValue(key : String) : String {
        return Pattern
            .compile("$key=(.+?);")
            .matcher(dataString)
            .apply { find() }
            .run {
                group(1) ?: ""
            }
    }
}