package com.example.algorism.sort

import android.util.ArrayMap
import org.junit.Assert
import org.junit.Test
import kotlin.math.pow
import kotlin.random.Random


class TestSort {
    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2)
    }


    @Test
    fun sortTest(){
        val arraySize = 10
        val targetArray = arrayOf(1, 4, 10, 14, 7, 9, 3, 2, 8, 16)
//        val targetArray = Array(arraySize){ Random.nextInt(arraySize)}
//        val targetArray = arrayOf(5, 1, 2, 3, 4)
//        println("targetArray init result : ${targetArray.toList()}")
//        bubbleSort(targetArray.copyOf())
//        selectSort(targetArray.copyOf())
//        insertSort(targetArray.copyOf())
        quickSort(targetArray.copyOf())
        mergeSort(targetArray.copyOf())
        heapSort(targetArray.copyOf())
    }

    /**
     * 1. 처음부터 lastIndex(lastIndex - 1)까지 index, index+1 값을 비교
     * 2. index > index + 1 크면 교체함(오름차순)
     * 3. 비교가 끝나면 index++
     * 4. 1사이클 끝났을 때 교체가 진행되면 lastIndex-- 한 후 1번으로 다시 진행
     * 5. 교체할 것이 없을 때 까지 반복
     */
    fun bubbleSort(sortResult : Array<Int>){

        var startTime = System.currentTimeMillis()
        var lastIndex = sortResult.size - 1 //nextCheck -1
        var isSwap = false

        do{
            isSwap = false
            for(index in 0 until lastIndex--){
                //왼쪽게 더 크면 교체
                if(sortResult[index] > sortResult[index+1]){
                    sortResult.swap(index, index+1)
                    isSwap = true
                }
            }
        }while (isSwap)

//        println("bubbleSort Result : ${sortResult.toList()}")
        println("bubbleSort CheckTime : ${System.currentTimeMillis() - startTime}")
    }

    /**
     * 1. 첫번째 index를 기준으로 우측 값을을 확인해감
     * 2. index값 보다 작은 값이 있을 경우 save
     * 3. 모든값 확인 후 save가 있으면 index값과 save값 교체
     * 4. index++ 한 후 반복
     * 5. 교체할 것이 없을 때 까지 반복
     */
    fun selectSort(sortResult : Array<Int>){

        var startTime = System.currentTimeMillis()
        var saveIndex = 0

        for(startIndex in 0 until sortResult.size){

            saveIndex = startIndex
            for(diffIndex in startIndex until sortResult.size){
                if(sortResult[saveIndex] > sortResult[diffIndex]){
                    saveIndex = diffIndex
                }
            }
            if(saveIndex != startIndex){
                sortResult.swap(startIndex, saveIndex)
            }
        }

//        println("selectSort Result : ${sortResult.toList()}")
        println("selectSort CheckTime : ${System.currentTimeMillis() - startTime}")
    }

    /**
     * 1. 인덱스를 한칸 우측으로 이동한다.
     * 2. 좌측의 데이터가 나보다 작은지 찾는다.
     * 3. 좌측보다 작으면 바꾼다.
     * 3. 마지막 인덱스까지 1 , 2 를 반복한다.
     */

    fun insertSort(sortResult : Array<Int>){
        var startTime = System.currentTimeMillis()

        for(slotIndex in 0 until sortResult.size ){

            var checkIndex = slotIndex

            for(targetIndex in slotIndex - 1 downTo 0 ){
                if(sortResult[targetIndex] > sortResult[checkIndex]){
                    sortResult.swap(checkIndex, targetIndex)
                    checkIndex = targetIndex
                }
            }
        }

//        println("insertSort Result : ${sortResult.toList()}")
        println("insertSort CheckTime : ${System.currentTimeMillis() - startTime}")

    }

    /**
     * 1. 블럭내에 피벗값을 기준으로 우측으로 으로 전진하며 기준값보다 큰 값을 찾는다
     * 2. 블럭내에 피벗값을 기준으로 마지막부터 좌측으로 후진하며 기준값보다 작은 값을 찾는다.
     * 3. 만약 교차하지 않았다면 두 값을 바꿔준다.
     * 4. 만약 교차하였다면 피벗값과 후진한 값을 교체하고 피벗값을 기준으로 좌 , 우 블럭으로 나눈 후 두 블럭 다 1,2번을 탄다.
     */
    fun quickSort(sortResult: Array<Int>){

        var startTime = System.currentTimeMillis()
        fun quickSort(c : Array<Int>, startIndex : Int, endIndex : Int){
            if(startIndex >= endIndex) return

            var pivotIndex = startIndex
            var leftIndex = pivotIndex + 1
            var rightIndex = endIndex

            while(leftIndex <= rightIndex){

                while(leftIndex <= endIndex && sortResult[pivotIndex] >= sortResult[leftIndex]) leftIndex++
                while(pivotIndex < rightIndex && sortResult[pivotIndex] <= sortResult[rightIndex]) rightIndex--

                if(leftIndex < rightIndex) sortResult.swap(leftIndex, rightIndex)
            }

            sortResult.swap(rightIndex, pivotIndex)
            pivotIndex = rightIndex

            quickSort(sortResult, startIndex, pivotIndex - 1)

            quickSort(sortResult, pivotIndex + 1, endIndex)
        }

        quickSort(sortResult, 0, sortResult.size - 1)
//        println("quickSort Result : ${sortResult.toList()}")
        println("quickSort CheckTime : ${System.currentTimeMillis() - startTime}")
    }


    /**
     * 정렬할 블럭을 가운데를 기준으로 2분할 한다
     * 2분할 블럭들을 작은순서대로 머지블럭에 다시 조합한다.
     * 만약 조합중 한쪽 블럭이 다 소진되면 나중에 남은 블럭을 다 넣어준다.
     * 마지막으로 머지된 블럭을 원래 블럭과 교체해준다.
     */

    fun mergeSort(sortResult : Array<Int>){

        var startTime = System.currentTimeMillis()
        val mergeArray = sortResult.copyOf()
        fun merge(start : Int, middle : Int, end : Int){

            var leftIndex = start
            var rightINdex = middle + 1
            var currentIndex = leftIndex

            while(leftIndex <= middle && rightINdex <= end){

                if(sortResult[leftIndex] <= sortResult[rightINdex]){
                    mergeArray[currentIndex++] = sortResult[leftIndex++]
                }
                else{
                    mergeArray[currentIndex++] = sortResult[rightINdex++]
                }
            }

            for(index in leftIndex .. middle){
                mergeArray[currentIndex++] = sortResult[index]
            }

            for(index in rightINdex .. end){
                mergeArray[currentIndex++] = sortResult[index]
            }

            for(index in start .. end){
                sortResult[index] = mergeArray[index]
            }
        }

        fun mergeSort(start : Int, end : Int){

            if(start < end){
                val middle = (start + end) / 2
                if(middle != start && middle != end){
                    mergeSort(start , middle)
                    mergeSort(middle+1 , end)
                }

                    merge(start, middle, end)
            }
        }

        fun mergeSort2(start : Int, end : Int){
            //2개씩 비교
            //4개씩 비교
            //8개씩 비교
            //2*n 씩 비교
            var compareCount = 2

            var startIndex = 0
            var endIndex = 0

            while(compareCount < end){
                startIndex = start
                endIndex = startIndex + compareCount - 1

                while(endIndex <= end){
                    merge(startIndex , (startIndex + endIndex) / 2, endIndex)
                    startIndex += compareCount
                    endIndex += compareCount
                }

                if(endIndex < end){
                    merge(endIndex , (endIndex + end) / 2, end)
                }

                compareCount *= 2
            }

            if(compareCount < end){
                merge(compareCount , (compareCount + end) / 2, end)
            }
            merge(start , (start + end) / 2, end)
        }

        mergeSort(0, sortResult.size - 1)

//        println("mergeSort Result : ${sortResult.toList()}")
        println("mergeSort CheckTime : ${System.currentTimeMillis() - startTime}")
    }

    /**
     * 1. 힙트리를 생성한다.(힙트리는 부모가 자식노드들 보다 항상 큰 완전 이진 트리를 의미함)
     * 2. root와 가장 마지막 노드를 교체한다.
     * 3. 마지막 노드를 제외하고 다시 1, 2번을 반복한다.
     *
     */
    fun heapSort(sortResult : Array<Int>){

        var startTime = System.currentTimeMillis()

        fun changeHeapTree(targetIndex : Int, endIndex : Int){
            var root = targetIndex
            var left: Int
            var right: Int
            var compare : Int

            while(root < endIndex) {
                left = root * 2 + 1
                right = root * 2 + 2

                compare = root

                if (left <= endIndex && sortResult[compare] < sortResult[left]) {
                    compare = left
                }

                if (right <= endIndex && sortResult[compare] < sortResult[right]) {
                    compare = right
                }

                if(compare == root) break

                sortResult.swap(compare, root)

                root = compare
            }
        }

        fun createHeapTree(endIndex : Int){
            var root = endIndex / 2

            while (root >= 0) {
                changeHeapTree(root, endIndex)
                root--
            }
        }

        var lastIndex = sortResult.size - 1

        createHeapTree(lastIndex)
        sortResult.swap(lastIndex--, 0)

        for(index in lastIndex downTo 0){
            changeHeapTree(0, index)
            sortResult.swap(index, 0)
        }
//        println("heapSort Result: ${sortResult.toList()}")
        println("heapSort CheckTime : ${System.currentTimeMillis() - startTime}")
    }

    inline fun Array<Int>.swap(leftIndex: Int, rightIndex: Int){
        val temp = this[leftIndex]
        this[leftIndex] = this[rightIndex]
        this[rightIndex] = temp
    }
}
