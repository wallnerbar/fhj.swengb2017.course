package at.fhj.swengb.apps.battleship

import org.scalatest.WordSpecLike

class BattleShipSpec extends WordSpecLike {

  val bp = BattlePos(0, 0)

  val validPositions: Set[BattlePos] = Set(BattlePos(0, 0),
    BattlePos(0, 1),
    BattlePos(0, 2),
    BattlePos(0, 3),
    BattlePos(0, 4),
  )

  val validxPos: Set[BattlePos] = (0 until 5).map(x => BattlePos(x, 0)).toSet
  val validyPos: Set[BattlePos] = (0 until 5).map(y => BattlePos(0, y)).toSet
  val illegalPos: Set[BattlePos] = Set(BattlePos(3, 1)) ++ validxPos.tail

  // TODO WP5.1 make all tests green
  "Battleship" should {

    "a correct battleship" in {

      val battleShip = BattleShip("a name",
        validPositions)

      assert(battleShip != null)
    }
    "a battleship without a name can clear the port" in {

      // intercept checks if an exception is thrown
      intercept[IllegalArgumentException] {
        val battleShip = BattleShip("",
          validPositions)
      }

    }
    "0 pos is illegal" in intercept[IllegalArgumentException](BattleShip("a", Set()))
    "1 pos is illegal" in intercept[IllegalArgumentException](BattleShip("b", Set(bp)))
    "2 pos is illegal" in intercept[IllegalArgumentException](BattleShip("c", Set(bp, bp)))
    "3 pos is illegal" in intercept[IllegalArgumentException](BattleShip("d", Set(bp, bp, bp)))
    "4 pos is illegal" in intercept[IllegalArgumentException](BattleShip("e", Set(bp, bp, bp, bp)))
    "6 pos is illegal" in intercept[IllegalArgumentException](BattleShip("f", Set(bp, bp, bp, bp, bp, bp)))


    "pos has to be horizonal" in {
      val abc = BattleShip("hallo welt", validxPos)
      assert(abc != null)
    }
    "pos has to be vertical" in {
      assert(BattleShip("def", validyPos) != null)
    }
    // illegalPos defines a ship which is not possible
    "pos is connected" in intercept[IllegalArgumentException](BattleShip("a b c", illegalPos))

  }
}
