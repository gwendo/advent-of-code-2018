package se.radicalcode.aoc

import java.util.*

fun extractPair(it: String) : Pair<String,String> {
    return it
        .replace("Step ", "")
        .replace("must be finished before step", ",")
        .replace("can begin.", "")
        .split(",").chunked(2).map{Pair(it.first().trim(), it.last().trim())}.first()
}

data class StepDef(val step: String, val preReqs: List<String>?,val executionTime : Int? = 0)


 class Worker (val id: Int, var workStep: StepDef? = null, var elapsedTime: Int = 0) {
     fun isFree(): Boolean {
         return workStep == null
     }

     fun isBusy(): Boolean {
         return workStep != null
     }

    fun isWorkComplete(): Boolean {
        return workStep != null && elapsedTime == workStep!!.executionTime
    }
    fun collectWork(): StepDef {
        elapsedTime = 0
        val stepValue = workStep!!
        workStep = null;
        return stepValue
    }

    fun assignWork(newStep: StepDef) {
        workStep = newStep
    }
    fun tick() {
        elapsedTime++
    }

     override fun toString(): String {
         return "Worker: $id $workStep elapsedTime: $elapsedTime"
     }

 }

fun startWork(numberOfWorkers: Int, work: List<StepDef>): Int {
    var tick: Int = 0
    val workers = IntRange(1, numberOfWorkers).map { Worker(id=it) }
    var workList = work
    while((workList.isNotEmpty() || workers.any{ worker -> !worker.isFree() }) ) {
        workList = assignWorkToWorkers(workList, workers)
        tick++
        println(tick)
        work(workers)
        workers.filter{ worker -> worker.isBusy() && worker.isWorkComplete() }.forEach { workList = completeStep(it.collectWork(), workList)}
    }
    return tick
}
fun work(workers: List<Worker>) {
    workers.filter{ worker -> worker.isBusy() }.forEach { it.tick() }
}
fun assignWorkToWorkers(work: List<StepDef>, workers: List<Worker>): List<StepDef> {
    var availableWork = work.filter{it.preReqs.isNullOrEmpty()}
    println("available " + availableWork.joinToString { it.step })
    workers.filter{worker -> worker.isFree() }.zip(availableWork).forEach{ it.first.assignWork(it.second) }
    val assigned = workers.filter{ it.isBusy() }.map{it.workStep!!.step}
    println("assigned size: " + assigned.size)
    val newWorkList = work.filter{ !assigned.contains(it.step)}
    println("new work list: " + newWorkList.size)
    return newWorkList

}

fun completeStep(completedStep: StepDef, workList: List<StepDef>) : List<StepDef> {
    println("completed: " + completedStep.step)
    return workList.map{ stepDef -> StepDef(stepDef.step, stepDef.preReqs.orEmpty().filter{ it != completedStep.step}, executionTime = stepDef.executionTime) }
}

fun takeNext(resultString: String, stepDefs : List<StepDef>) : String {

    var rs = resultString
    if (stepDefs.isNotEmpty()) {
        var firstEmptyPreReq : StepDef? = stepDefs.find { it.preReqs.isNullOrEmpty() }
        rs += firstEmptyPreReq!!.step

        rs = takeNext(rs, stepDefs.filter{it.step != firstEmptyPreReq.step}.map{ stepDef -> StepDef(stepDef.step, stepDef.preReqs.orEmpty().filter { it != firstEmptyPreReq.step }, executionTime = stepDef.executionTime) })
    }
    return rs
}

fun generateStepCollection(stepPairs: List<Pair<String,String>>, executionTimeMap: Map<String, Int>, timeOffSet: Int = 0) : List<StepDef> {
    val preReqs =
        stepPairs
            .groupBy { it.second }
            .mapValues{ it.value }
            .mapValues{ entry -> entry.value.map{it.first}}



    var stepList = stepPairs.unzip().toList().flatten().distinct()

    return stepList.map{ StepDef(step = it, preReqs = preReqs.getOrDefault(it, Collections.emptyList()).sorted(), executionTime = executionTimeMap.getOrDefault(it, 0)
     + timeOffSet)}.sortedBy { it.step }
}

