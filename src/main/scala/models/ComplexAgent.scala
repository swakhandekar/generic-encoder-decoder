package models

sealed trait InfectionState
case object Susceptible extends InfectionState
case object Infected extends InfectionState
case object Recovered extends InfectionState

case class ComplexAgent(name: String, age: Int, infectionState: InfectionState)
