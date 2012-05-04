package scala.tools.eclipse.scalatest.ui

import org.eclipse.debug.core.ILaunch
import scala.tools.eclipse.ScalaProject

class ScalaTestRunSession(val fLaunch: ILaunch, val fRunName: String, val projectName: String) {
  
  var startedCount = 0
  var succeedCount = 0
  var failureCount = 0
  var ignoredCount = 0
  var pendingCount = 0
  var canceledCount = 0
  var totalCount = 0
  var suiteCount = 0
  var suiteAbortedCount = 0
  
  var rootNode: RunModel = null
  
  private var running = false
  private var userStop = false
  
  def run() {
    running = true
    userStop = false
  }
  
  def done() {
    running = false
  }
  
  def stop() {
    fLaunch.terminate()
    running = false
    userStop = true
  }
  
  def isStopped = userStop 
  
  def isRunning = running
}