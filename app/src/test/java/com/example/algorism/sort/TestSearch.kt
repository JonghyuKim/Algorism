package com.example.algorism.sort

import org.junit.Assert
import org.junit.Test
import kotlin.random.Random


class TestSearch {
    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2)
    }

    @Test
    fun searchTest(){
        val arraySize = 10
//        val targetArray = Array(arraySize){ Random.nextInt(arraySize)}
        val binaryTree = arrayOf(0,1,2,3,4,5,6)

        completeBinaryTreeDfsSearch(binaryTree)

        val graphMatrix = arrayOf(
            arrayOf(0,1,1,1,0,0,0,0,0,0),
            arrayOf(1,0,0,0,1,1,0,0,0,0),
            arrayOf(1,0,0,0,0,1,0,0,0,0),
            arrayOf(1,0,0,0,0,1,1,0,0,0),
            arrayOf(0,1,0,0,0,0,0,1,0,0),
            arrayOf(0,1,1,1,0,0,0,1,1,1),
            arrayOf(0,0,0,1,0,0,0,0,0,1),
            arrayOf(0,0,0,0,1,1,0,0,1,0),
            arrayOf(0,0,0,0,0,1,0,1,0,1),
            arrayOf(0,0,0,0,0,1,1,0,1,0)
        )

        matrixDfsSearch(graphMatrix)

//        val graphLinkedList = MutableList(5){ MutableList(0){0}}
//        graphLinkedList[0].apply {
//            add(1)
//            add(2)
//        }
//        graphLinkedList[1].apply {
//            add(3)
//            add(4)
//        }
//
//        linkedListDfsSearch(graphLinkedList)

    }

    fun completeBinaryTreeDfsSearch(targetArray : Array<Int>){

        val checkVisit = Array(targetArray.size){ false }

        fun search(rootIndex : Int, endIndex : Int){
            val left = rootIndex * 2 + 1
            val right = rootIndex * 2 + 2

            if(left <= endIndex && !checkVisit[left]){
                checkVisit[left] = true
                print("${targetArray[left]} ")

                search(left, endIndex)
            }
            else return

            if(right <= endIndex && !checkVisit[right]){
                checkVisit[right] = true
                print("${targetArray[right]} ")
                search(right, endIndex)
            }
            else return
        }

        print("${targetArray[0]} ")
        search(0, targetArray.size - 1)
    }

    fun matrixDfsSearch(graph: Array<Array<Int>>){

        val maxLength = graph.size
        val visited = Array<Boolean>(maxLength){false}

        fun search(index : Int){
            visited[index] = true

            println("search : $index")

            for(target in 0 until maxLength){
                if(graph[index][target] == 1 && !visited[target]) search(target)
            }
        }

        search(0)

    }

    fun linkedListDfsSearch(graph : List<List<Int>>){
        val checkVisit = Array(graph.size){false}
        fun search(startIndex : Int){

            print("$startIndex ")
            checkVisit[startIndex] = true

            for(index in 0 until graph[startIndex].size){
                val value = graph[startIndex][index]
                if(!checkVisit[value]){
                    search(value)
                }
            }
        }
        search(0)
    }

    inline fun MutableList<Int>.push(value : Int){
        add(value)
    }

    inline fun MutableList<Int>.pop() : Int{
        return get(size - 1)
    }
}