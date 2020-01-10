package com.game.rockgameserver.rest

import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should return a list of games stats"
    request{
        method GET()
        url "/game/stats"
    }
    response {
        body([[
              id: $(p(positiveInt()), c(1)),
              rounds: $(p(positiveInt()), c(3)),
              playersNames: [
                  $(p(anyNonEmptyString()), c("Tom")),
                  $(p(anyNonEmptyString()), c("rocky"))
              ],
              stats: [
                  $(p(anyPositiveInt()),c(1)),
                  $(p(anyPositiveInt()),c(2)),
                  $(p(anyPositiveInt()),c(3)),
                  $(p(anyPositiveInt()),c(4)),
                  $(p(anyPositiveInt()),c(5))]
            ],[
                id: $(p(positiveInt()), c(2)),
                rounds: $(p(positiveInt()), c(3)),
                playersNames: [
                    $(p(anyNonEmptyString()), c("TomA")),
                    $(p(anyNonEmptyString()), c("rockyA"))
                ],
                stats: [
                    $(p(anyPositiveInt()),c(10)),
                    $(p(anyPositiveInt()),c(20)),
                    $(p(anyPositiveInt()),c(30)),
                    $(p(anyPositiveInt()),c(40)),
                    $(p(anyPositiveInt()),c(50))]
            ],[
                id: $(p(positiveInt()), c(3)),
                rounds: $(p(positiveInt()), c(3)),
                playersNames: [
                    $(p(anyNonEmptyString()), c("TomB")),
                    $(p(anyNonEmptyString()), c("rockyB"))
                ],
                stats: [
                    $(p(anyPositiveInt()),c(100)),
                    $(p(anyPositiveInt()),c(200)),
                    $(p(anyPositiveInt()),c(300)),
                    $(p(anyPositiveInt()),c(400)),
                    $(p(anyPositiveInt()),c(500))]
            ]])
        bodyMatchers {
            jsonPath('$[*].stats', byType {
                minOccurrence(0)
            })
            jsonPath('$[*].playersNames', byType {
                minOccurrence(0)
            })
        }
        status 200
    }
}
