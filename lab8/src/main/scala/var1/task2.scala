package var1.task2

import scala.util.Random

object Robot:
  def run(): Unit = 
    val leftLeg = new Leg("LEFT")
    val rightLeg = new Leg("RIGHT")

    val leftThread = new Thread(new Runnable {
      override def run(): Unit = 
        leftLeg.walk()
    })

    val rightThread = new Thread(new Runnable {
      override def run(): Unit = 
        rightLeg.walk()
    })

    leftThread.start()
    rightThread.start()

    Thread.sleep(5000) // Allow 5 seconds for the robot to walk

    leftLeg.stop()
    rightLeg.stop()

class Leg(name: String):
  private var isWalking = true

  def walk(): Unit = 
    while (isWalking) {
      println(s"$name step")
      Thread.sleep(Random.nextInt(1000)) // Random delay up to 1 second
    }

  def stop(): Unit = 
    isWalking = false

def test(): Unit =
  Robot.run()